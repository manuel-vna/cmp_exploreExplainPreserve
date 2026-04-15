package com.example.cmpexploreexplainpreserve.documentScanner

import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Environment.DIRECTORY_DOWNLOADS
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions.RESULT_FORMAT_PDF
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions.SCANNER_MODE_FULL
import com.google.mlkit.vision.documentscanner.GmsDocumentScanning
import com.google.mlkit.vision.documentscanner.GmsDocumentScanningResult
import java.io.File

class AndroidDocumentScanner(private val activity: ComponentActivity) : DocumentScanner {

    val options =
        GmsDocumentScannerOptions.Builder()
            .setGalleryImportAllowed(false)
            .setPageLimit(5)
            .setResultFormats(RESULT_FORMAT_PDF)
            .setScannerMode(SCANNER_MODE_FULL)
            .build()

    val scanner = GmsDocumentScanning.getClient(options)

    val scannerLauncher =
        activity.registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val scanResult = GmsDocumentScanningResult.fromActivityResultIntent(result.data)
                scanResult?.pdf?.let { pdf ->
                    showPostScanDialog(pdf.uri)
                }
            }
        }

    private fun showPostScanDialog(pdfUri: Uri) {
        AlertDialog.Builder(activity)
            .setTitle("Scan Successful")
            .setMessage("What would you like to do with the PDF?")
            .setPositiveButton("Save to Downloads") { _, _ ->
                savePdfToDownloads(pdfUri)
            }
            .setNegativeButton("Share") { _, _ ->
                sharePdf(pdfUri)
            }
            .setNeutralButton("Cancel", null)
            .show()
    }

    private fun sharePdf(pdfUri: Uri) {
        val contentUri = try {
            FileProvider.getUriForFile(
                activity,
                "${activity.packageName}.fileprovider",
                File(pdfUri.path!!)
            )
        } catch (e: Exception) {
            pdfUri
        }

        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "application/pdf"
            putExtra(Intent.EXTRA_STREAM, contentUri)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        activity.startActivity(Intent.createChooser(shareIntent, "Share Scanned PDF"))
    }

    private fun savePdfToDownloads(pdfUri: Uri) {
        val contentResolver = activity.contentResolver
        val fileName = "Scan_${System.currentTimeMillis()}.pdf"

        // Prepare metadata for the file
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
            put(MediaStore.MediaColumns.MIME_TYPE, "application/pdf")
            put(MediaStore.MediaColumns.RELATIVE_PATH, DIRECTORY_DOWNLOADS)
        }

        // Insert the record into MediaStore
        val destinationUri = contentResolver.insert(
            MediaStore.Downloads.EXTERNAL_CONTENT_URI,
            contentValues
        )

        // Copy the file content from the ML Kit URI to the Downloads URI
        destinationUri?.let { dest ->
            contentResolver.openOutputStream(dest)?.use { outputStream ->
                contentResolver.openInputStream(pdfUri)?.use { inputStream ->
                    inputStream.copyTo(outputStream)
                }
            }
            println("File saved successfully to Downloads: $fileName")
        }
    }

    override fun scan() {
        scanner.getStartScanIntent(activity)
            .addOnSuccessListener { intentSender ->
                scannerLauncher.launch(IntentSenderRequest.Builder(intentSender).build())
            }
            .addOnFailureListener { e ->
                print("Failure: $e")
            }
    }
}
package com.example.cmpexploreexplainpreserve.documentScanner

import android.app.Activity.RESULT_OK
import androidx.activity.ComponentActivity
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions.RESULT_FORMAT_PDF
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions.SCANNER_MODE_FULL
import com.google.mlkit.vision.documentscanner.GmsDocumentScanning
import com.google.mlkit.vision.documentscanner.GmsDocumentScanningResult

class AndroidDocumentScanner(private val activity: ComponentActivity) : DocumentScanner {

    val options =
        GmsDocumentScannerOptions.Builder()
            .setGalleryImportAllowed(false)
            .setPageLimit(2)
            .setResultFormats(RESULT_FORMAT_PDF)
            .setScannerMode(SCANNER_MODE_FULL)
            .build()

    val scanner = GmsDocumentScanning.getClient(options)

    val scannerLauncher =
        activity.registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val result = GmsDocumentScanningResult.fromActivityResultIntent(result.data)
                result?.pages?.let { pages ->
                    for (page in pages) {
                        val imageURI = pages.get(0).imageUri
                    }
                }
                result?.pdf?.let { pdf ->
                    val pdfURI = pdf.uri
                    val pageCount = pdf.pageCount
                }
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
# About the project

This project is meant for exploring new technologies within the Compose Multiplatform environment,
explaining these to myself in simple examples and preserving the results for future use cases.

# Implementations

## Document Scanner

Uses MLKit to scan documents with the phone camera, being able to edit them
and finally save them in PDF format.

<pre> com/example/cmpexploreexplainpreserve/documentScanner </pre>

***

## Navigation 3

Backstack based handling of navigations
<pre> com/example/cmpexploreexplainpreserve/navigation3 </pre>

***

## Preferences DataStore

Jetpack DataStore (Preferences) stores data in a file internal to your app.
The exact location depends on the platform:
- preferences_pb = Protocol Buffer-encoded file.
- This location is not accessible to users or other apps (except root).

<pre> com/example/cmpexploreexplainpreserve/dataStorePref </pre>

***

## Room Database

Each platform has a custom DatabaseBuilder.
The database, data and DAO definitions are shared on commonMain

<pre> com/example/cmpexploreexplainpreserve/room </pre>

***

## Notifications

Planned
<pre> To Do </pre>

***
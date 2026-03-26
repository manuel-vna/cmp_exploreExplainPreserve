# Implementations

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

## Local Notifications

Since notifications are rather different on Android and iOs the implementation of local
notification consists of mainly platform specific code.

<pre> com/example/cmpexploreexplainpreserve/notifications </pre>

***
# Marvel CDB KMM
Project using marvel card database API developed in Kotlin multiplatform for Android and iOS.

Target of project: test the possibilities offered by KMM.

This project is focused in the communication between KMM code in shared module and kotlin and swift code in android and iOS modules,
how do the minimum code and minimum custom classes but applying the KISS principle.

API used: https://marvelcdb.com/

Libraries used:
- 🫂shared:
  - Call services : ktor for kotlin multiplatform
  - Dependency injection: koin for kotlin multiplatform

The contact point between 🫂shared module and :apple: iOS and :robot: android module is the UseCase file.
The UseCase class return a ResultData class, witch it is a custom Kotlin Result class to avoid the 'Any' result in Swift code,
this allow simplify the process of get the result data or the error returned by the data layer

- :robot: Android
  - UI: Jetpack Compose :warning: under construction

- :apple: iOS
  - UI: SwiftUI :warning: under construction
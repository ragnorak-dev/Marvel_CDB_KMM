# Marvel CDB KMM
Project using marvel card database API developed in Kotlin multiplatform for Android and iOS.

Target of project: test the possibilities offered by KMM.

This project is focused in the communication between KMM code in shared module and kotlin and swift code in android and iOS modules,
how do the minimum code and minimum custom classes but applying the KISS principle.

API used: https://marvelcdb.com/

# Logic
## Libraries used:
- 🫂shared:
  - Call services : ktor for kotlin multiplatform
  - Persistence: Room for kotlin multiplatform
  - Dependency injection: koin for kotlin multiplatform

The contact point between 🫂shared module and :apple: iOS and :robot: android module is the UseCase file.
The UseCase class return a ResultData class, witch it is a custom Kotlin Result class to avoid the 'Any' result in Swift code,
this allow simplify the process of get the result data or the error returned by the data layer

## Class diagram of shared module:
![shared](https://github.com/ragnorak-dev/Marvel_CDB_KMM/assets/7957314/36ccd0f9-a168-4c48-9922-3243c8f95bce)

# UI

- :robot: Android
  - UI: Jetpack Compose
  - Navigation: Jetpack Navigation


## Class diagram of android module:
![android](https://github.com/ragnorak-dev/Marvel_CDB_KMM/assets/7957314/a404ede5-af27-44be-8366-f0d04c194ce3)

- :apple: iOS
  - UI: SwiftUI :warning: under construction


## Architecture:
### Clean Architecture:
![image](https://github.com/ragnorak-dev/Marvel_CDB_KMM/assets/7957314/c91dfd36-0fd6-40be-80b5-2e5c5ad969a8)

- Data an domain layers are in shared->CommonMain module
- View layer is in androidApp/iosApp module

## Testing:
- Unit test:
  - For data and domain layers are in "test" package
  - For UI is in androidApp module in "test" package
- UI and Instrumentation tests:
  - Every tests are in androidApp module in androidTest package
    - Compose tests
    - Persistence data (Room) tests
   
## Final result:

https://github.com/ragnorak-dev/Marvel_CDB_KMM/assets/7957314/06dc0d2c-104e-416c-8cf2-7fa5f92fbd2c



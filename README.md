<!-- ABOUT THE PROJECT -->
## CaseStudy_MVVM
<p float="left">
  <img src="https://user-images.githubusercontent.com/18207490/162331072-c5021d3c-082d-4457-8e8f-bd5fae534865.jpg" height="200">
  <img src="https://user-images.githubusercontent.com/18207490/162331075-6f22d5a9-cebe-4369-94f8-2cd35a3b6321.jpg" height="200">
  <img src="https://user-images.githubusercontent.com/18207490/162331078-84051977-fd3a-46eb-afd9-fdfe14e46ffd.jpg" height="200">
  <img src="https://user-images.githubusercontent.com/18207490/162331079-37b72409-11ad-4679-8f89-dbf448607b03.jpg" height="200">
  <img src="https://user-images.githubusercontent.com/18207490/162331082-bbc9ab8d-c0f3-4a0b-a9d9-e75c61bf9739.jpg" height="200">
  <img src="https://user-images.githubusercontent.com/18207490/162331083-5281dba4-ccc7-4df1-a2a0-6bdb67a33efe.jpg" height="200">
</p>

This application is written using the custom api. The application, MVVM, Coroutines, Hilt, Navigation component features were used.

 * Architecture is written in MVVM. 
 * Asynchronous transactions were made with Coroutines. 
 * StateFlow was used to control the values returned in the Retrofit and to perform operations according to the returned values.
 * Picasso is used to display the pictures.
 * Hilt is used for Dependency Injection.

### Built With

Libraries used in the application.

* [Coroutines](https://developer.android.com/kotlin/coroutines)
* [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
* [Navigation component](https://developer.android.com/guide/navigation/navigation-getting-started)
* [View Binding](https://developer.android.com/topic/libraries/view-binding)
* [Retrofit](https://square.github.io/retrofit/)
* [StateFlow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow)
* [Picasso](https://square.github.io/picasso/)

<!-- GETTING STARTED -->
## Getting Started

### Installation

1. Get a free API
2. Enter your API in `build.gradle`
   ```js
   buildConfigField 'String', 'API_BASE_URL', '"https://my-json-server.typicode.com/engincancan/case"'
   ```

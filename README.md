# Android App with MVVM 

Highlights

1.  MVVM Architecture
2.  Unit test using JUnit and Mockito
4.  UI unit test using Espresso
5.  Gradle scripts for running code coverage

The application has been designed using  Android Architecture components. 

   # Programming Practices Followed

1) Android Architectural Components  
2) Dagger 2 for Dependency Injection  
3) MVVM  
4) Retrofit with Okhttp  

5) JUnit and Mockito for Unit testing  



# Building the App 

**Debug build**

Open terminal and type the below command 

`gradlew assembleDebug`

**Release build**

Open terminal and type the below command 

`gradlew assembleRelease`


# Generate code coverage report 

Open terminal and type the following command

`gradlew clean jacocoTestReport`

The coverage report will be generated on the following path.

`app/build/reports`

# Screenshot
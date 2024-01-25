
# Diagonal Programming Test: v4 (2020.06.14) - Android Project

## Overview

This Android project demonstrates the completion of the Diagonal Programming Test v4. The task involved creating a mobile version of a content listing page using native Android development tools. The project showcases proficiency in Kotlin, MVVM architecture, and Dependency Injection (Koin/Dagger).

## Project Details

### Page Layout:
Implemented a content listing page with a page title and a vertically scrolling grid.
Responsive grid layout with 3 columns for portrait and 7 columns for landscape orientations.
### Search Functionality:
Implemented search functionality triggered by the Search icon.
Enabled searching for items with results shown for queries containing 3 or more characters.
Provided user hints and messages during the search mode.
### Layout Adaptation:
Adapted the layout based on Android dp using the Redline folder details.
Responsive design considering a screen resolution of 1080x1920.
### Image Assets:
Utilized images from the Slices folder, including poster images specified in the provided JSON.
### Data Retrieval:
Retrieved data from the provided API folder (PAGEAPI-PAGE(#NUM).json).
Implemented lazy loading for seamless data retrieval as the user scrolls.
### Edge Cases:
Creatively handled edge cases for two content items on Page 3 without compromising UI consistency.
Package Contents:
Included the source code and the signed APK.
### Distribution:
Bonus marks achieved for distributing the APK via Crashlytics Beta.
## Instructions for Reviewers

### Source Code:
Navigate to the app/ folder to review the main Android application code.
Unit tests are located in the src/test/ folder.
### Building the Project:
Clone the repository: git clone https://github.com/your-username/diagonal-programming-test.git
Open the project in Android Studio.
Build and run the application on an Android emulator or device.
## Release Notes:
Version 1.0.0
Search functionality added.
Lazy loading implemented for seamless data retrieval.
Edge cases on Page 3 creatively resolved.
Responsive design for various orientations.
Bonus: APK distributed via Crashlytics Beta.
## Author

Saurabh Sharma

Thank you for the opportunity to complete this test. If you have any questions or require further clarification, feel free to reach out.

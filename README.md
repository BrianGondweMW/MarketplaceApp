Marketplace App

BICT2406 Mini Project – Group 3

A mobile marketplace application developed using Android Studio, Kotlin, Jetpack Compose, Firebase, and PayChangu. The application allows users to browse products, view product details, rate and like products, manage a shopping cart, and proceed through checkout and payment.

The project also includes a web-based administration portal for managing products stored in Firebase.

Group Members

Name	Student ID
Solomon N Mfune	BICTU1725
Christopher Nkankha	BICTU2125
Brian Gondwe	BICTU0725
Kenneth Zirenga BICTU2625

Implemented Features

* User registration and login using Firebase Authentication
* Product browsing
* Product categories
* Product details
* Product ratings
* Product like functionality
* Shopping cart
* Checkout
* PayChangu payment integration
* Payment success handling
* Firebase Firestore product storage
* Firebase-connected administration portal
* Admin product management
    * Add products
    * Edit products
    * Delete products
* Responsive Jetpack Compose user interface
* Marketplace splash screen
* Firebase Hosting for the administration portal

Technologies Used

* Kotlin
* Android Studio
* Jetpack Compose
* Material 3
* Firebase Authentication
* Firebase Firestore
* Firebase Functions
* Firebase Hosting
* PayChangu
* React
* Vite
* GitHub

Project Structure

MarketplaceApp/
├── app/          # Android mobile application
├── admin/        # Web administration portal
├── functions/    # Firebase Cloud Functions
├── images/       # Project images
├── gradle/       # Gradle configuration
└── README.md

Administration Portal

The project includes a web administration portal that connects to Firebase. Changes made by an administrator to products are stored in Firebase and can be reflected in the mobile application.

Firebase

Firebase is used for authentication, Firestore database services, cloud functions, and hosting of the administration portal.

Payment

The application integrates PayChangu to provide a checkout/payment process.

Development

The project was developed progressively, beginning with the application interface and Firebase integration, followed by product browsing, authentication, cart and checkout functionality, payment integration, and finally the administration portal.

Repository

The complete project is available on GitHub:

https://github.com/kennethzirenga-cyber/MarketplaceApp

Academic Submission

Course: BICT2406 Mini Project
Group: 3

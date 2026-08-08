Marketplace App – Development Documentation

BICT2406 Mini Project – Group 3

1. Introduction

The Marketplace App is a mobile e-commerce application developed to provide users with a convenient platform for browsing products, viewing product information, adding products to a shopping cart, checking out, and making payments.

The project also includes a web-based administration portal that allows administrators to manage marketplace products through Firebase.

2. Development Tools and Technologies

The following technologies and tools were used:

* Android Studio
* Kotlin
* Jetpack Compose
* Material 3
* Firebase Authentication
* Firebase Firestore
* Firebase Cloud Functions
* Firebase Hosting
* PayChangu
* React
* Vite
* GitHub

3. Project Planning and Setup

The project was started by creating an Android Studio project using Kotlin and Jetpack Compose.

The initial project structure was created and the application package was configured as:

com.example.marketplaceapp

The project was then connected to Firebase to provide backend services.

Screenshot:
Insert screenshot showing the initial Android Studio project.

4. Firebase Configuration

Firebase was configured to provide authentication, database, cloud functions, and other backend services.

Firebase Authentication was used to manage user accounts, while Cloud Firestore was used to store user and product information.

Screenshot:
Insert screenshot showing Firebase project configuration.

5. Application Splash Screen

A customized splash screen was developed for the Marketplace App.

The splash screen uses the application’s blue colour scheme and marketplace logo, together with the application name.

The purpose of the splash screen is to provide a branded introduction before the main application interface is displayed.

Screenshot:
Insert screenshot of the Marketplace App splash screen.

6. User Registration

A registration screen was implemented using Jetpack Compose.

Users can provide:

* Full name
* Email address
* Password
* Confirm password

Firebase Authentication is used to create the user’s account. Additional user information is stored in Firestore.

Screenshot:
Insert screenshot of the registration screen.

7. User Login

A login screen was implemented to allow registered users to access the application.

Users authenticate using their email address and password through Firebase Authentication.

Screenshot:
Insert screenshot of the login screen.

8. Home Screen and Product Catalogue

The home screen was developed to display available marketplace products.

Products are presented using a user-friendly interface that allows users to browse available items and select products for more information.

Screenshot:
Insert screenshot of the home/product catalogue screen.

9. Product Details

A product details screen was implemented to provide additional information about a selected product.

The screen allows users to view product information before deciding whether to add the product to their shopping cart.

Screenshot:
Insert screenshot of the product details screen.

10. Shopping Cart

A shopping cart was implemented to allow users to manage products they intend to purchase.

The cart provides functionality for:

* Adding products
* Viewing selected products
* Managing quantities
* Calculating the total amount

Cart data is also managed locally so that the application can maintain the user’s cart information.

Screenshot:
Insert screenshot of the shopping cart.

11. Checkout

A checkout screen was implemented to allow users to review their order before making payment.

The checkout process displays the relevant order information and total amount before the user proceeds to the payment method.

Screenshot:
Insert screenshot of the checkout screen.

12. Payment Method

A dedicated payment method screen was created to provide the user with the available payment option.

The current implementation provides PayChangu as the payment method.

The interface was designed using the application’s blue and orange colour scheme.

Screenshot:
Insert screenshot of the payment method screen.

13. PayChangu Integration

PayChangu was integrated into the application to provide online payment functionality.

The application sends the required payment information to a Firebase Cloud Function. The Cloud Function communicates with the payment service and returns a checkout URL.

The application then opens the PayChangu checkout page for the user to complete the payment.

Screenshot:
Insert screenshot of the PayChangu payment screen.

14. Payment Success

A payment-success flow was implemented to handle the return from the payment process.

The application uses a custom application URL scheme to recognize a successful payment return and display the appropriate payment-success screen.

Screenshot:
Insert screenshot of the payment success screen.

15. Firebase Cloud Functions

Firebase Cloud Functions were implemented to handle backend operations required by the application.

The functions provide a secure location for server-side operations such as creating the PayChangu payment request.

This prevents sensitive payment credentials from being placed directly inside the Android application.

Screenshot:
Insert screenshot showing the Firebase Functions configuration/code.

16. Administration Web Application

A separate web-based administration application was developed for managing marketplace products.

The administration application was created using:

* React
* Vite
* Firebase

The admin application allows authorized administrators to manage marketplace data.

Screenshot:
Insert screenshot of the admin dashboard.

17. Firebase Hosting

The administration web application was built using Vite and deployed using Firebase Hosting.

The deployed administration application is available at:

https://marketplace-app-d6b93.web.app

Screenshot:
Insert screenshot showing the deployed admin application.

18. Testing

The application was tested during development to verify the main functionality.

The following areas were tested:

* User registration
* User login
* Product browsing
* Product details
* Shopping cart
* Checkout
* Payment method selection
* PayChangu payment flow
* Payment return
* Firebase data operations
* Administration portal

Problems identified during development were corrected and the application interface was adjusted for different screen sizes.

Screenshot:
Insert screenshot showing the application running on an emulator/device.

19. GitHub Repository

The complete Android Studio project, Firebase configuration, administration application, and supporting project files were uploaded to GitHub.

Repository:

https://github.com/kennethzirenga-cyber/MarketplaceApp

20. Group Members

Name	Student ID
Solomon N Mfune	BICTU1725
Christopher Nkankha	BICTU
Brian Gondwe	BICTU0725

21. Conclusion

The Marketplace App demonstrates the development of a complete mobile marketplace solution using modern Android development technologies.

The project combines a Kotlin and Jetpack Compose mobile application with Firebase backend services, PayChangu payment integration, and a web-based administration portal.

The development process provided practical experience in mobile application development, cloud services, database management, authentication, payment integration, web administration, testing, and version control using GitHub.

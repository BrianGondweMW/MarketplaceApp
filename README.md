MarketplaceApp

A full-stack digital marketplace platform developed as an academic software development project. The system combines an Android mobile application with a Firebase backend and a web-based administration portal.

📱 Project Overview

MarketplaceApp allows users to create accounts, browse products, view product details, interact with products, add items to a shopping cart, and proceed through checkout.

The system also includes an administration portal for managing marketplace products and platform data.

✨ Key Features

Mobile Application

* User registration and authentication
* Product browsing and categories
* Product details
* Product ratings
* Like/favourite products
* Shopping cart
* Cart quantity management
* Automatic cart total calculation
* Checkout functionality
* Product images
* Firebase data integration

Administration Portal

* Admin authentication
* Product management
* Add products
* Edit products
* Delete products
* Product information management

Backend & Services

* Firebase Authentication
* Cloud Firestore
* Firebase Cloud Functions
* Payment integration
* API-based services

🛠️ Technology Stack

Mobile Development

* Kotlin
* Jetpack Compose
* Android

Backend

* Firebase Authentication
* Cloud Firestore
* Firebase Cloud Functions

Web Administration

* React
* Vite

Other Technologies

* REST/API integration
* PayChangu payment integration
* Coil for image loading

👨🏾‍💻 My Contributions

As a contributor to this academic project, I gained hands-on experience in the design, development, integration, and debugging of the marketplace platform. I worked across the Android application, Firebase backend, APIs, and supporting functionality while collaborating on the overall system.

My practical work included implementing and troubleshooting:

* Android UI using Jetpack Compose
* Firebase Authentication
* Firestore data handling
* Product browsing and product details
* Likes and ratings
* Shopping cart functionality
* Cart quantities and total calculations
* Application navigation
* Product image loading
* API and payment integration
* Firebase Cloud Functions
* Debugging application and backend issues

The project provided practical experience in developing and integrating different components of a complete digital product.

🏗️ System Architecture

The project consists of three main components:

                    🏗️ System Architecture

The MarketplaceApp consists of three main components connected through Firebase services and external APIs:

┌─────────────────────────────────────────────────────┐
│                  MARKETPLACE APP                     │
└─────────────────────────────────────────────────────┘
                         │
          ┌──────────────┴──────────────┐
          │                             │
          ▼                             ▼
┌───────────────────┐          ┌──────────────────────┐
│ Android Mobile    │          │   Admin Portal       │
│ Application       │          │                      │
│                   │          │ React + Vite         │
│ Kotlin            │          │ JavaScript           │
│ Jetpack Compose   │          │ CSS                  │
└─────────┬─────────┘          └──────────┬───────────┘
          │                               │
          └───────────────┬───────────────┘
                          ▼
              ┌─────────────────────────┐
              │    Firebase Services    │
              │                         │
              │ • Authentication        │
              │ • Cloud Firestore       │
              │ • Cloud Functions       │
              └────────────┬────────────┘
                           │
                           ▼
              ┌─────────────────────────┐
              │    External Services    │
              │                         │
              │ • PayChangu API         │
              │ • Payment Processing     │
              └─────────────────────────┘

Application Flow

Users → Android App → Firebase → External Services

Administrators → Admin Portal → Firebase → Database & Cloud Functions

This architecture separates the mobile application, administration interface, backend services, database, and external payment services into distinct components.

🎯 Project Objective

The objective of the project was to design and develop a functional marketplace platform demonstrating practical skills in mobile application development, database management, authentication, API integration, user interface design, and full-stack system development.

📚 Academic Project

This project was developed as part of an academic software development project and involved collaborative development.

🚀 Future Improvements

Potential improvements include:

* Improved offline support
* Enhanced search and filtering
* Push notifications
* More advanced analytics
* Improved payment workflows
* Expanded user profiles
* Automated testing
* Further security and performance improvements

📌 Portfolio

This project demonstrates practical experience in building and integrating a modern mobile and web-based digital platform using Kotlin, Jetpack Compose, Firebase, React, APIs, and cloud services.

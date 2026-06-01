# 🚗 RideShare — Android Carpooling App

A real-time ride-sharing Android application built with Kotlin and MVVM architecture. Connects drivers and passengers for daily commutes with live GPS tracking, in-app chat, and secure payments.

## 🚀 Tech Stack

- **Android**: Kotlin, MVVM, Jetpack Compose, Hilt (DI)
- **Maps**: Google Maps SDK, Directions API, Places API
- **Backend**: Node.js, Express.js, Socket.io (real-time)
- **Database**: MongoDB + Firebase Realtime DB (live location)
- **Auth**: Firebase Authentication (Phone OTP + Google)
- **Payments**: Razorpay SDK
- **Push Notifications**: Firebase Cloud Messaging (FCM)

## ✨ Features

- 📍 Real-time GPS tracking with live map updates
- 🔍 Smart route matching — driver and passenger path overlap
- 💬 In-app chat between driver and passenger
- 💰 Fare estimation based on distance + demand
- ⭐ Rating system for both drivers and passengers
- 🔔 FCM push notifications for ride updates
- 💳 Razorpay payment integration
- 🛡️ SOS emergency button with location sharing
- 📊 Ride history and spending analytics

## 🏗️ Architecture

MVVM + Repository Pattern
```
ui/           # Jetpack Compose screens
viewmodel/    # Business logic + LiveData
repository/   # Data source abstraction
model/        # Data classes
network/      # Retrofit API service
```

## ⚙️ Setup

### Android
1. Clone the repo
2. Add `google-services.json` to `/android/app/`
3. Add your Maps API key to `local.properties`: `MAPS_API_KEY=your_key`
4. Open in Android Studio and run

### Backend
```bash
cd server
npm install
cp .env.example .env
npm run dev
```

## 📄 License
MIT - Sarth Hemant Joshi

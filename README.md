# Jatre Namma Pride

An Android application built to celebrate Karnataka Culture, featuring a dynamic schedule, an interactive festival map, a lost & found portal, safety resources, and a dedicated admin panel. 

## Features
- **Home**: Overview of festival highlights and cultural elements.
- **Schedule**: Live event schedules and timings.
- **Map**: Interactive festival map using Google Maps.
- **Lost & Found**: Community portal to report and track lost/found items.
- **Safety**: Emergency SOS, location sharing, and first aid info.
- **AI**: Generated cultural stories regarding the festival.
- **Admin**: Secured admin panel for managing events and lost items.

## Technologies Used
- **Kotlin 2.0**
- **Jetpack Compose** (UI)
- **Firebase** (Firestore Database, Authentication)
- **Google Maps SDK**

---

## 🛠️ Setup Instructions for Evaluators

This repository does not include the sensitive `google-services.json` or `local.properties` (with the Maps API Key). To build and run this application locally, you must provide your own API keys.

### 1. Google Maps API Key
1. Obtain an API key from the [Google Cloud Console](https://console.cloud.google.com/) for Maps SDK for Android.
2. In the root directory of the project, create or edit `local.properties`.
3. Add your key as follows:
   ```properties
   MAPS_API_KEY=YOUR_API_KEY_HERE
   ```

### 2. Firebase Configuration
1. Create a project in the [Firebase Console](https://console.firebase.google.com/).
2. Enable **Firestore Database** and **Authentication** (Email/Password).
3. Add an Android App to your Firebase project with the package name: `com.example.jatrenammapride`
4. Download the `google-services.json` file.
5. Place this file inside the `app/` directory of the project.
   *(Alternatively, use the provided `app/google-services.json.example` template by renaming it and inserting your Firebase project details).*

### 3. Build & Run
- Open the project in Android Studio.
- Sync Project with Gradle Files.
- Run the `app` module on an emulator or physical device.

---

## Firebase Security Rules Note
Ensure your Firestore rules are set to allow authenticated users to write, but anyone to read. For example:
```javascript
rules_version = '2';
service cloud.firestore {
  match /databases/{database}/documents {
    match /{document=**} {
      allow read: if true;
      allow write: if request.auth != null; 
    }
  }
}
```

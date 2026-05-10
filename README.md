# SmartQuiz

Android Java quiz app scaffold built with MVVM, Room, Navigation Component, Material 3, Firebase Auth hooks, and MPAndroidChart.

## Project Structure

```text
app/
  src/main/java/com/smartquiz
    auth/
    home/
    quiz/
    result/
    profile/
    database/
    core/util/
```

## Setup

1. Open `C:\Users\Mandar's Katana\Desktop\quiz app` in Android Studio Hedgehog or newer.
2. Let Gradle sync and install Android SDK 34 if prompted.
3. Create a Firebase project and add an Android app with package name `com.smartquiz`.
4. Download `google-services.json` and place it in `app/google-services.json`.
5. If you want Firebase resource generation enabled immediately, apply `id 'com.google.gms.google-services'` in `app/build.gradle` after adding the JSON file.
6. Run the app on an emulator or device with Google Play services if you plan to extend Google Sign-In.

## Included Features

- Splash, login, signup, and forgot-password screens
- Home dashboard with category progress cards
- Room-backed seeded question bank with 4 categories x 50 questions
- Timed quiz flow with progress state
- Result screen with pie chart analytics
- Profile screen with average score and logout

## Notes

- The Room database is prepopulated locally on first launch.
- Firebase email/password screens are wired, but runtime auth requires a valid Firebase project config.
- Google Sign-In is left as a guided hook because it also needs Firebase console and SHA setup.

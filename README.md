# EKart

EKart is a multi-module Android e-commerce app built with Clean Architecture, Jetpack Compose, and modern Android libraries. It is designed to be offline-first and uses a type-safe navigation system.

![Design Diagram](./design-diagram.png)

## Downloads
Pre-built APKs are available in the root folder:
*   [EKart-release.apk](./EKart-release.apk?raw=true) (Signed Release)
*   [EKart-debug.apk](./EKart-debug.apk?raw=true) (Debug with Analytics Logs)

## Quick Start
To run the app on your emulator:

1. **Clone the repository.**
2. **Add Configuration:** Open `local.properties` in the root folder and add the following line:
   ```properties
   BASE_URL=https://dummyjson.com/
   ```
3. **Open in Android Studio:** Use the latest version (Ladybug+ recommended).
4. **Set JDK:** Ensure you are using **JDK 17** (Settings > Build Tools > Gradle).
5. **Run:** Select the `app` configuration and click **Run** (Play button) to launch it on your emulator.

## Features
- **Product Discovery:** Infinite scrolling with category filters (Paging 3).
- **Flash Deals:** Real-time countdown timers.
- **Cart:** Persistent shopping cart using Room.
- **Analytics:** Locally buffered events synced in background via WorkManager.
- **Design System:** Shared UI components in a core module.

## Tech Stack
- **UI:** 100% Jetpack Compose
- **Concurrency:** Coroutines & Flow
- **DI:** Dagger Hilt
- **Database:** Room (Single Source of Truth)
- **Networking:** Retrofit & OkHttp
- **Images:** Coil

## Project Structure
- `:app`: Glue module handling Hilt and global navigation.
- `:core`: Shared network logic, design system, and utilities.
- `:feed`: Main product browsing experience.
- `:flashdeal`: Timer logic and deals UI.
- `:cart`: Database and cart logic.
- `:analytics`: Background event tracking.

Modules communicate via interfaces defined in `:core` to maintain isolation.

## Build Commands
If you prefer the command line:
```bash
# Generate Release APK
./gradlew :app:assembleRelease

# Generate Debug APK
./gradlew :app:assembleDebug
```

## Navigation
The app uses Navigation 2.8.0+ with Kotlin Serialization. Routes are defined as `@Serializable` objects. Features register themselves using Hilt multibindings, allowing the `:app` module to remain decoupled from screen implementation details.

## Performance
- **Baseline Profiles:** Included in the `:baselineprofile` module for faster startup and smoother scrolling.
- **Lifecycle-aware:** Network requests use a 5-second timeout on flows to survive configuration changes.

## Testing
- **Unit Tests:** ViewModels, Repositories, and business logic.
- **Instrumented Tests:** Room DAOs and Hilt dependency graph validation.

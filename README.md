# EKart

EKart is an Android e-commerce application built with Clean Architecture and modern development practices. It uses a multi-module setup to showcase reactive programming, offline-first data handling, and modular UI design.

![Design Diagram](./design-diagram.png)

## Download & Build APKs

The latest build APKs are available in the root directory:

*   **Release APK:** [EKart-release.apk](./EKart-release.apk) (Signed and optimized)
*   **Debug APK:** [EKart-debug.apk](./EKart-debug.apk) (For development)

### To generate these files from source:
Run the following command in the terminal:
```bash
# For Release APK
./gradlew :app:assembleRelease

# For Debug APK
./gradlew :app:assembleDebug
```

## Features

- Product Discovery: Category filtering and infinite scrolling using Paging 3.
- Flash Deals: Real-time countdown timers for limited-time offers.
- Cart Management: Room-based persistent shopping cart.
- Background Analytics: Local buffering of events synced via WorkManager.
- Core Design System: Shared UI components in a dedicated module.

## Tech Stack

- UI: Jetpack Compose
- Logic: Kotlin Coroutines & Flow
- Dependency Injection: Dagger Hilt
- Database: Room
- Networking: Retrofit & OkHttp
- Image Loading: Coil
- Background Tasks: WorkManager

## Project Structure

The project is divided into several Gradle modules to keep boundaries clean and improve build times:

- app: App entry point, Hilt setup, and global navigation.
- core: Shared design system, networking, and common utilities.
- feed: Product browsing and paging.
- flashdeal: Timed deals and countdown logic.
- cart: Shopping cart persistence and checkout.
- analytics: Interaction tracking and background sync.

Features are isolated and communicate through interfaces defined in the core module. This allows features to be modified or replaced without affecting the rest of the app.

## Build Variants & Environment Configuration

The application implements build-variant-specific logic to separate development tools from production features:

### 1. Build Variants
- **debug**: Includes the **Analytics Screen** (Logs Viewer) for development. The app ID has a `.debug` suffix.
- **release**: A clean production version. The Analytics UI code is completely stripped out using source sets and Proguard, ensuring zero overhead.

### 2. Environment Setup (local.properties)
To keep sensitive URLs or environment-specific data out of the source code, the project uses `local.properties`. 
Before building the app, ensure you have the following entry in your `local.properties` file:
```properties
BASE_URL=https://dummyjson.com/
```
The build system reads this value and injects it into `BuildConfig.BASE_URL` within the `:feed` module.

### 3. Modular & Type-Safe Navigation
The application uses a decentralized, type-safe navigation system based on **Jetpack Navigation 2.8.0+** and **Kotlin Serialization**:

- **Decentralized Registration**: Each feature module implements a `FeatureApi` and uses Hilt `@IntoSet` multibindings to register itself. The `:app` module is a clean composition root that knows nothing about specific feature routes.
- **Type-Safety**: Routes are defined as `@Serializable` objects (implementing a common `BaseRoute` marker) instead of raw strings. This eliminates runtime typos and ensures compile-time safety for arguments.
- **Dynamic Graph**: The `EKartNavGraph` is built at runtime by iterating over the registered features. It dynamically identifies the entry point using the `isStartDestination` property.
- **Selected State Logic**: Bottom navigation items use the modern `hasRoute(T::class)` API to accurately determine the active destination without string matching.

### 4. Baseline Profiles
To ensure high performance and smooth scrolling even when the app is shared as a standalone APK (without Play Store's Cloud Profiles), the project includes a `:baselineprofile` module. 
Baseline Profiles pre-compile critical code paths on the device, reducing app startup time and eliminating "jank" during first-run animations and list scrolling.

To generate a new Baseline Profile:
```bash
./gradlew :app:generateBaselineProfile
```
The generated profiles are automatically bundled into the release APK.

## Data Strategy

The app follows a single source of truth pattern. The UI observes the local Room database, and the data layer handles syncing that database with the network. This ensures the app remains functional offline and provides a smooth user experience.

## Getting Started

1. Clone the repository.
2. Open it in Android Studio.
3. Sync Gradle and ensure the project is set to use JDK 17.
4. Run the :app configuration on an emulator or device (API 26+).

## Performance and Optimization

- We use lifecycle-aware state collection to prevent memory leaks and save battery.
- Flows use a 5-second subscription timeout to survive configuration changes like screen rotations without restarting data fetches.
- Transient objects, such as timer flows, are cleared from memory when they are no longer needed.

## Testing

The project uses a mix of local unit tests and instrumented tests to verify business logic and infrastructure.

| Module | Component | Category | Coverage |
| :--- | :--- | :--- | :--- |
| **flashdeal** | FlashDealViewModel | Unit | UI state mapping, countdown timer flows, and user intent handling |
| **cart** | CartRepository | Unit | Domain mapping and business rules (e.g., auto-delete on zero quantity) |
| **cart** | CartDao | Instrumented | Room database CRUD operations and reactive Flow emissions |
| **analytics** | AnalyticsRepository | Unit | Local event buffering and interaction logging persistence |
| **multi** | Hilt Modules | Instrumented | Dependency injection graph resolution and repository provision |

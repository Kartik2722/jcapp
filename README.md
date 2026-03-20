# 🛍️ ShopApp — Jetpack Compose + MVVM

A simple e-commerce Android app built with **Jetpack Compose** and **MVVM architecture**, powered by [FakeStoreAPI](https://fakestoreapi.com/).

## 📱 Screens
- **Product List** — 2-column grid of all products
- **Product Detail** — Full product info with Add to Cart button

## 🏗️ Architecture: MVVM
```
data/
  model/       → Data classes (Product, Rating)
  remote/      → Retrofit API + Client
  repository/  → ProductRepository
ui/
  screen/      → Composable screens
  theme/       → App theme & colors
viewmodel/     → ProductViewModel + UiState
navigation/    → NavController setup
```

## 🚀 How to Run

### Option 1 — Android Studio (Recommended)
1. Clone this repo
2. Open in Android Studio Hedgehog or newer
3. Wait for Gradle sync
4. Run on emulator or real device (API 24+)

### Option 2 — GitHub Actions (Get APK)
1. Push code to GitHub
2. Go to **Actions** tab
3. Run **Build APK** workflow
4. Download APK from **Artifacts**

## 📦 Dependencies
- Jetpack Compose + Material3
- Navigation Compose
- Retrofit2 + Gson
- Coil (Image loading)
- ViewModel + StateFlow

# 🌜 Anime Explorer App

An Android app built with Kotlin that allows users to browse, view, and save their favorite anime titles. The app uses Retrofit to fetch data from an external API and Room for local data storage. It features clean architecture with a modular design and modern Android development practices.

---

## 📱 Features

- 🔍 View a curated list of anime titles
- 📄 See detailed information about each anime
- ❤️ Add/remove anime from favorites
- 💾 Offline access to favorite anime using Room DB
- 🧹 Clean and modular architecture (MVVM-style)

---

## 🧰 Tech Stack

| Component      | Technology                          |
|----------------|--------------------------------------|
| Language       | Kotlin                               |
| UI             | XML Layouts                          |
| Architecture   | MVVM (ViewModel + Repository Pattern)|
| Networking     | Retrofit                             |
| Persistence    | Room Database with DAO & TypeConverter |
| Image Loading  | Glide or Coil *(TBD)*                |
| IDE            | Android Studio                       |

---

## 📂 Project Structure

```
com.example.anime
├── MainActivity.kt
├── AnimeDetail.kt
├── FavAnime.kt
├── adapter/
│   ├── AnimeAdapter.kt
│   └── FavAnimeAdapter.kt
├── data/
│   ├── Anime.kt
│   ├── AnimeDao.kt
│   ├── AnimeRepository.kt
│   ├── AppDB.kt
│   └── ImageTypeConverter.kt
└── network/
    ├── IapiResponse.kt
    └── RetrofitInstance.kt
```

---

## 🚀 Getting Started

### 1. Clone the repository:
```bash
git clone https://github.com/<your-username>/anime-app-kotlin.git
```

### 2. Open in Android Studio

### 3. Add your anime API base URL and key
Inside `RetrofitInstance.kt`, configure your endpoint:
```kotlin
private const val BASE_URL = "https://your-anime-api.com/"
```

### 4. Build and Run

Connect your Android device or emulator, then:
```
Run ▶️
```

---

## ✍️ Author

- Harshbir Singh  
  [LinkedIn](https://linkedin.com/in/harshbir) • [GitHub](https://github.com/harshbir)

---

## 📜 License

This project is licensed under the MIT License.


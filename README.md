# 🎵 Music Streaming API

A RESTful backend for managing **Songs**, **Albums**, **Artists**, and **Playlists** — built with **Spring Boot**, **PostgreSQL**, and **Docker Compose**.

---

## 🧰 Tech Stack

* **Spring Boot 3**
* **Hibernate / JPA**
* **PostgreSQL**
* **Flyway** (for database migrations)
* **MapStruct** (DTO mapping)
* **Docker & Docker Compose**
* **Maven Build Tool**

---

## ⚙️ Setup Instructions

### 1️⃣ Install Prerequisites

* Java 25+
* Docker Desktop
* Maven

---

### 2️⃣ Create the Database

Ensure PostgreSQL is installed and running locally.
Then, open your terminal and run:

```sql
CREATE DATABASE music_db;
\c music_db;
```

---

### 3️⃣ Configure Database Credentials

Open `src/main/resources/application.properties` and update:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/music_db
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.properties.hibernate.hbm2ddl.auto=none
spring.jpa.properties.hibernate.show_sql=true
spring.jpa.properties.hibernate.format_sql=true

spring.flyway.enabled=true
spring.flyway.locations=classpath:db/migration
spring.flyway.baseline-on-migrate=true
```

---

## 🐳 Run with Docker

```bash
# 1. Build the JAR file
mvn clean package -DskipTests

# 2. Build and start services
docker compose up --build
```

Your application will be available at:
👉 **[http://localhost:8080/api/v1/songs](http://localhost:8080/api/v1/songs)**

---

## 🧪 API Endpoints

### 🎶 Songs

| Method | Endpoint             | Description               |
| ------ | -------------------- | ------------------------- |
| GET    | `/api/v1/songs`      | Get all songs (paginated) |
| GET    | `/api/v1/songs/{id}` | Get a song by ID          |
| POST   | `/api/v1/songs`      | Create a new song         |
| PATCH  | `/api/v1/songs/{id}` | Update song details       |
| DELETE | `/api/v1/songs/{id}` | Delete a song             |

---

### 💿 Albums

| Method | Endpoint                         | Description           |
| ------ | -------------------------------- | --------------------- |
| GET    | `/api/v1/albums`                 | Get all albums        |
| GET    | `/api/v1/albums/{id}`            | Get album by ID       |
| POST   | `/api/v1/albums`                 | Create a new album    |
| PATCH  | `/api/v1/albums/{id}`            | Update album          |
| DELETE | `/api/v1/albums/{id}`            | Delete album          |
| GET    | `/api/v1/albums/{albumId}/songs` | Get songs in an album |

---

### 🎤 Artists

| Method | Endpoint                           | Description         |
| ------ | ---------------------------------- | ------------------- |
| GET    | `/api/v1/artists`                  | Get all artists     |
| GET    | `/api/v1/artists/{id}`             | Get artist by ID    |
| POST   | `/api/v1/artists`                  | Create a new artist |
| PATCH  | `/api/v1/artists/{id}`             | Update artist       |
| DELETE | `/api/v1/artists/{id}`             | Delete artist       |
| GET    | `/api/v1/artists/{artistId}/songs` | Get songs by artist |

---

### 🎧 Playlists

| Method | Endpoint                                        | Description               |
| ------ | ----------------------------------------------- | ------------------------- |
| GET    | `/api/v1/playlists`                             | Get all playlists         |
| GET    | `/api/v1/playlists/{id}`                        | Get playlist by ID        |
| POST   | `/api/v1/playlists`                             | Create a new playlist     |
| PATCH  | `/api/v1/playlists/{id}`                        | Update playlist           |
| DELETE | `/api/v1/playlists/{id}`                        | Delete playlist           |
| POST   | `/api/v1/playlists/{playlistId}/songs/{songId}` | Add song to playlist      |
| DELETE | `/api/v1/playlists/{playlistId}/songs/{songId}` | Remove song from playlist |

---

## ⚠️ Error Handling

Handled globally with custom exceptions:

| Exception                              | Message Example                                   |
| -------------------------------------- | ------------------------------------------------- |
| `SongNotFoundException`                | “Song not found” / “Song not found with id: {id}” |
| `AlbumNotFoundException`               | “Album not found”                                 |
| `ArtistNotFoundException`              | “Artist not found”                                |
| `PlaylistNotFoundException`            | “Playlist not found”                              |
| `SongAlreadyExistsInPlaylistException` | “Song already exists in playlist”                 |
| `SongNotInPlaylistException`           | “Song not exists in playlist”                     |
| `SomeSongsNotFoundException`           | “Some songs not found”                            |

All handled via a **GlobalExceptionHandler** that returns consistent JSON responses.

---

## 📦 Flyway Migrations

| Version                         | Description           |
| ------------------------------- | --------------------- |
| `V1__init.sql`                  | Create tables         |
| `V2__insert_reference_data.sql` | Insert sample records |

---

## 🧰 Common Commands

| Command                         | Description                   |
| ------------------------------- | ----------------------------- |
| `mvn clean install`             | Build and test the project    |
| `mvn clean package -DskipTests` | Create `.jar` file for Docker |
| `docker compose up --build`     | Build and start all services  |
| `docker compose stop`           | Stop all containers           |

---

## 🛠️ API Examples

### Get Songs in Album

```bash
GET /api/v1/albums/{albumId}/songs
```

### Delete Song from Playlist

```bash
DELETE /api/v1/playlists/{playlistId}/songs/{songId}
```

---

## ✅ Summary

You now have a fully containerized **Music Streaming API** ready to:

* Manage songs, albums, artists, and playlists
* Handle migrations with Flyway
* Run both locally and in Docker
* Provide clean, structured REST endpoints with pagination and DTO mapping

## ✅ Notes
- Make sure Docker Desktop is running before using Docker Compose.
- Update database credentials properly before first migration.
- Use `mvn clean package` before container build to ensure a fresh JAR.
- Check logs with `docker compose logs -f` for troubleshooting.


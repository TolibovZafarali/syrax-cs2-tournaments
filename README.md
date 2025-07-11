# 🎮 Syrax CS2 Tournaments

A full-stack tournament management platform designed for weekly CS2 competitions hosted by the Syrax community. Built to be intuitive, admin-controlled, and optimized for team-based matches (1v1, 2v2, 5v5).

---

## 📌 Purpose & Vision

Syrax CS2 Tournaments enables:

- Admins to create, manage, and update CS2 tournaments.
- Players to register via Steam, create/join teams, and participate in matches.
- Manual match control with admin-defined results and brackets.
- Communication support through Discord and Telegram handle collection.

> Lightweight. Admin-controlled. Competitive-ready.

---

## 🛠 Tech Stack

### Backend
- Java 21
- Spring Boot 3
- Spring Data JPA (Hibernate)
- Jakarta Validation
- Lombok
- BCrypt (for admin password hashing)
- Global Exception Handling (`@ControllerAdvice`)
- Role-based security ready (Spring Security integration planned)

### Frontend (MVP Phase)
- React
- Axios or Fetch API (TBD)
- Modular component architecture
- Lightweight styling with scope for expansion

---

## 🧩 Core Features

### 🗂 Tournament Management
- Create / update / delete tournaments
- Track game type, team size, prize pool, and status (`UPCOMING`, `ONGOING`, `COMPLETED`, `CANCELLED`)
- Fetch tournament listings or specific tournament data

### 👥 Team Management
- Teams have: name, logo URL, owner Steam ID, and associated players
- Teams belong to a tournament and are limited by team size constraints

### 🎮 Match Handling
- Each match includes:
  - Round number
  - Lobby link (e.g. Steam lobby)
  - Team A vs Team B
  - Result set manually by admin
- Matches are connected to tournaments

### 🧑‍🤝‍🧑 Player Profiles
- Players include:
  - Steam ID
  - Username
  - Discord & Telegram
- Each player is assigned to one team

### 🛡 Admin System
- Seeded default admin (`syraxadmin` / `admin123`)
- All admin functionality handled manually for simplicity
- Future integration with Spring Security (`@PreAuthorize`) supported

### 💬 Communication
- Captures Discord tags and Telegram handles for better team coordination

---

## ✅ Completed Milestones

- ✅ Full backend CRUD built for all entities (Tournament, Team, Player, Match, Admin)
- ✅ DTO mapping with validation and null safety
- ✅ Postman-tested REST API
- ✅ Exception handling via `GlobalExceptionHandler`
- ✅ GitHub integration with local project sync
- ✅ All controller endpoints stable and production-ready
- ✅ Basic data seeding via `CommandLineRunner`

---

## 📬 API Endpoints

### Tournaments
`/api/tournaments`
  - `GET` list tournaments
  - `GET /{id}` get tournament by id
  - `POST` create tournament
  - `PUT /{id}` update tournament
  - `DELETE /{id}` delete tournament

### Teams
`/api/teams`
  - `POST` create a team
`/api/teams/tournament/{tournamentId}`
  - `GET` list teams for a tournament

### Players
`/api/players`
  - `GET` list players
  - `POST` create player
`/api/players/{id}`
  - `GET` get player by id

### Matches
`/api/matches`
  - `GET` list matches
  - `POST` create match
`/api/matches/{id}`
  - `GET` get match by id
  - `PUT` update match
  - `DELETE` delete match

### Admin
`/api/admin`
  - `GET` admin welcome message

---

## 🔧 Requirements

### Backend
* Java 21+
* Maven (the project includes a Maven Wrapper)

### Frontend
* Node.js and npm

## 💻 Running the Backend

```bash
cd syrax-tournament-backend
./mvnw spring-boot:run
```

## 💻 Running the Frontend

```bash
cd frontend
npm install
npm start
```

## 🗄 Environment Variables

The backend connects to a MySQL database. You can override the default
`application.properties` values via environment variables:

| Variable | Description |
| -------- | ----------- |
| `SPRING_DATASOURCE_URL` | JDBC connection URL |
| `SPRING_DATASOURCE_USERNAME` | Database user |
| `SPRING_DATASOURCE_PASSWORD` | Database password |


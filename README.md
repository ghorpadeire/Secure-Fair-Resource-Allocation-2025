# Secure & Fair Resource Allocation — Modernized (2025)

🚀 Modernization of a legacy Java web application (JSP/Servlet-based) focused on **secure and fair resource allocation in cloud environments**.  
This repository represents a step-by-step professional rebuild using **modern Java, Docker, CI/CD, and DevOps practices**.

---

## 📌 Project Overview
- **Legacy Stack**: JSP/Servlets, WAR deployed on Tomcat, MariaDB SQL backend.
- **Key Features**:
  - AES-based file encryption & decryption
  - File splitting, deduplication & secure storage
  - Role-based dashboards (Admin/User)
  - Resource allocation workflows

---

## ✅ Day 1 Progress (Bootstrap)
- Database containerized via **MariaDB (Docker)**, schema imported.
- Application deployed on **Tomcat 9**.
- Verified homepage running at: [http://localhost:8080](http://localhost:8080).

![screenshot](docs/day1-login.png)

---

## 🔜 Upcoming (Days 2–5)
- **Day 2** → Add environment configs, JUnit tests, CI workflow.
- **Day 3** → Dockerize the app (Tomcat + WAR).
- **Day 4** → Docker Compose for full stack (App + DB).
- **Day 5** → End-to-end tests + GitHub Actions CI/CD pipeline.

---

## 🛠 Tech Stack
- **Backend**: Java 17+ (migrating to Java 21), JSP/Servlets
- **Server**: Apache Tomcat 9
- **Database**: MariaDB (Dockerized)
- **Build Tool**: Legacy WAR (modern build migration planned)
- **DevOps**: GitHub Actions, Docker, Docker Compose (planned)

---

## 📂 Repo Structure
.
-├── docs/ # Screenshots & diagrams
-├── scripts/ # Helper scripts (Windows & Linux)
-├── sql/ # Database schema & seed data
-├── webapps/ # Deployed WARs (legacy)
-├── README.md # Project documentation
-└── LICENSE # Open-source license

---

## 👨‍💻 Author
Pranav Ghorpade  
- MSc Cybersecurity, NCI Dublin  
- [LinkedIn](https://linkedin.com/in/pranav-ire/) | [GitHub](https://github.com/ghorpadeire)

---

## 📜 License
MIT License — free to use and modify.

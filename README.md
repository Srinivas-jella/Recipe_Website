# Recipe App

A simple yet powerful Recipe Web Application where users can search, view details, and explore recipes, combining:

* Spring Boot (Java Backend)
* MySQL Database
* REST API Integration.
* HTML, CSS & JavaScript Frontend

---

## Features

* Search recipes by name (checks both local DB & external API)
* View all available recipes
* See detailed recipe instructions, ingredients, and YouTube video (if available)
* Modern, responsive, stylish frontend
* Auto-fetch from external API if recipe not found in your database
* Clean separation of layers: Controller, Service, DAO, Repository

---

## Tech Stack

| Frontend      | Backend            | Database | Others                |
| ------------- | ------------------ | -------- | --------------------- |
| HTML, CSS, JS | Spring Boot (Java) | MySQL    | REST API, JPA |

---

## Project Structure

```
recipes-app/
├── src/main/java/com/example/recipesapp/
│   ├── controller/       // REST Controllers
│   ├── dao/              // Data Access Object Layer
│   ├── dto/              // Entity & DTO Combined
│   ├── repository/       // JPA Repositories
│   ├── service/          // Service & Implementation
│   └── RecipesappApplication.java
│
├── src/main/resources/
│   └── application.properties
│
├── static/
│   ├── index.html
│   ├── style.css
│   └── script.js
│
└── README.md
```

---

## How to Run

### Backend (Spring Boot)

1. Setup MySQL Database:

```sql
CREATE DATABASE recipesdb;
```

2. Update `application.properties` with your DB credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/recipesdb
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
spring.jpa.hibernate.ddl-auto=update
```

3. Run the Spring Boot Application:

```bash
mvn spring-boot:run
```

---

### Frontend

* Simply open `index.html` in your browser
* Make sure backend is running on `http://localhost:8080`

---


## Credits

* Inspired by food enthusiasts and open-source recipe APIs
* UI inspired by modern minimalist web design principles

---

## Future Enhancements

* Recipe category filters
* Pagination for large datasets
* Image uploads
* User authentication (admin recipe management)

---



## License

Contact for use.

---

## Screenshot

### Recipe List
![Recipe Screenshot](Screenshot/Screenshot%202025-07-06%20221436.png)
### Recipe Details
- You can view Category, Area, Ingredients, Preparation, Process, Youtube link for recipe
![Recipe Screenshot 2](Screenshot/Screenshot%202025-07-06%20222521.png)


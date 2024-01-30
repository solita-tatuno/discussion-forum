## Installation

Create .env file to root, template: 

```
POSTGRES_USER=
POSTGRES_PASSWORD=
POSTGRES_DB=
DB_USER=
DB_USER_PASSWORD=
FLYWAY_USER=
FLYWAY_USER_PASSWORD=
DATASOURCE=jdbc:postgresql://127.0.0.1:5432/${POSTGRES_DB}
```

Start db. If database initialization script is failing due permissions, give executable permissions for init/init-db.sh
```
docker compose up
```

Run project
```
gradle bootRun
```
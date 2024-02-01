## Installation

Create .env file to root, template: 

```
POSTGRES_USER=postgres
POSTGRES_PASSWORD=postgres
POSTGRES_DB=discussion_forum
DB_USER=dbuser
DB_USER_PASSWORD=dbuserpassword
FLYWAY_USER=flyway
FLYWAY_USER_PASSWORD=flywaypassword
DATASOURCE=jdbc:postgresql://127.0.0.1:5432/${POSTGRES_DB}
```

Create gradle.properties to root, template:
```
systemProp.DATASOURCE=jdbc:postgresql://127.0.0.1:5432/discussion_forum
systemProp.DB_USER=dbuser
systemProp.DB_USER_PASSWORD=dbuserpassword
systemProp.FLYWAY_USER=flyway
systemProp.FLYWAY_PASSWORD=flywaypassword

```

Start db. If database initialization script is failing due permissions, give executable permissions for init/init-db.sh
```
docker compose up
```

Run project
```
gradle bootRun
```
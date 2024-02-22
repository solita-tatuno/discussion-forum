## Environment

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

### RSA Public & Private Keys

Create new directory under src/main/resources/certs and generate RSA keys to certs directory with following commands:

```
# create rsa key pair
openssl genrsa -out keypair.pem 2048

# extract public key
openssl rsa -in keypair.pem -pubout -out public.pem

# create private key in PKCS#8 format
openssl pkcs8 -topk8 -inform PEM -outform PEM -nocrypt -in keypair.pem -out private.pem
```

If no errors, you can delete keypair.pem file.

### Start project

Start db. If database initialization script is failing due permissions, give executable permissions for init/init-db.sh

```
docker compose up
```

Run project

```
gradle bootRun
```
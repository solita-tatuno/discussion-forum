#!/bin/bash
set -e
export PGPASSWORD=$POSTGRES_PASSWORD;
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
  CREATE USER $DB_USER WITH PASSWORD '$DB_USER_PASSWORD';
  CREATE USER $FLYWAY_USER WITH PASSWORD '$FLYWAY_USER_PASSWORD';
  GRANT ALL ON SCHEMA public TO $FLYWAY_USER;
  ALTER DEFAULT PRIVILEGES FOR ROLE $FLYWAY_USER IN SCHEMA public
  GRANT SELECT, UPDATE, DELETE, INSERT ON TABLES TO $DB_USER;
  ALTER DEFAULT PRIVILEGES FOR ROLE $FLYWAY_USER IN SCHEMA public
  GRANT USAGE, SELECT, UPDATE ON SEQUENCES TO $DB_USER;
EOSQL

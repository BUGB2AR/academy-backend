CREATE DATABASE IF NOT EXISTS academy_db;
CREATE DATABASE IF NOT EXISTS keycloak_db;

CREATE USER IF NOT EXISTS 'academsys'@'%' IDENTIFIED BY 'academsys123';
GRANT ALL PRIVILEGES ON academy_db.* TO 'academsys'@'%';
GRANT ALL PRIVILEGES ON keycloak_db.* TO 'academsys'@'%';
FLUSH PRIVILEGES;

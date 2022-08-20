CREATE SCHEMA AKkoord ;

CREATE TABLE AKkoord.UserChord (
  toon varchar(1) NOT NULL,
  toonsoort varchar(15) NOT NULL,
  PRIMARY KEY (toon, toonsoort));


CREATE USER 'user'@'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON AKkoord.* TO 'user'@'localhost';
--
-- File generated with SQLiteStudio v3.2.1 on Kam Okt 11 05:42:55 2018
--
-- Text encoding used: System
--
PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

-- Table: undangan
CREATE TABLE undangan (id VARCHAR (70) PRIMARY KEY, nik VARCHAR (18), nama VARCHAR (150), alamat VARCHAR (250), hp VARCHAR (16), status VARCHAR (2));
INSERT INTO undangan (id, nik, nama, alamat, hp, status) VALUES ('001nurzen', '1471040904840021', 'Nurzen', '', '085355868105', '');

COMMIT TRANSACTION;
PRAGMA foreign_keys = on;

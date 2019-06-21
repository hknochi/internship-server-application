DROP TABLE IF EXISTS user_profile;
CREATE TABLE user_profile (
username VARCHAR(16) NOT NULL,
password VARCHAR(32) NOT NULL,
first_name VARCHAR(20) NOT NULL,
last_name VARCHAR(20) NOT NULL,
gender VARCHAR(7) NOT NULL,
year_of_birth SMALLINT NOT NULL,
PRIMARY KEY(username)
);

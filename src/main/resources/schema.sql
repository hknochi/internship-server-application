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


DROP TABLE IF EXISTS message;
CREATE TABLE message (
msg_id INT NOT NULL,
transmitter_username VARCHAR(16) NOT NULL,
receiver_username VARCHAR(16) NOT NULL,
msg_content VARCHAR(140),
send_time BIGINT NOT NULL
);

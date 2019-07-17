CREATE TABLE user (
    user_id INT NOT NULL AUTO_INCREMENT,
    user_name VARCHAR NOT NULL,
    user_email VARCHAR NOT NULL,
    user_status VARCHAR NOT NULL,
    user_password VARCHAR,
    salt VARCHAR,
    session_token VARCHAR,
    session_token_issued_time BIGINT,
    PRIMARY KEY(user_id),
);

INSERT INTO user(user_name, user_email, user_status, user_password, salt) VALUES ('baphemetis', 'john@gmail.com', 'Basic', 'ZTx3wUH/EAwZUHmnC1/ruW6LaaoPlR3aoVDvGTVsqAU=', 'D9g38dFXuUIi3hYu8Zb9ynbuEgODvclNUPqu8VHiGpI');
INSERT INTO user(user_name, user_email, user_status, user_password, salt) VALUES ('larnth', 'caleb.hobson@gmail.com', 'Basic', 'ZTx3wUH/EAwZUHmnC1/ruW6LaaoPlR3aoVDvGTVsqAU=', 'D9g38dFXuUIi3hYu8Zb9ynbuEgODvclNUPqu8VHiGpI');
INSERT INTO user(user_name, user_email, user_status, user_password, salt) VALUES ('brobson', 'tyler123@gmail.com', 'Basic', 'ZTx3wUH/EAwZUHmnC1/ruW6LaaoPlR3aoVDvGTVsqAU=', 'D9g38dFXuUIi3hYu8Zb9ynbuEgODvclNUPqu8VHiGpI');
INSERT INTO user(user_name, user_email, user_status, user_password, salt) VALUES ('matt', 'matt@gmail.com', 'Basic', 'ZTx3wUH/EAwZUHmnC1/ruW6LaaoPlR3aoVDvGTVsqAU=', 'D9g38dFXuUIi3hYu8Zb9ynbuEgODvclNUPqu8VHiGpI');

CREATE TABLE owner_editor (
    owner_editor_id INT NOT NULL AUTO_INCREMENT,
    owner_id INT NOT NULL,
    editor_id INT NOT NULL,
    PRIMARY KEY(owner_editor_id),
    FOREIGN KEY (owner_id) REFERENCES user(user_id),
    FOREIGN KEY (editor_id) REFERENCES user(user_id)
);

INSERT INTO owner_editor(owner_id, editor_id) VALUES (1, 2);
INSERT INTO owner_editor(owner_id, editor_id) VALUES (1, 3);
INSERT INTO owner_editor(owner_id, editor_id) VALUES (2, 1);

CREATE TABLE ball (
    ball_id INT NOT NULL AUTO_INCREMENT,
    ball_name VARCHAR NOT NULL,
    ball_image VARCHAR NOT NULL,
    PRIMARY KEY(ball_id)
);

INSERT INTO ball(ball_name, ball_image) VALUES ('Pokeball', 'pokeball.png');
INSERT INTO ball(ball_name, ball_image) VALUES ('Great Ball', 'great_ball.png');
INSERT INTO ball(ball_name, ball_image) VALUES ('Master Ball', 'master_ball.png');

CREATE TABLE capture_method (
    capture_method_id INT NOT NULL AUTO_INCREMENT,
    capture_method_name VARCHAR NOT NULL,
    PRIMARY KEY(capture_method_id)
);

INSERT INTO capture_method(capture_method_name) VALUES ('Soft Resets');

CREATE TABLE pokemon_species (
    pokemon_species_id INT NOT NULL AUTO_INCREMENT,
    pokemon_species_name VARCHAR NOT NULL,
    PRIMARY KEY(pokemon_species_id)
);

INSERT INTO pokemon_species(pokemon_species_name) VALUES ('Bulbasaur');
INSERT INTO pokemon_species(pokemon_species_name) VALUES ('Squirtle');
INSERT INTO pokemon_species(pokemon_species_name) VALUES ('Charmander');

CREATE TABLE pokemon (
    pokemon_id INT NOT NULL AUTO_INCREMENT,
    pokemon_name VARCHAR NOT NULL,
    pokemon_image VARCHAR NOT NULL,
    pokemon_species_id INT,
    PRIMARY KEY(pokemon_id),
    FOREIGN KEY (pokemon_species_id) REFERENCES pokemon_species(pokemon_species_id)
);

INSERT INTO pokemon(pokemon_name, pokemon_image, pokemon_species_id) VALUES ('Bulbasaur', 'bulbasaur.png', 1);
INSERT INTO pokemon(pokemon_name, pokemon_image, pokemon_species_id) VALUES ('Squirtle', 'squirtle.png', 2);
INSERT INTO pokemon(pokemon_name, pokemon_image, pokemon_species_id) VALUES ('Charmander', 'charmander.png', 3);

CREATE TABLE pokedex (
    pokedex_id INT NOT NULL AUTO_INCREMENT,
    pokedex_name VARCHAR NOT NULL,
    PRIMARY KEY(pokedex_id)
);

INSERT INTO pokedex(pokedex_name) VALUES ('National Dex');

CREATE TABLE pokemon_pokedex (
    pokemon_pokedex_id INT NOT NULL AUTO_INCREMENT,
    pokemon_id INT,
    pokedex_id INT,
    PRIMARY KEY(pokemon_pokedex_id),
    FOREIGN KEY (pokemon_id) REFERENCES pokemon(pokemon_id),
    FOREIGN KEY (pokedex_id) REFERENCES pokedex(pokedex_id)
);

INSERT INTO pokemon_pokedex(pokemon_id, pokedex_id) VALUES (1, 1);
INSERT INTO pokemon_pokedex(pokemon_id, pokedex_id) VALUES (2, 1);
INSERT INTO pokemon_pokedex(pokemon_id, pokedex_id) VALUES (3, 1);

CREATE TABLE entry (
    entry_id INT NOT NULL AUTO_INCREMENT,
    user_id INT,
    pokemon_pokedex_id INT,
    ball_id INT,
    capture_method_id INT,
    PRIMARY KEY(entry_id),
    FOREIGN KEY (user_id) REFERENCES user(user_id),
    FOREIGN KEY (pokemon_pokedex_id) REFERENCES pokemon_pokedex(pokemon_pokedex_id),
    FOREIGN KEY (ball_id) REFERENCES ball(ball_id),
    FOREIGN KEY (capture_method_id) REFERENCES capture_method(capture_method_id)
);

INSERT INTO entry(user_id, pokemon_pokedex_id, ball_id, capture_method_id) VALUES (1, 1, 1, 1);
INSERT INTO entry(user_id, pokemon_pokedex_id, ball_id, capture_method_id) VALUES (1, 2, 2, 1);
INSERT INTO entry(user_id, pokemon_pokedex_id, ball_id, capture_method_id) VALUES (1, 3, 3, 1);

INSERT INTO entry(user_id, pokemon_pokedex_id, ball_id, capture_method_id) VALUES (2, 1, 1, 1);

INSERT INTO entry(user_id, pokemon_pokedex_id, ball_id, capture_method_id) VALUES (4, 1, 1, 1);
INSERT INTO entry(user_id, pokemon_pokedex_id, ball_id, capture_method_id) VALUES (4, 2, 2, 1);
INSERT INTO entry(user_id, pokemon_pokedex_id, ball_id, capture_method_id) VALUES (4, 3, 3, 1);

CREATE TABLE encounter (
    encounter_id INT NOT NULL AUTO_INCREMENT,
    encounter_count INT,
    user_id INT,
    pokemon_id INT,
    PRIMARY KEY(encounter_id),
    FOREIGN KEY (user_id) REFERENCES user(user_id),
    FOREIGN KEY (pokemon_id) REFERENCES pokemon(pokemon_id)
);


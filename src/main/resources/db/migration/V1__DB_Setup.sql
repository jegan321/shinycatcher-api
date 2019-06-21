CREATE TABLE user (
    user_id INT NOT NULL AUTO_INCREMENT,
    user_name VARCHAR NOT NULL,
    user_email VARCHAR NOT NULL,
    user_status VARCHAR NOT NULL,
    PRIMARY KEY(user_id),
);

INSERT INTO user(user_name, user_email, user_status) VALUES ('baphemetis', 'john@gmail.com', 'Basic');
INSERT INTO user(user_name, user_email, user_status) VALUES ('larnth', 'caleb.hobson@gmail.com', 'Basic');
INSERT INTO user(user_name, user_email, user_status) VALUES ('brobson', 'tyler123@gmail.com', 'Basic');

CREATE TABLE ball (
    ball_id INT NOT NULL AUTO_INCREMENT,
    ball_name VARCHAR NOT NULL,
    ball_image VARCHAR NOT NULL,
    PRIMARY KEY(ball_id)
);

CREATE TABLE capture_method (
    capture_method_id INT NOT NULL AUTO_INCREMENT,
    capture_method_name VARCHAR NOT NULL,
    PRIMARY KEY(capture_method_id)
);

CREATE TABLE pokemon_species (
    pokemon_species_id INT NOT NULL AUTO_INCREMENT,
    pokemon_species_name VARCHAR NOT NULL,
    PRIMARY KEY(pokemon_species_id)
);

CREATE TABLE pokemon (
    pokemon_id INT NOT NULL AUTO_INCREMENT,
    pokemon_name VARCHAR NOT NULL,
    pokemon_image VARCHAR NOT NULL,
    pokemon_species_id INT,
    PRIMARY KEY(pokemon_id),
    FOREIGN KEY (pokemon_species_id) REFERENCES pokemon_species(pokemon_species_id)
);

CREATE TABLE pokedex (
    pokedex_id INT NOT NULL AUTO_INCREMENT,
    pokedex_name VARCHAR NOT NULL,
    PRIMARY KEY(pokedex_id)
);

CREATE TABLE pokemon_pokedex (
    pokemon_pokedex_id INT NOT NULL AUTO_INCREMENT,
    pokemon_id INT,
    pokedex_id INT,
    PRIMARY KEY(pokedex_id),
    FOREIGN KEY (pokemon_id) REFERENCES pokemon(pokemon_id),
    FOREIGN KEY (pokedex_id) REFERENCES pokedex(pokedex_id)
);

CREATE TABLE entry (
    entry_id INT NOT NULL AUTO_INCREMENT,
    pokemon_pokedex_id INT,
    user_id INT,
    ball_id INT,
    capture_method_id INT,
    PRIMARY KEY(entry_id),
    FOREIGN KEY (pokemon_pokedex_id) REFERENCES pokemon_pokedex(pokemon_pokedex_id),
    FOREIGN KEY (user_id) REFERENCES user(user_id),
    FOREIGN KEY (ball_id) REFERENCES ball(ball_id),
    FOREIGN KEY (capture_method_id) REFERENCES capture_method(capture_method_id)
);


INSERT INTO entry(user_id) VALUES (1);
INSERT INTO entry(user_id) VALUES (1);
INSERT INTO entry(user_id) VALUES (1);
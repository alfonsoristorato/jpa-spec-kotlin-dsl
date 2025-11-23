create table persona
(
    id        BIGINT PRIMARY KEY,
    name      TEXT NOT NULL,
    last_name TEXT NOT NULL,
    age       INT  NOT NULL,
    user_name TEXT NOT NULL
);

CREATE SEQUENCE persona_id_sequence START 1 INCREMENT 1;

create table post
(
    id      BIGINT PRIMARY KEY,
    persona_id BIGINT NOT NULL,
    title   TEXT   NOT NULL,
    content TEXT   NOT NULL,
    FOREIGN KEY (persona_id) REFERENCES persona (id)
);

CREATE SEQUENCE post_id_sequence START 1 INCREMENT 1;

create table comment
(
    id      BIGINT PRIMARY KEY,
    post_id BIGINT NOT NULL,
    persona_id BIGINT NOT NULL,
    content TEXT   NOT NULL,
    FOREIGN KEY (post_id) REFERENCES post (id),
    FOREIGN KEY (persona_id) REFERENCES persona (id)
);

CREATE SEQUENCE comment_id_sequence START 1 INCREMENT 1;

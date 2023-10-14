
DELETE FROM obras WHERE id_obra > 0;
DELETE FROM usuarios WHERE id_usuario > 0;
DELETE FROM autores WHERE id_autor > 0;
DELETE FROM avaliadores WHERE id_avaliador > 0;
DELETE FROM gerentes WHERE id_gerente > 0;

SELECT * FROM usuarios;
SELECT * FROM autores;
SELECT * FROM avaliadores;
SELECT * FROM obras;
SELECT * FROM gerentes;

DROP TABLE obras;
DROP TABLE gerentes;
DROP TABLE avaliadores;
DROP TABLE autores;
DROP TABLE usuarios;


CREATE TABLE usuarios (
    id_usuario SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(11) UNIQUE NOT NULL,
    endereco VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    tipo VARCHAR(255) NOT NULL
);

CREATE TABLE gerentes (
    id_gerente SERIAL PRIMARY KEY,
	nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    id_usuario INT UNIQUE REFERENCES usuarios (id_usuario) ON DELETE CASCADE
);

CREATE TABLE autores (
    id_autor SERIAL PRIMARY KEY,
	nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(11) UNIQUE NOT NULL,
    endereco VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    id_usuario INT UNIQUE REFERENCES usuarios (id_usuario) ON DELETE CASCADE
);

CREATE TABLE avaliadores (
    id_avaliador SERIAL PRIMARY KEY,
	nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(11) UNIQUE NOT NULL,
    endereco VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    id_usuario INT UNIQUE REFERENCES usuarios (id_usuario) ON DELETE CASCADE
);

CREATE TABLE obras (
    id_obra SERIAL PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    genero VARCHAR(255) NOT NULL,
    ano DATE NOT NULL,
    status VARCHAR(255),
    data_avaliacao DATE,
    id_autor INT REFERENCES autores (id_autor) ON DELETE CASCADE,
    id_avaliador INT REFERENCES avaliadores (id_avaliador),
    pdf_obra BYTEA /*NOT NULL*/,
    pdf_avaliacao BYTEA
);
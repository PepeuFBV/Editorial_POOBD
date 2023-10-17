
DELETE FROM obras WHERE id_obra > 0;
DELETE FROM usuarios WHERE id_usuario > 0;
DELETE FROM autores WHERE id_autor > 0;
DELETE FROM avaliadores WHERE id_avaliador > 0;
DELETE FROM gerentes WHERE id_gerente > 0;

DROP TABLE obras;
DROP TABLE gerentes;
DROP TABLE avaliadores;
DROP TABLE autores;
DROP TABLE usuarios;


SELECT * FROM usuarios;
SELECT * FROM autores;
SELECT * FROM avaliadores;
SELECT * FROM obras;
SELECT * FROM gerentes;


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
    pdf_obra BYTEA NOT NULL,
    pdf_avaliacao BYTEA
);



CREATE VIEW avaliadores_e_suas_obras AS
SELECT a.id_avaliador, a.nome AS nome_avaliador, o.id_obra, o.titulo AS titulo_obra
FROM avaliadores a
LEFT JOIN obras o ON a.id_avaliador = o.id_avaliador;

SELECT * FROM avaliadores_e_suas_obras;



CREATE OR REPLACE FUNCTION on_avaliador_delete()
RETURNS TRIGGER AS $$
BEGIN
    UPDATE obras
    SET id_avaliador = NULL, data_avaliacao = NULL, status = 'Avaliador Pendente'
    WHERE id_avaliador = OLD.id_avaliador;
    
    RETURN OLD;
END;
$$ LANGUAGE plpgsql;



CREATE TRIGGER trigger_on_avaliador_delete
AFTER DELETE
ON avaliadores
FOR EACH ROW
EXECUTE FUNCTION on_avaliador_delete();
ALTER TABLE obras
ADD CONSTRAINT check_status
CHECK (status IN ('Em avaliação', 'Avaliador Pendente', 'Aceita', 'Rejeitada'));
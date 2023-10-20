# Editorial do Paulão

## Descrição do projeto
É um software para o gerenciamento de uma editora. O software é capaz de cadastrar dois tipos de usuários (autores e avaliadores), cadastrar obras, definir os autores e avaliadores das obras no sistema. Ele também é capaz de separar os dados mostrados a cada tipo de usuário, permite os usuários avaliadores avaliarem obras, assim como os usuários autores cadastrarem obras.

## Para o desenvolvimento do projeto
- JDK 17 ou superior
- VSCode ou Eclipse na versão mais recente
- SceneBuilder na versão mais recente
- PostgreSQL versão 15 para Windows
- Driver do JavaFX e Postgres
- DIA Diagram Editor v0.97.2
- Maven for Java v0.42.0 ou superior
- Bibliotecas javax.mail-1.6.2.jar e activation.jar

## Para contribuir no projeto
Clone o repósitorio do GitHub:
```bash
cd "diretório para o projeto"
git clone https://github.com/PepeuFBV/Editorial_POOBD
```

Baixe o <a src="https://code.visualstudio.com/download"> VSCode </a> na versão mais recente para o seu sistema operacional.

Baixe o <a src="https://www.devmedia.com.br/instalacao-e-configuracao-do-pacote-java-jdk/23749"> JDK </a> 17 ou superior.

Baixe o Maven for Java como extensão no VSCode ou no bash:

````bash
mvn clean install
````

#### Para alterar o Banco de Dados
Baixe o <a src="https://www.postgresql.org/download/"> PostgreSQL </a> para o seu sistema operacional na versão 15.

Baixe e conecte o <a src="https://jdbc.postgresql.org/"> driver </a> do PostgreSQL no seu projeto no VSCode.

#### Para ver as telas
Baixe e conecte a <a src="https://gluonhq.com/products/javafx/"> bilioteca </a> do JavaFX no seu projeto no VSCode.

#### Extra
Baixe e conecte as bibliotecas 'javax.mail-1.6.2.jar' e 'activation.jar'.

### Iniciando
Para iniciar o programa pela primeira vez rode o comando a seguir no seu banco de dados: 
`````bash
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

INSERT INTO TABLE usuarios (nome, email, senha, tipo) VALUES ("Paulão", "seuemail@gmail.com", "suasenha", Gerente);
INSERT INTO TABLE gerentes (nome, email, senha, id_usuario) VALUES ("Paulão", "seuemail@gmail.com", "suasenha", 1);
`````

Isso inicializará as tabelas e criará o usuário gerente com o seu email para o seu uso (mude os últimos dois comandos para seu email e senha desejada).


#### Rode com segurança!

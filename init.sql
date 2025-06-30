CREATE TABLE Usuario (
                         id_usuario SERIAL PRIMARY KEY,
                         nome VARCHAR(100) NOT NULL,
                         email VARCHAR(100) UNIQUE NOT NULL,
                         senha VARCHAR(255) NOT NULL
);


CREATE TABLE Livro (
                       id_livro SERIAL PRIMARY KEY,
                       titulo VARCHAR(150) NOT NULL,
                       autor VARCHAR(100) NOT NULL,
                       editora VARCHAR(100) NOT NULL,
                       ano INT NOT NULL
);

CREATE TABLE Emprestimo (
                            id_emprestimo SERIAL PRIMARY KEY,
                            id_usuario INT NOT NULL,
                            id_livro INT NOT NULL,
                            FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario),
                            FOREIGN KEY (id_livro) REFERENCES Livro(id_livro)
);

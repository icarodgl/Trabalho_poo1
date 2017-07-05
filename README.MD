Para Utilizar o programa instale o Postgresql e crie um banco de dados "POO" com as tabelas:
```sql
/* Em construção: */

CREATE TABLE Regra (
    ID serial PRIMARY KEY,
    Direita varchar(20),
    Esquerda varchar(20)
);

CREATE TABLE Modelo (
    id serial PRIMARY KEY,
    Nome varchar(50),
    FK_Regra_ID serial,
    FK_Dominio_id serial
);

CREATE TABLE Dominio (
    id serial PRIMARY KEY,
    Nome varchar(50),
    FK_Atividade_id serial
);

CREATE TABLE Atividade (
    id serial PRIMARY KEY,
    tid varchar(50),
    nome varchar(50),
    tipo varchar(50)
);

CREATE TABLE Recurso (
    id serial PRIMARY KEY,
    tipo varchar(50),
    nome varchar(50),
    descricao varchar(50),
    FK_Atividade_id serial
);
 
ALTER TABLE Modelo ADD CONSTRAINT FK_Modelo_1
    FOREIGN KEY (FK_Regra_ID)
    REFERENCES Regra (ID);
 
ALTER TABLE Modelo ADD CONSTRAINT FK_Modelo_2
    FOREIGN KEY (FK_Dominio_id)
    REFERENCES Dominio (id);
 
ALTER TABLE Dominio ADD CONSTRAINT FK_Dominio_1
    FOREIGN KEY (FK_Atividade_id)
    REFERENCES Atividade (id);
 
ALTER TABLE Recurso ADD CONSTRAINT FK_Recurso_1
    FOREIGN KEY (FK_Atividade_id)
    REFERENCES Atividade (id);
    ```

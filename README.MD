``` sql
CREATE TABLE Modelo (
    id serial PRIMARY KEY,
    nome varchar(50)
);

CREATE TABLE Regra (
    id serial PRIMARY KEY,
    tipo varchar(50),
    ladoE varchar(50),
    ladoD varchar(50)
);

CREATE TABLE Atividade (
    id serial PRIMARY KEY,
    nome varchar(50),
    tid varchar(50),
    tipo varchar(50),
	inicio varchar(50),
	fim varchar(50),
	recurso_alocado varchar(50)
);

CREATE TABLE Recurso (
    id serial PRIMARY KEY,
    nome varchar(50),
    tipo varchar(50),
    descricao varchar(100)
);

CREATE TABLE modelo_regra (
    FK_Regra_id serial,
    FK_Modelo_id serial
);

CREATE TABLE Atividade_recurso (
    FK_Atividade_id serial,
    FK_Recurso_id serial
);

CREATE TABLE Dominio (
    FK_Atividade_id serial,
    FK_Modelo_id serial
);
 
ALTER TABLE modelo_regra ADD CONSTRAINT FK_modelo_regra_0
    FOREIGN KEY (FK_Regra_id)
    REFERENCES Regra (id);
 
ALTER TABLE modelo_regra ADD CONSTRAINT FK_modelo_regra_1
    FOREIGN KEY (FK_Modelo_id)
    REFERENCES Modelo (id);
 
ALTER TABLE Atividade_recurso ADD CONSTRAINT FK_Atividade_recurso_0
    FOREIGN KEY (FK_Atividade_id)
    REFERENCES Atividade (id);
 
ALTER TABLE Atividade_recurso ADD CONSTRAINT FK_Atividade_recurso_1
    FOREIGN KEY (FK_Recurso_id)
    REFERENCES Recurso (id);
 
ALTER TABLE Dominio ADD CONSTRAINT FK_Dominio_0
    FOREIGN KEY (FK_Atividade_id)
    REFERENCES Atividade (id);
 
ALTER TABLE Dominio ADD CONSTRAINT FK_Dominio_1
    FOREIGN KEY (FK_Modelo_id)
    REFERENCES Modelo (id);```

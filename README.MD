``` sql
/* Em construção: */

CREATE TABLE Modelo (
    id serial PRIMARY KEY
    FK_Dominio_id serial,
);

CREATE TABLE Regra (
    id serial PRIMARY KEY
    tipo varchar(50)
    ladoE varchar(50)
    ladoD varchar(50),
);

CREATE TABLE Dominio (
    id serial PRIMARY KEY,
);

CREATE TABLE Atividade (
    id serial PRIMARY KEY
    nome varchar(50)
    tid varchar(50)
    tipo varchar(50),
);

CREATE TABLE Recruso (
    id serial PRIMARY KEY
    nome varchar(50)
    tipo varchar(50)
    descricao varchar(100),
);

CREATE TABLE modelo_regra (
    FK_Regra_id serial
    FK_Modelo_id serial,
);

CREATE TABLE Atividade_recurso (
    FK_Atividade_id serial
    FK_Recruso_id serial,
);

CREATE TABLE Dominio_Atividade (
    FK_Atividade_id serial
    FK_Dominio_id serial,
);
 
ALTER TABLE Modelo ADD CONSTRAINT FK_Modelo_1
    FOREIGN KEY (FK_Dominio_id)
    REFERENCES Dominio (id);
 
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
    FOREIGN KEY (FK_Recruso_id)
    REFERENCES Recruso (id);
 
ALTER TABLE Dominio_Atividade ADD CONSTRAINT FK_Dominio_Atividade_0
    FOREIGN KEY (FK_Atividade_id)
    REFERENCES Atividade (id);
 
ALTER TABLE Dominio_Atividade ADD CONSTRAINT FK_Dominio_Atividade_1
    FOREIGN KEY (FK_Dominio_id)
    REFERENCES Dominio (id);
```

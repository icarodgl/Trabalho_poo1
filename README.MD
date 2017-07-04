Para Utilizar o programa instale o Postgresql e crie um banco de dados "POO" com as tabelas:
```sql
CREATE TABLE modelo (
    nome varchar(20),
    id serial PRIMARY KEY
);

CREATE TABLE instancia (
    id serial PRIMARY KEY,
    Nome varchar(20)
);

CREATE TABLE regra (
    id serial PRIMARY KEY,
    tipo varchar(20),
    esquerda varchar(20),
    direita varchar(20)
);

CREATE TABLE dominio (
    id serial PRIMARY KEY,
    aividade varchar(20),
    tipo varchar(20)
);

CREATE TABLE atividade (
    id serial PRIMARY KEY,
    nome varchar(20),
    tid varchar(20),
    tipo varchar(20),
    data_ini varchar(20),
    data_fim varchar(20),
    FK_recurso_id serial
);

CREATE TABLE recurso (
    id serial PRIMARY KEY,
    tipo varchar(20),
    nome varchar(20),
    descricao varchar(20)
);

CREATE TABLE instanciaAtividade (
    FK_instancia_id serial,
    FK_atividade_id serial
);

CREATE TABLE modeloRegras (
    FK_modelo_id serial,
    FK_regra_id serial
);

CREATE TABLE ModeloDominio (
    FK_modelo_id serial,
    FK_dominio_id serial
);
 
ALTER TABLE atividade ADD CONSTRAINT FK_atividade_1
    FOREIGN KEY (FK_recurso_id)
    REFERENCES recurso (id);
 
ALTER TABLE instanciaAtividade ADD CONSTRAINT FK_instanciaAtividade_0
    FOREIGN KEY (FK_instancia_id)
    REFERENCES instancia (id);
 
ALTER TABLE instanciaAtividade ADD CONSTRAINT FK_instanciaAtividade_1
    FOREIGN KEY (FK_atividade_id)
    REFERENCES atividade (id);
 
ALTER TABLE modeloRegras ADD CONSTRAINT FK_modeloRegras_0
    FOREIGN KEY (FK_modelo_id)
    REFERENCES modelo (id);
 
ALTER TABLE modeloRegras ADD CONSTRAINT FK_modeloRegras_1
    FOREIGN KEY (FK_regra_id)
    REFERENCES regra (id);
 
ALTER TABLE ModeloDominio ADD CONSTRAINT FK_ModeloDominio_0
    FOREIGN KEY (FK_modelo_id)
    REFERENCES modelo (id);
 
ALTER TABLE ModeloDominio ADD CONSTRAINT FK_ModeloDominio_1
    FOREIGN KEY (FK_dominio_id)
    REFERENCES dominio (id);
    ```

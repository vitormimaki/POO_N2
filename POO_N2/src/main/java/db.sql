create database PetShop;

use PetShop;

CREATE TABLE CEP (
	 cep INT PRIMARY KEY NOT NULL,
	 endereco VARCHAR(70) NOT NULL,
         cidade VARCHAR(50) NOT NULL,
         bairro VARCHAR(30) NOT NULL,
         uf VARCHAR(2) NOT NULL
);

CREATE TABLE Cliente (
         id_cliente INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
         cpf INT NOT NULL UNIQUE,
         nome VARCHAR(70) NOT NULL,
         nasc DATE NOT NULL,
         foto BLOB,
         cep INT NOT NULL,
         numcasa INT NOT NULL,
         complemento VARCHAR(15),
         telefone VARCHAR(15),
         email VARCHAR(70),
         data_cadastro DATE NOT NULL,
         FOREIGN KEY (cep) REFERENCES CEP(cep)
);

CREATE TABLE Pets(
         id_pet INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
         cpf_cliente INT NOT NULL,
         nome VARCHAR(30) NOT NULL,
         nasc DATE NOT NULL,
         foto BLOB,
         especie VARCHAR(20) NOT NULL,
         raca VARCHAR(20) NOT NULL,
         data_cadastro DATE NOT NULL,
         FOREIGN KEY (cpf_cliente) REFERENCES Cliente(cpf)
);

CREATE TABLE Servicos (
         id_servico INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	 tipo VARCHAR(15) NOT NULL UNIQUE,
	 descricao VARCHAR(20),
	 duracao TIME,
	 preco DECIMAL(10,2)
);

CREATE TABLE Agendamentos (
         id_agendamento INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
         tipo VARCHAR(15) NOT NULL,
         cpf_cliente INT NOT NULL,
         id_pet INT NOT NULL,
         data_agendamento DATE NOT NULL,
         hora_agendamento TIME NOT NULL,
         FOREIGN KEY (tipo) REFERENCES Servicos(tipo),
         FOREIGN KEY (cpf_cliente) REFERENCES Cliente(cpf),
         FOREIGN KEY (id_pet) REFERENCES Pets(id_pet)
);

CREATE TABLE Login (
         id_login INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
         usuario VARCHAR(15) NOT NULL,
         senha VARCHAR(15) NOT NULL
);

CREATE TABLE Fornecedor (
         codForn INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
         razaoSocial VARCHAR(20)
);

CREATE TABLE Produtos (
         codProd INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
         codForn INT,
         descricao VARCHAR(30) NOT NULL,
         tipo VARCHAR(1) NOT NULL,
         qtde DECIMAL(5,2) NOT NULL,
         vlrUnit DECIMAL(6,2) NOT NULL,
         FOREIGN KEY (codForn) REFERENCES Fornecedor(codForn)
);

CREATE TABLE Vendas (
         id_venda INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
         data_venda DATE NOT NULL,
         cpf_cliente INT NOT NULL,
         vlrTotal DECIMAL(10,2) NOT NULL,
         FOREIGN KEY (cpf_cliente) REFERENCES Cliente(cpf)
);
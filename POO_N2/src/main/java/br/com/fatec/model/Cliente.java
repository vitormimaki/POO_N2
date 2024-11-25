/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.model;

/**
 *
 * @author Aluno
 */
public class Cliente {
    private int id_cliente;
    private int cpf;
    private String nome;
    private String nasc;
    private String foto;
    private CEP cep;
    private int numcasa;
    private String complemento;
    private String telefone;
    private String email;
    private String data_cadastro;

    public Cliente() {
    }

    public Cliente(int id_cliente, int cpf, String nome, String nasc, CEP cep, int numcasa, String data_cadastro) {
        this.id_cliente = id_cliente;
        this.cpf = cpf;
        this.nome = nome;
        this.nasc = nasc;
        this.cep = cep;
        this.numcasa = numcasa;
        this.data_cadastro = data_cadastro;
    }
    
    

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNasc() {
        return nasc;
    }

    public void setNasc(String nasc) {
        this.nasc = nasc;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public CEP getCep() {
        return cep;
    }

    public void setCep(CEP cep) {
        this.cep = cep;
    }

    public int getNumcasa() {
        return numcasa;
    }

    public void setNumcasa(int numcasa) {
        this.numcasa = numcasa;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getData_cadastro() {
        return data_cadastro;
    }

    public void setData_cadastro(String data_cadastro) {
        this.data_cadastro = data_cadastro;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.id_cliente;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cliente other = (Cliente) obj;
        return this.id_cliente == other.id_cliente;
    }

    @Override
    public String toString() {
        return getNome();
    }
    
}

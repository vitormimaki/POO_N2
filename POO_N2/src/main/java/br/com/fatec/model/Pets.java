/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.model;

/**
 *
 * @author Aluno
 */
public class Pets {
    private int id_pet;
    private Cliente cliente;
    private String nome;
    private String nasc;
    private String foto;
    private String especie;
    private String raca;
    private String data_cadastro;

    public Pets(int id_pet, Cliente cliente, String nome, String nasc, String especie, String raca, String data_cadastro) {
        this.id_pet = id_pet;
        this.cliente = cliente;
        this.nome = nome;
        this.nasc = nasc;
        this.especie = especie;
        this.raca = raca;
        this.data_cadastro = data_cadastro;
    }

    public int getId_pet() {
        return id_pet;
    }

    public void setId_pet(int id_pet) {
        this.id_pet = id_pet;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
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
        hash = 89 * hash + this.id_pet;
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
        final Pets other = (Pets) obj;
        return this.id_pet == other.id_pet;
    }

    @Override
    public String toString() {
        return getNome();
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.model;

/**
 *
 * @author Aluno
 */
public class Agendamentos {
    private int id_agendamento;
    private String tipo;
    private Cliente cliente;
    private Pets pet;
    private String data_agendamento;
    private String hora_agendamento;

    public Agendamentos() {
    }
    
    

    public Agendamentos(int id_agendamento, String tipo, Cliente cliente, Pets pet, String data_agendamento, String hora_agendamento) {
        this.id_agendamento = id_agendamento;
        this.tipo = tipo;
        this.cliente = cliente;
        this.pet = pet;
        this.data_agendamento = data_agendamento;
        this.hora_agendamento = hora_agendamento;
    }
    
    public int getId_agendamento() {
        return id_agendamento;
    }

    public void setId_agendamento(int id_agendamento) {
        this.id_agendamento = id_agendamento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Pets getPet() {
        return pet;
    }

    public void setPet(Pets pet) {
        this.pet = pet;
    }

    public String getData_agendamento() {
        return data_agendamento;
    }

    public void setData_agendamento(String data_agendamento) {
        this.data_agendamento = data_agendamento;
    }

    public String getHora_agendamento() {
        return hora_agendamento;
    }

    public void setHora_agendamento(String hora_agendamento) {
        this.hora_agendamento = hora_agendamento;
    }
    
    
}

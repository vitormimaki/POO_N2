/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.dao;

import br.com.fatec.model.Cliente;
import br.com.fatec.persistencia.Banco;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author usuario
 */
public class ClienteDAO implements DAO<Cliente> {

    //variaveis auxiliares
    //permite o uso de comandos DML (select, insert, delete e update) para
    //acessar nosso SGBD
    private java.sql.PreparedStatement pst;
    
    //permite armazenar um conjunto de dados vindo do SGBD para ser
    //manipulado
    private java.sql.ResultSet rs;
    
    //representar os dados do  meu negócio
    private Cliente cliente; //meu MODEL   
    
    
    
    @Override
    public boolean insere(Cliente obj) throws SQLException {
        String sql = "INSERT INTO Cliente (id_cliente, cpf, nome, nasc, cep, numcasa, data_cadastro) " +
                     " VALUES (?, ?, ?, ?, ?, ?, ?)"; //a ? indica parametros
        
        //abre a conexao com o banco
        Banco.conectar();
        //preparar o comando PST
        pst = Banco.obterConexao().prepareStatement(sql);
        
        LocalDate currentDate = LocalDate.now();

        // Definir o formato desejado
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-mm-yyyy");

        // Converter para String no formato desejado
        String formattedDate = currentDate.format(formatter);
        
        //associar os dados do objeto Proprietario com o comando INSERT
        pst.setInt(1, obj.getId_cliente());
        pst.setInt(2, obj.getCpf());
        pst.setString(3, obj.getNome());
        pst.setString(4, obj.getNasc());
        pst.setInt(5, obj.getCep().getCep());
        pst.setInt(6, obj.getNumcasa());
        pst.setString(7, formattedDate);
        
        //executar o comando
        int res = pst.executeUpdate(); //esse método serve para Insert, delete e update
        
        //fecha a conexao
        Banco.desconectar();
        
        //devolve se funcionou ou nao
        return res != 0;
        
    }

    @Override
    public boolean remove(Cliente obj) throws SQLException {
        String sql = "DELETE FROM Cliente WHERE id_cliente = ?"; //a ? indica parametros
        
        //abre a conexao com o banco
        Banco.conectar();
        //preparar o comando PST
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //associar os dados do objeto Proprietario com o comando DELETE
        pst.setInt(1, obj.getId_cliente());
        
        //executar o comando
        int res = pst.executeUpdate(); //esse método serve para Insert, delete e update
        
        //fecha a conexao
        Banco.desconectar();
        
        //devolve se funcionoou ou nao
        return res != 0;
    }

    @Override
    public boolean altera(Cliente obj) throws SQLException {
        String sql = "UPDATE Agendamentos SET cep = ? "
                + "WHERE id_cliente = ?"; //a ? indica parametros
        
        //abre a conexao com o banco
        Banco.conectar();
        //preparar o comando PST
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //associar os dados do objeto Proprietario com o comando UPDATE
        pst.setInt(2, obj.getId_cliente());
        pst.setInt(1, obj.getCep().getCep());
        
        //executar o comando
        int res = pst.executeUpdate(); //esse método serve para Insert, delete e update
        
        //fecha a conexao
        Banco.desconectar();
        
        //devolve se funcionoou ou nao
        return res != 0;
    }

    /**
     * Busca um dado de proprietario baseado em sua Chave Primaria
     * @param obj
     * @return
     * @throws SQLException 
     */
    @Override
    public Cliente buscaID(Cliente obj) throws SQLException {
        String sql = "SELECT * FROM Cliente "
                   + "WHERE id_cliente = ?"; //a ? indica parametros
        
        //abre a conexao com o banco
        Banco.conectar();
        //preparar o comando PST
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //associar os dados do objeto Proprietario com o comando UPDATE
        //pst.setInt(1, obj.getCodProprietario());
        
        //executar o comando
        rs = pst.executeQuery(); //esse método serve para SELECT
        
        //verificar se trouxe algum registro
        //rs.next() faz a leitura do próximo registro, se existir devolve true
        //se nao devolve false
        if(rs.next()) {
            //mover os dados(campos da tab) do resultSet para o objeto proprietário
            cliente = new Cliente();
            cliente.setCpf(rs.getInt("cpf"));
            cliente.setNome(rs.getString("nome"));
            cliente.setNasc(rs.getString("nasc"));
            //cliente.setCep(rs.getString("cep"));            
            cliente.setNumcasa(rs.getInt("numcasa"));
            cliente.setData_cadastro(rs.getString("data_cadastro"));
        }
        else {
            //não encontrou o registro solicitado
            cliente = null;
        }
                
        //fecha a conexao
        Banco.desconectar();
        
        //devolve o objeto proprietario
        return cliente;

    }

    @Override
    public Collection<Cliente> lista(String criterio) throws SQLException {
        //cria uma lista para armazenar os dados vindos do banco
        ArrayList<Cliente> lista = new ArrayList<>();
        
        String sql = "SELECT * FROM Cliente ";

        //precisa fazer filtro para listagem
        if(criterio != null && criterio.length() > 0) {
            sql += " WHERE " + criterio;
        }
        
        //abre a conexao com o banco
        Banco.conectar();
        
        //preparar o comando PST
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //executar o comando
        rs = pst.executeQuery(); //esse método serve para SELECT
        
        //Varre todo o resultado da consulta e coloca cada registro dentro
        //de um objeto e coloca o objeto dentro da coleção
        while(rs.next()) {
            //criar o objeto
            cliente = new Cliente();
            
            //mover os dados do resultSet para o objeto proprietário
            cliente.setCpf(rs.getInt("cpf"));
            cliente.setNome(rs.getString("nome"));
            cliente.setNasc(rs.getString("nasc"));
            //cliente.setCep(rs.getString("cep"));            
            cliente.setNumcasa(rs.getInt("numcasa"));
            cliente.setData_cadastro(rs.getString("data_cadastro"));           
            //move o objeto para a coleção
            lista.add(cliente);
        }
                
        //fecha a conexao
        Banco.desconectar();
        
        //devolve o objeto proprietario
        return lista;
        
    }
    
}

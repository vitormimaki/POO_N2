/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.dao;

import br.com.fatec.model.Vendas;
import br.com.fatec.persistencia.Banco;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author usuario
 */
public class VendasDAO implements DAO<Vendas> {
    
    //variaveis auxiliares
    //permite o uso de comandos DML (select, insert, delete e update) para
    //acessar nosso SGBD
    private java.sql.PreparedStatement pst;
    
    //permite armazenar um conjunto de dados vindo do SGBD para ser
    //manipulado
    private java.sql.ResultSet rs;
    
    //representar os dados do  meu negócio
    private Vendas vendas; //meu MODEL
    
    // Obter a data e hora atual
    LocalDateTime currentDateTime = LocalDateTime.now();

    // Definir o formato para a data
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    // Definir o formato para a hora
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    // Converter para String no formato desejado
    String formattedDate = currentDateTime.format(dateFormatter);  // Data no formato dd-MM-yyyy
    String formattedTime = currentDateTime.format(timeFormatter);  // Hora no formato HH:mm
    
    @Override
    public boolean insere(Vendas obj) throws SQLException {
        String sql = "INSERT INTO Vendas (id_venda, data_venda, cpf_cliente, vlrTotal) " +
                    " VALUES (?, ?, ?, ?)"; //a ? indica parametros
        
        //abre a conexao com o banco
        Banco.conectar();
        //preparar o comando PST
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //associar os dados do objeto Proprietario com o comando INSERT
        pst.setInt(1, obj.getId_venda());
        pst.setString(2, obj.getData_venda());
        pst.setInt(3, obj.getCliente().getCpf());
        pst.setFloat(4, obj.getVlrTotal());
        
        //executar o comando
        int res = pst.executeUpdate(); //esse método serve para Insert, delete e update
        
        //fecha a conexao
        Banco.desconectar();
        
        //devolve se funcionou ou nao
        return res != 0;
        
    }

    @Override
    public boolean remove(Vendas obj) throws SQLException {
        String sql = "DELETE FROM Vendas WHERE id_venda = ?"; //a ? indica parametros
        
        //abre a conexao com o banco
        Banco.conectar();
        //preparar o comando PST
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //associar os dados do objeto Proprietario com o comando DELETE
        pst.setInt(1, obj.getId_venda());
        
        //executar o comando
        int res = pst.executeUpdate(); //esse método serve para Insert, delete e update
        
        //fecha a conexao
        Banco.desconectar();
        
        //devolve se funcionoou ou nao
        return res != 0;
    }

    @Override
    public boolean altera(Vendas obj) throws SQLException {
       throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Busca um dado de proprietario baseado em sua Chave Primaria
     * @param obj
     * @return
     * @throws SQLException 
     */
    @Override
    public Vendas buscaID(Vendas obj) throws SQLException {
        String sql = "SELECT * FROM Vendas "
                   + "WHERE id_venda = ?"; //a ? indica parametros
        
        //abre a conexao com o banco
        Banco.conectar();
        //preparar o comando PST
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //associar os dados do objeto Proprietario com o comando UPDATE
        pst.setInt(1, obj.getId_venda());
        
        //executar o comando
        rs = pst.executeQuery(); //esse método serve para SELECT
        
        //verificar se trouxe algum registro
        //rs.next() faz a leitura do próximo registro, se existir devolve true
        //se nao devolve false
        if(rs.next()) {
        
            vendas = new Vendas();
            
            //vendas.setCliente(rs.getInt("cpf"));
            vendas.setData_venda(rs.getString("data_venda"));
            vendas.setVlrTotal(rs.getFloat("vlrTotal"));
        }
        else {
            //não encontrou o registro solicitado
            vendas = null;
        }
                
        //fecha a conexao
        Banco.desconectar();
        
        //devolve o objeto proprietario
        return vendas;

    }

    @Override
    public Collection<Vendas> lista(String criterio) throws SQLException {
        //cria uma lista para armazenar os dados vindos do banco
        ArrayList<Vendas> lista = new ArrayList<>();
        
        String sql = "SELECT * FROM Vendas ";

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
            vendas = new Vendas();
            
            //vendas.setCliente(rs.getInt("cpf"));
            vendas.setData_venda(rs.getString("data_venda"));
            vendas.setVlrTotal(rs.getFloat("vlrTotal"));
            
            //move o objeto para a coleção
            lista.add(vendas);
        }
                
        //fecha a conexao
        Banco.desconectar();
        
        //devolve o objeto proprietario
        return lista;
        
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.dao;

import br.com.fatec.model.Fornecedor;
import br.com.fatec.persistencia.Banco;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author usuario
 */
public class FornecedorDAO implements DAO<Fornecedor> {

    //variaveis auxiliares
    //permite o uso de comandos DML (select, insert, delete e update) para
    //acessar nosso SGBD
    private java.sql.PreparedStatement pst;
    
    //permite armazenar um conjunto de dados vindo do SGBD para ser
    //manipulado
    private java.sql.ResultSet rs;
    
    //representar os dados do  meu negócio
    private Fornecedor fornecedor; //meu MODEL   
    
    @Override
    public boolean insere(Fornecedor obj) throws SQLException {
        String sql = "INSERT INTO Fornecedor (codForn, razaoSocial) " +
                    " VALUES (?, ?)"; //a ? indica parametros
        
        //abre a conexao com o banco
        Banco.conectar();
        //preparar o comando PST
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //associar os dados do objeto Proprietario com o comando INSERT
        pst.setInt(1, obj.getCodForn());
        pst.setString(2, obj.getRazaoSocial());
        
        //executar o comando
        int res = pst.executeUpdate(); //esse método serve para Insert, delete e update
        
        //fecha a conexao
        Banco.desconectar();
        
        //devolve se funcionou ou nao
        return res != 0;
        
    }

    @Override
    public boolean remove(Fornecedor obj) throws SQLException {
        String sql = "DELETE FROM Fornecedor WHERE codForn = ?"; //a ? indica parametros
        
        //abre a conexao com o banco
        Banco.conectar();
        //preparar o comando PST
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //associar os dados do objeto Proprietario com o comando DELETE
        pst.setInt(1, obj.getCodForn());
        
        //executar o comando
        int res = pst.executeUpdate(); //esse método serve para Insert, delete e update
        
        //fecha a conexao
        Banco.desconectar();
        
        //devolve se funcionoou ou nao
        return res != 0;
    }

    @Override
    public boolean altera(Fornecedor obj) throws SQLException {
        String sql = "UPDATE Fornecedor SET razaoSocial = ? "
                   + "WHERE codForn = ?"; //a ? indica parametros
        
        //abre a conexao com o banco
        Banco.conectar();
        //preparar o comando PST
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //associar os dados do objeto Proprietario com o comando UPDATE
        pst.setInt(2, obj.getCodForn());
        pst.setString(1, obj.getRazaoSocial());
        
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
    public Fornecedor buscaID(Fornecedor obj) throws SQLException {
        String sql = "SELECT * FROM Fornecedor "
                   + "WHERE codForn = ?"; //a ? indica parametros
        
        //abre a conexao com o banco
        Banco.conectar();
        //preparar o comando PST
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //associar os dados do objeto Proprietario com o comando UPDATE
        pst.setInt(1, obj.getCodForn());
        
        //executar o comando
        rs = pst.executeQuery(); //esse método serve para SELECT
        
        //verificar se trouxe algum registro
        //rs.next() faz a leitura do próximo registro, se existir devolve true
        //se nao devolve false
        if(rs.next()) {
            //mover os dados(campos da tab) do resultSet para o objeto proprietário
            fornecedor = new Fornecedor();
            fornecedor.setCodForn(rs.getInt("codForn"));
            fornecedor.setRazaoSocial(rs.getString("razaoSocial"));
        }
        else {
            //não encontrou o registro solicitado
            fornecedor = null;
        }
                
        //fecha a conexao
        Banco.desconectar();
        
        //devolve o objeto proprietario
        return fornecedor;

    }

    @Override
    public Collection<Fornecedor> lista(String criterio) throws SQLException {
        //cria uma lista para armazenar os dados vindos do banco
        ArrayList<Fornecedor> lista = new ArrayList<>();
        
        String sql = "SELECT * FROM Fornecedor ";

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
            fornecedor = new Fornecedor();
            
            //mover os dados do resultSet para o objeto proprietário
            fornecedor.setCodForn(rs.getInt("codForn"));
            fornecedor.setRazaoSocial(rs.getString("razaoSocial"));
            
            //move o objeto para a coleção
            lista.add(fornecedor);
        }
                
        //fecha a conexao
        Banco.desconectar();
        
        //devolve o objeto proprietario
        return lista;
        
    }
    
}

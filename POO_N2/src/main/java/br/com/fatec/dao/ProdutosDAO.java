/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.dao;

import br.com.fatec.model.Produtos;
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
public class ProdutosDAO implements DAO<Produtos> {

    //variaveis auxiliares
    //permite o uso de comandos DML (select, insert, delete e update) para
    //acessar nosso SGBD
    private java.sql.PreparedStatement pst;
    
    //permite armazenar um conjunto de dados vindo do SGBD para ser
    //manipulado
    private java.sql.ResultSet rs;
    
    //representar os dados do  meu negócio
    private Produtos produtos; //meu MODEL
    
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
    public boolean insere(Produtos obj) throws SQLException {
        String sql = "INSERT INTO Produtos (codProd, codForn, descricao, tipo, qtde, vlrUnit) " +
                    " VALUES (?, ?, ?, ?, ?, ?)"; //a ? indica parametros
        
        //abre a conexao com o banco
        Banco.conectar();
        //preparar o comando PST
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //associar os dados do objeto Proprietario com o comando INSERT
        pst.setInt(1, obj.getCodProd());
        pst.setInt(2, obj.getFornecedor().getCodForn());
        pst.setString(3, obj.getDescricao());
        //pst.setString(4, parseString(obj.getTipo()));
        pst.setFloat(5, obj.getQtde());  // Para a data
        pst.setFloat(6, obj.getVlrUnit());  // Para a hora
        
        
        //executar o comando
        int res = pst.executeUpdate(); //esse método serve para Insert, delete e update
        
        //fecha a conexao
        Banco.desconectar();
        
        //devolve se funcionou ou nao
        return res != 0;
        
    }

    @Override
    public boolean remove(Produtos obj) throws SQLException {
        String sql = "DELETE FROM Produtos WHERE codProd = ?"; //a ? indica parametros
        
        //abre a conexao com o banco
        Banco.conectar();
        //preparar o comando PST
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //associar os dados do objeto Proprietario com o comando DELETE
        pst.setInt(1, obj.getCodProd());
        
        //executar o comando
        int res = pst.executeUpdate(); //esse método serve para Insert, delete e update
        
        //fecha a conexao
        Banco.desconectar();
        
        //devolve se funcionoou ou nao
        return res != 0;
    }

    @Override
    public boolean altera(Produtos obj) throws SQLException {
        String sql = "UPDATE Produtos SET vlrUnit = ? "
                   + "WHERE codProd = ?"; //a ? indica parametros
        
        //abre a conexao com o banco
        Banco.conectar();
        //preparar o comando PST
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //associar os dados do objeto Proprietario com o comando UPDATE
        pst.setInt(2, obj.getCodProd());
        pst.setFloat(1, obj.getVlrUnit());
        
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
    public Produtos buscaID(Produtos obj) throws SQLException {
        String sql = "SELECT * FROM Produtos "
                   + "WHERE codProd = ?"; //a ? indica parametros
        
        //abre a conexao com o banco
        Banco.conectar();
        //preparar o comando PST
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //associar os dados do objeto Proprietario com o comando UPDATE
        pst.setInt(1, obj.getCodProd());
        
        //executar o comando
        rs = pst.executeQuery(); //esse método serve para SELECT
        
        //verificar se trouxe algum registro
        //rs.next() faz a leitura do próximo registro, se existir devolve true
        //se nao devolve false
        if(rs.next()) {
        
            produtos = new Produtos();
            
            //produtos.setFornecedor(fornecedor);
            produtos.setDescricao(rs.getString("descricao"));
            //produtos.setTipo(rs.getString("tipo"));
            produtos.setQtde(rs.getFloat("qtde"));
            produtos.setVlrUnit(rs.getFloat("vlrUnit"));

            
        }
        else {
            //não encontrou o registro solicitado
            produtos = null;
        }
                
        //fecha a conexao
        Banco.desconectar();
        
        //devolve o objeto proprietario
        return produtos;

    }

    @Override
    public Collection<Produtos> lista(String criterio) throws SQLException {
        //cria uma lista para armazenar os dados vindos do banco
        ArrayList<Produtos> lista = new ArrayList<>();
        
        String sql = "SELECT * FROM Produtos ";

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
            produtos = new Produtos();
            
            //produtos.setFornecedor(fornecedor);
            produtos.setDescricao(rs.getString("descricao"));
            //produtos.setTipo(rs.getString("tipo"));
            produtos.setQtde(rs.getFloat("qtde"));
            produtos.setVlrUnit(rs.getFloat("vlrUnit"));
            
            //move o objeto para a coleção
            lista.add(produtos);
        }
                
        //fecha a conexao
        Banco.desconectar();
        
        //devolve o objeto proprietario
        return lista;
        
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.dao;

import br.com.fatec.model.Pets;
import br.com.fatec.persistencia.Banco;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author usuario
 */
public class PetsDAO implements DAO<Pets> {

    //variaveis auxiliares
    //permite o uso de comandos DML (select, insert, delete e update) para
    //acessar nosso SGBD
    private java.sql.PreparedStatement pst;
    
    //permite armazenar um conjunto de dados vindo do SGBD para ser
    //manipulado
    private java.sql.ResultSet rs;
    
    //representar os dados do  meu negócio
    private Pets pets; //meu MODEL   
    
    
    
    @Override
    public boolean insere(Pets obj) throws SQLException {
        String sql = "INSERT INTO Pets (id_pet, cpf_cliente, nome, nasc, especie, raca, data_cadastro) " +
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
        pst.setInt(1, obj.getId_pet());
        pst.setInt(2, obj.getCliente().getCpf());
        pst.setString(3, obj.getNome());
        pst.setString(4, obj.getNasc());
        pst.setString(5, obj.getEspecie());
        pst.setString(6, obj.getRaca());
        pst.setString(7, formattedDate);
        
        //executar o comando
        int res = pst.executeUpdate(); //esse método serve para Insert, delete e update
        
        //fecha a conexao
        Banco.desconectar();
        
        //devolve se funcionou ou nao
        return res != 0;
        
    }

    @Override
    public boolean remove(Pets obj) throws SQLException {
        String sql = "DELETE FROM Pets WHERE id_pet = ?"; //a ? indica parametros
        
        //abre a conexao com o banco
        Banco.conectar();
        //preparar o comando PST
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //associar os dados do objeto Proprietario com o comando DELETE
        pst.setInt(1, obj.getId_pet());
        
        //executar o comando
        int res = pst.executeUpdate(); //esse método serve para Insert, delete e update
        
        //fecha a conexao
        Banco.desconectar();
        
        //devolve se funcionoou ou nao
        return res != 0;
    }

    @Override
    public boolean altera(Pets obj) throws SQLException {
        String sql = "UPDATE Pets SET nome = ? "
                + "WHERE id_pet = ?"; //a ? indica parametros
        
        //abre a conexao com o banco
        Banco.conectar();
        //preparar o comando PST
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //associar os dados do objeto Proprietario com o comando UPDATE
        pst.setInt(2, obj.getId_pet());
        pst.setString(1, obj.getNome());
        
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
    public Pets buscaID(Pets obj) throws SQLException {
        String sql = "SELECT * FROM Pets "
                   + "WHERE id_pet = ?"; //a ? indica parametros
        
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
            pets = new Pets();
            //pets.setCpf();
            pets.setNome(rs.getString("nome"));
            pets.setNasc(rs.getString("nasc"));
            pets.setEspecie(rs.getString("especie"));
            pets.setRaca(rs.getString("raca"));
            pets.setData_cadastro(rs.getString("data_cadastro"));
        }
        else {
            //não encontrou o registro solicitado
            pets = null;
        }
                
        //fecha a conexao
        Banco.desconectar();
        
        //devolve o objeto proprietario
        return pets;

    }

    @Override
    public Collection<Pets> lista(String criterio) throws SQLException {
        //cria uma lista para armazenar os dados vindos do banco
        ArrayList<Pets> lista = new ArrayList<>();
        
        String sql = "SELECT * FROM Pets ";

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
            pets = new Pets();
            //pets.setCpf();
            pets.setNome(rs.getString("nome"));
            pets.setNasc(rs.getString("nasc"));
            pets.setEspecie(rs.getString("especie"));
            pets.setRaca(rs.getString("raca"));
            pets.setData_cadastro(rs.getString("data_cadastro"));     
            //move o objeto para a coleção
            lista.add(pets);
        }
                
        //fecha a conexao
        Banco.desconectar();
        
        //devolve o objeto proprietario
        return lista;
        
    }
    
}

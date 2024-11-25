/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.dao;

import br.com.fatec.model.Agendamentos;
import br.com.fatec.persistencia.Banco;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author usuario
 */
public class AgendamentosDAO implements DAO<Agendamentos> {
    
    //variaveis auxiliares
    //permite o uso de comandos DML (select, insert, delete e update) para
    //acessar nosso SGBD
    private java.sql.PreparedStatement pst;
    
    //permite armazenar um conjunto de dados vindo do SGBD para ser
    //manipulado
    private java.sql.ResultSet rs;
    
    //representar os dados do  meu negócio
    private Agendamentos agendamento; //meu MODEL
    
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
    public boolean insere(Agendamentos obj) throws SQLException {
        String sql = "INSERT INTO Agendamentos (id_agendamento, tipo, cpf_cliente, id_pet, data_agendamento, hora_agendamento) " +
                    " VALUES (?, ?, ?, ?, ?, ?)"; //a ? indica parametros
        
        //abre a conexao com o banco
        Banco.conectar();
        //preparar o comando PST
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //associar os dados do objeto Proprietario com o comando INSERT
        pst.setInt(1, obj.getId_agendamento());
        pst.setString(2, obj.getTipo());
        pst.setInt(3, obj.getCliente().getCpf());
        pst.setInt(4, obj.getPet().getId_pet());
        pst.setString(5, formattedDate);  // Para a data
        pst.setString(6, formattedTime);  // Para a hora
        
        
        //executar o comando
        int res = pst.executeUpdate(); //esse método serve para Insert, delete e update
        
        //fecha a conexao
        Banco.desconectar();
        
        //devolve se funcionou ou nao
        return res != 0;
        
    }

    @Override
    public boolean remove(Agendamentos obj) throws SQLException {
        String sql = "DELETE FROM Agendamentos WHERE id_agendamento = ?"; //a ? indica parametros
        
        //abre a conexao com o banco
        Banco.conectar();
        //preparar o comando PST
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //associar os dados do objeto Proprietario com o comando DELETE
        pst.setInt(1, obj.getId_agendamento());
        
        //executar o comando
        int res = pst.executeUpdate(); //esse método serve para Insert, delete e update
        
        //fecha a conexao
        Banco.desconectar();
        
        //devolve se funcionoou ou nao
        return res != 0;
    }

    @Override
    public boolean altera(Agendamentos obj) throws SQLException {
        String sql = "UPDATE Agendamentos SET data_agendamento = ? "
                   + "WHERE id_agendamento = ?"; //a ? indica parametros
        
        //abre a conexao com o banco
        Banco.conectar();
        //preparar o comando PST
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //associar os dados do objeto Proprietario com o comando UPDATE
        pst.setInt(2, obj.getId_agendamento());
        pst.setString(1, obj.getData_agendamento());
        
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
    public Agendamentos buscaID(Agendamentos obj) throws SQLException {
        String sql = "SELECT * FROM Agendamentos "
                   + "WHERE id_agendamento = ?"; //a ? indica parametros
        
        //abre a conexao com o banco
        Banco.conectar();
        //preparar o comando PST
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //associar os dados do objeto Proprietario com o comando UPDATE
        pst.setInt(1, obj.getId_agendamento());
        
        //executar o comando
        rs = pst.executeQuery(); //esse método serve para SELECT
        
        //verificar se trouxe algum registro
        //rs.next() faz a leitura do próximo registro, se existir devolve true
        //se nao devolve false
        if(rs.next()) {
        
            agendamento = new Agendamentos();
            agendamento.setTipo(rs.getString("tipo"));
            //agendamento.setCliente(rs.getString("cpf"));
            //agendamento.setPet(rs.getInt("id_pet"));
            pst.setString(5, formattedDate);  // Para a data
            pst.setString(6, formattedTime);  // Para a hora
        }
        else {
            //não encontrou o registro solicitado
            agendamento = null;
        }
                
        //fecha a conexao
        Banco.desconectar();
        
        //devolve o objeto proprietario
        return agendamento;

    }

    @Override
    public Collection<Agendamentos> lista(String criterio) throws SQLException {
        //cria uma lista para armazenar os dados vindos do banco
        ArrayList<Agendamentos> lista = new ArrayList<>();
        
        String sql = "SELECT * FROM Agendamentos ";

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
            agendamento = new Agendamentos();
            agendamento.setTipo(rs.getString("tipo"));
            //agendamento.setCliente(rs.getString("cpf"));
            //agendamento.setPet(rs.getInt("id_pet"));
            pst.setString(5, formattedDate);  // Para a data
            pst.setString(6, formattedTime);  // Para a hora
            
            //move o objeto para a coleção
            lista.add(agendamento);
        }
                
        //fecha a conexao
        Banco.desconectar();
        
        //devolve o objeto proprietario
        return lista;
        
    }
    
}

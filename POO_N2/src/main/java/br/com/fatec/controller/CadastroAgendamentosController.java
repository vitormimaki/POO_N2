/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec.controller;

import br.com.fatec.dao.AgendamentosDAO;
import br.com.fatec.model.Agendamentos;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author usuario
 */
public class CadastroAgendamentosController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //faz a ligação da coluna do tableView com 
        //os atributos da classe Proprietario
        colSelecionado.setCellValueFactory(
                new PropertyValueFactory<>("selecionado"));
        colNome.setCellValueFactory(
                new PropertyValueFactory<>("nome"));
        colCodigo.setCellValueFactory(
                new PropertyValueFactory<>("codProprietario"));
        
        //coloca o checkbox na coluna
        colSelecionado.setCellFactory(
                CheckBoxTableCell.forTableColumn(colSelecionado));

        //preenche a tabela
        tblProprietario.setItems(preencheTabela());
    }    
    
    private ObservableList<Agendamentos> preencheTabela() {
        AgendamentosDAO dao = new AgendamentosDAO();
        ObservableList<Agendamentos> agendamentos
            = FXCollections.observableArrayList();
        
        try {
            //busca somente que termina com 'a'
            //proprietarios.addAll(dao.lista("nome like '%a'"));
            //busca todo mundo
            agendamentos.addAll(dao.lista(""));
        } catch (SQLException ex) {
            Alert alerta = new Alert(Alert.AlertType.ERROR,
                    "Erro Preenche Tabela: " + ex.getMessage(),
                    ButtonType.OK);
            alerta.showAndWait();
        }
        
        return agendamentos;
    }
    
    //------------------------- COMBO BOX -----------------------------
    
    private void configuraChangeValueComboProduto() {
        //programando o evento change da combo para
        //exibir seu conteudo nos texts
        cbProduto.valueProperty().addListener((value, velho, novo) -> {
            if(novo != null) {
                txtCodigo.setText(Integer.toString(novo.getCodigo()));
                txtDescricao.setText(novo.getDescricao());
                txtPreco.setText(String.valueOf(novo.getPreco()));
            }
        });
    }
        
    @FXML
    private void btnPreencher_Click(ActionEvent event) {
        listaProdutos.clear();
        txtCodigo.clear();
        txtDescricao.clear();
        txtPreco.clear();
                
        listaProdutos.add(new Produto(1, "Caneta", 5.87f));
        listaProdutos.add(new Produto(2, "Regua", 10.20f));
        listaProdutos.add(new Produto(3, "Compasso", 15.80f));
    }

    @FXML
    private void btnExibir_Click(ActionEvent event) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Mensagem");
        alerta.setContentText("");

        if(cbProduto.getValue() == null) {
            alerta.setHeaderText("Tem que selecionar um item");
            alerta.showAndWait();
        }
        else {
            //obtem o objeto que está selecionado na combo
            Produto p = cbProduto.getValue();

            //monta o que será exibido
            StringBuilder texto = new StringBuilder();
            texto.append("Produto Selecionado: ").append(p.getDescricao());
            texto.append("\nCodigo: ").append(String.valueOf(p.getCodigo()));
            texto.append("\nPreço: ").append(String.valueOf(p.getPreco()));
            
            alerta.setHeaderText(texto.toString());

            alerta.showAndWait(); //exibe a mensage
        }
    }
}

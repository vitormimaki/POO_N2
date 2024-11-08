/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author Aluno
 */
public class CaixaController implements Initializable {

    @FXML
    private ImageView img_fotoProd;
    @FXML
    private TextField txt_codBarra;
    @FXML
    private TextField txt_valorUnit;
    @FXML
    private TextField txt_qtd;
    @FXML
    private TextField txt_totalItem;
    @FXML
    private TextField txt_codItem;
    @FXML
    private TextField txt_subtotal;
    @FXML
    private TextField txt_totalRecebido;
    @FXML
    private TextField txt_troco;
    @FXML
    private TableView<?> tbv_lista;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> txt_codItem.requestFocus());
        
        txt_totalRecebido.setOnKeyTyped((KeyEvent evento) -> {
            if(!"0123456789".contains(evento.getCharacter())) {
                evento.consume();
            }
            if(evento.getCharacter().trim().length() == 0) { //Se excluiu
                txt_troco.setText("");
            } else {
                if (txt_subtotal.getText() != "") {
                    int recebido = Integer.parseInt(txt_totalRecebido.getText());
                    int subtotal = Integer.parseInt(txt_subtotal.getText());
                    if (recebido <= subtotal) {
                        recebido = subtotal;
                    }
                    txt_troco.setText(Integer.toString(recebido - subtotal));
                }
            }
        });
    }
    
}

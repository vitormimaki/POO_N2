/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Aluno
 */
public class CadastroPetsController implements Initializable {

    @FXML
    private DatePicker dtp_dataNasc;
    @FXML
    private ComboBox<?> cmb_cliente;
    @FXML
    private TextField txt_especie;
    @FXML
    private Button btn_excluirRegistro;
    @FXML
    private ImageView img_foto;
    @FXML
    private Button btn_salvarRegistro;
    @FXML
    private TextField txt_nome;
    @FXML
    private TextField txt_raca;
    @FXML
    private ComboBox<?> cmb_parametro;
    @FXML
    private TextField txt_filtra;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void mudarFoto(MouseEvent event) {
    }
    
}

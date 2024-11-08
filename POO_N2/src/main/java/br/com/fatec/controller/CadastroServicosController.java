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

/**
 * FXML Controller class
 *
 * @author Aluno
 */
public class CadastroServicosController implements Initializable {

    @FXML
    private TextField txt_cpfAgendar;
    @FXML
    private ComboBox<?> cmb_pet;
    @FXML
    private ImageView img_pet;
    @FXML
    private ComboBox<?> cmb_tipo;
    @FXML
    private DatePicker dtp_dataServico;
    @FXML
    private TextField txt_horaServico;
    @FXML
    private Button btn_agendar;
    @FXML
    private TextField txt_tipo;
    @FXML
    private TextField txt_desc;
    @FXML
    private ImageView img_foto;
    @FXML
    private TextField txt_duracao;
    @FXML
    private TextField txt_preco;
    @FXML
    private Button btn_salvar;
    @FXML
    private Button btn_excluir;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

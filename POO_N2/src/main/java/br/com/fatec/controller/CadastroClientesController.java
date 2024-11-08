/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec.controller;

import br.com.fatec.App;
import br.com.fatec.MaskFormatter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author usuario
 */
public class CadastroClientesController implements Initializable {

    @FXML
    private TextField txt_cep;
    @FXML
    private TextField txt_cpf;
    @FXML
    private TextField txt_num;
    @FXML
    private TextField txt_cidade;
    @FXML
    private TextField txt_endereco;
    @FXML
    private TextField txt_email;
    @FXML
    private TextField txt_nome;
    @FXML
    private TextField txt_uf;
    @FXML
    private TextField txt_bairro;
    @FXML
    private TextField txt_comp;
    @FXML
    private ImageView img_foto;
    @FXML
    private TextField txt_fone;
    @FXML
    private DatePicker dtp_dataNasc;
    @FXML
    private Button btn_excluir;
    @FXML
    private Button btn_salvar;
    @FXML
    private TextField txt_filtro;
    @FXML
    private ComboBox<?> cmb_parametro;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Platform.runLater(() -> txt_cpf.requestFocus());

        MaskFormatter cep = new MaskFormatter(txt_cep);
        cep.setMask(MaskFormatter.CEP);
        cep.showMask();
        MaskFormatter cpf = new MaskFormatter(txt_cpf);
        cpf.setMask(MaskFormatter.CPF);
        cpf.showMask();
        MaskFormatter fone = new MaskFormatter(txt_fone);
        fone.setMask(MaskFormatter.TEL_9DIG);
        fone.showMask();
        MaskFormatter data = new MaskFormatter(dtp_dataNasc);
        data.setMask(MaskFormatter.DATA_BARRA);
        data.showMask();  
    }
    

    @FXML
    private void selecionarFoto(MouseEvent event) {
    }

    @FXML
    private void salvarRegistro(ActionEvent event) {
    }
    
    @FXML
    private void excluirRegistro(ActionEvent event) {
    }

}

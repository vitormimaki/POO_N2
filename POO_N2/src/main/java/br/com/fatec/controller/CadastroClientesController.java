/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec.controller;

import br.com.fatec.Imagem;
import br.com.fatec.MaskFormatter;
import static br.com.fatec.persistencia.Banco.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

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
    public void selecionarFoto() {
        // Criando o FileChooser para permitir ao usuário selecionar uma imagem
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imagens", "*.png", "*.jpg", "*.jpeg", "*.gif"));

        // Obtendo o arquivo selecionado
        File file = fileChooser.showOpenDialog(img_foto.getScene().getWindow());

        if (file != null) {
            // Carregando a imagem selecionada na ImageView
            Image image = new Image(file.toURI().toString());
            img_foto.setImage(image);

            // Aqui você pode serializar e armazenar a imagem no banco de dados
            try (Connection conn = DriverManager.getConnection("jdbc:mariadb://" + servidor +
                     ":" + porta + "/" + bancoDados, usuario, senha)) {
                Imagem.serializarImagem(image, conn);
            } catch (SQLException | IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @FXML
    private void salvarRegistro(ActionEvent event) {

    }
    
    @FXML
    private void excluirRegistro(ActionEvent event) {
    }
    
}

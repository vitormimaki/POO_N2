/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec.controller;

import br.com.fatec.App;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Aluno
 */
public class LoginController implements Initializable {

    @FXML
    private PasswordField txt_senha;
    @FXML
    private TextField txt_login;
    @FXML
    private Button btn_entrar;
    @FXML
    private TextField txt_senhavisivel;
    @FXML
    private ImageView btn_ver;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void autenticarLogin(ActionEvent event) {
    }

    @FXML
    private void trocarVisibilidade(MouseEvent event) {
        if(txt_senha.isVisible()) {
            // Obtém o texto digitado no PasswordField
            String senha = txt_senha.getText();

            // Coloca esse texto em um TextField, para exibir o texto da senha
            carregarImagem(btn_ver, "visivel");
            txt_senhavisivel.setText(senha);
            txt_senha.setVisible(false);
            txt_senhavisivel.setVisible(true);
            txt_senhavisivel.requestFocus();
        } else {
            String senha = txt_senhavisivel.getText();

            carregarImagem(btn_ver, "oculto");
            txt_senha.setText(senha);
            txt_senhavisivel.setVisible(false);
            txt_senha.setVisible(true);
            txt_senha.requestFocus();
        }

    }
    
    private void carregarImagem(ImageView img, String foto) {
        // Use o caminho relativo à pasta resources
        Image imagem = App.carregarImagem("/br/com/fatec/bin/icones/" + foto + ".png");
        if (imagem != null) {
            img.setImage(imagem);
        } else {
            App.mensagem("Imagem não encontrada ou erro ao carregar.", 1);
        }
    }
    
}

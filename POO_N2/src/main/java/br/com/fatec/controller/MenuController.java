/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec.controller;

import br.com.fatec.App;
import com.mysql.cj.x.protobuf.MysqlxExpect;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author usuario
 */
public class MenuController implements Initializable {

    @FXML   
    private Button btn_caixa;
    @FXML
    private Button btn_login;
    @FXML
    private Button btn_cadastroClientes;
    @FXML
    private Button btn_cadastroPets;
    @FXML
    private Button btn_cadastroServicos;
    @FXML
    private Button btn_consultaAgendamentos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btn_cadastroClientes.setOnMouseEntered(event -> hoverIn());
        btn_cadastroClientes.setOnMouseExited(event -> hoverOut());
    }    

    @FXML
    private void abrirForm(ActionEvent event) {
        String[] split = event.getSource().toString().split("id=btn_");
        String[] tituloSplit = event.getSource().toString().split("'");

        String form = split[1]; //Formulário a abrir
        String titulo = tituloSplit[1]; //Titulo do Formulário

        split = form.split(",");
        form = split[0];
        
        String diretorio = "/br/com/fatec/view/";
        try {
            // Carrega o FXML do formulário secundário
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(diretorio + form + ".fxml"));
            Parent root = fxmlLoader.load();

            // Cria uma nova janela (Stage) para o formulário secundário
            Stage stage = new Stage();
            stage.setTitle(titulo);
            stage.setScene(new Scene(root));
            stage.show();

            // Fecha a janela atual
            //Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            //currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
     // Efeito quando o mouse entra no botão
    private void hoverIn() {
        DropShadow hoverEffect = new DropShadow();
        hoverEffect.setColor(Color.BLUE);
        hoverEffect.setRadius(10);
        hoverEffect.setOffsetX(5);
        hoverEffect.setOffsetY(5);
        btn_cadastroClientes.setEffect(hoverEffect);
    }

    // Efeito quando o mouse sai do botão
    private void hoverOut() {
        btn_cadastroClientes.setEffect(null);  // Remove o efeito
    }
    
}

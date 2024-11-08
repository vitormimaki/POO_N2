/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec.controller;

import br.com.fatec.MaskFormatter;
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

        MaskFormatter totalRecebido = new MaskFormatter(txt_totalRecebido);
        totalRecebido.setMask(MaskFormatter.REAL);
        txt_totalRecebido.setText("R$ 0,00");
        totalRecebido.showMask();

        MaskFormatter subtotalMask = new MaskFormatter(txt_subtotal);
        subtotalMask.setMask(MaskFormatter.REAL);
        subtotalMask.showMask();

        MaskFormatter troco = new MaskFormatter(txt_troco);
        troco.setMask(MaskFormatter.REAL);
        troco.showMask();

        txt_totalRecebido.setOnKeyReleased((KeyEvent evento) -> {
            totalRecebido.setMask(MaskFormatter.REAL);
            troco.setMask(MaskFormatter.REAL);

            if (!"0123456789".contains(evento.getCharacter())) {
                evento.consume();
            }

            
            String subtotalStr = MaskFormatter.tirarFormatacao(txt_subtotal, MaskFormatter.REAL);
            String recebidoStr = MaskFormatter.tirarFormatacao(txt_totalRecebido, MaskFormatter.REAL);

            // Validação para strings vazias
            if (!subtotalStr.isEmpty() && !recebidoStr.isEmpty()) {
                float subtotal = Float.parseFloat(subtotalStr);
                float recebido = Float.parseFloat(recebidoStr);

                if(recebido < subtotal) {
                    recebido = subtotal;
                }
                
                float diferenca = recebido - subtotal;
                String formattedDiferenca = String.format("R$ %.2f", diferenca).replace('.', ',');

                txt_troco.setText(formattedDiferenca);
            }
        });
    }
}
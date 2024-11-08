package br.com.fatec;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.util.converter.DefaultStringConverter;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    public String diretorio = "/br/com/fatec/view/";

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("view/menu"));
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void mascaraData(DatePicker datePicker){

        datePicker.getEditor().setOnKeyTyped((KeyEvent event) -> {
            if("0123456789".contains(event.getCharacter())==false){
                event.consume();
            }

            if(event.getCharacter().trim().length()==0){ // apagando
                if(datePicker.getEditor().getText().length()==3){
                    datePicker.getEditor().setText(datePicker.getEditor().getText().substring(0,2));
                    datePicker.getEditor().positionCaret(datePicker.getEditor().getText().length());
                }
                if(datePicker.getEditor().getText().length()==6){
                    datePicker.getEditor().setText(datePicker.getEditor().getText().substring(0,5));
                    datePicker.getEditor().positionCaret(datePicker.getEditor().getText().length());
                }

            }else{ // escrevendo

                if(datePicker.getEditor().getText().length()==10) event.consume();

                if(datePicker.getEditor().getText().length()==2){
                    datePicker.getEditor().setText(datePicker.getEditor().getText()+"/");
                    datePicker.getEditor().positionCaret(datePicker.getEditor().getText().length());
                }
                if(datePicker.getEditor().getText().length()==5){
                    datePicker.getEditor().setText(datePicker.getEditor().getText()+"/");
                    datePicker.getEditor().positionCaret(datePicker.getEditor().getText().length());
                }

            }
        });

        datePicker.getEditor().setOnKeyReleased((KeyEvent evt) -> {

            if(!datePicker.getEditor().getText().matches("\\d/*")){
                datePicker.getEditor().setText(datePicker.getEditor().getText().replaceAll("[^\\d/]", ""));
                datePicker.getEditor().positionCaret(datePicker.getEditor().getText().length());
            }
        });

    }
    public static void mascaraCEP(TextField textField){

        String val = "";

        textField.setOnKeyTyped((KeyEvent event) -> {
            if("0123456789".contains(event.getCharacter())==false){
                event.consume();
            }

            if(event.getCharacter().trim().length()==0){ // apagando

                if(textField.getText().length()==6){
                    textField.setText(textField.getText().substring(0,5));
                    textField.positionCaret(textField.getText().length());
                }

            }else{ // escrevendo

                if(textField.getText().length()==9) event.consume();

                if(textField.getText().length()==5){
                    textField.setText(textField.getText()+"-");
                    textField.positionCaret(textField.getText().length());
                }


            }
        });

        textField.setOnKeyReleased((KeyEvent evt) -> {

            if(!textField.getText().matches("\\d-*")){
                textField.setText(textField.getText().replaceAll("[^\\d-]", ""));
                textField.positionCaret(textField.getText().length());
            }
        });

    }
    
    /**
     * @param textField
     * Texto do FXML que será restringido
     * @param tamanho 
     * Tamanho Máximo do Campo
     */
    public static void tamanhoMaximo(TextField textField, int tamanho) {
        textField.setTextFormatter(new TextFormatter<>(new DefaultStringConverter(), "",
            change -> change.getControlNewText().length() <= tamanho ? change : null));
    }
    public static void tamanhoMaximo(DatePicker datepicker, int tamanho) {
        TextField editor = datepicker.getEditor();
        editor.setTextFormatter(new TextFormatter<>(new DefaultStringConverter(), "",
            change -> change.getControlNewText().length() <= tamanho ? change : null));
    }
    public static Image carregarImagem(String caminho) {
        try {
            Image imagem = new Image(App.class.getResourceAsStream(caminho));
            if (imagem.isError()) {
                mensagem("Erro ao carregar a imagem: " + caminho, 1);
                return null;
            }
            return imagem;
        } catch (NullPointerException e) {
            mensagem("Erro ao carregar a imagem: " + e.getMessage(), 1);
            return null;
        }
        
        /**
         *  private void carregarImagem() {
                // Use o caminho relativo à pasta resources
                Image imagem = App.carregarImagem("/br/com/fatec/bin/icones/exemplo.png");
                if (imagem != null) {
                    imageView.setImage(imagem);
                } else {
                    System.out.println("Imagem não encontrada ou erro ao carregar.");
                }
            }
         */
    }
    /**
     * @param msg
     * Mensagem que vai aparecer
     * @param alert
     * 
     * 0 -> Confirmation | 
     * 1 -> Error | 
     * 2 -> Information | 
     * 3 -> Warning | 
     * Default -> None | 
     */
    public static void mensagem(String msg, int alert) {
        AlertType tipo;
        switch(alert) {
            case 0:
                tipo = AlertType.CONFIRMATION;
                break;
            case 1:
                tipo = AlertType.ERROR;
                break;
            case 2:
                tipo = AlertType.INFORMATION;
                break;
            case 3:
                tipo = AlertType.WARNING;
                break;
            default:
                tipo = AlertType.NONE;
        }
        Alert alerta = new Alert(tipo);
        alerta.setTitle("Mensagem");
        alerta.setHeaderText(msg);
        alerta.setContentText("");
        
        alerta.showAndWait();
    }
}
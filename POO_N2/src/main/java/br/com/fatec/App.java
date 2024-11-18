package br.com.fatec;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Stage stage;
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

    public static void fechar() {
        stage.close();
    }
    
    public static void main(String[] args) {
        launch();
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
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec;

import static br.com.fatec.App.mensagem;
import javafx.scene.image.Image;
import java.io.*;
import java.sql.*;

/**
 *
 * @author Aluno
 */
public class Imagem {
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
   

    public static void serializarImagem(Object obj, Connection conn) throws SQLException, IOException {
        // Serializa o objeto para um array de bytes
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(obj);
        byte[] objBytes = byteArrayOutputStream.toByteArray();

        // Prepara a consulta SQL para armazenar o array de bytes no campo BLOB
        String sql = "INSERT INTO objetos_serializados (objeto_blob) VALUES (?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBytes(1, objBytes);
            stmt.executeUpdate();
        }
        /*Image imagem = Imagem.carregarImagem("/caminho/para/a/imagem.png");
        if (imagem != null) {
            try (Connection conn = DriverManager.getConnection("jdbc:seu_banco_de_dados")) {
                Imagem.serializarImagem(imagem, conn);
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }
        */
    }
    
    public static Object desserializarImagem(Connection conn, int id) throws SQLException, IOException, ClassNotFoundException {
        // Prepara a consulta SQL para ler o BLOB
        String sql = "SELECT objeto_blob FROM objetos_serializados WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Lê o BLOB do banco de dados
                byte[] objBytes = rs.getBytes("objeto_blob");

                // Desserializa o array de bytes para um objeto
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(objBytes);
                ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
                return objectInputStream.readObject();
            }
        }
        return null;
        /*
        try (Connection conn = DriverManager.getConnection("jdbc:seu_banco_de_dados")) {
            Image imagemRecuperada = Imagem.desserializarImagem(conn, 1); // Supondo que o ID seja 1
            if (imagemRecuperada != null) {
                // Exibir ou usar a imagem
            } else {
                System.out.println("Imagem não encontrada.");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        */
    }
}

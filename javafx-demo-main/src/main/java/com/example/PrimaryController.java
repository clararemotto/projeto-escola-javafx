package com.example;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class PrimaryController {

    @FXML TextField txtNome;
    @FXML ListView <Aluno> lista;

    ArrayList<Aluno> nomes = new ArrayList<>();
    
    @FXML
    private void adicionar(){
        
        String nome = txtNome.getText();
        var aluno = new Aluno(nome,"1J", 23012);
        
        nomes.add(aluno);
        nomes.sort((o1, o2) -> o1.nome().compareTo(o2.nome()));
        mostrarAlunos();
    }

    public void mostrarAlunos(){
        lista.getItems().clear();
        for(var nome : nomes){
            lista.getItems().add(nome);
        }
    }

    public void apagar(){
        var aluno = lista.getSelectionModel().getSelectedItem();
        nomes.remove(aluno);
        mostrarAlunos();
        
        Alert mensagem = new Alert(AlertType.INFORMATION);
        mensagem.setHeaderText("Sucesso");
        mensagem.setContentText("Aluno apagado com sucesso");
        mensagem.show();
    }
}


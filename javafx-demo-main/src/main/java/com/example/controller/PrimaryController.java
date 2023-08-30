package com.example.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import com.example.data.AlunoDao;
import com.example.model.Aluno;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class PrimaryController {

    @FXML TextField txtNome;
    @FXML ListView <Aluno> lista;

    ArrayList<Aluno> nomes = new ArrayList<>();
    private AlunoDao alunoDao = new AlunoDao();
    
    @FXML
    private void adicionar(){
        String nome = txtNome.getText();
        var aluno = new Aluno(nome,"1J", 23012);        
        try{
            alunoDao.inserir(aluno);         
            mostrarMensagem(AlertType.INFORMATION, "Sucesso", "Aluno inserido com sucesso"); 
        }catch(Exception erro){
            mostrarMensagem(AlertType.ERROR, "Erro", erro.getLocalizedMessage());
        }
        nomes.add(aluno);
        mostrarAlunos();
    }

    public void mostrarAlunos(){
        try {
            alunoDao.listarTodos().forEach(aluno -> lista.getItems().add(aluno));
        } catch (SQLException e) {
            mostrarMensagem(AlertType.ERROR, "Erro", "Erro ao buscar alunos." + e.getMessage() );
        }
    }

    public void apagar(){
        var aluno = lista.getSelectionModel().getSelectedItem();
        nomes.remove(aluno);
        mostrarAlunos();
        mostrarMensagem(AlertType.INFORMATION, "Sucesso", "Aluno apagado com sucesso");
    }

    private void mostrarMensagem(AlertType tipo, String titulo, String conteudo){
        Alert alertaErro = new Alert (tipo);
        alertaErro.setTitle(titulo);
        alertaErro.setContentText(conteudo);
        alertaErro.show();
    }
}


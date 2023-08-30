package com.example;

import java.sql.DriverManager;
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

    private static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    private static final String USER = "";
    private static final String PASS = "";
    
    @FXML
    private void adicionar(){
        
        String nome = txtNome.getText();
        var aluno = new Aluno(nome,"1J", 23012);

        try{
           
            var conexao = DriverManager.getConnection(URL, USER, PASS);
           
            var sql = "INSERT INTO TBL_ALUNOS_RM97898 (NOME, TURMA, RM) VALUES (?, ?, ?)";
            var comando = conexao.prepareStatement(sql);
            comando.setString(1, aluno.nome());
            comando.setString(2, aluno.turma());
            comando.setInt(3, aluno.rm());

            comando.executeUpdate();

            Alert alertaErro = new Alert (AlertType.INFORMATION);
            alertaErro.setTitle("Sucesso.");
            alertaErro.setContentText("Aluno inserido com sucesso");
            alertaErro.show();
            conexao.close();
        }catch(Exception erro){
            Alert alertaErro = new Alert (AlertType.ERROR);
            alertaErro.setTitle("Erro ao conectar.");
            alertaErro.setContentText(erro.getLocalizedMessage());
            alertaErro.show();
        }
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


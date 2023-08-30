package com.example.data;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Aluno;

public class AlunoDao {

    private static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    private static final String USER = "";
    private static final String PASS = "";
    
    public void inserir(Aluno aluno) throws SQLException {
        var conexao = DriverManager.getConnection(URL, USER, PASS);
           
            var sql = "INSERT INTO TBL_ALUNOS_RM97898 (NOME, TURMA, RM) VALUES (?, ?, ?)";
            var comando = conexao.prepareStatement(sql);
            comando.setString(1, aluno.nome());
            comando.setString(2, aluno.turma());
            comando.setInt(3, aluno.rm()); 
            comando.executeUpdate();
            conexao.close();
    }

    public List<Aluno> listarTodos() throws SQLException{
        //conectar com o bd
        var conexao = DriverManager.getConnection(URL, USER, PASS);
        var alunos = new ArrayList<Aluno>();

        //fazer o select
        var result = conexao
                        .createStatement()
                        .executeQuery("SELECT * FROM TBL_ALUNOS_RM97898");

        //para cada registro
        while(result.next()){
            //adicionar o aluno na lista
            alunos.add(new Aluno( 
                result.getString("nome"), 
                result.getString("turma"),
                result.getInt("rm") 
            ));
        }

        //retotnar a lista
        conexao.close();
        return alunos;
    }

}

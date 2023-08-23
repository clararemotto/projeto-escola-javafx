package com.example;

public record Aluno (String nome, String turma, Integer rm){
    
    @Override
    public String toString (){
        return rm + ": " + nome + " - " + turma;

    }
  
}

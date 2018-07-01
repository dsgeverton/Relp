package br.edu.iff.pooa.relp.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class MembroRepublica extends RealmObject{

    @PrimaryKey
    private int id;
    private int cpf;
    private int idade;
    private String nome;
    private String sobrenome;

    public MembroRepublica(){}

    public MembroRepublica (int id, int cpf, int idade, String nome, String sobrenome){
        this.id = id;
        this.cpf = cpf;
        this.idade = idade;
        this.nome = nome;
        this.sobrenome = sobrenome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }
}

package br.edu.iff.pooa.relp.model;


import io.realm.Realm;
import io.realm.RealmObject;

public class MembroRepublica extends RealmObject{

    private int id;
    private int idRepublica;
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

    public int getIdRepublica() {
        return idRepublica;
    }

    public void setIdRepublica(int idRepublica) {
        this.idRepublica = idRepublica;
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

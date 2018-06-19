package br.edu.iff.pooa.relp.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Republica extends RealmObject{

    @PrimaryKey
    private int id;
    private String nome;
    private String rua, bairro, cidade;
    private int numero;

    public Republica() {}

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getId() {

        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getRua() {
        return rua;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public int getNumero() {
        return numero;
    }

    public Republica(int id, String nome, String rua, String bairro, String cidade, int numero) {

        this.id = id;
        this.nome = nome;
        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
        this.numero = numero;
    }
}

package br.edu.iff.pooa.relp.model;

import io.realm.RealmObject;

public class Produto extends RealmObject{
    private int id;
    private int idDespesa;
    private float valor;
    private String nome;
    private int quantidade;

    public Produto(){}
    public Produto(int id, int idDespesa, float valor, String nome, int quantidade){
        this.id = id;
        this.idDespesa = idDespesa;
        this.valor = valor;
        this.nome = nome;
        this.quantidade = quantidade;
    }

    public int getId() {
        return id;
    }

    public int getIdDespesa() {
        return idDespesa;
    }

    public void setIdDespesa(int idDespesa) {
        this.idDespesa = idDespesa;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}

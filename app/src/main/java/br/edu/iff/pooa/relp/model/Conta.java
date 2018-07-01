package br.edu.iff.pooa.relp.model;

import java.util.Date;

import io.realm.RealmObject;

public class Conta extends RealmObject {

    private int id;
    private int idDespesa;
    private float valor;
    private String tipo;
    private Date dataVencimento;

    public Conta(){}
    public Conta(int id, int idDespesa, float valor, String tipo, Date dataVencimento ){
        this.id = id;
        this.idDespesa = idDespesa;
        this.valor = valor;
        this.tipo = tipo;
        this.dataVencimento = dataVencimento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdDespesa() {
        return idDespesa;
    }

    public void setIdDespesa(int idDespesa) {
        this.idDespesa = idDespesa;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }
}

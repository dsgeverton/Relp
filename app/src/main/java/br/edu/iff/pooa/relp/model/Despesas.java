package br.edu.iff.pooa.relp.model;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Despesas extends RealmObject {

    @PrimaryKey
    private int id;
    private String idRepublica;
    private float valorAluguel;
    private RealmList<Produto> listProdutos;
    private RealmList<Conta> contas;
    private Date dataVencimento;

    public Despesas(){}
    public Despesas(int id, String idRepublica, float valorAluguel, Date dataVencimento){
        this.id = id;
        this.idRepublica = idRepublica;
        this.valorAluguel = valorAluguel;
        this.listProdutos = listProdutos;
        this.contas = contas;
        this.dataVencimento = dataVencimento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdRepublica() {
        return idRepublica;
    }

    public void setIdRepublica(String idRepublica) {
        this.idRepublica = idRepublica;
    }

    public float getValorAluguel() {
        return valorAluguel;
    }

    public void setValorAluguel(float valorAluguel) {
        this.valorAluguel = valorAluguel;
    }

    public RealmList<Produto> getListProdutos() {
        return listProdutos;
    }

    public void setListProdutos(RealmList<Produto> listProdutos) {
        this.listProdutos = listProdutos;
    }

    public RealmList<Conta> getContas() {
        return contas;
    }

    public void setContas(RealmList<Conta> contas) {
        this.contas = contas;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }
}

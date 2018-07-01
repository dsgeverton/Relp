package br.edu.iff.pooa.relp.model;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Republica extends RealmObject{

    @PrimaryKey
    private String id;
    private String nome, administrador;
    private String rua, bairro, cidade, complemento;
    private int numero;
    private boolean isEnable;
    private Despesas despesa;
    private RealmList<MembroRepublica> membros;

    public Republica() {}

    public Republica(String id, String nome, String rua, String bairro, String cidade, String complemento, int numero, String administrador, boolean isEnable) {

        this.id = id;
        this.administrador = administrador;
        this.isEnable = isEnable;
        this.nome = nome;
        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
        this.complemento = complemento;
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getAdministrador() {
        return administrador;
    }

    public void setAdministrador(String administrador) {
        this.administrador = administrador;
    }

    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }

    public void setId(String id) {
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

    public String getId() {

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

    public Despesas getDespesa() {
        return despesa;
    }

    public void setDespesa(Despesas despesa) {
        this.despesa = despesa;
    }

    public RealmList<MembroRepublica> getMembros() {
        return membros;
    }

    public void setMembros(RealmList<MembroRepublica> membros) {
        this.membros = membros;
    }
}

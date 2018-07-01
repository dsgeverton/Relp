package br.edu.iff.pooa.relp.model;

import java.util.Date;
import java.util.List;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Despesas extends RealmObject {

    @PrimaryKey
    private int id;
    private int idRepublica;
    private float valorAluguel;
    private List<Produto> produtos;
    private List<Conta> contas;
    private Date dataVencimento;

}

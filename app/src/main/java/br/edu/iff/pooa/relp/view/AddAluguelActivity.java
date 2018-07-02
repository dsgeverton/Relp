package br.edu.iff.pooa.relp.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

import br.edu.iff.pooa.relp.R;
import br.edu.iff.pooa.relp.model.Despesas;
import br.edu.iff.pooa.relp.model.Republica;
import io.realm.Realm;

public class AddAluguelActivity extends AppCompatActivity {

    private ViewHolder mViewHolder = new ViewHolder();
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_aluguel);

        this.mViewHolder.valorAluguel = (EditText) findViewById(R.id.editTextValorAluguel);
        this.mViewHolder.salvarValor = (Button) findViewById(R.id.buttonSalvarValorAluguel);

        realm = Realm.getDefaultInstance();
        this.mViewHolder.salvarValor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mViewHolder.valorAluguel.getText().toString().equals("") || !mViewHolder.dataVenc.getText().toString().equals("")) {
                    addDespesa();
                    finish();
                }
            }
        });

    }

    public static class ViewHolder{
        EditText valorAluguel, dataVenc;
        Button salvarValor;
    }

    public void addDespesa(){
        Intent intent = getIntent();
        Despesas despesa = new Despesas();

        int token = 1;
        if(realm.where(Despesas.class).max("id") != null)
            token = realm.where(Despesas.class).max("id").intValue()+1;

        despesa.setId(token);
        despesa.setIdRepublica((String) intent.getSerializableExtra("idRepublica"));
        despesa.setValorAluguel(Float.parseFloat(mViewHolder.valorAluguel.getText().toString()));
////                    //despesa.setDataVencimento(Date.parse(mViewHolder.dataVenc.getText().toString()));
//                    despesa.setContas(null);
//                    despesa.setListProdutos(null);
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(despesa);
        realm.commitTransaction();
        realm.close();
    }

}

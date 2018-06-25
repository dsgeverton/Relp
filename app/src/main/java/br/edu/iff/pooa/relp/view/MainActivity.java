package br.edu.iff.pooa.relp.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import br.edu.iff.pooa.relp.R;
import br.edu.iff.pooa.relp.adapter.RepAdapter;
import br.edu.iff.pooa.relp.model.Republica;
import br.edu.iff.pooa.relp.view.DRepublica;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lista =  (ListView) findViewById(R.id.lvRepublicas);
        ArrayList<Republica> republicas = addRepublica();
        ArrayAdapter adapter = new RepAdapter(this, republicas);
        lista.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

    }

    private ArrayList<Republica> addRepublica(){

        ArrayList<Republica> republicas = new ArrayList<Republica>();
        Republica r = new Republica();

        r.setNome("República Teste");
        r.setRua("Rua teste");
        r.setNumero(55);
        r.setBairro("Centro");
        r.setCidade("Campos dos Goytacazes");
        r.setId(1);

        return republicas;
    }

}

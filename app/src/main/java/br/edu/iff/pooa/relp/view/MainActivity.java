package br.edu.iff.pooa.relp.view;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import br.edu.iff.pooa.relp.R;
import br.edu.iff.pooa.relp.adapter.ClickRecyclerViewListener;
import br.edu.iff.pooa.relp.adapter.RepAdapter;
import br.edu.iff.pooa.relp.model.Republica;
import br.edu.iff.pooa.relp.view.DRepublica;

public class MainActivity extends AppCompatActivity implements ClickRecyclerViewListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       Intent intent = new Intent(MainActivity.this, CadastrarRepublicaActivity.class);
                                       startActivity(intent);
                                   }
                               });

        ArrayList<Republica> republicas = addRepublica();

    }

    private ArrayList<Republica> addRepublica(){

        ArrayList<Republica> republicas = new ArrayList<>();
        Republica r = new Republica();

        r.setNome("República Teste");
        r.setRua("Rua teste");
        r.setNumero(55);
        r.setBairro("Centro");
        r.setCidade("Campos dos Goytacazes");
        r.setId(1);
        republicas.add(r);

        return republicas;
    }

    @Override
    public void onClick(Object object) {

    }

    protected void onResume() {
        super.onResume();
        ArrayList<Republica> republicas = addRepublica();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_Republica);

        recyclerView.setAdapter(new RepAdapter(this, republicas,this));
        RecyclerView.LayoutManager layout = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layout);
    }
}

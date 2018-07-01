package br.edu.iff.pooa.relp.view;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import br.edu.iff.pooa.relp.R;
import br.edu.iff.pooa.relp.adapter.ClickRecyclerViewListener;
import br.edu.iff.pooa.relp.adapter.RepAdapter;
import br.edu.iff.pooa.relp.model.Republica;
import br.edu.iff.pooa.relp.util.SessionApplication;
import io.realm.Realm;

public class MainActivity extends AppCompatActivity implements ClickRecyclerViewListener{

    private Realm realm;

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

    }

    private List<Republica> addRepublica(){

        SessionApplication SESSION = (SessionApplication)getApplicationContext();
        realm = Realm.getDefaultInstance();

        List<Republica> reps = realm.where(Republica.class).equalTo("administrador", SESSION.getUserLogged()).findAll();
        if (reps != null){ return reps;}

        realm.close();
        return reps;
    }

    @Override
    public void onClick(Object object) {
        Republica republica = (Republica) object;
        Intent intent = new Intent(MainActivity.this, GerenciadorActivity.class);
        intent.putExtra("id", republica.getId());
        startActivity(intent);

    }

    protected void onResume() {
        super.onResume();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_Republica);

        recyclerView.setAdapter(new RepAdapter(this, addRepublica(),this));
        RecyclerView.LayoutManager layout = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layout);
    }
}

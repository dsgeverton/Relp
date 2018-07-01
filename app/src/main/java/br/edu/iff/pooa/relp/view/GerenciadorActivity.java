package br.edu.iff.pooa.relp.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.edu.iff.pooa.relp.R;
import br.edu.iff.pooa.relp.adapter.ClickRecyclerViewListener;
import br.edu.iff.pooa.relp.adapter.RepAdapter;
import br.edu.iff.pooa.relp.adapter.StatusAdapter;
import br.edu.iff.pooa.relp.model.Republica;
import br.edu.iff.pooa.relp.util.SessionApplication;
import io.realm.Realm;

public class GerenciadorActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ClickRecyclerViewListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private Realm realm;
    private Republica republica;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciador);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.gerenciador, menu);
        this.mViewHolder.nomeRepublica = (TextView) findViewById(R.id.NomeRepublica);
        this.mViewHolder.idRepublica = (TextView) findViewById(R.id.IdRepublica);
        this.mViewHolder.logo_rep = findViewById(R.id.LogoRepHeader);

        this.mViewHolder.logo_rep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        // recuperar os dados da republica passada
        realm = Realm.getDefaultInstance();
        Intent intent = getIntent();
        String id = (String) intent.getSerializableExtra("id");
        republica = realm.where(Republica.class).equalTo("id", id).findFirst();
        realm.close();
        if (republica != null){
            mViewHolder.nomeRepublica.setText(republica.getNome());
            mViewHolder.idRepublica.setText(republica.getId());
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_excluir) {
            new AlertDialog.Builder(this).setTitle("Deletar República").
            setMessage("Tem certeza que deseja excluir esta república?").
            setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    realm = Realm.getDefaultInstance();
                    Intent intent = getIntent();
                    String id = (String) intent.getSerializableExtra("id");
                    republica = realm.where(Republica.class).equalTo("id", id).findFirst();
                    realm.beginTransaction();
                    republica.deleteFromRealm();
                    realm.commitTransaction();
                    realm.close();
                    finish();
                }
            }).setNegativeButton("Não", null).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    protected void onResume() {
        super.onResume();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_Status);

        recyclerView.setAdapter(new StatusAdapter(this, addRepublica(),this));
        RecyclerView.LayoutManager layout = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layout);
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
    }

    public static class ViewHolder{
        TextView nomeRepublica, idRepublica;
        ImageView logo_rep;
    }
}

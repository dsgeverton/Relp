package br.edu.iff.pooa.relp.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import br.edu.iff.pooa.relp.R;
import br.edu.iff.pooa.relp.model.Despesas;
import br.edu.iff.pooa.relp.model.Republica;
import io.realm.Realm;

public class GerenciadorActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private Realm realm;
    private Republica republica;
    private Intent intent;
    private String idRepublica;

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
                Snackbar.make(view, "Essa ação ainda não foi implementada!", Snackbar.LENGTH_LONG)
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
        this.mViewHolder.linearDespesas = (LinearLayout) findViewById(R.id.linearLayoutDespesas);
        this.mViewHolder.linearMembros = (LinearLayout) findViewById(R.id.linearLayoutMembros);
        this.mViewHolder.linearContas = (LinearLayout) findViewById(R.id.linearLayoutContas);
        this.mViewHolder.linearProdutos = (LinearLayout) findViewById(R.id.linearLayoutProdutos);
        this.mViewHolder.valorDespesas = (TextView) findViewById(R.id.textViewValorDespesas);
        this.mViewHolder.novoProduto = (TextView) findViewById(R.id.textViewNovoProduto);
        this.mViewHolder.relativeAddAluguel = (RelativeLayout) findViewById(R.id.relativeValorAluguel);
        this.mViewHolder.relativeNovoProduto = (RelativeLayout) findViewById(R.id.relativeNovoProduto);

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
        this.mViewHolder.nomeRepublica = findViewById(R.id.NomeRepublica);
        this.mViewHolder.idRepublica = findViewById(R.id.IdRepublica);
        this.mViewHolder.logo_rep = findViewById(R.id.LogoRepHeader);

        this.mViewHolder.logo_rep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        // recuperar os dados da republica passada
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
                    realm.beginTransaction();
                    republica.deleteFromRealm();
                    realm.commitTransaction();
                    finish();
                }
            }).setNegativeButton("Não", null).show();
            return true;
        }
        if (id == R.id.action_editarRepublica) {
            Intent alterscreen = new Intent(GerenciadorActivity.this, RepublicaDetalhesActivity.class);
            alterscreen.putExtra("idRepublica", republica.getId());
            startActivity(alterscreen);
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_addMembro) {
            mViewHolder.linearMembros.getLayoutParams().height = LinearLayout.LayoutParams.WRAP_CONTENT;
            mViewHolder.linearMembros.requestLayout();
            mViewHolder.linearMembros.setElevation(10);
        } else if (id == R.id.nav_aluguel) {
            Intent i = new Intent(getApplicationContext(), AddAluguelActivity.class);
            i.putExtra("idRepublica", republica.getId());
            startActivity(i);
//            mViewHolder.linearDespesas.getLayoutParams().height = LinearLayout.LayoutParams.WRAP_CONTENT;
//            mViewHolder.linearDespesas.requestLayout();
//            mViewHolder.linearDespesas.setElevation(10);

        } else if (id == R.id.nav_contas) {
            mViewHolder.linearContas.getLayoutParams().height = LinearLayout.LayoutParams.WRAP_CONTENT;
            mViewHolder.linearContas.requestLayout();
            mViewHolder.linearContas.setElevation(10);
        } else if (id == R.id.nav_produtos) {
            mViewHolder.linearProdutos.getLayoutParams().height = LinearLayout.LayoutParams.WRAP_CONTENT;
            mViewHolder.linearProdutos.requestLayout();
            mViewHolder.linearProdutos.setElevation(10);
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    protected void onResume() {
        super.onResume();

        intent = getIntent();
        idRepublica = (String) intent.getSerializableExtra("id");
        realm = Realm.getDefaultInstance();
        republica = realm.where(Republica.class).equalTo("id", idRepublica).findFirst();
        realm.close();


        this.mViewHolder.valorDespesas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AddAluguelActivity.class);
                startActivity(i);
            }
        });

        realm = Realm.getDefaultInstance();
        Despesas despesas = realm.where(Despesas.class).equalTo("idRepublica", republica.getId()).findFirst();
        realm.close();

        if (despesas == null){

            mViewHolder.linearDespesas.getLayoutParams().height = 1;
            mViewHolder.linearDespesas.requestLayout();
            mViewHolder.linearDespesas.setElevation(0);
            mViewHolder.valorDespesas.setClickable(false);
        } else{
            mViewHolder.valorDespesas.setText(String.format("R$ %.2f",despesas.getValorAluguel()));
        }

        if (republica.getMembros().isEmpty()){

            mViewHolder.linearMembros.getLayoutParams().height = 1;
            mViewHolder.linearMembros.requestLayout();
            mViewHolder.linearMembros.setElevation(0);
        }

        if (republica.getDespesa() == null){

            mViewHolder.linearContas.getLayoutParams().height = 1;
            mViewHolder.linearContas.requestLayout();
            mViewHolder.linearContas.setElevation(0);
        }

        if (republica.getDespesa() == null){

            mViewHolder.linearProdutos.getLayoutParams().height = 1;
            mViewHolder.linearProdutos.requestLayout();
            mViewHolder.linearProdutos.setElevation(0);
        }
    }

//    @Override
//    public void onClick(Object object) {
//        Republica republica = (Republica) object;
//    }

    public static class ViewHolder{
        TextView nomeRepublica, idRepublica;
        ImageView logo_rep;
        LinearLayout linearDespesas, linearMembros, linearContas, linearProdutos;
        RelativeLayout relativeAddAluguel, relativeNovoProduto;

        TextView valorDespesas, novoProduto;

    }
}

package br.edu.iff.pooa.relp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.List;
import br.edu.iff.pooa.relp.R;
import br.edu.iff.pooa.relp.adapter.ClickRecyclerViewListener;
import br.edu.iff.pooa.relp.adapter.RepAdapter;
import br.edu.iff.pooa.relp.model.Republica;
import br.edu.iff.pooa.relp.util.SessionApplication;
import io.realm.Realm;

public class MainActivity extends AppCompatActivity implements ClickRecyclerViewListener{

    private Realm realm;
    private GoogleSignInClient mGoogleSignInClient;
    GoogleSignInAccount mGoogleSignInAccount;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout botaoSair = findViewById(R.id.sairButton);

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       Intent intent = new Intent(MainActivity.this, CadastrarRepublicaActivity.class);
                                       startActivity(intent);
                                   }
                               });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        mGoogleSignInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if (mGoogleSignInAccount == null){
            botaoSair.setVisibility(View.INVISIBLE);
            botaoSair.setClickable(false);
        } else {
            botaoSair.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("teste----------", "TESTEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
                    signOut();
                }
            });
        }
    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        finish();
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
        RecyclerView recyclerView = findViewById(R.id.rv_Republica);

        recyclerView.setAdapter(new RepAdapter(this, addRepublica(),this));
        RecyclerView.LayoutManager layout = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layout);
    }
}

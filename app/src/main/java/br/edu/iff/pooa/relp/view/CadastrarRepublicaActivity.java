package br.edu.iff.pooa.relp.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import java.util.Random;
import br.edu.iff.pooa.relp.R;
import br.edu.iff.pooa.relp.model.Republica;
import br.edu.iff.pooa.relp.util.SessionApplication;
import io.realm.Realm;

public class CadastrarRepublicaActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewHolder mViewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_republica);

        this.mViewHolder.nomeRepublica = findViewById(R.id.editTextNomeRepublica);
        this.mViewHolder.rua = findViewById(R.id.editTextRua);
        this.mViewHolder.complemento = findViewById(R.id.editTextComplemento);
        this.mViewHolder.cidade = findViewById(R.id.editTextCidade);
        this.mViewHolder.numero = findViewById(R.id.editTextNumero);
        this.mViewHolder.bairro = findViewById(R.id.editTextBairro);
        this.mViewHolder.cadastrar = findViewById(R.id.buttonSalvarRepublica);
        this.mViewHolder.alert = findViewById(R.id.textViewAlert);
        this.mViewHolder.alert.setVisibility(View.INVISIBLE);
        this.mViewHolder.cadastrar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.buttonSalvarRepublica){
            String nomeRepublica = mViewHolder.nomeRepublica.getText().toString();
            String rua = mViewHolder.rua.getText().toString();
            String numero = mViewHolder.numero.getText().toString();
            String bairro = mViewHolder.bairro.getText().toString();
            String cidade = mViewHolder.cidade.getText().toString();
            String complemento = mViewHolder.complemento.getText().toString();

            String idRep = getRandomHexString();

            Realm realm = Realm.getDefaultInstance();

            Republica query = realm.where(Republica.class).equalTo("id", idRep).findFirst();

            while (query != null) {
                idRep = getRandomHexString();
                query = realm.where(Republica.class).equalTo("id", idRep).findFirst();
            }

            if ( nomeRepublica.equals("") || numero.equals("") || rua.equals("") || bairro.equals("") || cidade.equals("") || complemento.equals("") ) {
                Toast.makeText(getApplicationContext(), "Existem campos em branco!", Toast.LENGTH_SHORT).show();
            }
            else {
                query = realm.where(Republica.class).equalTo("nome", nomeRepublica).findFirst();
                if (query == null) {
                    SessionApplication SESSION = (SessionApplication)getApplicationContext();
                    Republica rep = new Republica();
                    rep.setId("#" + idRep);
                    rep.setNome(nomeRepublica);
                    rep.setRua(rua);
                    rep.setNumero(Integer.parseInt(numero));
                    rep.setBairro(bairro);
                    rep.setEnable(true);
                    // verificar se está com usuário do Google
                    GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
                    if (account != null)
                        rep.setAdministrador(account.getId());
                    else
                        rep.setAdministrador(SESSION.getUserLogged());
                    rep.setCidade(cidade);
                    rep.setComplemento(complemento);
                    realm.beginTransaction();
                    realm.copyToRealm(rep);
                    realm.commitTransaction();
                    Toast.makeText(getApplicationContext(), "República cadastrada!", Toast.LENGTH_SHORT).show();
                    finish();
                    realm.close();
                } else {
                    Toast.makeText(getApplicationContext(), "Nome da república já existe!", Toast.LENGTH_SHORT).show();
                }
            }

            realm.close();

        }
    }

    public static class ViewHolder{
        EditText nomeRepublica, rua, numero, bairro, complemento, cidade;
        Button cadastrar;
        TextView alert;
    }

    public String getRandomHexString(){
        int numchars = 6;
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        while(sb.length() < numchars){
            sb.append(Integer.toHexString(r.nextInt()));
        }
        return sb.toString().substring(0, numchars);
    }
}

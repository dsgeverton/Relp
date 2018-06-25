package br.edu.iff.pooa.relp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.iff.pooa.relp.R;
import br.edu.iff.pooa.relp.model.Usuario;
import io.realm.Realm;
import io.realm.RealmResults;

public class CadastroUserActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewHolder mViewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_user);

        this.mViewHolder.nome = (EditText) findViewById(R.id.editTextNome);
        this.mViewHolder.sobrenome = (EditText) findViewById(R.id.editTextSobrenome);
        this.mViewHolder.email = (EditText) findViewById(R.id.editTextEmail);
        this.mViewHolder.login = (EditText) findViewById(R.id.editTextLogin);
        this.mViewHolder.senha = (EditText) findViewById(R.id.editTextSenha);
        this.mViewHolder.csenha = (EditText) findViewById(R.id.editTextCSenha);
        this.mViewHolder.cadastrar = (Button) findViewById(R.id.buttonCadastrar);

        this.mViewHolder.cadastrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.buttonCadastrar){
            String nome = this.mViewHolder.nome.getText().toString(),
                   sobrenome = this.mViewHolder.sobrenome.getText().toString(),
                   email = this.mViewHolder.email.getText().toString(),
                   login = this.mViewHolder.login.getText().toString(),
                   senha = this.mViewHolder.senha.getText().toString(),
                   csenha = this.mViewHolder.csenha.getText().toString();
            if ( nome.equals("") || email.equals("") || sobrenome.equals("") || login.equals("") || senha.equals("") || csenha.equals("") ) {
                Toast.makeText(getApplicationContext(), "Existem campos em branco!", Toast.LENGTH_SHORT).show();
            }
            else {
                if (!senha.equals(csenha)){
                    Toast.makeText(getApplicationContext(), "Senhas não coincidem", Toast.LENGTH_SHORT).show();
                } else {
                    Realm realm = Realm.getDefaultInstance();
                    Usuario UsuarioCadastrado = realm.where(Usuario.class).equalTo("login", login).findFirst();
                    if (UsuarioCadastrado == null){
//                        Toast.makeText(getApplicationContext(), "Vazio!", Toast.LENGTH_SHORT).show();
                        Number currentIdNum = realm.where(Usuario.class).max("id");
                        int nextId;
                        if(currentIdNum == null) {
                            nextId = 1;
                        } else {
                            nextId = currentIdNum.intValue() + 1;
                        }
                        Usuario user = new Usuario();
                        user.setId(nextId);
                        user.setNome(nome);
                        user.setSobrenome(sobrenome);
                        user.setEmail(email);
                        user.setLogin(login);
                        user.setSenha(senha);
                        realm.beginTransaction();
                        realm.copyToRealm(user);
                        realm.commitTransaction();
                        Toast.makeText(getApplicationContext(), "Usuário cadastrado!", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getApplicationContext(), "Usuário já existe!", Toast.LENGTH_SHORT).show();
                    }
                    realm.close();
                }
            }
        }
    }

    public static class ViewHolder{
        EditText nome, sobrenome, senha, csenha, email, login;
        Button cadastrar;
    }
}

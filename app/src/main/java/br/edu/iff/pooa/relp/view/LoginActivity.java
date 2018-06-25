package br.edu.iff.pooa.relp.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.edu.iff.pooa.relp.R;
import br.edu.iff.pooa.relp.model.Usuario;
import io.realm.Realm;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewHolder mViewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.mViewHolder.btnEntrar = (Button) findViewById(R.id.buttonEntrar);
        this.mViewHolder.edtSenha = (EditText) findViewById(R.id.edtSenha);
        this.mViewHolder.edtLogin = (EditText) findViewById(R.id.edtLogin);
        this.mViewHolder.tvRegistrar = (TextView) findViewById(R.id.tvRegistrar);

        this.mViewHolder.btnEntrar.setOnClickListener(this);
        this.mViewHolder.tvRegistrar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.buttonEntrar){
            String login = this.mViewHolder.edtLogin.getText().toString();
            String senha = this.mViewHolder.edtSenha.getText().toString();

            if (login.equals("") || senha.equals("")) {
                Toast.makeText(getApplicationContext(), "Login ou Senha em branco", Toast.LENGTH_SHORT).show();
            } else {
                Realm realm = Realm.getDefaultInstance();
                Usuario user = realm.where(Usuario.class).equalTo("login", login).findFirst();
                if(user == null){
                    Toast.makeText(getApplicationContext(), "Login os Senha incorretos", Toast.LENGTH_SHORT).show();
                } else {
                    if (login.equals(user.getLogin()) && senha.equals(user.getSenha())) {
                        Intent intent = new Intent(this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Login os Senha incorretos", Toast.LENGTH_SHORT).show();
                        this.mViewHolder.edtSenha.setText("");
    //                    this.mViewHolder.edtSenha.setInputType(InputType.TYPE_CLASS_TEXT); // VISUALIZAR A SENHA
                    }
                }

                realm.close();
            }
        }

        if (id == R.id.tvRegistrar){
            Intent intent = new Intent(this, CadastroUserActivity.class);
            startActivity(intent);
        }
    }

    public static class ViewHolder{
        Button btnEntrar;
        EditText edtLogin, edtSenha;
        TextView tvRegistrar;
    }
}

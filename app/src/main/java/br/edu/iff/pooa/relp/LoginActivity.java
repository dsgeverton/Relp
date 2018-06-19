package br.edu.iff.pooa.relp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

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

            if (login.equals("admin") && senha.equals("admin")){
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
        }

        if (id == R.id.tvRegistrar){
            Toast.makeText(getApplicationContext(), "Você será redirecionado", Toast.LENGTH_SHORT).show();
        }
    }

    public static class ViewHolder{
        Button btnEntrar;
        EditText edtLogin, edtSenha;
        TextView tvRegistrar;
    }
}
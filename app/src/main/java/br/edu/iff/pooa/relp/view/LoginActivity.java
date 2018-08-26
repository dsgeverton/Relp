package br.edu.iff.pooa.relp.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import br.edu.iff.pooa.relp.R;
import br.edu.iff.pooa.relp.model.Usuario;
import br.edu.iff.pooa.relp.util.SessionApplication;
import io.realm.Realm;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewHolder mViewHolder = new ViewHolder();
    private GoogleSignInClient mGoogleSignInClient;
    private static final String TAG = "TESTE";
    private static final int RC_SIGN_IN = 9001;
    private boolean passwdVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.mViewHolder.btnEntrar = findViewById(R.id.buttonEntrar);
        this.mViewHolder.edtSenha = findViewById(R.id.edtSenha);
        this.mViewHolder.edtLogin = findViewById(R.id.edtLogin);
        this.mViewHolder.tvRegistrar = findViewById(R.id.tvRegistrar);
        this.mViewHolder.sign_in = findViewById(R.id.sign_in_button);
        this.mViewHolder.verSenha = findViewById(R.id.verSenha);

        this.mViewHolder.verSenha.setOnClickListener(this);
        this.mViewHolder.sign_in.setOnClickListener(this);
        this.mViewHolder.btnEntrar.setOnClickListener(this);
        this.mViewHolder.tvRegistrar.setOnClickListener(this);

        passwdVisible = false;

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        updateUI(account);
    }

    private void updateUI(GoogleSignInAccount account) {

        if (account != null){
            Log.i("GOOGLE SIGN IN ID:", account.getId());
            Log.i("GOOGLE SIGN IN NAME:", account.getDisplayName());
            Log.i("GOOGLE SIGN IN EMAIL:", account.getEmail());
            Log.i("GOOGLE SIGN IN PHOTO:", account.getPhotoUrl().toString());
            SessionApplication SESSION = (SessionApplication)getApplicationContext();
            SESSION.setUserLogged(account.getId());
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.buttonEntrar){
            String login = this.mViewHolder.edtLogin.getText().toString();
            login = login.trim();
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
                        SessionApplication SESSION = (SessionApplication)getApplicationContext();
                        SESSION.setUserLogged(login);
                    } else {
                        Toast.makeText(getApplicationContext(), "Login os Senha incorretos", Toast.LENGTH_SHORT).show();
                        this.mViewHolder.edtSenha.setText("");
                    }
                }

                realm.close();
            }
        }

        if (id == R.id.verSenha){
            if (passwdVisible) {
                this.mViewHolder.edtSenha.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD); // VISUALIZAR A SENHA
                this.mViewHolder.verSenha.setImageResource(R.drawable.ic_eye_on);
                passwdVisible = false;
            } else {
                this.mViewHolder.edtSenha.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD); // OCULTAR A SENHA
                this.mViewHolder.verSenha.setImageResource(R.drawable.ic_eye_off);
                passwdVisible = true;
            }
        }

        if (id == R.id.tvRegistrar){
            Intent intent = new Intent(this, CadastroUserActivity.class);
            startActivity(intent);
        }

        if (id == R.id.sign_in_button){
            signIn();
        }
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            updateUI(null);
        }
    }

    public static class ViewHolder{
        Button btnEntrar;
        EditText edtLogin, edtSenha;
        TextView tvRegistrar;
        SignInButton sign_in;
        ImageView verSenha;
    }
}

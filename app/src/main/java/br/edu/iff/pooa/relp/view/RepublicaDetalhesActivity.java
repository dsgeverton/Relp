package br.edu.iff.pooa.relp.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.InterruptedIOException;

import br.edu.iff.pooa.relp.R;
import br.edu.iff.pooa.relp.model.Republica;
import br.edu.iff.pooa.relp.util.SessionApplication;
import io.realm.Realm;

public class RepublicaDetalhesActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewHolder mViewHolder = new ViewHolder();
    private Realm realm;
    private Republica republica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_republica_detalhes);

        this.mViewHolder.nomeRepublica = findViewById(R.id.editTextNomeRepublicaEdit);
        this.mViewHolder.rua = findViewById(R.id.editTextRuaEdit);
        this.mViewHolder.complemento = findViewById(R.id.editTextComplementoEdit);
        this.mViewHolder.cidade = findViewById(R.id.editTextCidadeEdit);
        this.mViewHolder.numero = findViewById(R.id.editTextNumeroEdit);
        this.mViewHolder.bairro = findViewById(R.id.editTextBairroEdit);
        this.mViewHolder.cadastrar = findViewById(R.id.buttonEditarRepublica);
        this.mViewHolder.id = findViewById(R.id.detalhesID);
        this.mViewHolder.alert = findViewById(R.id.textViewAlertEdit);
        this.mViewHolder.habilitarEdicao = findViewById(R.id.switchHabilitarEdicao);

        this.mViewHolder.alert.setVisibility(View.VISIBLE);
        this.mViewHolder.cadastrar.setOnClickListener(this);


        povoate();

        this.mViewHolder.habilitarEdicao.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    mViewHolder.cadastrar.setEnabled(true);
                    mViewHolder.id.setEnabled(true);
                    mViewHolder.nomeRepublica.setEnabled(true);
                    mViewHolder.rua.setEnabled(true);
                    mViewHolder.numero.setEnabled(true);
                    mViewHolder.numero.setInputType(InputType.TYPE_CLASS_NUMBER);
                    mViewHolder.complemento.setEnabled(true);
                    mViewHolder.bairro.setEnabled(true);
                    mViewHolder.cidade.setEnabled(true);
                } else {
                    atualizar();
                    povoate();
                    mViewHolder.cadastrar.setEnabled(false);
                    mViewHolder.id.setEnabled(false);
                    mViewHolder.nomeRepublica.setEnabled(false);
                    mViewHolder.rua.setEnabled(false);
                    mViewHolder.numero.setEnabled(false);
                    mViewHolder.complemento.setEnabled(false);
                    mViewHolder.bairro.setEnabled(false);
                    mViewHolder.cidade.setEnabled(false);
                }
            }
        });

    }

    private void atualizar() {

        realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        republica.setNome(mViewHolder.nomeRepublica.getText().toString());
        republica.setRua(mViewHolder.rua.getText().toString());
        republica.setNumero(Integer.parseInt(mViewHolder.numero.getText().toString()));
        republica.setComplemento(mViewHolder.complemento.getText().toString());
        republica.setBairro(mViewHolder.bairro.getText().toString());
        republica.setCidade(mViewHolder.cidade.getText().toString());
        realm.copyToRealmOrUpdate(republica);
        realm.commitTransaction();
        realm.close();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.buttonEditarRepublica){
            atualizar();
            povoate();
            finish();
        }
    }

    public static class ViewHolder{
        EditText nomeRepublica, rua, numero, bairro, complemento, cidade;
        Button cadastrar;
        TextView alert, id;
        Switch habilitarEdicao;
    }

    public void povoate(){

        Intent intent = getIntent();
        realm = Realm.getDefaultInstance();
        republica = realm.where(Republica.class).equalTo("id", (String)intent.getSerializableExtra("idRepublica")).findFirst();
        realm.close();

        mViewHolder.id.setText(republica.getId());
        mViewHolder.nomeRepublica.setText(republica.getNome());
        mViewHolder.rua.setText(republica.getRua());
        mViewHolder.numero.setInputType(InputType.TYPE_CLASS_TEXT);
        mViewHolder.numero.setText(String.valueOf(republica.getNumero()));
        mViewHolder.complemento.setText(republica.getComplemento());
        mViewHolder.bairro.setText(republica.getBairro());
        mViewHolder.cidade.setText(republica.getCidade());
    }

}

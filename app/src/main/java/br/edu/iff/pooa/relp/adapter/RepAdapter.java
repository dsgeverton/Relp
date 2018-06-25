package br.edu.iff.pooa.relp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import br.edu.iff.pooa.relp.R;
import br.edu.iff.pooa.relp.model.Republica;

public class RepAdapter extends ArrayAdapter<Republica> {

    private ViewHolder mViewHolder = new ViewHolder();
    private final Context context;
    private final ArrayList<Republica> reps;


    public RepAdapter(@NonNull Context context, ArrayList<Republica> reps) {
        super(context, R.layout.card_republica, reps);
        this.context = context;
        this.reps = reps;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.card_republica, parent,false);

        mViewHolder.nomeRep = (TextView) rowView.findViewById(R.id.textViewNomeRepublica);
        mViewHolder.enderecoRep = (TextView) rowView.findViewById(R.id.textViewEndereco);
        mViewHolder.nomeAdminRep = (TextView) rowView.findViewById(R.id.textViewAdministrador);
        mViewHolder.imagemFundo = (ImageView) rowView.findViewById(R.id.imageViewFundoRepublica);

        mViewHolder.nomeRep.setText(reps.get(position).getNome());
        String enderecoCompleto = String.format("%s, N°. %d, %s", reps.get(position).getRua(),reps.get(position).getNumero(), reps.get(position).getBairro());
        mViewHolder.enderecoRep.setText(enderecoCompleto);
        mViewHolder.nomeAdminRep.setText(reps.get(position).getCidade());
        mViewHolder.imagemFundo.setImageResource(R.drawable.teste);
        return rowView;

    }

    public static class ViewHolder{

        TextView nomeRep, enderecoRep, nomeAdminRep;
        ImageView imagemFundo;
    }
}

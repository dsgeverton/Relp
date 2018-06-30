package br.edu.iff.pooa.relp.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.edu.iff.pooa.relp.R;
import br.edu.iff.pooa.relp.model.Republica;

public class RepAdapter extends RecyclerView.Adapter {

    private final Context context;
    private final List<Republica> republicas;
    private static ClickRecyclerViewListener clickRecyclerViewListener;


    public RepAdapter(@NonNull Context context, List<Republica> reps, ClickRecyclerViewListener clickRecyclerViewListener) {
        this.clickRecyclerViewListener = clickRecyclerViewListener;
        this.context = context;
        this.republicas = reps;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.card_republica, parent, false);
        RepublicaViewHolder republicaViewHolder = new RepublicaViewHolder(view);

        return republicaViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        RepublicaViewHolder viewHolder = (RepublicaViewHolder) holder;
        Republica republica = republicas.get(position);

        viewHolder.imagemFundo.setImageResource(R.drawable.teste);
        viewHolder.nomeRepublica.setText(republica.getNome());
        viewHolder.endereco.setText(republica.getRua()+", "+republica.getNumero()+", "+republica.getBairro());
        viewHolder.cidade.setText(republica.getCidade());
    }

    @Override
    public int getItemCount() {
        return republicas.size();
    }

    public class RepublicaViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imagemFundo;
        private final LinearLayout linearDetalhes;
        private final TextView nomeRepublica;
        private final TextView endereco;
        private final TextView cidade;

        public RepublicaViewHolder(View itemView) {
            super(itemView);
            imagemFundo = (ImageView) itemView.findViewById(R.id.imageViewFundoRepublica);
            linearDetalhes = (LinearLayout) itemView.findViewById(R.id.linearDetalhes);
            nomeRepublica = (TextView) itemView.findViewById(R.id.textViewNomeRepublica);
            endereco = (TextView) itemView.findViewById(R.id.textViewEndereco);
            cidade = (TextView) itemView.findViewById(R.id.textViewCidade);


            linearDetalhes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickRecyclerViewListener.onClick(republicas.get(getLayoutPosition()));
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                clickRecyclerViewListener.onClick(livros.get(getLayoutPosition()));
                }
            });
        }
    }

}

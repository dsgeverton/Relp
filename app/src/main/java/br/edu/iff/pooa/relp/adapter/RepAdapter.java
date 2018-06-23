package br.edu.iff.pooa.relp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import br.edu.iff.pooa.relp.R;
import br.edu.iff.pooa.relp.model.Republica;

public class RepAdapter extends ArrayAdapter<Republica> {

    private final Context context;
    private final ArrayList<Republica> reps;


    public RepAdapter(@NonNull Context context, ArrayList<Republica> rep) {
        super(context, R.layout.card_republica, rep);
        this.context = context;
        this.reps = rep;
    }


}

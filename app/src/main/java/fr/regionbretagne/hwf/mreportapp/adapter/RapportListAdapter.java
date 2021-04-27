package fr.regionbretagne.hwf.mreportapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import fr.regionbretagne.hwf.mreportapp.R;
import fr.regionbretagne.hwf.mreportapp.model.Rapport;

public class RapportListAdapter extends ArrayAdapter<Rapport> {

    public RapportListAdapter(@NonNull Context context, @NonNull List<Rapport> objects) {
        super(context,0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       Rapport rapport = getItem(position);
       if(convertView == null){
           convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_rapport, parent, false);
       }

        TextView tvRapportTitle = (TextView) convertView.findViewById(R.id.tvRapportTitle);
       tvRapportTitle.setText(rapport.getTitle());
        return convertView;
    }
}

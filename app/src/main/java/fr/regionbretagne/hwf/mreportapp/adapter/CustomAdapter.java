package fr.regionbretagne.hwf.mreportapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import fr.regionbretagne.hwf.mreportapp.R;
import fr.regionbretagne.hwf.mreportapp.model.Rapport;

public class CustomAdapter extends BaseAdapter {

    Context context;
    ArrayList<Rapport> arrayList;

    public CustomAdapter(Context context, ArrayList<Rapport> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView ==  null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_rapport, parent, false);
        }
        TextView tvRapportTitle;
        tvRapportTitle = (TextView) convertView.findViewById(R.id.tvRapportTitle);

        tvRapportTitle.setText(arrayList.get(position).getTitle());

        return convertView;
    }
}

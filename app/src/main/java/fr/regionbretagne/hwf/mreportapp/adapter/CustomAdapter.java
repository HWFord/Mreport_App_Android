package fr.regionbretagne.hwf.mreportapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
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
//        Rapport rapport = getItem(position);
        if (convertView ==  null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_rapport, parent, false);
        }
        TextView tvRapportTitle;
        LinearLayout llSecondRapportBlock;
        Button btnRapportAfficher;

        tvRapportTitle = (TextView) convertView.findViewById(R.id.tvRapportTitle);
        llSecondRapportBlock = (LinearLayout) convertView.findViewById(R.id.llSecondRapportBlock);
        btnRapportAfficher = (Button) convertView.findViewById(R.id.btnRapportAfficher);

        tvRapportTitle.setText(arrayList.get(position).getTitle());


        SharedPreferences preferences = context.getSharedPreferences("url",0);
        String url = preferences.getString("url", "");
        String reportId = arrayList.get(position).getId();

        btnRapportAfficher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                        //add condition for slash
                        Uri.parse(url + "mreport/" + reportId));
                context.startActivity(browserIntent);
            }
        });

//        if(position%2 ==0){
//            llSecondRapportBlock.setBackgroundColor(Color.parseColor("#ffffff"));
//        }

        return convertView;
    }
}

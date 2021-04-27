package fr.regionbretagne.hwf.mreportapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import fr.regionbretagne.hwf.mreportapp.R;
import fr.regionbretagne.hwf.mreportapp.Utils.Utils;
import fr.regionbretagne.hwf.mreportapp.adapter.RapportListAdapter;
import fr.regionbretagne.hwf.mreportapp.model.Rapport;

public class SecondActivity extends Activity {

    private static final String TAG = "RapportListTag";

    private ListView lvSecondReportList;
    private Button btnSecondConnexion;
    private Button btnSecondRapports;
    private TextView tvSecondUrl;
    private TextView tvSecondjson;

    private String url ="";

    private ArrayList<Rapport> rapportsArray = new ArrayList<>();

    private Intent intent;
    public static ArrayAdapter<Rapport> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        lvSecondReportList = findViewById(R.id.lvSecondReportList);
        btnSecondConnexion = findViewById(R.id.btnSecondConnexion);
        btnSecondRapports = findViewById(R.id.btnSecondRapports);
        tvSecondUrl = findViewById(R.id.tvSecondUrl);
        tvSecondjson = findViewById(R.id.tvSecondjson);

        String jsonFileString = Utils.getJsonFromAssets(getApplicationContext(), "jsonrapport.json");
        Log.d("RapportListTag", jsonFileString);

        Gson gson = new Gson();
        Type listRapportType = new TypeToken<List<Rapport>>() { }.getType();

        List<Rapport> rapports = gson.fromJson(jsonFileString, listRapportType);
        for(int i =0; i< rapports.size(); i++){
            Log.d("RapportListTag", "> Item " + i + "\n" + rapports.get(i));
        }





        adapter = new RapportListAdapter(this, rapportsArray);
        lvSecondReportList.setAdapter(adapter);

        SharedPreferences preferences = getSharedPreferences("url",0);
        url = preferences.getString("url", "");

        tvSecondUrl.setText(url);
//        Bundle bundle = getIntent().getExtras();
//        if(bundle != null){
//            String url = bundle.getString("url");
//        }

        intent = new Intent( SecondActivity.this, ThirdActivity.class);

        btnSecondConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openThirdActivity();
                //finish();
            }
        });
    }

    public void openThirdActivity(){
        startActivity(intent);
    }

//    public String loadJSONFromAsset() {
//        String json = null;
//        try {
//            InputStream is = getActivity().getAssets().open("yourfilename.json");
//            int size = is.available();
//            byte[] buffer = new byte[size];
//            is.read(buffer);
//            is.close();
//            json = new String(buffer, "UTF-8");
//        } catch (IOException ex) {
//            ex.printStackTrace();
//            return null;
//        }
//        return json;
//    }
}

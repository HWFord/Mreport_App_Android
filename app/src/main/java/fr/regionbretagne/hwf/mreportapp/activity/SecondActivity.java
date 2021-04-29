package fr.regionbretagne.hwf.mreportapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import fr.regionbretagne.hwf.mreportapp.R;
import fr.regionbretagne.hwf.mreportapp.adapter.CustomAdapter;
import fr.regionbretagne.hwf.mreportapp.model.Rapport;

public class SecondActivity extends Activity {

    private static final String TAG = "RapportListTag";

    private ListView lvSecondReportList;
    private Button btnSecondConnexion;
    private Button btnSecondRapports;

    private String url ="";

    private ArrayList<Rapport> arrayList ;

    private Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        lvSecondReportList = (ListView) findViewById(R.id.lvSecondReportList);
        btnSecondConnexion = findViewById(R.id.btnSecondConnexion);
        btnSecondRapports = findViewById(R.id.btnSecondRapports);

        arrayList = new ArrayList<>();

        try {
            JSONObject object = new JSONObject(readJSON());
            JSONArray array = object.getJSONArray("data");
            //JSONArray array = new JSONArray("data");
            Log.d("RapportListTag", array.toString() );
            for (int i = 0; i < array.length(); i++) {

                JSONObject jsonObject = array.getJSONObject(i);
                Log.d("RapportListTag", jsonObject.toString() );

                String report = jsonObject.getString("report");
                Log.d("RapportListTag", report );

                String title = jsonObject.getString("title");
                Log.d("RapportListTag", title );

                Rapport model = new Rapport();
                model.setId(report);
                model.setTitle(title);
                arrayList.add(model);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CustomAdapter adapter = new CustomAdapter(this, arrayList);
        lvSecondReportList.setAdapter(adapter);


        SharedPreferences preferences = getSharedPreferences("url",0);
        url = preferences.getString("url", "");

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

    public String readJSON() {
        String json = "";
        try {
            // Opening data.json file
            InputStream inputStream = getAssets().open("data.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            // read values in the byte array
            inputStream.read(buffer);
            inputStream.close();
            // convert byte to string
            json = new String(buffer, "UTF-8");
            Log.d("RapportListTag", json );
        } catch (IOException e) {
            e.printStackTrace();
            return json;
        }
        return json;
    }
}

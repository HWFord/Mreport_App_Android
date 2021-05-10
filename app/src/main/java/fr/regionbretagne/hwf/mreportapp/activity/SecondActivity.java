package fr.regionbretagne.hwf.mreportapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
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

        SharedPreferences preferencesJson = getSharedPreferences("response",0);
        String jsonStr = preferencesJson.getString("response", "");
        if(jsonStr!= null) {

            try {
                JSONObject object = new JSONObject(jsonStr);
                JSONArray array = object.getJSONArray("reports");

                for (int i = 0; i < array.length(); i++) {

                    JSONObject jsonObject = array.getJSONObject(i);

                    String report = jsonObject.getString("report");

                    String title = jsonObject.getString("title");

                    Rapport model = new Rapport();
                    model.setId(report);
                    model.setTitle(title);
                    arrayList.add(model);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
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
                finish();
            }
        });
    }

    public void openThirdActivity(){
        startActivity(intent);
    }

}

package fr.regionbretagne.hwf.mreportapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

import fr.regionbretagne.hwf.mreportapp.R;
import fr.regionbretagne.hwf.mreportapp.handler.HttpHandler;

public class MainActivity extends Activity {

    private EditText etMainUrl;
    private Button btnMainGetApi;

    private Intent intent2;
    private Intent intent3;

    ArrayList<HashMap<String, String>> reportList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etMainUrl = findViewById(R.id.etMainUrl);
        btnMainGetApi = findViewById(R.id.btnMainGetApi);

        reportList = new ArrayList<>();


        SharedPreferences pref = getApplicationContext().getSharedPreferences("url", 0);
        SharedPreferences.Editor editor = pref.edit();

        intent2 = new Intent( MainActivity.this, SecondActivity.class);
        intent3 = new Intent( MainActivity.this, ThirdActivity.class);

        etMainUrl.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s.toString().isEmpty()){
                    btnMainGetApi.setEnabled(false);
                    btnMainGetApi.setBackgroundColor(Color.parseColor("#bbbbbb"));

                }else{
                    btnMainGetApi.setEnabled(true);
                    btnMainGetApi.setBackgroundColor(Color.parseColor("#006878"));
                    btnMainGetApi.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            editor.putString("url", etMainUrl.getText().toString());
                            editor.putString("urlApiReport", (etMainUrl.getText().toString()+"/api/report/"));
                            editor.apply();

                            new GetReports().execute();

                            SharedPreferences preferencesJson = getSharedPreferences("response",0);
                            String jsonStr = preferencesJson.getString("response", "");
                            if(jsonStr!= null) {
                                openSecondActivity();
                            }else{
                                openThirdActivity();
                            }

                            finish();
                        }
                    });
                }
            }
        });
     }

    public void openSecondActivity(){
        startActivity(intent2);
    }

    public void openThirdActivity(){
        startActivity(intent3);
    }

    private class GetReports extends AsyncTask<Void, Void, Void>{
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            Toast.makeText(MainActivity.this,"Récuperation des données",Toast.LENGTH_LONG).show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            SharedPreferences preferences = getSharedPreferences("urlApiReport", 0);
            String url = preferences.getString("urlApiReport", "");
            Log.d("RapportListTag", url );

            String jsonStr = sh.makeServiceCall(url);

            SharedPreferences pref = getApplicationContext().getSharedPreferences("response", 0);
            SharedPreferences.Editor editor = pref.edit();

            if (jsonStr != null) {
                editor.putString("response", jsonStr);
                editor.apply();
            } else {
                Log.e("RapportListTag", "Impossible de récuperer les données du serveur");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Impossible de récuperer les données du serveur. Vérifier votre URL.",
                                Toast.LENGTH_LONG).show();
                        intent3.putExtra("erreur",  true);
                    }
                });
            }
            return null;
        }

    }

}

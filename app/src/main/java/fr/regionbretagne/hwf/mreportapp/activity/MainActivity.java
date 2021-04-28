package fr.regionbretagne.hwf.mreportapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

import fr.regionbretagne.hwf.mreportapp.R;

public class MainActivity extends Activity {


    private EditText etMainUrl;
    private Button btnMainGetApi;
    private Button btnSecondConnexion;
    private Button btnSecondRapports;

    private Intent intent2;
    private Intent intent3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etMainUrl = findViewById(R.id.etMainUrl);
        btnMainGetApi = findViewById(R.id.btnMainGetApi);
        btnSecondConnexion = findViewById(R.id.btnSecondConnexion);
        btnSecondRapports = findViewById(R.id.btnSecondRapports);

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
                            editor.commit();
                            openThirdActivity();
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
}

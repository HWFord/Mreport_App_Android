package fr.regionbretagne.hwf.mreportapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import fr.regionbretagne.hwf.mreportapp.R;

public class ThirdActivity extends Activity {

    private Button btnThirdStartMain;
    private Button btnThirdRapports;
    private TextView tvThirdUrl;

    private Intent intentMain;
    private Intent intentSecond;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        btnThirdStartMain = findViewById(R.id.btnThirdStartMain);
        btnThirdRapports = findViewById(R.id.btnThirdRapports);
        tvThirdUrl = findViewById(R.id.tvThirdUrl);

        intentMain = new Intent( ThirdActivity.this, MainActivity.class);

        SharedPreferences preferences = getSharedPreferences("url",0);
        tvThirdUrl.setText(preferences.getString("url", ""));

        btnThirdStartMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intentMain);
                finish();
            }
        });

        intentSecond = new Intent( ThirdActivity.this, SecondActivity.class);
        btnThirdRapports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intentSecond);
            }
        });
    }
}

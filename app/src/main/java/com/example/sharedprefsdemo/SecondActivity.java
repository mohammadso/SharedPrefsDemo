package com.example.sharedprefsdemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private TextView tvName, tvProfession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        tvName = findViewById(R.id.tvName);
        tvProfession = findViewById(R.id.tvProfession);
    }

    public void loadAccountData(View view) {

        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName() + Constants.PREF_FILE_NAME, Context.MODE_PRIVATE);
        String name = sharedPreferences.getString(Constants.KEY_NAME, "N/A");
        String profession = sharedPreferences.getString(Constants.KEY_PROFESSION, "N/A");
        int profId = sharedPreferences.getInt(Constants.KEY_PROF_ID, 0);
        String string = profession + " " + profId;

        tvName.setText(name);
        tvProfession.setText(string);
    }

    public void clearAccountData(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName() + Constants.PREF_FILE_NAME ,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    public void removeProfessionKey(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName() + Constants.PREF_FILE_NAME ,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(Constants.KEY_PROFESSION);
        editor.apply();
    }
}

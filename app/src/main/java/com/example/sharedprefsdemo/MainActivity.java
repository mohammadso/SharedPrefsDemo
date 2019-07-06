package com.example.sharedprefsdemo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText etName, etProfession;
    private TextView tvName, tvProfession;
    private Switch pageColorSwitch;
    private ConstraintLayout pageLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etProfession = findViewById(R.id.etprofession);

        tvName = findViewById(R.id.tvName);
        tvProfession = findViewById(R.id.tvProfession);

        pageColorSwitch = findViewById(R.id.pageColorSwitch);

        pageLayout = findViewById(R.id.pageLayout);

        pageColorSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(Constants.KEY_GREEN, isChecked);
                editor.apply();

                pageLayout.setBackgroundColor(isChecked? Color.GREEN : Color.WHITE);
            }
        });

        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        Boolean isChecked = sharedPreferences.getBoolean(Constants.KEY_GREEN, false);
        pageColorSwitch.setChecked(isChecked);
    }

    public void saveAccountData(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName() + Constants.PREF_FILE_NAME ,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        String name = etName.getText().toString();
        String profession = etProfession.getText().toString();

        editor.putString(Constants.KEY_NAME, name);
        editor.putString(Constants.KEY_PROFESSION, profession);
        editor.putInt(Constants.KEY_PROF_ID, 423);

        editor.apply(); // editor.commit();
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

    public void openSecondActivity(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
}

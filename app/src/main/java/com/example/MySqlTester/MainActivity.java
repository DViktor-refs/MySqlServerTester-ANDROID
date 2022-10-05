package com.example.MySqlTester;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.example.MySqlTester.fragments.CreateTableFragment;
import com.example.MySqlTester.fragments.DropTableFragment;
import com.example.MySqlTester.fragments.HomeFragment;
import com.example.MySqlTester.fragments.InsertIntoFragment;
import com.example.MySqlTester.fragments.SelectFragment;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);

        resetSharedPrefs();
        setSpinner();

        spinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String s = parent.getItemAtPosition(position).toString();

                        switch (s) {
                            case "Test server" :
                                setGuiIfTestServerSelected();
                                break;

                            case "Create Table" :
                                setGuiIfCreateTableSelected();
                                break;

                            case "Drop Table" :
                                setGuiIfDropTableSelected();
                                break;
                            case "Select From" :
                                setGuiIfSelectSelected();
                                break;
                            case "Insert Into" :
                                setGuiIfInsertIntoSelected();
                                break;
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) { }
                });
    }

    private void setSpinner() {
        spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.mainmenuspinneritems, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
    }

    private void resetSharedPrefs() {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove("host");
        editor.remove("port");
        editor.remove("dbname");
        editor.remove("user");
        editor.remove("pass");
        editor.remove("connectionStatus");
        editor.apply();
    }


    @Override
    protected void onStop() {
        resetSharedPrefs();
        super.onStop();
    }

    private void setGuiIfTestServerSelected() {
        getSupportFragmentManager().beginTransaction().replace(R.id.Frame, new HomeFragment()).commit();

    }

    private void setGuiIfCreateTableSelected() {
        getSupportFragmentManager().beginTransaction().replace(R.id.Frame, new CreateTableFragment()).commit();
    }

    private void setGuiIfDropTableSelected() {
        getSupportFragmentManager().beginTransaction().replace(R.id.Frame, new DropTableFragment()).commit();
    }

    private void setGuiIfSelectSelected() {
        getSupportFragmentManager().beginTransaction().replace(R.id.Frame, new SelectFragment()).commit();
    }

    private void setGuiIfInsertIntoSelected() {
        getSupportFragmentManager().beginTransaction().replace(R.id.Frame, new InsertIntoFragment()).commit();
    }

}





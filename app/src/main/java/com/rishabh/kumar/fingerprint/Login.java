package com.rishabh.kumar.fingerprint;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText name,url,password;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        myDb = new DatabaseHelper(this);
        name = (EditText)findViewById(R.id.name);
        url = (EditText)findViewById(R.id.url);
        password = (EditText)findViewById(R.id.password);
        add = (Button)findViewById(R.id.add);
        AddData();
    }
    public void AddData()
    {
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText().toString().equals("")||url.getText().toString().equals("")||password.getText().toString().equals("")) {
                    Snackbar.make(view, "Please check whether any field is empty", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                }
                else {
                    boolean isInserted = myDb.insertData(name.getText().toString(), url.getText().toString(), password.getText().toString());
                    if (isInserted == true) {
                        Snackbar.make(view, "Entry recorded", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                    } else {
                        Snackbar.make(view, "Error cannot record your entry", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                    }
                }
            }
        });
    }

}

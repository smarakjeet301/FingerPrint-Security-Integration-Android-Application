package com.rishabh.kumar.fingerprint;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Update extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText name,url,password,id;
    Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        myDb = new DatabaseHelper(this);
        update = (Button) findViewById(R.id.update);
        name = (EditText)findViewById(R.id.name);
        id = (EditText) findViewById(R.id.id);
        url = (EditText)findViewById(R.id.url);
        password = (EditText)findViewById(R.id.password);
        UpdateData();
    }
    public void UpdateData()
    {
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (id.getText().toString().equals("")||name.getText().toString().equals("")||url.getText().toString().equals("")||password.getText().toString().equals("")) {
                    Snackbar.make(view, "Please check whether any field is empty", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                }
                else {
                    boolean isUpdate = myDb.updateData(id.getText().toString(), name.getText().toString(), url.getText().toString(), password.getText().toString());
                    if (isUpdate == true) {
                        Snackbar.make(view, "Entry Updated", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                    } else {
                        Snackbar.make(view, "Entry couldn't be updated", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                    }
                }
            }
        });
    }
}

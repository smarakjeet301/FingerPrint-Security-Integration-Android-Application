package com.rishabh.kumar.fingerprint;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NameActivity extends AppCompatActivity {
    EditText name ;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);
        name = (EditText)findViewById(R.id.textInputEditText);
        next = (Button)findViewById(R.id.button);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save(view);
                Intent i= new Intent(NameActivity.this,MainActivity2.class);
                startActivity(i);
            }
        });

    }

    public void save(View view)
    {
        SharedPreferences sharedPreferences = getSharedPreferences("name", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name",name.getText().toString());
        editor.apply();
    }
    public void showData(View view)
    {
        SharedPreferences sharedPreferences = getSharedPreferences("name", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("name","");
    }
}

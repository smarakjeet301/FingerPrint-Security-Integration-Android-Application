package com.rishabh.kumar.fingerprint;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity2 extends NameActivity {
    NameActivity nameActivity;
    TextView user_name;
    Button add_btn,show,update,delete;
    DatabaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        add_btn = (Button)findViewById(R.id.add);
        user_name = (TextView)findViewById(R.id.user_name);
        show = (Button)findViewById(R.id.show);
        update = (Button)findViewById(R.id.update);
        delete = (Button)findViewById(R.id.delete);
        user_name.setText(name.getText());
        DeleteData();
        viewAll();
        myDb = new DatabaseHelper(this);
        //go to update activity
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(MainActivity2.this,Update.class);
                startActivity(i);
            }
        });
        //goto login activity
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(MainActivity2.this,Login.class);
                startActivity(i);
            }
        });}
public void viewAll()
    {
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = myDb.getAllData();
                if (res.getCount() == 0)
                {
                    showMessage("Error", "nothing found!!");
                    return;

                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext())
                {
                    buffer.append("Id :"+ res.getString(0)+"\n");
                    buffer.append("website name :"+ res.getString(1)+"\n");
                    buffer.append("Username :"+ res.getString(2)+"\n");
                    buffer.append("Password :"+ res.getString(3)+"\n\n");
                }
                showMessage("Data", buffer.toString());
            }
        });
    }
    public void showMessage(String title, String message)
    {
        AlertDialog.Builder builder = new   AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void DeleteData()
    {
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity2.this, R.style.MyDialogTheme);
                alert.setTitle("Delete");
                alert.setMessage("Please Enter the ID of the entry you want to delete");
                // Set an EditText view to get user input
                final EditText input = new EditText(MainActivity2.this);
                alert.setView(input);
                final AlertDialog.Builder builder = alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        Editable value = input.getText();
                        // Do something with value!
                        Integer deleteRows = myDb.deleteData(value.toString());
                        if (deleteRows > 0) {
                            Snackbar.make(view, "Entry Deleted", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                        }
                    }
                });

                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Canceled.
                    }
                });

                alert.show();
            }
        });
    }
}

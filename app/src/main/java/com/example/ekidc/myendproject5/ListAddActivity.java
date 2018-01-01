package com.example.ekidc.myendproject5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ListAddActivity extends AppCompatActivity {
    private EditText editMyDate, editMyPosition, editMyTime;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listadd);

        editMyDate = (EditText) findViewById(R.id.editMyDate);
        editMyTime = (EditText) findViewById(R.id.editMyTime);
        editMyPosition = (EditText) findViewById(R.id.editMyPosition);

        editMyDate.setText("123");
        editMyPosition.setText("456");
        editMyTime.setText("789");

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            editMyDate.setText(bundle.getString("Date"));
            editMyPosition.setText(bundle.getString("Position"));
            editMyTime.setText(bundle.getString("Time"));
        }

        btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.putExtra("Date", editMyDate.getText().toString());
                i.putExtra("Position", editMyPosition.getText().toString());
                i.putExtra("Time", editMyTime.getText().toString());
                ListAddActivity.this.setResult(0, i);
                ListAddActivity.this.finish();

            }
        });
    }
}


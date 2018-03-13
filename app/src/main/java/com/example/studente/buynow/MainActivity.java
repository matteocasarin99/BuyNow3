package com.example.studente.buynow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button accedi=(Button) findViewById(R.id.accedi);
        EditText = findViewById(R.id.)
        accedi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(MainActivity.this,Accedi_Act.class);
                startActivity(i);
            }
        });
        Button registrati=(Button) findViewById(R.id.btnReg);
        registrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2=new Intent(MainActivity.this,Reg_Act.class);
                startActivity(i2);
            }
        });
    }
}

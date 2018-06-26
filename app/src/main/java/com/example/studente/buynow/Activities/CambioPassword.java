package com.example.studente.buynow.Activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.studente.buynow.R;
import com.example.studente.buynow.Utils.Utenti_Password;

public class CambioPassword extends AppCompatActivity {
    public Utenti_Password ut;
    private int idUt;
    private boolean controlloOLD=false;
    private boolean controlloPass=false;
    String password, tipo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cambio_password);
        ut=(Utenti_Password) getIntent().getExtras().getSerializable("Utenti");
        idUt=(Integer)getIntent().getExtras().getSerializable("id");
        password=(String)getIntent().getExtras().getSerializable("pass");
        tipo = (String) getIntent().getExtras().getSerializable("tipo");
        final EditText textOld=findViewById(R.id.textOldPass);
        final EditText textNew=findViewById(R.id.textNewPass);
        final EditText textNew2=findViewById(R.id.textNewPass2);
        Button cambia=findViewById(R.id.buttonCambia);
        textOld.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(textOld.getText().toString().compareTo("")==0){
                    textOld.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.darker_gray), PorterDuff.Mode.SRC_ATOP);
                }else{
                    if(textOld.getText().toString().compareTo(password)==0){
                        textOld.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_green_light), PorterDuff.Mode.SRC_ATOP);
                        controlloOLD=true;
                    }else{
                        if(textOld.getText().toString().compareTo(password)!=0){
                            textOld.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_red_dark), PorterDuff.Mode.SRC_ATOP);
                        }
                    }
                }
            }
        });
        textNew.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(textNew.getText().toString().compareTo("")==0 && textNew2.getText().toString().compareTo("")==0){
                    textNew.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.darker_gray), PorterDuff.Mode.SRC_ATOP);
                    textNew2.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.darker_gray), PorterDuff.Mode.SRC_ATOP);
                    }else{
                    if(textNew.getText().toString().compareTo(textNew2.getText().toString())==0){
                        textNew.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_green_light), PorterDuff.Mode.SRC_ATOP);
                        textNew2.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_green_light), PorterDuff.Mode.SRC_ATOP);
                        controlloPass=true;
                    }else{
                        if(textNew.getText().toString().compareTo(textNew2.getText().toString())!=0){
                            textNew2.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_red_dark), PorterDuff.Mode.SRC_ATOP);
                            textNew.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_red_dark), PorterDuff.Mode.SRC_ATOP);
                        }
                    }
                }
                textNew2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        if(textNew2.getText().toString().compareTo("")==0 && textNew.getText().toString().compareTo("")==0){
                            textNew.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.darker_gray), PorterDuff.Mode.SRC_ATOP);
                            textNew2.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.darker_gray), PorterDuff.Mode.SRC_ATOP);
                        }else{
                            if(textNew2.getText().toString().compareTo(textNew.getText().toString())==0){
                                textNew.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_green_light), PorterDuff.Mode.SRC_ATOP);
                                textNew2.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_green_light), PorterDuff.Mode.SRC_ATOP);
                                controlloPass=true;
                            }else{
                                if(textNew2.getText().toString().compareTo(textNew.getText().toString())!=0){
                                    textNew2.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_red_dark), PorterDuff.Mode.SRC_ATOP);
                                    textNew.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_red_dark), PorterDuff.Mode.SRC_ATOP);
                                }
                            }
                        }
                    }
                });
            }
        });
        cambia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean b;
                if(controlloPass==true && controlloOLD==true){
                    b = ut.cambio_Password(textNew.getText().toString(), idUt, tipo);
                    if(b){
                        Context context = getApplicationContext();
                        CharSequence text = "Password Cambiata.\nEffettua il login con la nuova password";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }else{
                        Context context = getApplicationContext();
                        CharSequence text = "Errore Riprova";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent i=new Intent(CambioPassword.this,MainActivity.class);
                            i.putExtra("Utenti",ut);
                            startActivity(i);
                            finish();
                        }
                    }, 1500);

                }else{
                    Context context = getApplicationContext();
                    CharSequence text = "CONTROLLA I CAMPI DELLE PASSWORD";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });


    }
}

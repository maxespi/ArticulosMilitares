package com.example.articulosmilitares;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class IntroActivity extends AppCompatActivity implements View.OnClickListener {

    private Button gologin, goregister, gorecovery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gologin = findViewById(R.id.gologin);
        goregister = findViewById(R.id.goregister);
        gorecovery = findViewById(R.id.gorecovery);

        gologin.setOnClickListener(this);
        goregister.setOnClickListener(this);
        gorecovery.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.gologin:
                startActivity(new Intent(IntroActivity.this, LoginActivity.class));
                Toast.makeText(this, "Login", Toast.LENGTH_SHORT).show();
                break;
            case R.id.goregister:
                startActivity(new Intent(IntroActivity.this, RegisterActivity.class));
                Toast.makeText(this, "Registro", Toast.LENGTH_SHORT).show();
                break;
            case R.id.gorecovery:
                startActivity(new Intent(IntroActivity.this, RecoveryActivity.class));
                Toast.makeText(this, "Recuperar", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}

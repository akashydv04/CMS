package com.example.cms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button addEmployee, seeAllEmployee, checkTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        seeAllEmployee = findViewById(R.id.see_all_employee);
        checkTransaction = findViewById(R.id.check_transaction);

        seeAllEmployee.setOnClickListener(this);
        checkTransaction.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.see_all_employee){
            Intent intent = new Intent(this, SeeAllEmployee.class);
            startActivity(intent);
        }

        if (view.getId() == R.id.check_transaction){
            Intent intent = new Intent(this, CheckTransaction.class);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
        super.onBackPressed();
    }
}
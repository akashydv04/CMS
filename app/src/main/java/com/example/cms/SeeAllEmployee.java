package com.example.cms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.cms.adapter.EmployeeAdapter;
import com.example.cms.database.DatabaseHelper;
import com.example.cms.model.EmployeeModel;

import java.util.ArrayList;
import java.util.List;

public class SeeAllEmployee extends AppCompatActivity {

    ListView employeeLists;
    DatabaseHelper dbHelper;
    ArrayList<EmployeeModel> list;
    EmployeeAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_all_employee);
        employeeLists = findViewById(R.id.employee_list);

        dbHelper = new DatabaseHelper(this);

        dbHelper.insertData("1","Akash Yadav", "akashydv04@gmail.com","8295018966","4000");
        dbHelper.insertData("2","Sanjay Yadav", "sanjay04@gmail.com","78945612302","4000");
        dbHelper.insertData("3","Akshay", "akshay@gmail.com","3442212345","4000");
        dbHelper.insertData("4","Lakshay", "lakshay@gmail.com","8790654321","4000");
        dbHelper.insertData("5","Sanju", "sanju@gmail.com","1234560987","4000");
        dbHelper.insertData("6","Sameer", "sameer@gmail.com","8970654321","4000");
        dbHelper.insertData("7","Sahil", "sahil@gmail.com","7890654321","4000");
        dbHelper.insertData("8","Akhil", "akhil@gmail.com","6789054321","4000");
        dbHelper.insertData("9","Ajay", "ajay@gmail.com","6543217890","4000");
        dbHelper.insertData("10","Veer", "veer@gmail.com","7654321890","4000");

        list = new ArrayList<>();
        showEmployeesFromDatabase();

        employeeLists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(SeeAllEmployee.this,employeeLists.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void showEmployeesFromDatabase() {

        list = dbHelper.getAllData();
        adapter = new EmployeeAdapter(this, R.layout.employee_list, list);
        employeeLists.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }

    @Override
    protected void onRestart() {
        super.onRestart();
        showEmployeesFromDatabase();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
        super.onBackPressed();
    }
}
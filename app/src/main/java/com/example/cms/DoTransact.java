package com.example.cms;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cms.database.DatabaseHelper;
import com.example.cms.model.EmployeeModel;

import java.util.ArrayList;

import static com.example.cms.database.DatabaseHelper.TABLE_NAME;

public class DoTransact extends AppCompatActivity {

    TextView id_txt, transferTo;
    EditText emp_name, emp_email, emp_phone, available_credit, transfer_credit;
    DatabaseHelper databaseHelper;
    Button trans_credit_btn;
    Spinner emp_list;
    ArrayList<EmployeeModel> list_emp;
    SQLiteDatabase db;
    String id, credit, amnt, toID, transTo, empName, transactionID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_transact);

        id_txt = findViewById(R.id.emp_id);
        emp_name = findViewById(R.id.emp_name);
        emp_email = findViewById(R.id.emp_email);
        emp_phone = findViewById(R.id.emp_phone);
        available_credit = findViewById(R.id.available_credit);
        emp_list = findViewById(R.id.empl_id);
        transferTo = findViewById(R.id.transfer_to);
        transfer_credit = findViewById(R.id.trans_credit);
        trans_credit_btn = findViewById(R.id.trans_credit_btn);

        databaseHelper = new DatabaseHelper(this);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        id_txt.setText(id);
        list_emp = new ArrayList<>();

        getAllDetails();
        loadSpinner();

        emp_list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                db = databaseHelper.getReadableDatabase();
                Cursor cursor = db.rawQuery("select * from "+TABLE_NAME+" where id="+emp_list.getSelectedItem().toString(),null);
                if (cursor.moveToFirst()){
                    String name = cursor.getString(1);
                    credit = cursor.getString(4);
                    transferTo.setText(name);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        trans_credit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (transfer_credit.getText().toString().isEmpty()) {
                    transfer_credit.setError("required");
                    transfer_credit.requestFocus();
                    return;
                } else {
                    int toCredit = Integer.parseInt(credit);
                    int transCredit = Integer.parseInt(transfer_credit.getText().toString().trim());
                    int empCredit = Integer.parseInt(available_credit.getText().toString().trim());

                    int balanceCredit = empCredit - transCredit;
                    if (balanceCredit >= 0) {
                        int totalCredit = toCredit + transCredit;

                        if (id.equals(emp_list.getSelectedItem().toString())) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(DoTransact.this);
                            builder.setTitle("Same User Error")
                                    .setMessage("Both receiver and sender have same id.")
                                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {

                                        }
                                    }).show();
                        } else {

                            transactionID = emp_name.getText().toString() +System.currentTimeMillis();
                            empName = emp_name.getText().toString();
                            transTo = transferTo.getText().toString();
                            toID = emp_list.getSelectedItem().toString();
                            amnt =String.valueOf(transCredit);

                            boolean data = databaseHelper.transactionInsert(transactionID, empName, id, transTo, toID, amnt);
                            if (data==true){
                                databaseHelper.updateData(id, String.valueOf(balanceCredit));
                                databaseHelper.updateData(emp_list.getSelectedItem().toString(), String.valueOf(totalCredit));
                            }else {
                                Toast.makeText(DoTransact.this, "no", Toast.LENGTH_SHORT).show();
                            }

                            final AlertDialog.Builder builder = new AlertDialog.Builder(DoTransact.this);
                            builder.setTitle("Transaction")
                                    .setMessage("Transaction Succesfull")
                                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            startActivity(new Intent(DoTransact.this, SeeAllEmployee.class));
                                            finish();
                                        }
                                    })
                                    .show();
                        }

                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(DoTransact.this);
                        builder.setTitle("Error")
                                .setMessage("Credit insufficient. Please try again!")
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                }).show();
                    }

                }
            }
        });
    }

    private void loadSpinner() {

        list_emp = databaseHelper.getAllUser();

        ArrayAdapter<EmployeeModel> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,list_emp);
        emp_list.setAdapter(adapter);

    }

    private void getAllDetails() {

        db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+TABLE_NAME+" where id="+id,null);
        if (cursor.moveToFirst()){
            String name = cursor.getString(1);
            String email = cursor.getString(2);
            String phone = cursor.getString(3);
            String credit = cursor.getString(4);
            emp_name.setText(name);
            emp_email.setText(email);
            emp_phone.setText(phone);
            available_credit.setText(credit);
        }



    }


}
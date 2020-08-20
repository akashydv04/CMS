package com.example.cms;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.cms.adapter.EmployeeAdapter;
import com.example.cms.adapter.TransactionAdapter;
import com.example.cms.database.DatabaseHelper;
import com.example.cms.model.EmployeeModel;
import com.example.cms.model.TransactionModel;

import java.util.ArrayList;

public class CheckTransaction extends AppCompatActivity {
    ListView transactionList;
    DatabaseHelper dbHelper;
    ArrayList<TransactionModel> list;
    TransactionAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_transaction);

        transactionList = findViewById(R.id.check_transaction_list);

        dbHelper = new DatabaseHelper(this);

        list = new ArrayList<>();
        showAllTransactionData();

    }

    private void showAllTransactionData() {

        list = dbHelper.getAllTransactionData();
        adapter = new TransactionAdapter(this, R.layout.transaction_list, list);
        transactionList.setAdapter(adapter);

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        showAllTransactionData();
    }
}
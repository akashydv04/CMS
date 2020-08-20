package com.example.cms.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;


import androidx.cardview.widget.CardView;

import com.example.cms.DoTransact;
import com.example.cms.R;
import com.example.cms.database.DatabaseHelper;
import com.example.cms.model.EmployeeModel;

import java.util.ArrayList;

public class EmployeeAdapter extends BaseAdapter {
    Context ctx;
    int res;
    ArrayList<EmployeeModel> models;

    DatabaseHelper databaseHelper = new DatabaseHelper(ctx);

    public EmployeeAdapter(Context ctx, int res, ArrayList<EmployeeModel> models) {

        this.ctx = ctx;
        this.res = res;
        this.models = models;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

            LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(res, null);

            final TextView emp_id = (TextView) view.findViewById(R.id.emp_id);
            TextView emp_name = (TextView) view.findViewById(R.id.emp_name);
            TextView emp_credit = (TextView) view.findViewById(R.id.emp_credit);
            TextView emp_email = (TextView) view.findViewById(R.id.emp_email);
            TextView emp_phone = (TextView) view.findViewById(R.id.emp_phone);
        CardView cardView = (CardView) view.findViewById(R.id.card_id);

            final EmployeeModel employeeModel = models.get(i);

        //adding data to views
        emp_id.setText(employeeModel.getId());
        emp_name.setText(employeeModel.getName());
        emp_credit.setText(employeeModel.getCredit());
        emp_email.setText(employeeModel.getEmail());
        emp_phone.setText(employeeModel.getPhone());

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ctx, DoTransact.class);
                intent.putExtra("id", emp_id.getText().toString());
                ctx.startActivity(intent);
            }
        });
        return view;
    }

}

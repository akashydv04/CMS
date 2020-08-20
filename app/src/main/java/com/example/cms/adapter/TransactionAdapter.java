package com.example.cms.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.example.cms.DoTransact;
import com.example.cms.R;
import com.example.cms.database.DatabaseHelper;
import com.example.cms.model.EmployeeModel;
import com.example.cms.model.TransactionModel;

import java.util.ArrayList;

public class TransactionAdapter extends BaseAdapter {
    Context ctx;
    int res;
    ArrayList<TransactionModel> models;

    DatabaseHelper databaseHelper = new DatabaseHelper(ctx);

    public TransactionAdapter(Context ctx, int res, ArrayList<TransactionModel> models) {

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

        final TextView transaction_id = (TextView) view.findViewById(R.id.transaction_id);
        TextView sender_name = (TextView) view.findViewById(R.id.sender_name);
        TextView sender_id = (TextView) view.findViewById(R.id.sender_id);
        TextView receiver_name = (TextView) view.findViewById(R.id.receiver_name);
        TextView receiver_id = (TextView) view.findViewById(R.id.receiver_id);
        TextView transaction_amount = (TextView) view.findViewById(R.id.transferred_amount);

        final TransactionModel transactionModel = models.get(i);

        //adding data to views
        transaction_id.setText(transactionModel.getTransaction_id());
        sender_name.setText(transactionModel.getSender_name());
        sender_id.setText(transactionModel.getSender_id());
        receiver_name.setText(transactionModel.getReceiver_name());
        receiver_id.setText(transactionModel.getReceiver_id());
        transaction_amount.setText(transactionModel.getTransaction_amount());


        return view;
    }

}

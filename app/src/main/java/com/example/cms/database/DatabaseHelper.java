package com.example.cms.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.cms.model.EmployeeModel;
import com.example.cms.model.TransactionModel;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "CMS_DATABASE.db";
    public static String TABLE_NAME = "CMS_EMPLOYEE";
    private static String COL_1 = "id";
    private static String COL_2 = "name";
    private static String COL_3 = "email";
    private static String COL_4 = "phone";
    private static String COL_5 = "credit";

    public static String TRANSACTION_TABLE_NAME = "TRANSACTION_DATA";
    private static String COLL_1 = "transactionid";
    private static String COLL_3 = "senderid";
    private static String COLL_5 = "receiverid";
    private static String COLL_2 = "sendername";
    private static String COLL_4 = "receivername";
    private static String COLL_6 = "transactionamount";

    public static final String table1 = "create table "+TABLE_NAME+" "
            +"("+COL_1+" INTEGER PRIMARY KEY ,"+COL_2+" TEXT, "+COL_3+" TEXT, "+COL_4+" TEXT, "+COL_5+" TEXT)";
    public static final String table2 = "create table "+TRANSACTION_TABLE_NAME+" "+"("+COLL_1+" " +
            "TEXT PRIMARY KEY, "+COLL_2+" TEXT, "+COLL_3+" TEXT, "+COLL_4+" TEXT, "+COLL_5+" TEXT, "+COLL_6+" TEXT)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(table1);

        sqLiteDatabase.execSQL(table2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TRANSACTION_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    public boolean transactionInsert(String transactionid, String sendername, String senderid, String receivername, String receiverid, String transaction_amount){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLL_1,transactionid);
        values.put(COLL_2,sendername);
        values.put(COLL_3,senderid);
        values.put(COLL_4,receivername);
        values.put(COLL_5,receiverid);
        values.put(COLL_6,transaction_amount);

        long result = db.insert(TRANSACTION_TABLE_NAME, null, values);
        if (result == -1){
            return false;
        }else {
            return true;
        }
    }

    public boolean insertData(String id, String name, String email, String phone, String credit){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_1,id);
        values.put(COL_2,name);
        values.put(COL_3,email);
        values.put(COL_4,phone);
        values.put(COL_5,credit);

        long result = db.insert(TABLE_NAME,null,values);
        if (result == -1){
            return false;
        }else {
            return true;
        }

    }


    public ArrayList<EmployeeModel> getAllData(){
        ArrayList<EmployeeModel> employeelist = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+TABLE_NAME, null);

        while (cursor.moveToNext()){
            String id = cursor.getString(0);
            String name = cursor.getString(1);
            String email = cursor.getString(2);
            String phone = cursor.getString(3);
            String credit = cursor.getString(4);

            EmployeeModel employeeModel = new EmployeeModel(id,name,email,phone,credit);

            employeelist.add(employeeModel);
        }
        return employeelist;
    }

    public ArrayList<TransactionModel> getAllTransactionData(){
        ArrayList<TransactionModel> transactionModels = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+TRANSACTION_TABLE_NAME, null);

        while (cursor.moveToNext()){
            String transaction_id = cursor.getString(0);
            String sender_name = cursor.getString(1);
            String sender_id = cursor.getString(2);
            String receiver_name = cursor.getString(3);
            String receiver_id = cursor.getString(4);
            String transferred_amount = cursor.getString(5);

            TransactionModel transactionModel = new TransactionModel(transaction_id, sender_name, sender_id, receiver_name, receiver_id, transferred_amount);

            transactionModels.add(transactionModel);
        }
        return transactionModels;
    }

    public ArrayList<EmployeeModel> getAllUser(){

        ArrayList<EmployeeModel> models = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+TABLE_NAME,null);

        while (cursor.moveToNext()){
            String id = cursor.getString(0);

            EmployeeModel employeeModel = new EmployeeModel(id);

            models.add(employeeModel);
        }
        return models;

    }

    public boolean updateData(String id, String credit){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues= new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_5, credit);

        db.update(TABLE_NAME, contentValues, "ID =?",new String[] {id});
        return true;
    }
    public int deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(TABLE_NAME, "ID=?",new String[]{id});

    }
}

package app.nirmlkar.dalejan.bluedart;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class BlueDartDatabase extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "BueDart";

    // User table name
    private static final String TABLE_ITEM_DETAILS = "item_detail";
    private static final String TABLE_DELIVERY_BOY_DETAILS = "delivery_boy_detail";


    private static final String COLUMN_ITEM_ID = "item_id";
    private static final String COLUMN_ITEM_NAME = "item_name";
    private static final String COLUMN_ITEM_BOY_ID = "item_boy_id";
    private static final String COLUMN_ITEM_PICKUP_PLACE = "item_pickup_place";
    private static final String COLUMN_ITEM_DROP_PLACE = "user_gr";
    private static final String COLUMN_ITEM_FLAG = "user_pgr";

    private static final String COLUMN_DELIVERY_BOY_ID = "boy_id";
    private static final String COLUMN_DELIVERY_BOY_NAME = "boy_name";
    private static final String COLUMN_DELIVERY_BOY_EMAIL = "boy_email";
    private static final String COLUMN_DELIVERY_BOY_PASSWORD = "boy_password";

    private static final String CREATE_ITEM_DETAILS_TABLE = "CREATE TABLE " + TABLE_ITEM_DETAILS + "("
            + COLUMN_ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_ITEM_NAME + " TEXT,"
            + COLUMN_ITEM_BOY_ID + " TEXT," + COLUMN_ITEM_PICKUP_PLACE + " TEXT," + COLUMN_ITEM_DROP_PLACE + " TEXT,"
            + COLUMN_ITEM_FLAG + " TEXT" + ")";

    private static final String CREATE_DELIVERY_BOY_DETAILS_TABLE = "CREATE TABLE " + TABLE_DELIVERY_BOY_DETAILS + "("
            + COLUMN_DELIVERY_BOY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_DELIVERY_BOY_NAME + " TEXT,"
            + COLUMN_DELIVERY_BOY_EMAIL + " TEXT," + COLUMN_DELIVERY_BOY_PASSWORD + " TEXT" + ")";

    BlueDartDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.d("jjjjjjj", "kjagfgkgj");


        Log.d("jjjjjjj11", "kjagfgkgj");

        db.execSQL(CREATE_ITEM_DETAILS_TABLE);

        Log.d("jjjjjjj22", "kjagfgkgj");


        Log.d("jjjjjjj33", "kjagfgkgj");

        db.execSQL(CREATE_DELIVERY_BOY_DETAILS_TABLE);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop User Table if exist
        String DROP_ITEM_TABLE = "DROP TABLE IF EXISTS " + TABLE_ITEM_DETAILS;
        db.execSQL(DROP_ITEM_TABLE);
        String DROP_BOY_TABLE = "DROP TABLE IF EXISTS " + TABLE_DELIVERY_BOY_DETAILS;
        db.execSQL(DROP_BOY_TABLE);

        // Create tables again
        onCreate(db);

    }


    void AddItem(ItemDetails item) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ITEM_NAME, item.getItem_name());
        values.put(COLUMN_ITEM_BOY_ID, item.getBoy_id());
        values.put(COLUMN_ITEM_PICKUP_PLACE, item.getPickup_place());
        values.put(COLUMN_ITEM_DROP_PLACE, item.getDrop_place());
        values.put(COLUMN_ITEM_FLAG, Integer.parseInt(item.getFlag()));

        // Inserting Row
        db.insert(TABLE_ITEM_DETAILS, null, values);
        db.close();
    }


    void AddDeliveryBoy(DeliveryBoy user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_DELIVERY_BOY_NAME, user.getBoy_name());
        values.put(COLUMN_DELIVERY_BOY_EMAIL, user.getBoy_email());
        values.put(COLUMN_DELIVERY_BOY_PASSWORD, user.getBoy_password());

        // Inserting Row
        db.insert(TABLE_DELIVERY_BOY_DETAILS, null, values);
        db.close();
    }
    void ConfirmItem(String id) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ITEM_FLAG, "1");


        db.update(TABLE_ITEM_DETAILS,values,COLUMN_ITEM_ID+"="+id,null);
        db.close();
    }


    List<DeliveryBoy> getAllDeliveryBoy() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_DELIVERY_BOY_ID,
                COLUMN_DELIVERY_BOY_NAME,
                COLUMN_DELIVERY_BOY_EMAIL,
                COLUMN_DELIVERY_BOY_PASSWORD
        };
        // sorting orders
        String sortOrder =
                COLUMN_DELIVERY_BOY_ID + " ASC";
        List<DeliveryBoy> userList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_DELIVERY_BOY_DETAILS, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order

        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                DeliveryBoy user;
                user = new DeliveryBoy();
                user.setBoy_id(cursor.getString(cursor.getColumnIndex(COLUMN_DELIVERY_BOY_ID)));
                user.setBoy_name(cursor.getString(cursor.getColumnIndex(COLUMN_DELIVERY_BOY_NAME)));
                user.setBoy_email(cursor.getString(cursor.getColumnIndex(COLUMN_DELIVERY_BOY_EMAIL)));
                user.setBoy_password(cursor.getString(cursor.getColumnIndex(COLUMN_DELIVERY_BOY_PASSWORD)));
                // Adding user record to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return userList;
    }


    List<ItemDetails> getAllItemProcess() {
        Log.d("jjjjjjj44", "kjagfgkgj");

        // array of columns to fetch
        String[] columns = {
                COLUMN_ITEM_ID,
                COLUMN_ITEM_NAME,
                COLUMN_ITEM_BOY_ID,
                COLUMN_ITEM_PICKUP_PLACE,
                COLUMN_ITEM_DROP_PLACE,
                COLUMN_ITEM_FLAG
        };
        // sorting orders
        String sortOrder =
                COLUMN_ITEM_ID + " ASC";
        List<ItemDetails> itemlist = new ArrayList<>();

        Log.d("jjjjjjj04", "kjagfgkgj");
        SQLiteDatabase db = this.getReadableDatabase();
        Log.d("jjjjjjj005", "kjagfgkgj");

        Cursor cursor = db.query(TABLE_ITEM_DETAILS, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        Log.d("jjjjjjj006", "kjagfgkgj");


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {


                ItemDetails item;
                item = new ItemDetails();
                item.setDetails_id(cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_ID)));
                item.setItem_name(cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_NAME)));
                item.setBoy_id(cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_BOY_ID)));
                item.setPickup_place(cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_PICKUP_PLACE)));
                item.setDrop_place(cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_DROP_PLACE)));
                item.setFlag(cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_FLAG)));
                // Adding user record to list
                itemlist.add(item);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return itemlist;
    }


    List<ItemDetails> getperticularBoy(String idboy) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_ITEM_ID,
                COLUMN_ITEM_NAME,
                COLUMN_ITEM_BOY_ID,
                COLUMN_ITEM_PICKUP_PLACE,
                COLUMN_ITEM_DROP_PLACE,
                COLUMN_ITEM_FLAG
        };


        // sorting orders
        String sortOrder =
                COLUMN_ITEM_ID + " ASC";
        List<ItemDetails> itemlist = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_ITEM_DETAILS, //Table to query
                columns,    //columns to return
                COLUMN_ITEM_BOY_ID +"="+Integer.parseInt(idboy),        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {


                ItemDetails item;
                item = new ItemDetails();
                item.setDetails_id(cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_ID)));
                item.setItem_name(cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_NAME)));
                item.setBoy_id(cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_BOY_ID)));
                item.setPickup_place(cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_PICKUP_PLACE)));
                item.setDrop_place(cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_DROP_PLACE)));
                item.setFlag(cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_FLAG)));
                // Adding user record to list
                itemlist.add(item);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return itemlist;
    }

    List<ItemDetails> getperticularitem(String idboy) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_ITEM_ID,
                COLUMN_ITEM_NAME,
                COLUMN_ITEM_BOY_ID,
                COLUMN_ITEM_PICKUP_PLACE,
                COLUMN_ITEM_DROP_PLACE,
                COLUMN_ITEM_FLAG
        };


        // sorting orders
        String sortOrder =
                COLUMN_ITEM_ID + " ASC";
        List<ItemDetails> itemlist = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_ITEM_DETAILS, //Table to query
                columns,    //columns to return
                COLUMN_ITEM_ID +"="+idboy,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {


                ItemDetails item;
                item = new ItemDetails();
                item.setDetails_id(cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_ID)));
                item.setItem_name(cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_NAME)));
                item.setBoy_id(cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_BOY_ID)));
                item.setPickup_place(cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_PICKUP_PLACE)));
                item.setDrop_place(cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_DROP_PLACE)));
                item.setFlag(cursor.getString(cursor.getColumnIndex(COLUMN_ITEM_FLAG)));
                // Adding user record to list
                itemlist.add(item);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return itemlist;
    }

/*

    public boolean checkUser(String email, String password) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_DELIVERY_BOY_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_DELIVERY_BOY_EMAIL + " = ?" + " AND " + COLUMN_DELIVERY_BOY_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {email, password};
        Cursor cursor = db.query(TABLE_DELIVERY_BOY_DETAILS, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        return cursorCount > 0;

    }
*/


}

package com.example.bicisharing.DB;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.example.bicisharing.Entities.User;

public class DBHelper extends SQLiteOpenHelper {
        private SQLiteDatabase sqLiteDatabase;

        private static final String DATABASE_NAME = "bici_sharing";
        private static final int DATABASE_VERSION = 1;



        private static final String COLUMN_ID = "id";
        private static final String COLUMN_NAME = "name";
        private static final String COLUMN_DESCRIPTION = "description";
        private static final String COLUMN_TIME = "time";
        private static final String COLUMN_PRICE = "price";
        private static final String COLUMN_IMAGE = "image";

        private static final String TABLE_NAME_EVENTS = "events";
        private static final String TABLE_NAME_PRODUCTS = "products";
        private static final String TABLE_NAME_FOOD = "food";

        private static final String TABLE_NAME_USERS = "users";
        private static final String COLUMN_EMAIL = "email";
        private static final String COLUMN_PASSWORD = "password";
        private static final String COLUMN_ROLE = "role";

        public DBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            sqLiteDatabase = this.getWritableDatabase();
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String createUsersTableQuery = "CREATE TABLE " + TABLE_NAME_USERS + " (" +
                    COLUMN_ID + " TEXT PRIMARY KEY," +
                    COLUMN_EMAIL + " TEXT UNIQUE," +
                    COLUMN_PASSWORD + " TEXT," +
                    COLUMN_ROLE + " TEXT)";

            String createEventTableQuery = "CREATE TABLE " + TABLE_NAME_EVENTS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_NAME + " TEXT," +
                    COLUMN_DESCRIPTION + " TEXT," +
                    COLUMN_IMAGE + " TEXT," +
                    COLUMN_TIME + " TEXT)";

            String createProductTableQuery = "CREATE TABLE " + TABLE_NAME_PRODUCTS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_NAME + " TEXT," +
                    COLUMN_DESCRIPTION + " TEXT," +
                    COLUMN_IMAGE + " TEXT," +
                    COLUMN_PRICE + " TEXT)";

            String createFoodTableQuery = "CREATE TABLE " + TABLE_NAME_FOOD + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_NAME + " TEXT," +
                    COLUMN_DESCRIPTION + " TEXT," +
                    COLUMN_IMAGE + " TEXT," +
                    COLUMN_PRICE + " TEXT)";

            db.execSQL(createUsersTableQuery);
            db.execSQL(createEventTableQuery);
            db.execSQL(createProductTableQuery);
            db.execSQL(createFoodTableQuery);


        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            String dropUsersQuery = "DROP TABLE IF EXISTS " + TABLE_NAME_USERS;
            db.execSQL(dropUsersQuery);
            String dropFoodQuery = "DROP TABLE IF EXISTS " + TABLE_NAME_FOOD;
            db.execSQL(dropFoodQuery);
            String dropProductsQuery = "DROP TABLE IF EXISTS " + TABLE_NAME_PRODUCTS;
            db.execSQL(dropProductsQuery);
            String dropEventsQuery = "DROP TABLE IF EXISTS " + TABLE_NAME_EVENTS;
            db.execSQL(dropEventsQuery);
            onCreate(db);
        }

        public void addUser(User user) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID, user.getId());
            values.put(COLUMN_EMAIL, user.getEmail());
            values.put(COLUMN_PASSWORD, user.getPassword());
            values.put(COLUMN_ROLE, user.getRole().name());

            db.insert(TABLE_NAME_USERS, null, values);
            db.close();
        }

        public User getUserByEmail(String email) {
            SQLiteDatabase db = this.getReadableDatabase();
            User user = null;
            String[] columns = {COLUMN_ID, COLUMN_EMAIL, COLUMN_PASSWORD, COLUMN_ROLE};
            String selection = COLUMN_EMAIL + " = ?";
            String[] selectionArgs = {email};

            Cursor cursor = db.query(TABLE_NAME_USERS, columns, selection, selectionArgs, null, null, null);
            if (cursor.moveToFirst()) {
                @SuppressLint("Range") String id = cursor.getString(cursor.getColumnIndex(COLUMN_ID));
                @SuppressLint("Range") String password = cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD));
                @SuppressLint("Range") String roleString = cursor.getString(cursor.getColumnIndex(COLUMN_ROLE));
                User.Role role = User.Role.valueOf(roleString);
                user = new User(email, password, role);
                user.setId(id);
            }
            cursor.close();
            db.close();

            return user;
        }

        // ***** ***** ***** CRUD OTHER TABLES ***** ***** *****
        public void addItem(String table, String name, String description, String image, String price_time) {
            Log.i("testPrice***", price_time);
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME, name);
            values.put(COLUMN_DESCRIPTION, description);
            values.put(COLUMN_IMAGE, image);
            if(table.equals(TABLE_NAME_EVENTS)){
                values.put(COLUMN_TIME, price_time);
            }else{
                values.put(COLUMN_PRICE, price_time);
            }

            db.insert(table, null, values);
            db.close();
        }

        public Cursor getItems(String table){
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + table, null);
            return  cursor;
        }

        public Cursor getItem(String table, String id){
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + table + " WHERE id = " + id, null);
            return  cursor;
        }

        public void deleteItemById(String id, String table){
            sqLiteDatabase.execSQL("DELETE FROM " + table + " WHERE id = " + id);
        }

        public void updateItem(String table, String id, String name, String description, String image, String price_time){
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", name);
            contentValues.put("description", description);
            contentValues.put("image", image);
            if(table.equals(TABLE_NAME_EVENTS)){
                contentValues.put("time", price_time);
            }else{
                contentValues.put("price", price_time);
            }

            sqLiteDatabase.update("PRODUCTS", contentValues, "id = ?", new String[]{id});
        }
}

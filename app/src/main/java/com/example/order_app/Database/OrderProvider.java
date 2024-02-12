// OrderProvider.java
package com.example.order_app.Database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class OrderProvider extends ContentProvider {
    private SQLiteDatabase db;

    static final String DATABASE_NAME = "Hotel_OrdersDB";
    static final int DATABASE_VERSION = 1;

    static final String PROVIDER_NAME = "com.example.order_app.Database.order_provider";
    static final String URL = "content://" + PROVIDER_NAME + "/orders";
    static final String URL_USER = "content://" + PROVIDER_NAME + "/users";
    public static final Uri CONTENT_URI = Uri.parse(URL);
    public static final Uri CONTENT_URI_USER = Uri.parse(URL_USER);

    static final String TABLE_NAME = "orders";
    static final String TABLE_NAME_USER = "users";

    static final int uriCodeOrders = 1;
    static final int uriCodeUsers = 2;
    static UriMatcher uriMatcher;

    static final String id = "id";
    public static final String itemName = "item_name";
    public static final String quantity = "quantity";
    public static final String Username = "username";
    public static final String Password = "password";

    static final String CREATE_DB_TABLE = "CREATE TABLE " + TABLE_NAME
            + " (id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + " item_name TEXT NOT NULL, "
            + " quantity INTEGER NOT NULL); ";

    static final String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_NAME_USER
            + " (id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + " username TEXT NOT NULL, "
            + " password TEXT NOT NULL); ";

    private static class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d("Abhay", "onCreate: databasecreated");
            db.execSQL(CREATE_DB_TABLE);
            db.execSQL(CREATE_USER_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_USER);
            onCreate(db);
        }
    }

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "orders", uriCodeOrders);
        uriMatcher.addURI(PROVIDER_NAME, "users", uriCodeUsers);
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
        return db != null;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case uriCodeOrders:
                return "com.example.order_app.Database.order_provider/orders";
            case uriCodeUsers:
                return "com.example.order_app.Database.order_provider/users";
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        long rowID;
        switch (uriMatcher.match(uri)) {
            case uriCodeOrders:
                rowID = db.insert(TABLE_NAME, "", values);
                break;
            case uriCodeUsers:
                rowID = db.insert(TABLE_NAME_USER, "", values);
                break;
            default:
                throw new SQLiteException("Failed to add a record into " + uri);
        }
        if (rowID > 0) {
            Uri _uri = ContentUris.withAppendedId(uri, rowID);
            getContext().getContentResolver().notifyChange(_uri, null);
            return _uri;
        }
        throw new SQLiteException("Failed to add a record into " + uri);
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Cursor cursor;
        switch (uriMatcher.match(uri)) {
            case uriCodeOrders:
                cursor = db.query(TABLE_NAME, projection, selection, selectionArgs, null,
                        null, sortOrder);
                break;
            case uriCodeUsers:
                cursor = db.query(TABLE_NAME_USER, projection, selection, selectionArgs, null,
                        null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count;
        switch (uriMatcher.match(uri)) {
            case uriCodeOrders:
                count = db.delete(TABLE_NAME, selection, selectionArgs);
                break;
            case uriCodeUsers:
                count = db.delete(TABLE_NAME_USER, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int count;
        switch (uriMatcher.match(uri)) {
            case uriCodeOrders:
                count = db.update(TABLE_NAME, values, selection, selectionArgs);
                break;
            case uriCodeUsers:
                count = db.update(TABLE_NAME_USER, values, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }
}

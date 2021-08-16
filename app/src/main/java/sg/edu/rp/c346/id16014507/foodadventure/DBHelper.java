package sg.edu.rp.c346.id16014507.foodadventure;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "foods.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_FOOD = "Food";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_FOOD = "food";
    private static final String COLUMN_SELLINGPT = "sellingpt";
    private static final String COLUMN_LOCATION = "location";
    private static final String COLUMN_STARS = "stars";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createFoodTableSql = "CREATE TABLE " + TABLE_FOOD + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_FOOD + " TEXT, "
                + COLUMN_SELLINGPT + " TEXT, "
                + COLUMN_LOCATION + " TEXT, "
                + COLUMN_STARS + " INTEGER )";
        db.execSQL(createFoodTableSql);
        Log.i("info", createFoodTableSql + "\ncreated tables");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOOD);
        onCreate(db);
    }

    public long insertFood(String food, String sellingpt, String location, int stars) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FOOD, food);
        values.put(COLUMN_SELLINGPT, sellingpt);
        values.put(COLUMN_LOCATION, location);
        values.put(COLUMN_STARS, stars);
        long result = db.insert(TABLE_FOOD, null, values);
        db.close();
        Log.d("SQL Insert","" + result);
        return result;
    }

    public ArrayList<Food> getAllFoods() {
        ArrayList<Food> foodslist = new ArrayList<Food>();
        String selectQuery = "SELECT " + COLUMN_ID + ","
                + COLUMN_FOOD + "," + COLUMN_SELLINGPT + ","
                + COLUMN_LOCATION + ","
                + COLUMN_STARS + " FROM " + TABLE_FOOD;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String food = cursor.getString(1);
                String sellingpt = cursor.getString(2);
                String location = cursor.getString(3);
                int stars = cursor.getInt(4);

                Food newFood = new Food(id, food, sellingpt, location, stars);
                foodslist.add(newFood);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return foodslist;
    }

    public ArrayList<Food> getAllFoodsByStars(int starsFilter) {
        ArrayList<Food> foodslist = new ArrayList<Food>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns= {COLUMN_ID, COLUMN_FOOD, COLUMN_SELLINGPT, COLUMN_LOCATION, COLUMN_STARS};
        String condition = COLUMN_STARS + ">= ?";
        String[] args = {String.valueOf(starsFilter)};

        Cursor cursor;
        cursor = db.query(TABLE_FOOD, columns, condition, args, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String food = cursor.getString(1);
                String sellingpt = cursor.getString(2);
                String location = cursor.getString(3);
                int stars = cursor.getInt(4);

                Food newFood = new Food(id, food, sellingpt, location, stars);
                foodslist.add(newFood);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return foodslist;
    }

    public int updateFood(Food data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FOOD, data.getFood());
        values.put(COLUMN_SELLINGPT, data.getSellingPoint());
        values.put(COLUMN_LOCATION, data.getLocation());
        values.put(COLUMN_STARS, data.getStars());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.getId())};
        int result = db.update(TABLE_FOOD, values, condition, args);
        db.close();
        return result;
    }

    public int deleteFood(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_FOOD, condition, args);
        db.close();
        return result;
    }

//    public ArrayList<String> getLocation() {
//        ArrayList<String> codes = new ArrayList<String>();
//
//        SQLiteDatabase db = this.getReadableDatabase();
//        String[] columns= {COLUMN_LOCATION};
//
//        Cursor cursor;
//        cursor = db.query(true, TABLE_FOOD, columns, null, null, null, null, null, null);
//        // Loop through all rows and add to ArrayList
//        if (cursor.moveToFirst()) {
//            do {
//                codes.add(cursor.getString(0));
//            } while (cursor.moveToNext());
//        }
//        // Close connection
//        cursor.close();
//        db.close();
//        return codes;
//    }
//
//    public ArrayList<Food> getAllSongsByYear(int yearFilter) {
//        ArrayList<Food> songslist = new ArrayList<Food>();
//
//        SQLiteDatabase db = this.getReadableDatabase();
//        String[] columns= {COLUMN_ID, COLUMN_FOOD, COLUMN_SELLINGPT, COLUMN_LOCATION, COLUMN_STARS};
//        String condition = COLUMN_LOCATION + "= ?";
//        String[] args = {String.valueOf(yearFilter)};
//
//        Cursor cursor;
//        cursor = db.query(TABLE_FOOD, columns, condition, args, null, null, null, null);
//
//        if (cursor.moveToFirst()) {
//            do {
//                int id = cursor.getInt(0);
//                String food = cursor.getString(1);
//                String sellingpt = cursor.getString(2);
//                String location = cursor.getString(3);
//                int stars = cursor.getInt(4);
//
//                Food newFood = new Food(id, food, sellingpt, location, stars);
//                songslist.add(newFood);
//            } while (cursor.moveToNext());
//        }
//
//        cursor.close();
//        db.close();
//        return songslist;
//    }
}

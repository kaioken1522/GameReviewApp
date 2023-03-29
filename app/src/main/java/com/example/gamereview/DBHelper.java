package com.example.gamereview;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "GameReviewApp";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_USERPROFILE = "userprofile";
    public static final String TABLE_LOCATION = "userlocation";
    private static final String KEY_ID = "id";
    private static final String KEY_FIRSTNAME = "firstname";
    private static final String KEY_LASTNAME = "lastname";
    private static final String KEY_CONTACT = "phone_no";
    private static final String KEY_EMAIL = "email_id";
    private static final String KEY_DATE = "b_date";
    public static final String KEY_LONGITUDE = "longitude";
    public static final String KEY_LATITUDE = "latitude";
    private static final String TABLE_GAMES = "games";
    private static final String PLATFORM_COL = "platform";
    private static final String NAME_COL = "name";
    private static final String IMAGE_COL = "image";
    private static final String TABLE_REVIEW = "review";

    private static final String MAILID_COL = "mail";
    private static final String REVIEW_COL = "reviewCol";
    private static final String GAMENAME_COL = "gameName";
    public static final String REVIEWIMAGE_COL = "profileImage";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        // CREATE TABLE userprofile ( id INTEGER PRIMARY KEY AUTOINCREMENT, firstname TEXT, lastname TEXT, phone_no TEXT, email_id TEXT);

        String q = " CREATE TABLE " + TABLE_USERPROFILE + " ("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_FIRSTNAME + " TEXT,"
                + KEY_LASTNAME + " TEXT,"
                + KEY_CONTACT + " TEXT,"
                + KEY_EMAIL + " TEXT not null unique)";



        String z = " CREATE TABLE " + TABLE_LOCATION + " ("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_LONGITUDE + " TEXT, "
                + KEY_LATITUDE+ " TEXT ) ";

        String usersQuery = "CREATE TABLE " + TABLE_USERS + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FIRST_NAME_COL + " TEXT,"
                + LAST_NAME_COL + " TEXT,"
                + EMAIL_COL + " TEXT not null unique,"
                + PASSWORD_COL + " TEXT not null,"
                + PHONE_COL + " TEXT,"
                + DOB_COL + " TEXT ,"
                + IMAGE_COL + " BLOB )";

        String gamesList = "CREATE TABLE " + TABLE_GAMES + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PLATFORM_COL + " TEXT,"
                + NAME_COL + " TEXT,"
                + IMAGE_COL + " INTEGER)";

        String reviewList = "CREATE TABLE " + TABLE_REVIEW + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + MAILID_COL + " TEXT,"
                + REVIEW_COL + " TEXT,"
                + GAMENAME_COL + " TEXT, "
                + REVIEWIMAGE_COL + " BLOB)";


        // sqLiteDatabase.execSQL(q);
        sqLiteDatabase.execSQL(z);
        sqLiteDatabase.execSQL(usersQuery);
        sqLiteDatabase.execSQL(gamesList);
        sqLiteDatabase.execSQL(reviewList);



    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

       // sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USERPROFILE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_LOCATION);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_GAMES);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_REVIEW);



        onCreate(sqLiteDatabase);

    }

    public void addReview(String email, String review, String gameName, byte [] image){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(MAILID_COL,email);
        values.put(REVIEW_COL,review);
        values.put(REVIEWIMAGE_COL, image);
        values.put(GAMENAME_COL,gameName);
        db.insert(TABLE_REVIEW,null,values);

    }

    public void addUser(String firstname, String lastname, String phone_no, String email_id ){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_FIRSTNAME, firstname);
        values.put(KEY_LASTNAME, lastname);
        values.put(KEY_CONTACT,phone_no);
        values.put(KEY_EMAIL,email_id);


        db.insert(TABLE_USERPROFILE,null,values);


    }

    public  long updateUser(String userId, String fName, String lName, String email, String contact, String dob, byte[] image)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] whereArgs ={userId};

        ContentValues values = new ContentValues();
        values.put(FIRST_NAME_COL, fName);
        values.put(LAST_NAME_COL, lName);
        values.put(EMAIL_COL, email);
        //values.put(PASSWORD_COL, password);
        values.put(PHONE_COL, contact);
        values.put(DOB_COL, dob);
        values.put(IMAGE_COL, image);

        int id =db.update(TABLE_USERS ,values,USER_ID+" = ?",whereArgs);
        return  id;
    }


    // user authentication

    private static final String TABLE_USERS = "users";

    private static final String USER_ID = "id";
    private static final String FIRST_NAME_COL = "fName";
    private static final String LAST_NAME_COL = "lName";
    private static final String EMAIL_COL = "email";
    private static final String PASSWORD_COL = "password";
    private static final String PHONE_COL = "phone";
    private static final String DOB_COL = "dob";
   // private static final String IMAGE_COL = "image";

    private static final String ID_COL = "id";


    public long insertNewUser(String fName, String lName, String email,String password,String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FIRST_NAME_COL, fName);
        values.put(LAST_NAME_COL, lName);
        values.put(EMAIL_COL, email);
        values.put(PASSWORD_COL, password);
        values.put(PHONE_COL, phone);
        values.put(DOB_COL, "");
        values.put(IMAGE_COL, new byte[]{0});
        long id=db.insert(TABLE_USERS, null, values);
        db.close();
        return id;

    }


    public void addLocation(String longitude, String latitude)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_LONGITUDE, longitude);
        values.put(KEY_LATITUDE, latitude);


        db.insert(TABLE_LOCATION,null,values);

    }


    public void addGames (String gameName, String platform, Integer Image){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(PLATFORM_COL, platform);
        values.put(NAME_COL, gameName);
        values.put(IMAGE_COL, Image);

        db.insert(TABLE_GAMES,null,values);

    }



    // Method which will validate and insert new user in db
    public long insertNewUser(String fName, String lName, String email,String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FIRST_NAME_COL, fName);
        values.put(LAST_NAME_COL, lName);
        values.put(EMAIL_COL, email);
        values.put(PASSWORD_COL, password);
        long id=db.insert(TABLE_USERS, null, values);
        db.close();
        return id;

    }

    @SuppressLint("Range")
    public ArrayList getList1() {

        final String TABLE_NAME = "games";


        SQLiteDatabase db  = this.getReadableDatabase();
        Cursor res  = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE platform = 'PC'", null);
        res.moveToFirst();
        ArrayList<String> array_list = new ArrayList<String>();
        while(!res.isAfterLast()) {
            array_list.add(res.getString(res.getColumnIndex("name")));
            //array_list.add(res.getString(res.getColumnIndex("image")));
            res.moveToNext();
        }
        res.close();
        return array_list;
    }

    @SuppressLint("Range")
    public ArrayList getImage1() {

        final String TABLE_NAME = "games";


        SQLiteDatabase db  = this.getReadableDatabase();
        Cursor res  = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE platform = 'PC'", null);
        res.moveToFirst();
        ArrayList<Integer> array_list = new ArrayList<Integer>();
        while(!res.isAfterLast()) {
            array_list.add(res.getInt(res.getColumnIndex("image")));
            res.moveToNext();
        }
        res.close();
        return array_list;
    }

    @SuppressLint("Range")
    public ArrayList getList2() {

        final String TABLE_NAME = "games";

        String selectQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE PLATFORM_COL = 'XBOX'";
        SQLiteDatabase db  = this.getReadableDatabase();
        Cursor cursor      = db.rawQuery(selectQuery, null);
        String[] data      = null;
        ArrayList<String> array_list = new ArrayList<String>();
        if (cursor.moveToFirst()) {
            do {
                array_list.add(cursor.getString(cursor.getColumnIndex("PLATFORM_COL")));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return array_list;
    }

    @SuppressLint("Range")
    public ArrayList getList3() {

        final String TABLE_NAME = "games";

        String selectQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE PLATFORM_COL = 'PS'";
        SQLiteDatabase db  = this.getReadableDatabase();
        Cursor cursor      = db.rawQuery(selectQuery, null);
        ArrayList<String> array_list = new ArrayList<String>();

        if (cursor.moveToFirst()) {
            do {
                array_list.add(cursor.getString(cursor.getColumnIndex("PLATFORM_COL")));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return array_list;
    }

    // Method which will validate and insert new user in db
    public UserModel validateAndLogin(String email, String password) {
        // array of columns to fetch
        String[] columns = {
                USER_ID,FIRST_NAME_COL,LAST_NAME_COL,EMAIL_COL,PASSWORD_COL,PHONE_COL,DOB_COL,IMAGE_COL,
        };
        SQLiteDatabase db = this.getWritableDatabase();
        // selection criteria
        String selection = EMAIL_COL + " = ?" + " AND " + PASSWORD_COL + " = ?";
        // selection arguments
        String[] selectionArgs = {email, password};
        // query user table with conditions

        Cursor cursor = db.query(TABLE_USERS, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order
        UserModel userModel = null;
        if (cursor != null){
            boolean b = cursor.moveToFirst();
            if(b){
                userModel = new UserModel(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),
                        cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getBlob(7));
            }
        }

        return userModel;
    }


    @SuppressLint("Range")
    public ArrayList getReviews() {

        final String TABLE_NAME = "review";

        String selectQuery = "SELECT  * FROM " + TABLE_NAME + "";
        SQLiteDatabase db  = this.getReadableDatabase();
        Cursor cursor      = db.rawQuery(selectQuery, null);
        ArrayList<String> array_list = new ArrayList<String>();

        if (cursor.moveToFirst()) {
            do {
                array_list.add(cursor.getString(cursor.getColumnIndex("mail")));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return array_list;
    }

    @SuppressLint("Range")
    public byte [] getUserProfile(String emailsss) {

        final String TABLE_NAME = "users";
        byte[] gg = new byte[50];
        //List<byte[]> list = new ArrayList<>();

       // byte [] profilePic = new byte[list.size()];
      //  for (int i = 0; i < list.size(); i++) {
     //       profilePic = list.get(i);
     //   }
       // String selectQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE EMAIL_COL = 'emailsss'", null;
        SQLiteDatabase db  = this.getReadableDatabase();

        Cursor cursor  = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE email = '"+emailsss +"'", null);

        //Cursor cursor  = db.rawQuery(selectQuery, null);
      //  ArrayList<String> array_list = new ArrayList<String>();

        if (cursor.moveToFirst()) {
            do {
           gg =  cursor.getBlob(cursor.getColumnIndex("image"));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return gg;

    }


    public ArrayList<ReadReviewsPageModal> readReviews(String gameName) {
        final String TABLE_NAME = "review";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE gameName = '"+gameName +"'" , null);

        ArrayList<ReadReviewsPageModal> readReviewsPageModalArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursor.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                readReviewsPageModalArrayList.add(new ReadReviewsPageModal(cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getBlob(4)));
            } while (cursor.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursor.close();
        return readReviewsPageModalArrayList;
    }




    public  int deleteUser(String email)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] whereArgs ={email};

        int count =db.delete(TABLE_USERS ,EMAIL_COL+" = ?",whereArgs);
        return  count;
    }


    public void deleteAllGamesData() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_GAMES);
    }

}

package huytranq.template.presenters.utilities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import huytranq.template.models.User;

/**
 * Created by huytr on 13-01-2016.
 */
public class UserDatabase extends SQLiteOpenHelper {
    private static final String DEBUG = "UserDatabase";

    //  TODO    Database Version
    public static final int VERSION = 1;
    //  TODO    Database Name
    public static final String NAME = "users.db";

    //  TODO    Helper instance
    private static UserDatabase instance;

    //  TODO    Database instance
    private static SQLiteDatabase database;

    private UserDatabase(Context context) {
        super(context , NAME , null , VERSION);
        //  TODO    Initialize database file
        String databasePath = Storage.getDatabasePath(context , NAME);
        if (Storage.exists(databasePath)) {
            Log.d(DEBUG , "User database already exists");
        }
        else {
            try {
                Storage.copyAsset(context , NAME , databasePath);
            }
            catch (Exception exception) {
                Log.d(DEBUG , "Cannot create database file! " + exception.getMessage());
            }
        }
    }

    public static synchronized UserDatabase getInstance(Context context) {
        if (instance == null)
            instance = new UserDatabase(context);
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //  TODO    Initialize database on 1st time
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion <= oldVersion) {
            Log.d(DEBUG, "SQLite database is up-to-date");
            return;
        }
        Log.d(DEBUG , "SQLite database is outdated. (" + oldVersion + " < " + newVersion + ")");
        //  TODO    Upgrade database
        Log.d(DEBUG , "SQLite database it upgraded to " + newVersion);
    }

    public boolean insert(User user) {
        database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(User.USERNAME , user.getUsername());
        values.put(User.PASSWORD, user.getPassword());
        values.put(User.BIRTHDAY, user.getBirthday());
        return (-1 != database.insert(User.TABLE , null , values));
    }

    public List<User> getUsers() {
        database = getReadableDatabase();
        ArrayList<User> result = new ArrayList<>();
        Cursor cursor = database.query(User.TABLE , null , null , null , null , null , null);
        result.ensureCapacity(cursor.getCount());
        cursor.moveToPosition(-1);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String username = cursor.getString(1);
            byte[] password = cursor.getBlob(2);
            String birthday = cursor.getString(3);
            User user = new User(id , username , password , birthday);
            result.add(user);
        }
        cursor.close();
        return result;
    }
}

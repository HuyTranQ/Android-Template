package huytranq.template.presenters.utilities;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by huytr on 07-01-2016.
 */
public class Database extends SQLiteOpenHelper {

    private static final String DEBUG = "Database";

    //  TODO    Database Version
    public static final int VERSION = 1;
    //  TODO    Database Name
    public static final String NAME = "Database Name";

    //  TODO    Helper instance
    private static Database instance;

    //  TODO    Database instance
    private static SQLiteDatabase database;

    private Database(Context context) {
        super(context , NAME , null , VERSION);
        //  TODO    Initialize database file
        copyDatabase(context);
    }

    private void copyDatabase(Context context) {
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

    public static synchronized Database getInstance(Context context) {
        if (instance == null)
            instance = new Database(context);
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //  TODO    Initialize database on 1st time
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion <= oldVersion) {
            Log.d(DEBUG , "SQLite database is up-to-date");
            return;
        }
        Log.d(DEBUG , "SQLite database is outdated. (" + oldVersion + " < " + newVersion + ")");
        //  TODO    Upgrade database
        Log.d(DEBUG , "SQLite database it upgraded to " + newVersion);
    }

}

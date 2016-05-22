package unicauca.movil.eplanetas.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Dario Chamorro on 22/05/2016.
 */
public class DataBaseHelper extends SQLiteOpenHelper {

    static final String NAME ="planetas.db";
    static int version = 1;

    public DataBaseHelper(Context context) {
        super(context, NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE planeta (_id INTEGER AUTO_INCREMENT PRIMARY KEY"
                +", nombre VARCHAR"
                +", gravedad FLOAT"
                +")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE planeta");
        onCreate(db);
    }
}

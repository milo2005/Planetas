package unicauca.movil.eplanetas.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import unicauca.movil.eplanetas.models.Planeta;

/**
 * Created by Dario Chamorro on 22/05/2016.
 */
public class PlanetaDao {

    private static final String TABLE="planeta";
    private static final String C_NOMBRE="nombre";
    private static final String C_GRAVEDAD="gravedad";


    SQLiteDatabase db;

    public PlanetaDao(Context context){
        DataBaseHelper helper = new DataBaseHelper(context);
        db = helper.getWritableDatabase();
    }

    public void insert(Planeta planeta){
        ContentValues cV = new ContentValues();
        cV.put(C_NOMBRE, planeta.getNombre());
        cV.put(C_GRAVEDAD, planeta.getGravedad());
        db.insert(TABLE, null, cV);
    }

    public void update(Planeta planeta){
        ContentValues cV = new ContentValues();
        cV.put(C_NOMBRE, planeta.getNombre());
        cV.put(C_GRAVEDAD, planeta.getGravedad());
        db.update(TABLE,cV, "_id=?", new String[]{planeta.getId()+""});

    }

    public void delete(long id){
        db.delete(TABLE,"_id="+id, null);
    }

    public Planeta getById(long id){

        Cursor cursor =  db.rawQuery("SELECT * FROM planeta WHERE _id="+id,null);
        return cursorToPlaneta(cursor);
    }

    public List<Planeta> getAll(){
        Cursor cursor =  db.rawQuery("SELECT * FROM planeta", null);
        return  cursorToList(cursor);
    }

    public List<Planeta> getByNombre(String nombre){
        Cursor cursor =  db.rawQuery("SELECT * FROM planeta WHERE nombre LIKE '%"+nombre+"%'", null);
        return  cursorToList(cursor);
    }

    private Planeta cursorToPlaneta(Cursor cursor) {
        Planeta planeta =  null;
        if(cursor.moveToNext()){
            planeta =  new Planeta();
            planeta.setId(cursor.getLong(0));
            planeta.setNombre(cursor.getString(1));
            planeta.setGravedad(cursor.getFloat(2));
        }
        return planeta;
    }

    private List<Planeta> cursorToList(Cursor cursor) {
        List<Planeta> data = new ArrayList<>();

        int size =  cursor.getCount();
        for(int i = 0; i<size; i++){
            Planeta p = cursorToPlaneta(cursor);
            data.add(p);
        }

        return data;
    }

}

package unicauca.movil.eplanetas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import unicauca.movil.eplanetas.adapters.PlanetaAdapter;
import unicauca.movil.eplanetas.databinding.ActivityMainBinding;
import unicauca.movil.eplanetas.db.PlanetaDao;
import unicauca.movil.eplanetas.models.Planeta;
import unicauca.movil.eplanetas.util.Preferences;

public class MainActivity extends AppCompatActivity {

    SharedPreferences preferences;

    PlanetaDao planetaDao;
    List<Planeta> data;
    PlanetaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding =  ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        preferences = getSharedPreferences(Preferences.NAME, MODE_PRIVATE);

        planetaDao = new PlanetaDao(this);
        data = new ArrayList<>();
        adapter = new PlanetaAdapter(getLayoutInflater(), data);

        binding.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadPlanetas();
    }

    private void loadPlanetas() {
        List<Planeta> list = planetaDao.getAll();
        if(list.size()==0){
            Planeta tierra = new Planeta();
            tierra.setNombre("Tierra");
            tierra.setGravedad(SensorManager.GRAVITY_EARTH);

            Planeta estrellaMuerte = new Planeta();
            estrellaMuerte.setNombre("Estrella de la Muerte");
            estrellaMuerte.setGravedad(SensorManager.GRAVITY_DEATH_STAR_I);

            planetaDao.insert(tierra);
            planetaDao.insert(estrellaMuerte);
            list = planetaDao.getAll();
        }

        for(Planeta p : list){
            data.add(p);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(Preferences.LOGIN,false);
        editor.commit();

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

        finish();

        return super.onOptionsItemSelected(item);
    }
}

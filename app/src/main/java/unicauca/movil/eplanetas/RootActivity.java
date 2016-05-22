package unicauca.movil.eplanetas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import unicauca.movil.eplanetas.util.Preferences;

public class RootActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preferences =  getSharedPreferences(Preferences.NAME, MODE_PRIVATE);
        boolean login = preferences.getBoolean(Preferences.LOGIN, false);
        Intent intent = null;
        if(login)
            intent =  new Intent(this, MainActivity.class);
        else
            intent = new Intent(this, LoginActivity.class);

        startActivity(intent);
    }
}

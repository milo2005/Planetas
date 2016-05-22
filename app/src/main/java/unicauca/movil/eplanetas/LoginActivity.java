package unicauca.movil.eplanetas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import unicauca.movil.eplanetas.databinding.ActivityLoginBinding;
import unicauca.movil.eplanetas.util.Preferences;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityLoginBinding binding;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.setOnClick(this);

        preferences = getSharedPreferences(Preferences.NAME, MODE_PRIVATE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnIn:
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean(Preferences.LOGIN, true);
                editor.commit();

                Intent intent =  new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}

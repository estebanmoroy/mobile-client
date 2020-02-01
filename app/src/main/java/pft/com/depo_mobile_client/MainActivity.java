package pft.com.depo_mobile_client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import pft.com.depo_mobile_client.familias.CrearFamiliaActivity;
import pft.com.depo_mobile_client.familias.FamiliasActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public void listarFamilias(View view) {
        Intent intent = new Intent(this, FamiliasActivity.class);
        startActivity(intent);
    }

    public void crearFamilia(View view) {
        Intent intent = new Intent(this, CrearFamiliaActivity.class);
        startActivity(intent);
    }
}
package pft.com.depo_mobile_client.familias;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import pft.com.depo_mobile_client.R;

public class FamiliasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_familias);
        FamiliasFragment FamiliasFragment = (FamiliasFragment)
                getSupportFragmentManager().findFragmentById(R.id.familias_container);

        if (FamiliasFragment == null) {
            FamiliasFragment = FamiliasFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.familias_container, FamiliasFragment)
                    .commit();
        }

    }
}

package pft.com.depo_mobile_client;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.view.View;
import pft.com.depo_mobile_client.familias.CrearFamiliaActivity;
import pft.com.depo_mobile_client.familias.FamiliasActivity;

public class MainActivity extends AppCompatActivity implements NavigationHost{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, new LoginFragment())
                    .commit();
        }
    }

    /*public void listarFamilias(View view) {
        Intent intent = new Intent(this, FamiliasActivity.class);
        startActivity(intent);
    }

    public void crearFamilia(View view) {
        Intent intent = new Intent(this, CrearFamiliaActivity.class);
        startActivity(intent);
    }*/

    /* Navigate to the given fragment.
    *
    * @param fragment       Fragment to navigate to.
    * @param addToBackstack Whether or not the current fragment should be added to the backstack.
    */
    @Override
    public void navigateTo(Fragment fragment, boolean addToBackstack) {
        FragmentTransaction transaction =
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, fragment);

        if (addToBackstack) {
            transaction.addToBackStack(null);
        }

        transaction.commit();
    }
}
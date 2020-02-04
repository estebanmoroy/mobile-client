package pft.com.depo_mobile_client.productos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import pft.com.depo_mobile_client.R;

public class ProductosListaFragment extends Fragment{

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);
  }

  @Override
  public View onCreateView(
      @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the layout for this fragment with the ProductosLista theme
    View view = inflater.inflate(R.layout.productos_lista_fragment, container, false);

    // Set up the toolbar
    setUpToolbar(view);

    return view;
  }

  private void setUpToolbar(View view){
    Toolbar toolbar = view.findViewById(R.id.app_bar);
    AppCompatActivity activity = (AppCompatActivity) getActivity();
    if (activity!=null) {
      activity.setSupportActionBar(toolbar);
    }
  }

  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater){
    menuInflater.inflate(R.menu.toolbar_menu, menu);
    super.onCreateOptionsMenu(menu, menuInflater);
  }

}

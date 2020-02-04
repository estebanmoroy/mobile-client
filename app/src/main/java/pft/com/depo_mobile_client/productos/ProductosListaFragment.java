package pft.com.depo_mobile_client.productos;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import pft.com.depo_mobile_client.R;

public class ProductosListaFragment extends Fragment{

  ListView mProductosList;
  ArrayAdapter<Producto> mProductosAdapter;

  public ProductosListaFragment(){}

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

    // Instancia del ListView.
    mProductosList = (ListView) view.findViewById(R.id.productos_list);

    ObtenerProductosTask tarea = new ObtenerProductosTask();
    tarea.execute();
    // Inicializar el adaptador con la fuente de datos.

    // Set up the toolbar
    setUpToolbar(view);

    return view;
  }

  private class ObtenerProductosTask extends AsyncTask<Void,Void,Boolean> {
    private ArrayAdapter<Producto> mProductosAdapter;

    @Override
    protected Boolean doInBackground(Void... params) {
      boolean result = true;
      URL url;
      HttpURLConnection urlConnection = null;
      try {
        String urlServicio ="http://192.168.1.32:8082/depo-REST-API/rest/productos/";

        url = new URL(urlServicio);

        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestProperty("content-type", "application/json");

        InputStream in = new BufferedInputStream(urlConnection.getInputStream());
        JSONArray respJSON = new JSONArray(getResponseText(in));

        List<Producto> Productos = new ArrayList<>();
        for(int i=0; i<respJSON.length(); i++){
          JSONObject obj = respJSON.getJSONObject(i);
          String nombre = obj.getString("nombre");
          String codigo = obj.getString("codigo");
          String familia = obj.getString("familia");
          String stockTotal = obj.getString("stockTotal");
          Productos.add(new Producto(nombre,codigo,familia,stockTotal));
        }
        mProductosAdapter = new ProductosAdapter(getActivity(), Productos);
      }
      catch(Exception ex) {
        Log.e("ServicioRest","Error!", ex);
        result = false;
      }
      return result;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
      if(aBoolean==true){
        //Relacionando la lista con el adaptador
        mProductosList.setAdapter(mProductosAdapter);
      }
    }

    private String getResponseText(InputStream inStream) {
      // http://weblogs.java.net/blog/pat/archive/2004/10/stupid_scanner_1.html
      return new Scanner(inStream).useDelimiter("\\A").next();
    }
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

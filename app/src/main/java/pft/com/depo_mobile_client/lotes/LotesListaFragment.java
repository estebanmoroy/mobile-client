package pft.com.depo_mobile_client.lotes;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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


public class LotesListaFragment extends Fragment {
    ListView mLotesList;
    ArrayAdapter<Lote> mLotesAdapter;

    public LotesListaFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment with the LotesLista theme
        View view = inflater.inflate(R.layout.fragment_lotes_lista, container, false);

        // Instancia del ListView.
        mLotesList = (ListView) view.findViewById(R.id.lotes_list);

        LotesListaFragment.ObtenerLotesTask tarea = new LotesListaFragment.ObtenerLotesTask();
        tarea.execute();
        // Inicializar el adaptador con la fuente de datos.

        // Set up the toolbar
        setUpToolbar(view);

        return view;
    }

    private class ObtenerLotesTask extends AsyncTask<Void,Void,Boolean> {
        private ArrayAdapter<Lote> mLotesAdapter;

        @Override
        protected Boolean doInBackground(Void... params) {
            boolean result = true;
            URL url;
            HttpURLConnection urlConnection = null;
            try {
                String urlServicio ="http://192.168.1.32:8082/depo-REST-API/rest/lotes/";

                url = new URL(urlServicio);

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestProperty("content-type", "application/json");

                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                JSONArray respJSON = new JSONArray(getResponseText(in));

                List<Lote> Lotes = new ArrayList<>();
                for(int i=0; i<respJSON.length(); i++){
                    JSONObject obj = respJSON.getJSONObject(i);
                    String codigo = obj.getString("codigo");
                    String producto = obj.getString("producto");
                    String cantidad = obj.getString("cantidad");
                    String fechaVencimiento = obj.getString("fechaVencimiento");
                    Lotes.add(new Lote(codigo,producto,cantidad,fechaVencimiento));
                }
                mLotesAdapter = new LotesAdapter(getActivity(), Lotes);
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
                mLotesList.setAdapter(mLotesAdapter);
            }
        }

        private String getResponseText(InputStream inStream) {
            // http://weblogs.java.net/blog/pat/archive/2004/10/stupid_scanner_1.html
            return new Scanner(inStream).useDelimiter("\\A").next();
        }
    }

    private void setUpToolbar(View view){
        Toolbar toolbar = view.findViewById(R.id.app_bar_lote);
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
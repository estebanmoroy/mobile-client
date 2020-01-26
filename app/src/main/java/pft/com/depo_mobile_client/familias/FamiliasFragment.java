package pft.com.depo_mobile_client.familias;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FamiliasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FamiliasFragment extends Fragment {
    ListView mFamiliasList ;
    ArrayAdapter<Familia> mFamiliasAdapter;

    public FamiliasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment FamiliasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FamiliasFragment newInstance() {
        FamiliasFragment fragment = new FamiliasFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_familias, container, false);

        // Instancia del ListView.
        mFamiliasList = (ListView) root.findViewById(R.id.familias_list);

        ObtenerFamiliaesTask tarea = new ObtenerFamiliaesTask();
        tarea.execute();
        // Inicializar el adaptador con la fuente de datos.
        return root;
    }

    private class ObtenerFamiliaesTask extends AsyncTask<Void,Void,Boolean> {
        private ArrayAdapter<Familia> mFamiliasAdapter;

        @Override
        protected Boolean doInBackground(Void... params) {
            boolean result = true;
            URL url;
            HttpURLConnection urlConnection = null;
            try {
                String urlServicio ="http://192.168.1.35:8082/depo-REST-API/rest/familias/";
                url = new URL(urlServicio);

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestProperty("content-type", "application/json");

                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                JSONArray respJSON = new JSONArray(getResponseText(in));

                List<Familia> Familias = new ArrayList<>();
                for(int i=0; i<respJSON.length(); i++){
                    JSONObject obj = respJSON.getJSONObject(i);
                    String nombre = obj.getString("nombre");
                    String codigo = obj.getString("codigo");
                    String descripcion = obj.getString("descripcion");
                    String incompatible = obj.getString("incompatible");
                    Familias.add(new Familia(nombre,codigo,descripcion,incompatible));
                }
                mFamiliasAdapter = new FamiliasAdapter(getActivity(), Familias);
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
                mFamiliasList.setAdapter(mFamiliasAdapter);
            }
        }

        private String getResponseText(InputStream inStream) {
            // http://weblogs.java.net/blog/pat/archive/2004/10/stupid_scanner_1.html
            return new Scanner(inStream).useDelimiter("\\A").next();
        }
    }
}

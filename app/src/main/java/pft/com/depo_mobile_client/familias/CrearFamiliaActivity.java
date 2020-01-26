package pft.com.depo_mobile_client.familias;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.LogPrinter;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import pft.com.depo_mobile_client.R;

public class CrearFamiliaActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_familia);
    }

    public void crearNuevaFamilia(View view) {
        CrearFamiliaTask tarea = new CrearFamiliaTask();
        EditText txtNombre = (EditText) findViewById(R.id.editNombre);
        EditText txtCodigo = (EditText) findViewById(R.id.editCodigo);
        EditText txtDescripcion = (EditText) findViewById(R.id.editDescripcion);
        EditText txtIncompatible = (EditText) findViewById(R.id.editIncopatibilidades);

        tarea.execute(txtNombre.getText().toString(), txtCodigo.getText().toString(), txtDescripcion.getText().toString(), txtIncompatible.getText().toString());
    }

    private class CrearFamiliaTask extends AsyncTask<String,String,Boolean> {
        @Override
        protected Boolean doInBackground(String... params ) {

            boolean result = true;
            URL url;
            HttpURLConnection urlConnection = null;
            try {
                String urlServicio ="http://192.168.1.35:8082/depo-REST-API/rest/familias/";
                url = new URL(urlServicio);

                urlConnection = (HttpURLConnection) url.openConnection();

                urlConnection.setRequestMethod("POST");
                urlConnection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);

                //Construimos el objeto Familia en formato JSON
                JSONObject dato = new JSONObject();
                dato.put("nombre", params[0]);
                dato.put("codigo", params[1]);
                dato.put("descripcion", params[2]);
                dato.put("incompatible", params[3]);


                urlConnection.setFixedLengthStreamingMode(dato.toString().getBytes().length);
                urlConnection.connect();

                DataOutputStream printout = new DataOutputStream(urlConnection.getOutputStream ());
                printout.writeBytes(dato.toString());
                printout.flush ();
                printout.close ();
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
                Toast.makeText(CrearFamiliaActivity.this, "Familia agregada", Toast.LENGTH_SHORT).show();
            }
        }
    }

}

package pft.com.depo_mobile_client.familias;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import pft.com.depo_mobile_client.R;

public class BorrarFamiliaActivity extends AppCompatActivity {

    ArrayAdapter<Familia> mFamiliasAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrar_familia);
    }

    class BorrarFamiliaTask extends AsyncTask<String, String, Boolean>{
        boolean result = true;
        URL url;
        HttpURLConnection urlConnection = null;

        protected Boolean doInBackground(String... params ) {

            try {
                String urlServicio = "http://192.168.0.103:8082/depo-REST-API/rest/familias/";
                url = new URL(urlServicio);

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("DELETE");
                urlConnection.setRequestProperty("Content-Type", "aplication/json;charset=utef-8");
                urlConnection.setDoOutput(true);
                urlConnection.connect();

                OutputStreamWriter wr = new OutputStreamWriter(urlConnection.getOutputStream());

                wr.write(String.valueOf(mFamiliasAdapter.getItemId(0)));
                wr.flush();

            }catch (Exception ex){


        }return result;
    }
}

}

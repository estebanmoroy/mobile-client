package pft.com.depo_mobile_client.familias;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import pft.com.depo_mobile_client.R;

public class FamiliasAdapter extends ArrayAdapter<Familia> {

    private Context context;
    private List<Familia> Familia;

    public FamiliasAdapter(Context context, List<Familia> Familia) {
        super(context, 0, Familia);
        this.Familia = Familia;
        this.context = context;

    }

    public int getCount() {
        return this.Familia.size();
    }

    public long getItemId(int id) {
        return id;
    }

    public Familia getItem(int position) {
        return this.Familia.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtener inflater.
        LayoutInflater inflater = LayoutInflater.from(this.context);

        // ¿Existe el view actual?
        if (null == convertView) {
            convertView = inflater.inflate(
                    R.layout.list_item_familia,
                    parent,
                    false);
        }

        // Referencias UI.
        TextView nombre = (TextView) convertView.findViewById(R.id.nombre_tv);
        TextView codigo = (TextView) convertView.findViewById(R.id.codigo_tv);
        TextView descripcion = (TextView) convertView.findViewById(R.id.descripcion_tv);
        TextView incompatible = (TextView) convertView.findViewById(R.id.incompatible_tv);

        // Familia actual.
        Familia Familia = getItem(position);

        // Setup.
        nombre.setText(Familia.getNombre());
        codigo.setText(Familia.getCodigo());
        descripcion.setText(Familia.getDescripcion());
        incompatible.setText(Familia.getIncompatible());


        return convertView;
    }
}
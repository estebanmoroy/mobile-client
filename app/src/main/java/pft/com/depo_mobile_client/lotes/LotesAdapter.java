package pft.com.depo_mobile_client.lotes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import pft.com.depo_mobile_client.R;

public class LotesAdapter extends ArrayAdapter<Lote> {

    private Context context;
    private List<Lote> Lote;

    public LotesAdapter(Context context, List<Lote> Lote) {
        super(context, 0, Lote);
        this.Lote = Lote;
        this.context = context;

    }

    public int getCount() {
        return this.Lote.size();
    }

    public long getItemId(int id) {
        return id;
    }

    public Lote getItem(int position) {
        return this.Lote.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtener inflater.
        LayoutInflater inflater = LayoutInflater.from(this.context);

        // ¿Existe el view actual?
        if (null == convertView) {
            convertView = inflater.inflate(
                    R.layout.list_item_lote,
                    parent,
                    false);
        }

        // Referencias UI.
        TextView codigo = (TextView) convertView.findViewById(R.id.l_codigo_tv);
        TextView producto = (TextView) convertView.findViewById(R.id.l_producto_tv);
        TextView cantidad = (TextView) convertView.findViewById(R.id.l_cantidad_tv);
        TextView fechaVencimiento = (TextView) convertView.findViewById(R.id.l_fechaVencimiento_tv);

        // Lote actual.
        Lote Lote = getItem(position);

        // Setup.
        codigo.setText(Lote.getCodigo());
        producto.setText(Lote.getProducto());
        cantidad.setText(Lote.getCantidad());
        fechaVencimiento.setText(Lote.getFechaVencimiento());


        return convertView;
    }

}
package pft.com.depo_mobile_client.productos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import pft.com.depo_mobile_client.R;


public class ProductosAdapter extends ArrayAdapter<Producto> {

    private Context context;
    private List<Producto> Producto;

    public ProductosAdapter(Context context, List<Producto> Producto) {
        super(context, 0, Producto);
        this.Producto = Producto;
        this.context = context;

    }

    public int getCount() {
        return this.Producto.size();
    }

    public long getItemId(int id) {
        return id;
    }

    public Producto getItem(int position) {
        return this.Producto.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtener inflater.
        LayoutInflater inflater = LayoutInflater.from(this.context);

        // ¿Existe el view actual?
        if (null == convertView) {
            convertView = inflater.inflate(
                    R.layout.list_item_productos,
                    parent,
                    false);
        }

        // Referencias UI.
        TextView nombre = (TextView) convertView.findViewById(R.id.p_nombre_tv);
        TextView codigo = (TextView) convertView.findViewById(R.id.p_codigo_tv);
        TextView familia = (TextView) convertView.findViewById(R.id.p_familias_tv);
        TextView stockTotal = (TextView) convertView.findViewById(R.id.p_stockTotal_tv);

        // Producto actual.
        Producto Producto = getItem(position);

        // Setup.
        nombre.setText(Producto.getNombre());
        codigo.setText(Producto.getCodigo());
        familia.setText(Producto.getFamilia());
        stockTotal.setText(Producto.getStockTotal());


        return convertView;
    }

}

package pft.com.depo_mobile_client.productos;

public class Producto {

    private String id;
    private String nombre;
    private String codigo;
    private String familia;
    private String stockTotal;

    public Producto(){}

    public Producto(String nombre, String codigo, String familia, String stockTotal) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.familia = familia;
        this.stockTotal = stockTotal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFamilia() {
        return familia;
    }

    public void setFamilia(String familia) {
        this.familia = familia;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getStockTotal() {
        return stockTotal;
    }

    public void setStockTotal(String stockTotal) {
        this.stockTotal = stockTotal;
    }

    @Override
    public String toString() {
        return "Producto{" +
                ", nombre='" + nombre + '\'' +
                ", codigo='" + codigo + '\'' +
                ", familia=" + familia + '\'' +
                ", stockTotal=" + stockTotal + '\'' +
                '}';
    }
}

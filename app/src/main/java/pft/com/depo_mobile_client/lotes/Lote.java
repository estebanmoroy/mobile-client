package pft.com.depo_mobile_client.lotes;

public class Lote {

    private String id;
    private String codigo;
    private String producto;
    private String cantidad;
    private String fechaVencimiento;

    public Lote(){}

    public Lote(String codigo, String producto, String cantidad, String fechaVencimiento) {
        this.codigo = codigo;
        this.producto = producto;
        this.cantidad = cantidad;
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    @Override
    public String toString() {
        return "Lote{" +
                ", codigo='" + codigo + '\'' +
                ", producto=" + producto + '\'' +
                ", cantidad=" + cantidad + '\'' +
                ", fechaVencimiento=" + fechaVencimiento + '\'' +
                '}';
    }
}

package pft.com.depo_mobile_client.familias;


public class Familia {

    private String id;
    private String nombre;
    private String codigo;
    private String descripcion;
    private String incompatible;

    public Familia(){}

    public Familia(String nombre, String codigo, String descripcion, String incompatible) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.incompatible = incompatible;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getIncompatible() {
        return incompatible;
    }

    public void setIncompatible(String incompatible) {
        this.incompatible = incompatible;
    }

    @Override
    public String toString() {
        return "Familia{" +
                ", nombre='" + nombre + '\'' +
                ", codigo='" + codigo + '\'' +
                ", descripcion=" + descripcion + '\'' +
                ", incompatible=" + incompatible + '\'' +
                '}';
    }
}
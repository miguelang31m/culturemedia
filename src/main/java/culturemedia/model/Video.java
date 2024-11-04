package culturemedia.model;

public class Video {
    private String codigo;
    private String titulo;
    private String descripcion;
    private Double duracion;

    // Constructor
    public Video(String codigo, String titulo, String descripcion, Double duracion) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.duracion = duracion;
    }

    // Getters y Setters
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getDuracion() {
        return duracion;
    }

    public void setDuracion(Double duracion) {
        this.duracion = duracion;
    }
}

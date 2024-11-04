package culturemedia.model;

import java.time.LocalDateTime;

public class Reproduccion {
    private String usuario;
    private LocalDateTime fechaHora;
    private Integer edad;
    private Video video;

    // Constructor
    public Reproduccion(String usuario, LocalDateTime fechaHora, Integer edad, Video video) {
        this.usuario = usuario;
        this.fechaHora = fechaHora;
        this.edad = edad;
        this.video = video;
    }

    // Getters y Setters
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }
}

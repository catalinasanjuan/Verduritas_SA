package cl.inacap.verduritas_fire;

import java.io.Serializable;
import cl.inacap.verduritas_fire.Cultivo;

public class Cultivo implements Serializable {
    private String tipoCultivo;
    private String fechaCultivo;
    private String fechaCosecha;

    public Cultivo(String tipoCultivo, String fechaCultivo, String fechaCosecha) {
        this.tipoCultivo = tipoCultivo;
        this.fechaCultivo = fechaCultivo;
        this.fechaCosecha = fechaCosecha;
    }

    // Métodos getter para cada campo
    public String getTipoCultivo() { return tipoCultivo; }
    public String getFechaCultivo() { return fechaCultivo; }
    public String getFechaCosecha() { return fechaCosecha; }
}

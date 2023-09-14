package model.atributos;

import model.enums.Meses;

public class FechaNacimiento {
    // ATRIBUTOS
    private String diaNacimiento;
    private Meses mesNacimiento;
    private String añoNacimiento;

    // CONSTRUCTOR
    public FechaNacimiento(String diaNacimiento, Meses mesNacimiento, String añoNacimiento) {
        this.diaNacimiento = diaNacimiento;
        this.mesNacimiento = mesNacimiento;
        this.añoNacimiento = añoNacimiento;
    }

    // MÉTODOS
    @Override
    public String toString() {
        return this.añoNacimiento + "-" + Meses.devolverFecha(mesNacimiento) + "-" + this.getDiaNacimiento();
    }
    public String getDiaNacimiento() {
        if (!diaNacimiento.contains("0") && Integer.parseInt(diaNacimiento)<10) {
            return  "0" + diaNacimiento;
        }
        return diaNacimiento;
    }
    public Meses getMesNacimiento() {
        return mesNacimiento;
    }
    public String getAñoNacimiento() {
        return añoNacimiento;
    }


}


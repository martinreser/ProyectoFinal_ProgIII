package model;

import java.util.Random;

public class FechaNacimiento {
    private String diaNacimiento;
    private Meses mesNacimiento;
    private String añoNacimiento;

    public FechaNacimiento(String diaNacimiento, Meses mesNacimiento, String añoNacimiento) {
        this.diaNacimiento = diaNacimiento;
        this.mesNacimiento = mesNacimiento;
        this.añoNacimiento = añoNacimiento;
    }

    public String getDiaNacimiento() {
        if (!diaNacimiento.contains("0") && Integer.parseInt(diaNacimiento)<10) {
            return  "0" + diaNacimiento;
        }
        return diaNacimiento;
    }

    public void setDiaNacimiento(String diaNacimiento) {
        this.diaNacimiento = diaNacimiento;
    }

    public Meses getMesNacimiento() {
        return mesNacimiento;
    }

    public void setMesNacimiento(Meses mesNacimiento) {
        this.mesNacimiento = mesNacimiento;
    }

    public String getAñoNacimiento() {
        return añoNacimiento;
    }

    public void setAñoNacimiento(String añoNacimiento) {
        this.añoNacimiento = añoNacimiento;
    }



    @Override
    public String toString() {
        return this.añoNacimiento + "-" + Meses.devolverFecha(mesNacimiento) + "-" + this.getDiaNacimiento();
    }
}


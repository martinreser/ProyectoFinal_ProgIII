package model;


import javax.swing.*;

public class Humano extends Personaje {

    public Humano(){}

    public Humano (RazaPersonaje razaPersonaje){
        super.setSalud(100);
        super.setRazaPersonaje(razaPersonaje);
        super.setImageIcon(razaPersonaje);
    }

    public Humano (RazaPersonaje razaPersonaje, String nombre, String apodo, FechaNacimiento fechaNacimiento,
                  int salud, Habilidades habilidades) {
        super.setRazaPersonaje(razaPersonaje);
        super.setNombre(nombre);
        super.setApodo(apodo);
        super.setFechaNacimiento(fechaNacimiento);
        super.setSalud(salud);
        super.setHabilidades(habilidades);
        super.setImageIcon(razaPersonaje);

    }

    @Override
    public double calcularDañoDeAtaque() {
        return (((super.valorDeAtaque() * super.efectividadDeDisparo()) - super.poderDeDefensa())/500) * 100;
    }
}

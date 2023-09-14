package model.personajes;


import model.enums.RazaPersonaje;

public class Humano extends Personaje {
    // CONSTRUCTOR
    public Humano (RazaPersonaje razaPersonaje){
        super.setRazaPersonaje(razaPersonaje);
        super.setImageIcon(razaPersonaje);
    }

    // MÉTODOS
    @Override
    public double calcularDañoDeAtaque() {
        return (((super.valorDeAtaque() * super.efectividadDeDisparo()) - super.poderDeDefensa())/500) * 100;
    }
}

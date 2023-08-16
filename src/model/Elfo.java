package model;

public class Elfo extends Personaje {
    public Elfo(RazaPersonaje razaPersonaje) {
        super.setSalud(100);
        super.setRazaPersonaje(razaPersonaje);
        super.setImageIcon(razaPersonaje);
    }

    @Override
    public double calcularDañoDeAtaque() {
        return ((((super.valorDeAtaque() * super.efectividadDeDisparo()) - super.poderDeDefensa())/500) * 100) * 1.1;
    }
}

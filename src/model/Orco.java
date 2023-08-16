package model;

public class Orco extends Personaje {

    public Orco(RazaPersonaje razaPersonaje) {
        super.setSalud(100);
        super.setRazaPersonaje(razaPersonaje);
        super.setImageIcon(razaPersonaje);
    }

    @Override
    public double calcularDañoDeAtaque() {
        return ((((super.valorDeAtaque() * super.efectividadDeDisparo()) - super.poderDeDefensa())/500) * 100) * 1.05;
    }
}

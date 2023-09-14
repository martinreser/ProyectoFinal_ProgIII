package model.atributos;

import utilidades.RandomNumberCreator;

public class Habilidades {
    // ATRIBUTOS
    private int velocidadPersonaje;     // de 1 a 10
    private int destrezaPersonaje;      // de 1 a 5
    private int fuerzaPersonaje;        // de 1 a 10
    private int nivelPersonaje;         // de 1 a 10
    private int armaduraPersonaje;      // de 1 a 10

    // CONSTRUCTOR
    public Habilidades(int velocidadPersonaje, int destrezaPersonaje, int fuerzaPersonaje, int nivelPersonaje, int armaduraPersonaje) {
        this.velocidadPersonaje = velocidadPersonaje;
        this.destrezaPersonaje = destrezaPersonaje;
        this.fuerzaPersonaje = fuerzaPersonaje;
        this.nivelPersonaje = nivelPersonaje;
        this.armaduraPersonaje = armaduraPersonaje;
    }

    // MÉTODOS
    public int calcularPoderDisparo(){
        return (destrezaPersonaje * fuerzaPersonaje * nivelPersonaje);
    }
    public double calcularEfectividad() {
        return (RandomNumberCreator.getInstance().generateRandomDouble(0.0, 1.0));
    }
    public double calcularValorAtaque() {
        return calcularEfectividad() * calcularPoderDisparo();
    }
    public int calcularPoderDefensa() {
        return (velocidadPersonaje * armaduraPersonaje);
    }
    public int getVelocidadPersonaje() {
        return velocidadPersonaje;
    }
    public int getDestrezaPersonaje() {
        return destrezaPersonaje;
    }
    public int getFuerzaPersonaje() {
        return fuerzaPersonaje;
    }
    public int getNivelPersonaje() {
        return nivelPersonaje;
    }
    public int getArmaduraPersonaje() {
        return armaduraPersonaje;
    }
}

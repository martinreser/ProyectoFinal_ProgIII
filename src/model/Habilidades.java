package model;

import java.util.Random;

public class Habilidades {
    private int velocidadPersonaje;     // de 1 a 10
    private int destrezaPersonaje;      // de 1 a 5
    private int fuerzaPersonaje;        // de 1 a 10
    private int nivelPersonaje;         // de 1 a 10
    private int armaduraPersonaje;      // de 1 a 10

    public Habilidades(int velocidadPersonaje, int destrezaPersonaje, int fuerzaPersonaje, int nivelPersonaje, int armaduraPersonaje) {
        this.velocidadPersonaje = velocidadPersonaje;
        this.destrezaPersonaje = destrezaPersonaje;
        this.fuerzaPersonaje = fuerzaPersonaje;
        this.nivelPersonaje = nivelPersonaje;
        this.armaduraPersonaje = armaduraPersonaje;
    }

    public int calcularPoderDisparo(){
        return (destrezaPersonaje * fuerzaPersonaje * nivelPersonaje);
    }

    public int calcularEfectividad() {
        Random random = new Random();
        return (random.nextInt(100));
    }

    public int calcularValorAtaque() {
        return (calcularEfectividad() * calcularPoderDisparo());
    }

    public int calcularPoderDefensa() {
        return (velocidadPersonaje * armaduraPersonaje);
    }

    public int getVelocidadPersonaje() {
        return velocidadPersonaje;
    }

    public void setVelocidadPersonaje(int velocidadPersonaje) {
        this.velocidadPersonaje = velocidadPersonaje;
    }

    public int getDestrezaPersonaje() {
        return destrezaPersonaje;
    }

    public void setDestrezaPersonaje(int destrezaPersonaje) {
        this.destrezaPersonaje = destrezaPersonaje;
    }

    public int getFuerzaPersonaje() {
        return fuerzaPersonaje;
    }

    public void setFuerzaPersonaje(int fuerzaPersonaje) {
        this.fuerzaPersonaje = fuerzaPersonaje;
    }

    public int getNivelPersonaje() {
        return nivelPersonaje;
    }

    public void setNivelPersonaje(int nivelPersonaje) {
        this.nivelPersonaje = nivelPersonaje;
    }

    public int getArmaduraPersonaje() {
        return armaduraPersonaje;
    }

    public void setArmaduraPersonaje(int armaduraPersonaje) {
        this.armaduraPersonaje = armaduraPersonaje;
    }
}

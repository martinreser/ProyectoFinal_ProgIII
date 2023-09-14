package model;

import model.personajes.Personaje;
import utilidades.RandomNumberCreator;

import javax.swing.table.TableRowSorter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {
    // ATRIBUTOS
    private static final int NUMERO_MAXIMO_PERSONAJES = 6;  // EL JUEGO ES DINÁMICO CON EL N°
    private static final int NUMERO_MAXIMO_RONDAS = 7;      // DE RONDAS Y DE PERSONAJES
    private static final List<Personaje> jugador1 = new ArrayList<>();
    private static final List<Personaje> jugador2 = new ArrayList<>();
    private static int numRonda = 0;
    private static int jugadorAtacante = 0;

    // MÉTODOS
    // PÚBLICOS
    public List<Personaje> getPersonajesJugador1() {
        return jugador1;
    }
    public List<Personaje> getPersonajesJugador2() {
        return jugador2;
    }
    public int getCantidadPersonajes() {
        return jugador1.size() + jugador2.size();
    }
    public int addPersonajeJugador(Personaje personaje, int numJugador) {
        if (numJugador == 1){
            jugador1.add(personaje);
            return 2;
        }
        if (numJugador == 2){
            jugador2.add(personaje);
            return 1;
        }
        return 0;
    }
    public void borrarPersonajes(){
        jugador1.clear();
        jugador2.clear();
    }
    public int getMaximoPersonaje(){
        return NUMERO_MAXIMO_PERSONAJES;
    }
    public int getMaximoRondas(){return NUMERO_MAXIMO_RONDAS;}
    public Personaje sortearPersonajeJug1(){
        if (!personajesVivosJug1().isEmpty()) {
            int index = RandomNumberCreator.getInstance().generateRandomInt(personajesVivosJug1().size());
            return personajesVivosJug1().get(index);
        }
        return null;
    }
    public Personaje sortearPersonajeJug2(){
        if (!personajesVivosJug2().isEmpty()) {
            int index = RandomNumberCreator.getInstance().generateRandomInt(personajesVivosJug2().size());
            return personajesVivosJug2().get(index);
        }
        return null;
    }
    public int comprobarIniciador(Personaje personaje1, Personaje personaje2){
        if (personajeVivo(personaje1) && !personajeVivo(personaje2)){
            jugadorAtacante = 2;
            return jugadorAtacante;
        }
        else if (personajeVivo(personaje2) && !personajeVivo(personaje1)){
            jugadorAtacante = 1;
            return jugadorAtacante;
        }
        else {
        jugadorAtacante = RandomNumberCreator.getInstance().generateRandomInt(1, 3);
            return jugadorAtacante;
        }
    }
    public String sortearJugador(Personaje personaje1, Personaje personaje2, boolean existeGanador){
        if (existeGanador){
            return imprimirSorteoJusto(personaje1, personaje2);
        }
        else {
            jugadorAtacante = RandomNumberCreator.getInstance().generateRandomInt(1, 3);
            return imprimirSorteoAleatorio(personaje1, personaje2);
        }
    }
    public String imprimirSorteoAleatorio(Personaje personajeSorteado, Personaje personajeSorteado2){
        return "De forma aleatoria, se ha decidido que comience el jugador " + jugadorAtacante +
                " atacando. Los personajes de esta ronda serán:\n" + "Jugador 1: " +
                personajeSorteado.getNombreYApodo() + "\nJugador 2: " + personajeSorteado2.getNombreYApodo() + "\n\n";
    }
    public String imprimirSorteoJusto(Personaje personajeSorteado, Personaje personajeSorteado2){
        return "Quisimos ser justos, y decidimos darle ventaja al jugador " + jugadorAtacante +
                " para que él comience atacando esta vez. Los personajes de esta ronda serán:\n" + "Jugador 1: " +
                personajeSorteado.getNombreYApodo() + "\nJugador 2: " + personajeSorteado2.getNombreYApodo() + "\n\n";
    }
    public List<Personaje> personajesVivosJug1(){
        List<Personaje> personajesVivos = new ArrayList<>();
        for (Personaje personaje : jugador1) {
            if (personaje.getSalud() > 0){
                personajesVivos.add(personaje);
            }
        }
        return personajesVivos;
    }
    public List<Personaje> personajesVivosJug2(){
        List<Personaje> personajesVivos = new ArrayList<>();
        for (Personaje personaje : jugador2) {
            if (personaje.getSalud() > 0){
                personajesVivos.add(personaje);
            }
        }
        return personajesVivos;
    }
    public String comprobarPersonajesJug1(){
        if (personajesVivosJug1().size() == 0){
            return "\nHan muerto todos los personajes del jugador " + 1 + ".";
        }
        return "\nAl jugador " + 1 + " le queda (n) " + personajesVivosJug1().size() + " personaje (s) vivo (s).";
    }
    public String comprobarPersonajesJug2(){
        if (personajesVivosJug2().size() == 0){
            return "\nHan muerto todos los personajes del jugador " + 2 + ".\n";
        }
        return "\nAl jugador " + 2 + " le queda (n) " + personajesVivosJug2().size() + " personaje (s) vivo (s).\n\n";
    }
    public String realizarAtaque(Personaje personajeJug1, Personaje personajeJug2){
        // Mientras que ambos jugadores tengan personajes vivos,
        // continúa el juego.
        if (numRonda > NUMERO_MAXIMO_RONDAS ) {
            reiniciarRonda();
            return "finalizar";
        }
        else {
            if (personajeVivo(personajeJug1) && personajeVivo(personajeJug2)){
                if (jugadorAtacante == 1) {
                    jugadorAtacante = 2;
                    return generarAtaque(personajeJug1, personajeJug2);
                }
                else if (jugadorAtacante == 2) {
                    jugadorAtacante = 1;
                    return generarAtaque(personajeJug2, personajeJug1);
                }
            }
            else return "muerto";
        }
        return null;
    }
    public void reiniciarRonda(){
        this.numRonda = 0;
    }
    public boolean rondaValida(){
        if (numRonda >= NUMERO_MAXIMO_RONDAS){
            reiniciarRonda();
            return false;
        }
        return true;
    }
    public String imprimirRonda(){
        numRonda++;
        String ronda = "-------- Ronda " + numRonda + " --------\n\n";
        return ronda;
    }
    public int devolverGanador() {
        if (personajesVivosJug1().size() == 0){
            return 2;
        }
        else {return 1;}
    }
    public boolean personajeVivo(Personaje personaje) {
        if (personaje.getSalud() > 0) {
            return true;
        }
        return false;
    }



    // PRIVADOS
    private String generarAtaque(Personaje personajeAtacante, Personaje personajeDefensor){
        double daño = personajeAtacante.calcularDañoDeAtaque();
        daño = corregirAtaque(daño);
        personajeDefensor.actualizarSalud(daño);
        String oracion = imprimirOracion(personajeDefensor);
        return imprimirAtaque(personajeAtacante, personajeDefensor, daño, oracion);
    }
    private String imprimirAtaque(Personaje personajeAtacante, Personaje personajeDefensor, double daño, String oracion){
        return  "-------- Ataque " + personajeAtacante.getNombre() + " --------\n" +
                personajeAtacante.getNombreYApodo() + " intentó atacar a " +
                personajeDefensor.getNombreYApodo() + ataqueAleatorio() + ", provocándole " + daño + " de daño" +
                oracion + "\n" + imprimirVidaPersonaje(personajeAtacante) + imprimirVidaPersonaje(personajeDefensor) + "\n";
    }
    private String ataqueAleatorio(){
        String ataque1 = " a cuchillazos";
        String ataque2 = " a golpes";
        String ataque3 = " a martillazos";
        String ataque4 = " a machetazos";
        List<String> ataques = Arrays.asList(ataque1, ataque2, ataque3, ataque4);
        return ataques.get(RandomNumberCreator.getInstance().generateRandomInt(ataques.size()));
    }
    private String imprimirOracion(Personaje personaje){
        if (!personajeVivo(personaje)) {
            return  ". Por desgracia, " + personaje.getNombre() + " no logró esquivar el ataque," +
                    " falleciendo al instante.";
        }
        if (personajeVivo(personaje)) {
            return ". " + personaje.getNombre() + ", tuvo suerte y logró esquivar el ataque.";
        }
        return "";
    }
    private String imprimirVidaPersonaje(Personaje personaje){
        if (personajeVivo(personaje)){
            return "- " + personaje.getNombreYApodo() + " tiene " + personaje.getSalud() + " de salud restante.\n";
        }
        return "- " + personaje.getNombreYApodo() + " ha muerto en combate. Sin embargo, su nombre quedará en la " +
                "inmortalidad por dar su vida en la batalla.\n";
    }
    private double corregirAtaque(double ataque){
        if (ataque < 0){
            return -(RandomNumberCreator.getInstance().reduceDecimal(ataque));   // A veces se generaban números negativos.
        }
        else { return RandomNumberCreator.getInstance().reduceDecimal(ataque);}
    }

}

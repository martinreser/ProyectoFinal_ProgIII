package model;

import javax.swing.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public abstract class Personaje {
    private static final List<String> nombresCargados = Arrays.asList("Acquilina", "Adelaide", "Amelia", "Beatriz", "Brenna",
            "Brunhilda", "Clotilda", "Eithne", "Eleanor", "Erika", "Gregoria", "Mathilda", "Mirabel", "Olive", "Orla",
            "Petra", "Sabina", "Sigrid", "Thomasina", "Ursula", "Zelda", "Aldous", "Arthur", "Bahram", "Bartholomew",
            "Benedict", "Bertram", "Björn", "Cassian", "Charibert", "Conrad", "Cyprian", "Dominic", "Dustin", "Ellis",
            "Finnian", "Florian", "Gage", "Galileo", "Gandalf", "Godfrey", "Godwin", "Gregory", "Haywood", "Henry",
            "Jeremiah", "Kenric", "Lancaster", "Merlin", "Neville", "Odel", "Randolf", "Theodoric", "Wymond");
    private static final List<String> apodosMujeres = Arrays.asList("La Loca", "La Morena", "La Chiquilla", "La Emperadora",
            "La Hechizada", "La Honesta", "La Justa", "La Sabia", "La Santa", "La Piadosa", "La Prudente");
    private static final List<String> apodosHombres = Arrays.asList("El Sabio", "El Emperador", "El Prudente",
            "El Hechizado", "El Honesto", "El Justo", "El Grande", "El Piadoso", "El Santo", "El Impotente", "El Justo");
    private static String ap;

    private RazaPersonaje razaPersonaje;
    private String nombre;
    private String apodo;
    private FechaNacimiento fechaNacimiento;
    private int edad;
    private int salud;
    private Habilidades habilidades;
    private ImageIcon imageIcon;

    public int getVelocidadPersonaje(){
        return habilidades.getVelocidadPersonaje();
    }

    public int getDestrezaPersonaje(){
        return habilidades.getDestrezaPersonaje();
    }

    public int getFuerzaPersonaje(){
        return habilidades.getFuerzaPersonaje();
    }

    public int getNivelPersonaje(){
        return habilidades.getNivelPersonaje();
    }

    public int getArmaduraPersonaje(){
        return habilidades.getArmaduraPersonaje();
    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    public void setImageIcon(RazaPersonaje razaPersonaje){
        switch (razaPersonaje){
            case Humano -> {
                this.imageIcon = new ImageIcon("src/cartasPersonajes/humano.png");
            }
            case Elfo -> {
                this.imageIcon = new ImageIcon("src/cartasPersonajes/elfo.png");
            }
            case Orco -> {
                this.imageIcon = new ImageIcon("src/cartasPersonajes/orco.png");
            }
        }
    };

    public String getRazaPersonaje() {
        if (razaPersonaje == null){
            return "";
        }
        return String.valueOf(razaPersonaje);
    }

    public void setRazaPersonaje(RazaPersonaje razaPersonaje) {
        this.razaPersonaje = razaPersonaje;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public FechaNacimiento getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(FechaNacimiento fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad() {
        this.edad = (2023 - Integer.parseInt(fechaNacimiento.getAñoNacimiento()));
    }

    public int getSalud() {
        return salud;
    }

    public void setSalud(int salud) {
        this.salud = salud;
    }

    public Habilidades getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(Habilidades habilidades) {
        this.habilidades = habilidades;
    }

    public abstract double calcularDañoDeAtaque();

    public int poderDeDisparo(){
        return habilidades.calcularPoderDisparo();
    }

    public int efectividadDeDisparo(){
        return habilidades.calcularEfectividad();
    }

    public int valorDeAtaque(){
        return habilidades.calcularValorAtaque();
    }

    public int poderDeDefensa(){
        return habilidades.calcularPoderDefensa();
    }

    @Override
    public String toString() {
        return  "\nTipo: " + getRazaPersonaje() +
                "\nNombre: " + getNombre() +
                "\nApodo: " + getApodo() +
                "\nFecha de Nacimiento: " + getFechaNacimiento() +
                "\nVelocidad: " + getVelocidadPersonaje() +
                "\nFuerza: " + getFuerzaPersonaje() +
                "\nNivel: " + getNivelPersonaje() +
                "\nArmadura: " + getArmaduraPersonaje() +
                "\nDestreza: " + getDestrezaPersonaje();
    }

    public static Personaje crearPersonajeAutomatico(){
        Personaje p = null;
        Random r = new Random();
        int tipoJugador = r.nextInt(1,3);
        int n = (r.nextInt(0, nombresCargados.size()-1));
        String nombre = nombresCargados.get(n);
        if (n < 21){
            ap = apodosMujeres.get(r.nextInt(0, apodosMujeres.size()-1));}
        if (n > 20){
            ap = apodosHombres.get(r.nextInt(0, apodosHombres.size()-1));}
        int velocidad = r.nextInt(1,10);
        int destreza = r.nextInt(1,5);
        int fuerza = r.nextInt(1,10);
        int nivel = r.nextInt(1,10);
        int armadura = r.nextInt(1,10);
        String diaNacimiento = String.valueOf(r.nextInt(29,31));
        Meses mesNacimiento = Meses.mesAleatorio(r.nextInt(0,2));
        String anioNacimiento = String.valueOf(r.nextInt(1723, 2026));
        FechaNacimiento f = new FechaNacimiento(diaNacimiento, mesNacimiento, anioNacimiento);
        while (Personaje.fechaInvalida(f.toString()) != 1){
            diaNacimiento = String.valueOf(r.nextInt(1,31));
            mesNacimiento = Meses.mesAleatorio(r.nextInt(0, 12));
            anioNacimiento = String.valueOf(r.nextInt(1723, 2026));
            f = new FechaNacimiento(diaNacimiento, mesNacimiento, anioNacimiento);
        }
        switch (tipoJugador){
            case 1:
                p = new Orco(RazaPersonaje.Orco);
                break;
            case 2:
                p = new Humano(RazaPersonaje.Humano);
                break;
            case 3:
                p = new Elfo(RazaPersonaje.Elfo);
                break;
        }
        p.setNombre(nombre);
        p.setApodo(ap);
        p.setFechaNacimiento(f);
        p.setHabilidades(new Habilidades(velocidad,destreza,fuerza,nivel,armadura));
        p.setEdad();
        return p;
    }

    public static int fechaInvalida(String fecha){
        try {
            LocalDate fechaActual = LocalDate.now();
            LocalDate fechaIngresada = LocalDate.parse(fecha);
            if (fechaIngresada.isAfter(fechaActual)) {
                return 3;
            }
        } catch (DateTimeParseException e){
            return 2;
        }
       return 1;
    }

    public Meses getMesNacimiento(){
        return fechaNacimiento.getMesNacimiento();
    }

    public String getAñoNacimiento() {
        return fechaNacimiento.getAñoNacimiento();
    }

    public boolean verificarAtributos() {
        if (this.nombre != null && this.apodo != null && this.fechaNacimiento != null &&
                this.habilidades != null && this.salud != 0){
            return true;
        }
        return false;
    }
}

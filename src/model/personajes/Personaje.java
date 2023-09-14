package model.personajes;

import model.atributos.FechaNacimiento;
import model.atributos.Habilidades;
import model.enums.Genero;
import model.enums.Meses;
import model.enums.RazaPersonaje;
import utilidades.RandomNumberCreator;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public abstract class Personaje {
    // ATRIBUTOS
    private RazaPersonaje razaPersonaje;
    private String nombre;
    private String apodo;
    private FechaNacimiento fechaNacimiento;
    private int edad;
    private double salud = 1;
    private Habilidades habilidades;
    private ImageIcon imageIcon;
    private Genero genero;


    // MÉTODOS
    // ABSTRACTOS
    public abstract double calcularDañoDeAtaque();

    // SOBREESCRITOS
    @Override
    public String toString() {
        return "Raza: " + this.razaPersonaje +
                "\nNombre: " + this.nombre +
                "\nApodo: " + this.apodo +
                "\nGénero: " + corregirTexto(this.genero.toString()) +
                "\nFecha de Nacimiento (dd-MM-yyyy): " + this.fechaNacimiento +
                "\nEdad: " + this.edad +
                imprimirHabilidades();
    }

    // GETTERS
    public int getVelocidadPersonaje() {
        return habilidades.getVelocidadPersonaje();
    }
    public int getDestrezaPersonaje() {
        return habilidades.getDestrezaPersonaje();
    }
    public int getFuerzaPersonaje() {
        return habilidades.getFuerzaPersonaje();
    }
    public int getNivelPersonaje() {
        return habilidades.getNivelPersonaje();
    }
    public int getArmaduraPersonaje() {
        return habilidades.getArmaduraPersonaje();
    }
    public String getNombre() {
        return nombre;
    }
    public String getApodo() {
        return apodo;
    }
    public String getRazaPersonaje() {
        if (razaPersonaje == null) {
            return "";
        }
        return String.valueOf(razaPersonaje);
    }
    public ImageIcon getImageIcon() {
        return imageIcon;
    }
    public double getSalud() {
        this.salud = RandomNumberCreator.getInstance().reduceDecimal(this.salud);
        return this.salud;
    }
    public Meses getMesNacimiento() {
        return fechaNacimiento.getMesNacimiento();
    }
    public String getAñoNacimiento() {
        return fechaNacimiento.getAñoNacimiento();
    }
    public String getDiaNacimiento() {
        return fechaNacimiento.getDiaNacimiento();
    }
    public Genero getGenero() {
        return genero;
    }
    public String getNombreYApodo(){
        return this.nombre + " '" + this.apodo + "'";
    }

    // SETTERS
    public void setImageIcon(RazaPersonaje razaPersonaje) {
        switch (razaPersonaje) {
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
    }
    public void setRazaPersonaje(RazaPersonaje razaPersonaje) {
        this.razaPersonaje = razaPersonaje;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setApodo(String apodo) {
        corregirTexto(apodo);
        if (genero == Genero.Hombre && !apodo.contains("El")){
            this.apodo = "El " + apodo;
        }
        if (genero == Genero.Mujer && !apodo.contains("La")){
            this.apodo = "La " + apodo;
        }
    }
    public void setFechaNacimiento(FechaNacimiento fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    public void setEdad() {
        this.edad = (2023 - Integer.parseInt(fechaNacimiento.getAñoNacimiento()));
    }
    public void setSalud(double salud) {
        this.salud = salud;
    }
    public void setHabilidades(Habilidades habilidades) {
        this.habilidades = habilidades;
    }
    public void setGenero(Genero genero) {
        this.genero = genero;
    }


    // HABILIDADES
    public double efectividadDeDisparo() {
        return habilidades.calcularEfectividad();
    }
    public double valorDeAtaque() {
        return habilidades.calcularValorAtaque();
    }
    public int poderDeDefensa() {
        return habilidades.calcularPoderDefensa();
    }
    public void actualizarSalud(double ataque) {
        this.salud = this.salud - ataque;
        // Si la salud quedó negativa, le asignamos 0.
        if (this.salud < 0){
            this.salud = 0;
        }
    }


    // IMPRESIÓN
    public String imprimirPersonajeTxt(int numJug){
        return "----- Personaje Creado -----\n" +
                "----- Jugador " + numJug + " -----" + "\n\n" + this;
    }
    public String imprimirHabilidades() {
        return "\nVelocidad: " + getVelocidadPersonaje() +
                "\nFuerza: " + getFuerzaPersonaje() +
                "\nNivel: " + getNivelPersonaje() +
                "\nArmadura: " + getArmaduraPersonaje() +
                "\nDestreza: " + getDestrezaPersonaje() + "\n";
    }
    public String mostrarHabilidades(){
        return "Velocidad: " + getVelocidadPersonaje() + " - Destreza: " + getDestrezaPersonaje() +
                " - Fuerza: " + getFuerzaPersonaje() + " - Nivel: " + getNivelPersonaje() +
                " - Armadura: " + getArmaduraPersonaje();
    }


    // CORRECCIÓN
    public static int fechaInvalida(String fecha) {
        // Si la fecha ingresada es una fecha futura, devuelve 3.
        try {
            LocalDate fechaActual = LocalDate.now();
            LocalDate fechaIngresada = LocalDate.parse(fecha);
            if (fechaIngresada.isAfter(fechaActual)) {
                return 3;
            }
            // Si la fecha ingresada es una fecha inexistente, devuelve 2.
        } catch (DateTimeParseException e) {
            return 2;
        }
        // Si la fecha ingresada es correcta, devuelve 1.
        return 1;
    }
    public boolean atributosInvalidos() {   // Este método solo verifica que ningún atributo sea nulo.
        if (this.nombre != null && this.apodo != null && this.fechaNacimiento != null &&
                this.habilidades != null && this.salud != 0) {
            return false;
        }
        return true;
    }
    public String corregirTexto(String texto) {
        // Este método se encarga de pasar de esto: 'maRtiN' --> 'Martin'
        // Es decir, pasa todo a minúscula excepto la primer letra.
        return texto.substring(0, 1).toUpperCase() + texto.substring(1).toLowerCase();
    }
}

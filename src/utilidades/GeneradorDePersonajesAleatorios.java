package utilidades;

import model.atributos.FechaNacimiento;
import model.atributos.Habilidades;
import model.enums.Genero;
import model.enums.Meses;
import model.enums.RazaPersonaje;
import model.personajes.Elfo;
import model.personajes.Humano;
import model.personajes.Orco;
import model.personajes.Personaje;

import java.util.Arrays;
import java.util.List;

public class GeneradorDePersonajesAleatorios {

    // Decidí utilizar la creación de nombres de este modo, y no utilizando
    // una API, ya que vi api's que generaban nombres muy aleatorios o que
    // no tenían la relación nombre - apodo que yo buscaba. De esta forma, si
    // dentro de los nombres cargados se elije uno femenino, su apodo también
    // lo será. Ejemplo: Eleanor (Nombre) 'La Hechizada' (Apodo).

    // ATRIBUTOS
    private static int numeroNombre;
    private static Genero genero;
    private static final List<String> nombresCargados = Arrays.asList("Acquilina", "Adelaide", "Amelia", "Beatriz", "Brenna",
            "Brunhilda", "Clotilda", "Eithne", "Eleanor", "Erika", "Gregoria", "Mathilda", "Mirabel", "Olive", "Orla",
            "Petra", "Sabina", "Sigrid", "Thomasina", "Ursula", "Zelda", "Aldous", "Arthur", "Bahram", "Bartholomew",
            "Benedict", "Bertram", "Björn", "Cassian", "Charibert", "Conrad", "Cyprian", "Dominic", "Dustin", "Ellis",
            "Finnian", "Florian", "Gage", "Galileo", "Gandalf", "Godfrey", "Godwin", "Gregory", "Haywood", "Henry",
            "Jeremiah", "Kenric", "Lancaster", "Merlin", "Neville", "Odel", "Randolf", "Theodoric", "Wymond");

    private static final List<String> apodosMujeres = Arrays.asList("Loca", "Morena", "Chiquilla", "Emperadora",
            "Hechizada", "Honesta", "Justa", "Sabia", "Santa", "Piadosa", "Prudente");
    private static final List<String> apodosHombres = Arrays.asList("Sabio", "Emperador", "Prudente",
            "Hechizado", "Honesto", "Justo", "Grande", "Piadoso", "Santo", "Impotente", "Justo");

    // MÉTODOS
    private static String getNombreAleatorio(){
        numeroNombre = (RandomNumberCreator.getInstance().generateRandomInt(nombresCargados.size()));
        return nombresCargados.get(numeroNombre);
    }
    private static String getApodoAleatorio(){
        if (numeroNombre < 21){
            genero = Genero.Mujer;
            return apodosMujeres.get(RandomNumberCreator.getInstance().generateRandomInt(apodosMujeres.size()));}
        if (numeroNombre > 20){
            genero = Genero.Hombre;
            return apodosHombres.get(RandomNumberCreator.getInstance().generateRandomInt(apodosHombres.size()));}
        return null;
    }
    public static Personaje generar(){
        Personaje p = null;
        // El tipo de jugador, su nombre, su apodo y sus habilidades, se crean
        // de manera automática y aleatoria.
        String nombre = getNombreAleatorio();
        String apodo = getApodoAleatorio();
        int tipoJugador = RandomNumberCreator.getInstance().generateRandomInt(1,4);  // 1 a 3
        int velocidad = RandomNumberCreator.getInstance().generateRandomInt(1,11);   // 1 a 10
        int destreza = RandomNumberCreator.getInstance().generateRandomInt(1,6);     // 1 a 5
        int fuerza = RandomNumberCreator.getInstance().generateRandomInt(1,11);      // 1 a 10
        int nivel = RandomNumberCreator.getInstance().generateRandomInt(1,11);       // 1 a 10
        int armadura = RandomNumberCreator.getInstance().generateRandomInt(1,11);    // 1 a 10
        // Crea una fecha automática, y luego verifica si la fecha creada es correcta. En caso de que no,
        // ingresa al bucle y realiza iteraciones hasta que sí lo sea.
        String diaNacimiento = String.valueOf(RandomNumberCreator.getInstance().generateRandomInt(1,32));        // 1 a 31
        Meses mesNacimiento = Meses.mesAleatorio(RandomNumberCreator.getInstance().generateRandomInt(12));                 // 1 a 12
        String anioNacimiento = String.valueOf(RandomNumberCreator.getInstance().generateRandomInt(1723,2024));  // 1723 a 2023
        FechaNacimiento f = new FechaNacimiento(diaNacimiento, mesNacimiento, anioNacimiento);
        // Ingresa al bucle para verificar.
        while (Personaje.fechaInvalida(f.toString()) != 1){
            diaNacimiento = String.valueOf(RandomNumberCreator.getInstance().generateRandomInt(1,32));
            mesNacimiento = Meses.mesAleatorio(RandomNumberCreator.getInstance().generateRandomInt(12));
            anioNacimiento = String.valueOf(RandomNumberCreator.getInstance().generateRandomInt(1723,2024));
            f = new FechaNacimiento(diaNacimiento, mesNacimiento, anioNacimiento);
        }
        // Según el número aleatorio anterior, se elige el tipo de personaje adecuado.
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
        // Por último, se setean los atributos aleatorios.
        p.setNombre(nombre);
        p.setGenero(genero);
        p.setApodo(apodo);
        p.setFechaNacimiento(f);
        p.setHabilidades(new Habilidades(velocidad,destreza,fuerza,nivel,armadura));
        p.setEdad();
        return p;
    }
}

package model;

import java.util.ArrayList;
import java.util.List;

public class Juego {
    private List<Personaje> listaPersonajes = new ArrayList<>();

    public List<Personaje> getListaPersonajes() {
        return listaPersonajes;
    }

    public void agregarPersonaje(Personaje personaje) {
        listaPersonajes.add(personaje);
    }



}

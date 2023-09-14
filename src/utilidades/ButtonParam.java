package utilidades;

import java.awt.*;

public class ButtonParam {
    // Esta clase se utiliza para reducir el contenido que se pasa dentro
    // de los parámetros de los métodos en la clase 'SimplifyCreation'.

    // ATRIBUTOS
    private String text;
    private int x;
    private int y;
    private int width;
    private int height;
    private Color colorFondo;
    private Color colorLetra;

    // CONSTRUCTOR
    public ButtonParam(String text, int x, int y, int width, int height, Color colorFondo, Color colorLetra) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.colorFondo = colorFondo;
        this.colorLetra = colorLetra;
    }

    // MÉTODOS
    public String getText() {
        return text;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {return height;}
    public Color getColorFondo() {
        return colorFondo;
    }
    public Color getColorLetra() {
        return colorLetra;
    }
}

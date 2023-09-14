package utilidades;

import java.awt.*;

public class LabelParam {
    // Esta clase se utiliza para reducir el contenido que se pasa dentro
    // de los parámetros de los métodos en la clase 'SimplifyCreation'.

    // ATRIBUTOS
    private String text;
    private int x;
    private int y;
    private int width;
    private int height;
    private Color colorLetra;
    private Font font;

    // CONSTRUCTOR
    public LabelParam(String text, int x, int y, int width, int height, Color colorLetra, Font font) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.colorLetra = colorLetra;
        this.font = font;
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
    public int getHeight() {
        return height;
    }
    public Color getColorLetra() {
        return colorLetra;
    }
    public Font getFont(){
        return font;
    }
}

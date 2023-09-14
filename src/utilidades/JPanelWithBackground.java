package utilidades;

import javax.swing.*;
import java.awt.*;

// Esta clase se encarga de crear JPanel con una imagen como fondo.
public class JPanelWithBackground extends JPanel {
    // ATRIBUTOS
    ImageIcon imageIcon;
    Image imagen;

    // CONSTRUCTOR
    public JPanelWithBackground(String string){
        this.imageIcon = new ImageIcon(string);
        this.imagen = imageIcon.getImage().getScaledInstance(3000, 1700, Image.SCALE_SMOOTH);
    }

    // MÉTODOS
    @Override
    public void paint(Graphics g) {
        g.drawImage(imagen, 0, 0, getWidth(), getHeight(),
                this);
        setOpaque(false);
        super.paint(g);
    }
}

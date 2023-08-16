import javax.swing.*;
import java.awt.*;

public class JPanelConFondo extends JPanel {
    ImageIcon imageIcon = new ImageIcon("src/img/fondo.jpg");

    Image imagen = imageIcon.getImage().getScaledInstance(2121, 1414, Image.SCALE_SMOOTH);;

    @Override
    public void paint(Graphics g) {
        g.drawImage(imagen, 0, 0, getWidth(), getHeight(),
                this);
        setOpaque(false);
        super.paint(g);
    }
}

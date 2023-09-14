package utilidades;

import javax.swing.*;
import java.awt.*;

public class SimplifyCreation {
    // Esta clase fue creada para la simplificación y el ahorro en las líneas
    // de código del programa. Además, de ayudar a evitar la repetición de código.
    public JButton createButton(ButtonParam bP) {
        JButton button = new JButton(bP.getText());
        button.setBounds(bP.getX(), bP.getY(), bP.getWidth(), bP.getHeight());
        button.setForeground(bP.getColorLetra());
        button.setBackground(bP.getColorFondo());
        return button;
    }
    public JButton createButtonTransparent(ButtonParam bP) {
        JButton button = new JButton();
        button.setName(bP.getText());
        button.setBounds(bP.getX(), bP.getY(), bP.getWidth(), bP.getHeight());
        button.setForeground(bP.getColorLetra());
        button.setBackground(bP.getColorFondo());
        button.setBorder(null);
        button.setOpaque(false);
        return button;
    }
    public JLabel createLabel(LabelParam lP) {
        JLabel label = new JLabel(lP.getText());
        label.setBounds(lP.getX(), lP.getY(), lP.getWidth(), lP.getHeight());
        label.setForeground(lP.getColorLetra());
        label.setFont(lP.getFont());
        return label;
    }
    public JPanelWithBackground createPanelWithBackground(String route, int x, int y, int width, int height) {
        JPanelWithBackground panel = new JPanelWithBackground(route);
        panel.setBounds(x, y, width, height);
        panel.setLayout(null);
        return panel;
    }
    public JPanel createPanel(int x, int y, int width, int height, Color colorBg) {
        JPanel panel = new JPanel();
        panel.setBounds(x, y, width, height);
        panel.setBackground(colorBg);
        panel.setLayout(null);
        return panel;
    }
    public void editarComponente(Component component, int x, int y, int width, int height, int tamañoLetra, Color color) {
        component.setBounds(x, y, width, height);
        component.setFont(new Font(null, 1, tamañoLetra));
        component.setForeground(color);
    }
    public JRadioButton createRadioButton(ButtonParam bP) {
        JRadioButton button = new JRadioButton(bP.getText());
        button.setBounds(bP.getX(), bP.getY(), bP.getWidth(), bP.getHeight());
        button.setForeground(bP.getColorLetra());
        button.setBackground(bP.getColorFondo());
        button.setOpaque(false);
        button.setBorder(null);
        return button;
    }
    public void añadirComponentes(JPanel panel, Component component1, Component component2, Component component3,
                                  Component component4, Component component5, Component component6) {
        panel.add(component1);
        panel.add(component2);
        panel.add(component3);
        panel.add(component4);
        panel.add(component5);
        panel.add(component6);
    }
}

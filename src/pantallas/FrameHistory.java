package pantallas;

import utilidades.SimplifyCreation;
import utilidades.ButtonParam;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameHistory extends JFrame implements ActionListener {
    // ATRIBUTOS - COMPONENTES
    private JFrame pantallaPrincipal;
    private JPanel panel;
    private JButton botonVolver;
    private SimplifyCreation sC = new SimplifyCreation();

    // CONSTRUCTOR
    public FrameHistory(JFrame pantallaPrincipal) {
        iniciarComponentes();
        this.setExtendedState(6);
        this.setLayout(null);
        this.setUndecorated(true);
        this.setResizable(false);
        this.setSize(1920, 1080);                                // Tamaño de la ventana
        this.setTitle("Orelman");                                            // Título de la aplicación
        this.setVisible(true);
        this.getContentPane().add(panel);
        this.pantallaPrincipal = pantallaPrincipal;
    }

    // MÉTODOS
    // MÉTODOS BOTONES
    @Override
    public void actionPerformed(ActionEvent e) {
        int inputt = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea volver al menú principal?", "ADVERTENCIA", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (inputt == 0){
            this.setVisible(false);
            pantallaPrincipal.setVisible(true);
        }
    }
    // INICIALIZACIÓN DE COMPONENTES
    public void iniciarComponentes(){
        // PANEL PRINCIPAL (-370, -100, 1920, 1080)
        panel = sC.createPanelWithBackground("src/img/historiaJuego.jpg", -370, -100, 1920, 1080);

        // BOTÓN VOLVER (440, 130, 100, 30)
        ButtonParam buttonParam = new ButtonParam("<< Volver", 440, 130, 100, 30, Color.BLACK, Color.WHITE);
        botonVolver = sC.createButton(buttonParam);
        botonVolver.addActionListener(this);
        botonVolver.setBorder(null);

        panel.add(botonVolver);
    }

}

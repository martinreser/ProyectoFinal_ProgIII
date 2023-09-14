package pantallas;

import model.Game;
import model.personajes.Personaje;
import utilidades.ArchivoDeTexto;
import utilidades.ButtonParam;
import utilidades.LabelParam;
import utilidades.SimplifyCreation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.SimpleFormatter;

public class FrameWinner extends JFrame implements ActionListener {
    // ATRIBUTOS
    private JFrame pantallaPrincipal;
    private JPanel panel;
    private SimplifyCreation sC = new SimplifyCreation();
    private Game juego = new Game();
    private List<Personaje> ganador;
    private List<String> personajes = new ArrayList<>();
    private JComboBox elegirPersonajes;
    private JLabel habilidades1 = new JLabel(""), fotoPersonaje = new JLabel();
    private int numGanador;

    // CONSTRUCTOR
    public FrameWinner(JFrame pantallaPrincipal) {
        asignarGanador();
        iniciarComponentes();
        this.setExtendedState(6);
        this.setLayout(null);
        this.setUndecorated(true);
        this.setResizable(false);
        this.setSize(1920, 1080);                                // Tamaño de la ventana
        this.setTitle("Orelman");                                            // Título de la aplicación
        this.setVisible(true);
        this.getContentPane().add(panel);
        this.pantallaPrincipal = pantallaPrincipal; // Asigna la referencia a la pantalla existente
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    // MÉTODOS
    // MÉTODOS BOTONES
    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        switch (cmd) {
            case "<< Volver":
                int inputt = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea volver al menú principal?", "ADVERTENCIA", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (inputt == 0) {
                    this.setVisible(false);
                    pantallaPrincipal.setVisible(true);
                    juego.borrarPersonajes();
                }
                break;
            case "Mostrar":
                mostrarPersonajes();
                break;
        }
    }

    // INICIALIZACIÓN DE COMPONENTES
    private void iniciarComponentes(){
        // PANEL PRINCIPAL (-370, -100, 1920, 1080)
        panel = sC.createPanelWithBackground("src/img/ganador.jpg", -370, -100, 1920, 1080);

        // LOGO ORELMAN (1000, 100, 600, 100)
        LabelParam labelParam1 = new LabelParam("", 1000, 100, 600, 100, Color.BLACK, new Font(null));
        JLabel logo = sC.createLabel(labelParam1);
        ImageIcon imageIconLabel = new ImageIcon("src/img/logo.png");
        ImageIcon logoRedimensionado = new ImageIcon(imageIconLabel.getImage().getScaledInstance(271, 50, Image.SCALE_SMOOTH));
        logo.setIcon(logoRedimensionado);

        // BOTÓN VOLVER (440, 130, 100, 30)
        ButtonParam buttonParam = new ButtonParam("<< Volver", 440, 130, 100, 30, Color.BLACK, Color.WHITE);
        JButton botonVolver = sC.createButton(buttonParam);
        botonVolver.addActionListener(this);
        botonVolver.setBorder(null);

        // BOTÓN MOSTRAR (1615, 333, 90, 24)
        ButtonParam buttonParam1 = new ButtonParam("Mostrar", 1615, 333, 90, 24, Color.BLACK, Color.WHITE);
        JButton botonMostrar = sC.createButton(buttonParam1);
        botonMostrar.addActionListener(this);

        elegirPersonajes = new JComboBox<>(elegirPersonaje());
        sC.editarComponente(elegirPersonajes, 1305, 335, 300, 20, 13, Color.BLACK);

        // TEXTO GANADOR --> Felicitaciones
        String text = "Felicitaciones Jugador " + numGanador;
        LabelParam labelParam2 = new LabelParam(text, 1320, 170, 500, 100, Color.WHITE, new Font(null, 1, 30));
        JLabel textoPersonaje = sC.createLabel(labelParam2);

        // TEXTO GANADOR --> Conquista
        String text2 = "¡Has conquistado el trono!";
        LabelParam labelParam3 = new LabelParam(text2, 1310, 230, 500, 100, Color.WHITE, new Font(null, 1, 30));
        JLabel textoPersonaje2 = sC.createLabel(labelParam3);

        // TEXTO GANADOR
        String text3 = "-- Tus valientes soldados --";
        LabelParam labelParam4 = new LabelParam(text3, 1310, 287, 200, 70, Color.WHITE, new Font(null, 1, 10));
        JLabel textoSoldados = sC.createLabel(labelParam4);

        // HABILIDADES PERSONAJE --> Personaje
        LabelParam labelParam8 = new LabelParam("", 1357, 765, 500, 200, Color.WHITE, new Font("Agency FB", 1, 15));
        habilidades1 = sC.createLabel(labelParam8);



        agregarGanadorTxt();
        sC.añadirComponentes(panel, textoSoldados, textoPersonaje2, fotoPersonaje, botonMostrar, elegirPersonajes,
                habilidades1);
        panel.add(textoPersonaje);
        panel.add(botonVolver);
        panel.add(logo);
    }


    // MÉTODOS EXTRAS
    private void asignarGanador(){
        numGanador = juego.devolverGanador();
        if (numGanador == 1){
            ganador = juego.getPersonajesJugador1();
        }
        if (numGanador == 2){
            ganador = juego.getPersonajesJugador2();
        }
    }
    private void mostrarPersonajes() {
        // TEXTO PERSONAJE
        Personaje personaje = devolverPersonaje(elegirPersonajes.getSelectedItem());
        fotoPersonaje.setBounds(1304, 217, 600, 800);
        fotoPersonaje.setIcon(personaje.getImageIcon());
        fotoPersonaje.setForeground(Color.WHITE);
        fotoPersonaje.setFont(new Font("Agency FB", Font.BOLD, 30));
        fotoPersonaje.setHorizontalTextPosition(JLabel.CENTER);
        fotoPersonaje.setVerticalTextPosition(JLabel.TOP);
        fotoPersonaje.setVisible(true);
        habilidades1.setText(personaje.mostrarHabilidades());
    }
    private String [] elegirPersonaje(){
        for (int i = 0; i < ganador.size(); i++) {
            if (ganador.get(i).getSalud() == 0){
                personajes.add(ganador.get(i).getNombreYApodo() + " -- En memoria");
            }
            else {
                personajes.add(ganador.get(i).getNombreYApodo() + " -- Con vida");
            }
        }
        return personajes.toArray(new String[personajes.size()]);
    }
    private Personaje devolverPersonaje(Object object){
        String nombreApodo = (String) object;
        for (Personaje personaje: ganador) {
            if (nombreApodo.contains(personaje.getNombreYApodo())){
                return personaje;
            }
        }
        return null;
    }
    private String imprimirGanador(){
        return "--- Felicitaciones Jugador " + numGanador + " ---" +
                "\n\n¡Eres el conquistador del trono!" +
                "\n\nTus soldados:" +
                "\n" + imprimirPersonajes() + "\n\n";
    }
    private String imprimirPersonajes(){
        String personajes = "";
        int numPersonaje = 1;
        for (Personaje p: ganador) {
            personajes = personajes + numPersonaje + "- [" + p.getRazaPersonaje() + "] " + p.getNombreYApodo()
                    + " [Salud: " + p.getSalud() + "]\n";
            numPersonaje++;
        }
        return personajes;
    }
    private void agregarGanadorTxt(){
        ArchivoDeTexto archivoDeTexto = new ArchivoDeTexto();
        archivoDeTexto.escribirArchivoDeTexto(imprimirGanador());
    }

}

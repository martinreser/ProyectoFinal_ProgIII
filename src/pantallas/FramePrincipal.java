package pantallas;

import utilidades.*;
import model.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class FramePrincipal extends JFrame implements ActionListener {
    // ATRIBUTOS - COMPONENTES
    private ArchivoDeTexto archivoDeTexto = new ArchivoDeTexto();
    private Game juego = new Game();
    private SimplifyCreation sC = new SimplifyCreation();
    private JLabel textoComienzo, textoPanelInferior;
    private JButton botonReglas, botonSalir, botonComenzar, botonElegirPersonajes,botonHistoria, botonPartidasAnteriores;
    private JPanel panelPrincipal;

    // CONSTRUCTOR
    public FramePrincipal() {
        iniciarComponentes();
        this.setExtendedState(6);
        this.setLayout(null);
        this.setUndecorated(true);
        this.setResizable(false);
        this.setSize(1920, 1080);                                // Tamaño de la ventana
        this.setTitle("Orelman");                                            // Título de la aplicación
        this.setVisible(true);
        this.getContentPane().setBackground(Color.WHITE);                    // Seteamos el color del fondo
        this.getContentPane().add(panelPrincipal);
        this.add(panelPrincipal);

        ImageIcon imageIcon = new ImageIcon("src/img/logoApp.png");  // Elegimos la imagen que queremos como logo
        this.setIconImage(imageIcon.getImage());                             // Seteamos el logo de la aplicación
    }

    // MÉTODOS
    // ACCIONES BOTONES
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        String nombreBoton = button.getName();
        switch (nombreBoton) {
            case "Comenzar":
                iniciarPartida();
                break;
            case "Elegir":
                iniciarEleccionPersonajes();
                break;
            case "Reglas":
                mostrarReglas();
                break;
            case "Historia":
                mostrarHistoria();
                break;
            case "Partidas":
                try {
                    mostrarPartidasAnteriores();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;
            case "Salir":
                salir();
                break;
        }
    }
    // INICIALIZACIÓN DE COMPONENTES
    private void iniciarComponentes(){

        // BOTONES
        // BOTÓN COMENZAR (1715, 515, 130, 30)
        ButtonParam buttonParam1 = new ButtonParam("Comenzar", 1715, 515, 130, 30, new Color(0, 0, 0,0), Color.WHITE);
        botonComenzar = sC.createButtonTransparent(buttonParam1);
        botonComenzar.addActionListener(this);


        LabelParam labelParam1 = new LabelParam("Comenzar", 1735, 515, 300, 30, Color.WHITE, new Font("Arial", 1, 20));
        JLabel textoComenzar = sC.createLabel(labelParam1);


        // BOTÓN ELEGIR PERSONAJES (1645, 580, 200, 30)
        ButtonParam buttonParam2 = new ButtonParam("Elegir", 1645, 580, 200, 30, new Color(0, 0, 0,0), Color.WHITE);
        botonElegirPersonajes = sC.createButtonTransparent(buttonParam2);
        botonElegirPersonajes.addActionListener(this);

        LabelParam labelParam2 = new LabelParam("Elegir Personajes", 1665, 580, 300, 30, Color.WHITE, new Font("Arial", 1, 20));
        JLabel textoElegirPersonajes = sC.createLabel(labelParam2);

        // BOTÓN REGLAS DEL JUEGO (1645, 645, 200, 30)
        ButtonParam buttonParam3 = new ButtonParam("Reglas", 1645, 645, 200, 30, new Color(0, 0, 0,0), Color.WHITE);
        botonReglas = sC.createButtonTransparent(buttonParam3);
        botonReglas.addActionListener(this);

        LabelParam labelParam3 = new LabelParam("Reglas del Juego", 1665, 645, 300, 30, Color.WHITE, new Font("Arial", 1, 20));
        JLabel textoReglas = sC.createLabel(labelParam3);

        // BOTÓN HISTORIA DEL JUEGO (1640, 705, 200, 30)
        ButtonParam buttonParam4 = new ButtonParam("Historia", 1640, 705, 200, 30, new Color(0, 0, 0,0), Color.WHITE);
        botonHistoria = sC.createButtonTransparent(buttonParam4);
        botonHistoria.addActionListener(this);

        LabelParam labelParam4 = new LabelParam("Historia del Juego", 1660, 705, 300, 30, Color.WHITE, new Font("Arial", 1, 20));
        JLabel textoHistoria = sC.createLabel(labelParam4);

        // BOTÓN SALIR (1770, 770, 70, 30)
        ButtonParam buttonParam5 = new ButtonParam("Partidas", 1640, 770, 200, 30, new Color(0, 0, 0,0), Color.WHITE);
        botonPartidasAnteriores = sC.createButtonTransparent(buttonParam5);
        botonPartidasAnteriores.addActionListener(this);

        LabelParam labelParam5 = new LabelParam("Partidas Anteriores", 1650, 770, 300, 30, Color.WHITE, new Font("Arial", 1, 20));
        JLabel textoPartidasAnteriores = sC.createLabel(labelParam5);

        // BOTÓN SALIR (1770, 770, 70, 30)
        ButtonParam buttonParam6 = new ButtonParam("Salir", 1770, 835, 70, 30, new Color(0, 0, 0,0), Color.WHITE);
        botonSalir = sC.createButtonTransparent(buttonParam6);
        botonSalir.addActionListener(this);

        LabelParam labelParam6 = new LabelParam("Salir", 1785, 835, 300, 30, Color.WHITE, new Font("Arial", 1, 20));
        JLabel textoSalir = sC.createLabel(labelParam6);


        // PANELES
        // PANEL COMIENZO (0, 0, 1000, 800) --> TÍTUlO
        panelPrincipal = new JPanelWithBackground("src/img/principal.jpg");
        panelPrincipal.setBounds(-370, -100, 1920, 1080);
        panelPrincipal.setLayout(null);

        // TEXTOS
        // TEXTO COMIENZO --> INTRODUCCIÓN
        LabelParam labelParam7 = new LabelParam("LA CONQUISTA DEL TRONO", 1350, 0, 1000, 800, Color.WHITE,
                new Font("Castellar", Font.BOLD, 30));
        textoComienzo = sC.createLabel(labelParam7);
        ImageIcon imageIconLabel = new ImageIcon("src/img/logo.png");
        Image imagen = imageIconLabel.getImage().getScaledInstance(387, 71, Image.SCALE_SMOOTH);
        ImageIcon imagenRedimensionada = new ImageIcon(imagen);
        textoComienzo.setIconTextGap(-5);
        textoComienzo.setIcon(imagenRedimensionada);
        textoComienzo.setVerticalTextPosition(JLabel.BOTTOM);
        textoComienzo.setHorizontalTextPosition(JLabel.CENTER);


        // TEXTO INFERIOR --> CREADOR
        String text = "Proyecto de Programación III - T.U.P. / Creado por Martín Reser";
        LabelParam labelParam8 = new LabelParam(text, 305, 0, 600, 30, Color.WHITE, new Font("Agency FB", 4, 20));
        textoPanelInferior = sC.createLabel(labelParam8);

        sC.añadirComponentes(panelPrincipal, textoComienzo, botonComenzar, botonElegirPersonajes, botonReglas,
                botonHistoria, botonSalir);
        sC.añadirComponentes(panelPrincipal, textoComenzar, textoElegirPersonajes, textoReglas, textoHistoria,
                textoSalir, textoPartidasAnteriores);
        panelPrincipal.add(botonPartidasAnteriores);
    }

    // BOTONES MENÚ PRINCIPAL
    private void iniciarPartida() {
        if (juego.getCantidadPersonajes() == juego.getMaximoPersonaje()) {
            FrameBattle frameBattle = new FrameBattle(this);
            this.setVisible(false);
            frameBattle.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, " No puede comenzar la partida. Los personajes áun\n" +
                    "no han sido elegidos. Seleccione 'Elegir Personajes'.", "Error del Usuario", JOptionPane.WARNING_MESSAGE);
        }
    }
    private void iniciarEleccionPersonajes(){
        int input;
        if (juego.getCantidadPersonajes() == juego.getMaximoPersonaje()){
            input = JOptionPane.showConfirmDialog(null, "Los personajes ya fueron creados. ¿Desea eliminarlos " +
                            "y crear personajes nuevos?", "CONFIRMAR ELECCIÓN PERSONAJES",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);;
                    if (input == 0){
                        // Si confirma el usuario, borramos los personajes ya cargados, y también los eliminamos
                        // del txt.
                        juego.borrarPersonajes();
                        archivoDeTexto.borrarArchivoDeTxtAPartirDeLinea(archivoDeTexto.devolverUltimaPartida());
                        JOptionPane.showMessageDialog(null, "Los personajes fueron eliminados con éxito.", "PERSONAJES ELIMINADOS",
                                JOptionPane.WARNING_MESSAGE);

                    }
        }
        else {
            FrameChooseCharacter frameChooseCharacter = new FrameChooseCharacter(this);
            this.setVisible(false);
            frameChooseCharacter.setVisible(true);
        }
    }
    private void mostrarReglas(){
        FrameRules frameRules = new FrameRules(this);
        this.setVisible(false);
        frameRules.setVisible(true);
    }
    private void mostrarHistoria(){
        FrameHistory frameHistory = new FrameHistory(this);
        this.setVisible(false);
        frameHistory.setVisible(true);
    }
    private void mostrarPartidasAnteriores() throws IOException {
        if (archivoDeTexto.vacio()) {
            JOptionPane.showMessageDialog(null, "Aún no existen partidas registradas.");
        }
        else {
            FramePreviousBattles framePreviousBattles = new FramePreviousBattles(this);
            this.setVisible(false);
            framePreviousBattles.setVisible(true);
        }
    }
    private void salir(){
        int input = JOptionPane.showConfirmDialog(null, "       ¿Está seguro que desea salir?\n" +
                "Su partida finalizará automáticamente.", "ADVERTENCIA", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (input == 0){
            System.exit(0);
        }
    }
}

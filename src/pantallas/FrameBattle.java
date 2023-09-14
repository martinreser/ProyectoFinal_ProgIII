package pantallas;

import model.*;
import model.personajes.Personaje;
import utilidades.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FrameBattle extends JFrame implements ActionListener {
    // ATRIBUTOS
    private Game juego = new Game();
    private JTextArea areaInformativa = new JTextArea(1,17);
    private JFrame pantallaPrincipal;
    private SimplifyCreation sC = new SimplifyCreation();
    private ArchivoDeTexto archivoDeTexto = new ArchivoDeTexto();
    private JPanel panel;
    private JLabel textoJugador1, textoJugador2, textoPersonaje1, textoPersonaje2, logoPersonaje1,
    logoPersonaje2, habilidades1, habilidades2;
    private JButton botonSiguienteAtaque, botonRealizarSorteo, botonSiguienteRonda, botonPresentarGanador;
    private Personaje personajeJug1 = null, personajeJug2 = null;
    private boolean existeGanador = false;
    private int ataque = 1;

    // CONSTRUCTOR
    public FrameBattle(JFrame pantallaPrincipal) {
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
    // ACCIONES BOTONES
    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        switch (cmd) {
            case "Realizar Sorteo":
                realizarSorteo();
                break;
            case "Com. Ataque":
                agregarTxt(juego.imprimirRonda());
                siguienteAtaque();
                break;
            case "Sig. Ataque":
                siguienteAtaque();
                break;
            case "Sig. Ronda":
                if (juego.rondaValida()){
                    agregarTxt(juego.imprimirRonda());
                    botonSiguienteRonda.setEnabled(false);
                    botonSiguienteAtaque.setEnabled(true);
                }
                else {
                    agregarTxt("\nSe ha alcanzado el límite de rondas. En caso de que los personajes sigan vivos," +
                            " podrán ser utilizados en futuras batallas.\n\n");
                    reiniciarBotones();
                }
                break;
            case "Presentar Ganador":
                presentarGanador();
        }
    }
    // INICIALIZACIÓN DE COMPONENTES
    private void iniciarComponentes(){
        // PANEL PRINCIPAL (-370, -100, 1920, 1080)
        panel = sC.createPanelWithBackground("src/img/batalla.jpg", -370, -100, 1920, 1080);

        // LOGO ORELMAN (1000, 100, 600, 100)
        LabelParam labelParam5 = new LabelParam("", 1000, 100, 600, 100, Color.BLACK, new Font(null));
        JLabel logo = sC.createLabel(labelParam5);
        ImageIcon imageIconLabel2 = new ImageIcon("src/img/logo.png");
        ImageIcon logoRedimensionado = new ImageIcon(imageIconLabel2.getImage().getScaledInstance(271, 50, Image.SCALE_SMOOTH));
        logo.setIcon(logoRedimensionado);

        // TEXTO JUGADORES --> JUGADOR 1 -- JUGADOR 2
        LabelParam labelParam1 = new LabelParam("Jugador 1", 810, 300, 1000, 100, Color.WHITE, new Font("Agency FB", 1, 30));
        textoJugador1 = sC.createLabel(labelParam1);

        LabelParam labelParam2 = new LabelParam("Jugador 2", 1680, 300, 1000, 100, Color.WHITE, new Font("Agency FB", 1, 30));
        textoJugador2 = sC.createLabel(labelParam2);

        // TEXTO PERSONAJES --> Personaje
        LabelParam labelParam3 = new LabelParam("Personaje: ", 520, 300, 1000, 100, Color.WHITE, new Font("Agency FB", 1, 30));
        textoPersonaje1 = sC.createLabel(labelParam3);

        LabelParam labelParam4 = new LabelParam("Personaje: ", 1400, 300, 1000, 100, Color.WHITE, new Font("Agency FB", 1, 30));
        textoPersonaje2 = sC.createLabel(labelParam4);

        // FOTO PERSONAJE --> Personaje
        LabelParam labelParam6 = new LabelParam("", 512, 215, 600, 800, Color.WHITE, new Font("Agency FB", 1, 30));
        logoPersonaje1 = sC.createLabel(labelParam6);

        LabelParam labelParam7 = new LabelParam("", 1392, 215, 600, 800, Color.WHITE, new Font("Agency FB", 1, 30));
        logoPersonaje2 = sC.createLabel(labelParam7);

        // HABILIDADES PERSONAJE --> Personaje
        LabelParam labelParam8 = new LabelParam("", 565, 765, 500, 200, Color.WHITE, new Font("Agency FB", 1, 15));
        habilidades1 = sC.createLabel(labelParam8);

        LabelParam labelParam9 = new LabelParam("", 1445, 765, 500, 200, Color.WHITE, new Font("Agency FB", 1, 15));
        habilidades2 = sC.createLabel(labelParam9);

        // BOTON  --> Sortear Personajes
        ButtonParam buttonParam3 = new ButtonParam("Realizar Sorteo", 930, 835, 147, 30, Color.BLACK, Color.WHITE);
        botonRealizarSorteo = sC.createButton(buttonParam3);
        botonRealizarSorteo.addActionListener(this);
        
        // BOTON  --> Siguiente Ataque
        ButtonParam buttonParam2 = new ButtonParam("Com. Ataque", 1093, 835, 127, 30, Color.BLACK, Color.WHITE);
        botonSiguienteAtaque = sC.createButton(buttonParam2);
        botonSiguienteAtaque.addActionListener(this);
        botonSiguienteAtaque.setEnabled(false);

        // BOTON  --> Siguiente Ronda
        ButtonParam buttonParam1 = new ButtonParam("Sig. Ronda", 1242, 835, 137, 30, Color.BLACK, Color.WHITE);
        botonSiguienteRonda = sC.createButton(buttonParam1);
        botonSiguienteRonda.addActionListener(this);
        botonSiguienteRonda.setEnabled(false);

        // BOTON  --> Presentar ganador
        ButtonParam buttonParam = new ButtonParam("Presentar Ganador", 930, 835, 437, 30, Color.BLACK, Color.WHITE);
        botonPresentarGanador = sC.createButton(buttonParam);
        botonPresentarGanador.addActionListener(this);
        botonPresentarGanador.setVisible(false);


        areaInformativa.setLineWrap(true);
        areaInformativa.setWrapStyleWord(true);
        areaInformativa.setFont(new Font("Agency FB", 1, 20));
        areaInformativa.setEditable(false);
        areaInformativa.setMargin(new Insets(5,5,5,5));

        JScrollPane scrollPane = new JScrollPane(areaInformativa);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(560, 275, 450, 450);


        agregarTxt("--- ¡Comienza la batalla! ---\n\n" + "Límite de ataques por personaje: " + juego.getMaximoRondas() + "\n\n");

        sC.añadirComponentes(panel, textoJugador1, textoJugador2, textoPersonaje1, textoPersonaje2,
                logo, logoPersonaje1);
        sC.añadirComponentes(panel, logoPersonaje2, habilidades1, habilidades2, botonSiguienteAtaque,
                botonSiguienteRonda, botonRealizarSorteo);
        panel.add(botonPresentarGanador);
        getContentPane().add(scrollPane);
    }
    // MÉTODOS EXTRAS
    private void realizarSorteo(){
        if (existeGanador){
            juego.comprobarIniciador(personajeJug1, personajeJug2);
        }
        verificarPersonajesVivos();
        personajeJug1 = juego.sortearPersonajeJug1();
        personajeJug2 = juego.sortearPersonajeJug2();
        mostrarPersonajesSorteados();
        agregarTxt(juego.sortearJugador(personajeJug1, personajeJug2, existeGanador));

        // Una vez finalizado el sorteo, habilitamos el botón de siguiente ataque.
        botonSiguienteAtaque.setEnabled(true);
        botonRealizarSorteo.setEnabled(false);
        existeGanador = false;
    }
    private void siguienteAtaque(){
        String a = juego.realizarAtaque(personajeJug1, personajeJug2);
        if (a.equals("finalizar")){
            reiniciarBotones();
        }
        if (a.contains("Ataque")){agregarTxt(a);}
        if (ataque % 2 == 0 && !a.equals("muerto")){
        botonSiguienteAtaque.setEnabled(false);
        botonSiguienteRonda.setEnabled(true);
        }
        ataque++;
        botonSiguienteAtaque.setText("Sig. Ataque");
        if (!juego.personajeVivo(personajeJug1) || !juego.personajeVivo(personajeJug2)) {
            juego.reiniciarRonda();
            agregarTxt(juego.comprobarPersonajesJug1());
            agregarTxt(juego.comprobarPersonajesJug2());
            existeGanador = true;
            ataque = 1;
            reiniciarBotones();
            verificarPersonajesVivos();
        }
    }
    private void agregarTxt(String texto){
        archivoDeTexto.escribirArchivoDeTexto(texto);
        areaInformativa.append(texto);
    }
    private void mostrarPersonajesSorteados() {
        logoPersonaje1.setIcon(personajeJug1.getImageIcon());
        logoPersonaje2.setIcon(personajeJug2.getImageIcon());
        textoPersonaje1.setText("Personaje: " + personajeJug1.getNombre());
        textoPersonaje2.setText("Personaje: " + personajeJug2.getNombre());
        habilidades1.setText(personajeJug1.mostrarHabilidades());
        habilidades2.setText(personajeJug2.mostrarHabilidades());
    }
    private void presentarGanador(){
        FrameWinner frameWinner= new FrameWinner(pantallaPrincipal);
        this.setVisible(false);
        frameWinner.setVisible(true);
    }
    private void reiniciarBotones(){
        botonSiguienteAtaque.setEnabled(false);
        botonSiguienteRonda.setEnabled(false);
        botonRealizarSorteo.setEnabled(true);
        botonSiguienteAtaque.setText("Com. Ataque");
    }
    private void finalizarPartida() {
        botonSiguienteAtaque.setVisible(false);
        botonSiguienteRonda.setVisible(false);
        botonRealizarSorteo.setVisible(false);
        botonPresentarGanador.setVisible(true);

    }
    private void verificarPersonajesVivos(){
        if (juego.personajesVivosJug1().size() == 0 || juego.personajesVivosJug2().size() == 0){
            finalizarPartida();
        }
    }

}

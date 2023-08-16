import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class MiMarco extends JFrame implements ActionListener {
    Juego juego = new Juego();

    Integer [] anios = elegirAnios();
    JComboBox elegirDia = new JComboBox<>(elegirDia()), elegirMes = new JComboBox<>(Meses.values()),
    elegirAnio = new JComboBox<>(elegirAnios()), elegirVelocidad = new JComboBox<>(elegirDe1a10()), elegirFuerza = new JComboBox<>(elegirDe1a10()),
    elegirNivel = new JComboBox<>(elegirDe1a10()), elegirArmadura = new JComboBox<>(elegirDe1a10()),
    elegirDestreza = new JComboBox<>(elegirDe1a5());

    int numJugador = 1, numRival = 1;
    JButton botonAutomatico, botonManual, botonComenzar, botonConfirmar;
    JRadioButton botonHumano, botonOrco, botonElfo;
    TextField campoNombre = new TextField(), campoApodo = new TextField(), campoTipo = new TextField(), campoSalud = new TextField();

    JLabel textoEscojaCreacion, textoEscojaPersonaje, titulo, textoPanelInferior, textoComienzo, textoInformativo,
            textoAutomatico, textoAutomatico2;

    JLabel textoPersonaje = new JLabel(), textoNombre = new JLabel("Nombre:"), textoApodo = new JLabel("Apodo:"),
    textoTipo = new JLabel("Tipo:"), textoJugador = new JLabel(), textoFechaNacimiento = new JLabel("Fecha de Nacimiento:"),
    textoDia = new JLabel("Día"), textoMes = new JLabel("Mes"), textoAnio = new JLabel("Año"),
    textoSalud = new JLabel("Salud:"), textoVelocidad = new JLabel("Velocidad:"), textoFuerza = new JLabel("Fuerza:"),
    textoArmadura = new JLabel("Armadura:"), textoNivel = new JLabel("Nivel:"), textoDestreza = new JLabel("Destreza:");

    JPanel panelSuperior, panelCentral, panelInferior, panelComienzo, panelBatalla, panelElegirPersonaje;

    ButtonGroup grupoPersonajes;
    Personaje personaje;
    MiMarco() {
        iniciarComponentes();
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);            // Salir de la aplicación
        this.setLayout(null);
        this.setSize(1000, 800);                          // Tamaño de la ventana
        this.setResizable(false);                                     // No se puede achicar la ventana
        this.setTitle("Juego Arcade");                                // Título de la aplicación
        this.setVisible(true);
        this.getContentPane().setBackground(Color.WHITE);             // Seteamos el color del fondo
        this.add(panelComienzo);
        this.add(panelSuperior);
        this.add(panelCentral);
        this.add(panelInferior);
        this.add(panelElegirPersonaje);


        ImageIcon imageIcon = new ImageIcon("src/img/logoApp.png");  // Elegimos la imagen que queremos como logo
        this.setIconImage(imageIcon.getImage());                       // Seteamos el logo de la aplicación


    }

    private void iniciarComponentes(){

        // BOTONES
        // BOTÓN COMENZAR (0, 0, 100,30)
        botonComenzar = new JButton("Comenzar");
        botonComenzar.setBounds(450,200,100,30);
        botonComenzar.setBackground(Color.BLACK);
        botonComenzar.setForeground(Color.WHITE);
        botonComenzar.addActionListener(this);

        // BOTÓN AUTOMÁTICO (400, 100, 100, 30)
        botonAutomatico = new JButton("Automático");
        botonAutomatico.setBounds(220,100, 100,30);
        botonAutomatico.setBackground(Color.BLACK);
        botonAutomatico.setForeground(Color.WHITE);
        botonAutomatico.addActionListener(this);
        botonAutomatico.setEnabled(false);

        // BOTÓN CONFIRMAR (0, 0, 100,30)
        botonConfirmar = new JButton("Confirmar");
        botonConfirmar.setBounds(580,300,100,30);
        botonConfirmar.setBackground(Color.BLACK);
        botonConfirmar.setForeground(Color.WHITE);
        botonConfirmar.addActionListener(this);

        // BOTÓN MANUAL (500, 100, 100, 30)
        botonManual = new JButton("Manual");
        botonManual.setBounds(330,100, 100,30);
        botonManual.setBackground(Color.BLACK);
        botonManual.setForeground(Color.WHITE);
        botonManual.addActionListener(this);
        botonManual.setEnabled(false);

        // BOTÓN ORCO (450, 100, 100, 30)
        botonOrco = new JRadioButton("Orco");
        botonOrco.setBounds(590,100, 75,30);
        botonOrco.setFont(new Font(null, 0, 15));
        botonOrco.setBackground(Color.white);
        botonOrco.setForeground(Color.black);
        botonOrco.addActionListener(this);
        botonOrco.setEnabled(false);

        // BOTÓN HUMANO (450, 100, 100, 30)
        botonHumano = new JRadioButton("Humano");
        botonHumano.setBounds(670,100,100,30);
        botonHumano.setFont(new Font(null, 0, 15));
        botonHumano.setBackground(Color.white);
        botonHumano.setForeground(Color.black);
        botonHumano.addActionListener(this);
        botonHumano.setEnabled(false);
        botonHumano.setHorizontalTextPosition(JLabel.RIGHT);
        botonHumano.setVerticalTextPosition(JLabel.CENTER);


        // BOTÓN ELFO (600, 100, 100, 30)
        botonElfo = new JRadioButton("Elfo");
        botonElfo.setBounds(770,100, 100,30);
        botonElfo.setFont(new Font(null, 0, 15));
        botonElfo.setBackground(Color.white);
        botonElfo.setForeground(Color.black);
        botonElfo.addActionListener(this);
        botonElfo.setEnabled(false);

        grupoPersonajes = new ButtonGroup();
        grupoPersonajes.add(botonOrco);
        grupoPersonajes.add(botonElfo);
        grupoPersonajes.add(botonHumano);


        // PANELES
        // PANEL COMIENZO (0, 0, 1000, 800) --> TÍTUlO
        panelComienzo = new JPanelConFondo();
        panelComienzo.setBackground(new Color(192, 192, 192));
        panelComienzo.setBounds(0, 0, 1000, 800);
        panelComienzo.setLayout(null);

        // PANEL SUPERIOR (0, 0, 1000, 80) --> TÍTUlO
        panelSuperior = new JPanel();
        panelSuperior.setBackground(new Color(0, 0, 0));
        panelSuperior.setBounds(0, 0, 1000, 80);
        panelSuperior.setLayout(null);
        panelSuperior.setVisible(false);

        // PANEL CENTRAL (0, 100, 1000, 200) --> CONTENIDO PRINCIPAL
        panelCentral = new JPanel();
        panelCentral.setBackground(new Color(255, 255, 255));
        panelCentral.setBounds(0, 80, 1000, 220);
        panelCentral.setLayout(null);
        panelCentral.setVisible(false);

        // PANEL ELEGIR PERSONAJE (0, 300, 1000, 435) --> ELEGIR PERSONAJE
        panelElegirPersonaje = new JPanel();
        panelElegirPersonaje.setBackground(new Color(255, 255, 255));
        panelElegirPersonaje.setBounds(0, 300, 1000, 435);
        panelElegirPersonaje.setLayout(null);
        panelElegirPersonaje.setVisible(false);

        // PANEL BATALLA (0, 100, 1000, 70) --> BATALLA
        panelBatalla = new JPanel();
        panelBatalla.setBackground(Color.WHITE);
        panelBatalla.setBounds(0, 80, 1000, 655);
        panelBatalla.setLayout(null);
        panelBatalla.setVisible(false);

        // PANEL INFERIOR (0, 735, 1000, 65) --> PIE DE PÁGINA
        panelInferior = new JPanel();
        panelInferior.setBackground(Color.black);
        panelInferior.setBounds(0, 735, 1000, 65);
        panelInferior.setLayout(null);
        panelInferior.setVisible(false);

        // TEXTOS
        // TEXTO COMIENZO --> INTRODUCCIÓN
        textoComienzo = new JLabel();
        textoComienzo.setText("BIENVENIDO AL JUEGO ARCADE");
        ImageIcon imageIconLabel = new ImageIcon("src/img/logo.png");
        Image imagen = imageIconLabel.getImage().getScaledInstance(387, 71, Image.SCALE_SMOOTH);
        ImageIcon imagenRedimensionada = new ImageIcon(imagen);
        textoComienzo.setIconTextGap(-10);
        textoComienzo.setIcon(imagenRedimensionada);
        textoComienzo.setForeground(Color.white);
        textoComienzo.setVerticalTextPosition(JLabel.BOTTOM);
        textoComienzo.setHorizontalTextPosition(JLabel.CENTER);
        textoComienzo.setFont(new Font("Agency FB", Font.BOLD, 50));
        textoComienzo.setBounds(250,-300,1000,800);

        // TEXTO SUPERIOR --> TÍTULO
        titulo = new JLabel();
        ImageIcon imageIconLabel2 = new ImageIcon("src/img/logo.png");
        ImageIcon imagenRedimensionada2 = new ImageIcon(imageIconLabel2.getImage().getScaledInstance(271, 50, Image.SCALE_SMOOTH));
        titulo.setIcon(imagenRedimensionada2);
        titulo.setForeground(Color.BLACK);                            // Setear color del texto
        titulo.setFont(new Font("Agency FB", Font.BOLD, 30));
        titulo.setVerticalTextPosition(JLabel.CENTER);
        titulo.setHorizontalTextPosition(JLabel.RIGHT);
        titulo.setBounds(375,-10,600,100);

        // TEXTO MEDIO --> CONTENIDO

        textoEscojaCreacion = new JLabel("1. Crear Personaje:");
        textoEscojaCreacion.setForeground(Color.black);
        textoEscojaCreacion.setFont(new Font("Agency FB", Font.BOLD, 30));
        textoEscojaCreacion.setBounds(230,0,500,100);

        textoEscojaPersonaje = new JLabel("2. Escoja su personaje:");
        textoEscojaPersonaje.setForeground(Color.black);
        textoEscojaPersonaje.setFont(new Font("Agency FB", Font.BOLD, 30));
        textoEscojaPersonaje.setBounds(590,0,300,100);

        // TEXTO INFERIOR --> CREADOR
        textoPanelInferior = new JLabel("Proyecto de Programación III - T.U.P. / Creado por Martín Reser");
        textoPanelInferior.setForeground(Color.white);
        textoPanelInferior.setFont(new Font("Agency FB", 4, 20));
        textoPanelInferior.setBounds(305,0,600,30);

        // TEXTO INFORMATIVO --> Creando personaje ...
        textoInformativo = new JLabel("Creando personaje " + numJugador + " ...");
        textoInformativo.setForeground(Color.black);
        textoInformativo.setFont(new Font("Agency FB", 1, 15));
        textoInformativo.setBounds(450,5,600,30);

        // TEXTO INFORMATIVO --> Creando personaje ...
        textoAutomatico = new JLabel("Al crear los personajes de forma automática, no podrá modificar sus atributos.");
        textoAutomatico.setForeground(Color.black);
        textoAutomatico.setFont(new Font("Agency FB", 1, 20));
        textoAutomatico.setBounds(400,300,1000,100);
        textoAutomatico.setVisible(false);

        textoAutomatico2 = new JLabel("Si el personaje no es de su agrado, presione el botón 'Automático' hasta que lo sea.");
        textoAutomatico2.setForeground(Color.black);
        textoAutomatico2.setFont(new Font("Agency FB", 1, 20));
        textoAutomatico2.setBounds(385,320,1000,100);
        textoAutomatico2.setVisible(false);

        panelElegirPersonaje.add(textoAutomatico2);
        panelElegirPersonaje.add(textoAutomatico);
        panelCentral.add(textoInformativo);
        panelComienzo.add(textoComienzo);
        panelComienzo.add(botonComenzar);
        panelSuperior.add(titulo);
        panelCentral.add(botonHumano);
        panelCentral.add(botonOrco);
        panelCentral.add(botonElfo);
        panelCentral.add(botonAutomatico);
        panelCentral.add(botonManual);
        panelCentral.add(textoEscojaPersonaje);
        panelCentral.add(textoEscojaCreacion);
        panelInferior.add(textoPanelInferior);
        panelCentral.setVisible(false);



    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        switch (cmd) {
            case "Comenzar":
                panelComienzo.setVisible(false);
                panelSuperior.setVisible(true);
                panelCentral.setVisible(true);
                panelInferior.setVisible(true);
                botonAutomatico.setEnabled(true);
                botonManual.setEnabled(true);
                break;
            case "Humano":
                personaje = new Humano(RazaPersonaje.Humano);
                elegirPersonaje();
                break;
            case "Orco":
                personaje = new Orco(RazaPersonaje.Orco);
                elegirPersonaje();
                break;
            case "Elfo":
                personaje = new Elfo(RazaPersonaje.Elfo);
                elegirPersonaje();
                break;
            case "Manual":
                botonHumano.setEnabled(true);
                botonElfo.setEnabled(true);
                botonOrco.setEnabled(true);
                break;
            case "Automático":
                textoAutomatico.setVisible(true);
                textoAutomatico2.setVisible(true);
                personaje = Personaje.crearPersonajeAutomatico();
                botonHumano.setEnabled(false);
                botonElfo.setEnabled(false);
                botonOrco.setEnabled(false);
                elegirPersonaje();
                mostrarAtributosAutomaticos();
                break;
            case "Confirmar":
                int input = 1;
                if (campoNombre.getText().equals("") || campoApodo.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Los campos de nombre y/o apodo," +
                            " no pueden estar vacíos.");
                } else if (!personaje.verificarAtributos() || input != 0) {
                    if (juego.getListaPersonajes().size() < 6) {
                        Meses m = (Meses) elegirMes.getSelectedItem();
                        String dia = (String) elegirDia.getSelectedItem();
                        String anio = String.valueOf(elegirAnio.getSelectedItem());
                        FechaNacimiento f = new FechaNacimiento(dia, m, anio);
                        if (personaje.fechaInvalida(f.toString()) == 3) {
                            LocalDate fechaActual = LocalDate.now();
                            JOptionPane.showMessageDialog(null, "No puede viajar al futuro. Ingrese una fecha anterior a " + fechaActual +
                                    " (aaaa-mm-dd)");
                        }
                        if (personaje.fechaInvalida(f.toString()) == 2) {
                            JOptionPane.showMessageDialog(null, "Ingrese una fecha válida. " + m.name() +
                                    " no tiene " + dia + " dias.");
                        }
                        if (personaje.fechaInvalida(f.toString()) == 1) {
                            personaje.setFechaNacimiento(f);
                            personaje.setNombre(campoNombre.getText());
                            personaje.setApodo(campoApodo.getText());
                            Habilidades h = new Habilidades((int) elegirVelocidad.getSelectedItem(), (int) elegirDestreza.getSelectedItem(),
                                    (int) elegirFuerza.getSelectedItem(), (int) elegirNivel.getSelectedItem(), (int) elegirArmadura.getSelectedItem());
                            personaje.setHabilidades(h);
                            personaje.setEdad();
                            input = 0;
                        }
                    }
                }
                if (personaje.verificarAtributos() && input == 0) {
                    input = JOptionPane.showConfirmDialog(null, "Usted eligió:" +
                                    personaje, "Confirmar personaje ...",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (input == 0) {
                        JOptionPane.showMessageDialog(null, "PERSONAJE  CREADO");
                        if (juego.getListaPersonajes().size() >= 3) {
                            numRival++;
                        }
                        numJugador++;
                        panelElegirPersonaje.setVisible(false);
                        botonOrco.setEnabled(false);
                        botonHumano.setEnabled(false);
                        botonElfo.setEnabled(false);
                        juego.agregarPersonaje(personaje);
                        textoInformativo.setText("Creando personaje " + numJugador + " ...");
                        if (juego.getListaPersonajes().size() >= 3) {
                            textoInformativo.setText("Creando rival " + numRival + " ...");
                            textoInformativo.setBounds(460, 5, 600, 30);
                        }
                        resetearEntradas();
                    }
                }
                break;
            case "Comenzar Batalla":
        }
        textoPersonaje.setVisible(true);
        if (juego.getListaPersonajes().size() == 6) {
            textoInformativo.setBounds(420, 5, 600, 30);
            textoInformativo.setText("Creación de personajes finalizada.");
            JOptionPane.showMessageDialog(null, "TODOS LOS PERSONAJES FUERON CREADOS");
            panelCentral.setVisible(false);
            panelElegirPersonaje.setVisible(false);
            panelBatalla.setVisible(true);

        }
    }


    public void mostrarAtributosAutomaticos() {
        campoNombre.setText(personaje.getNombre());
        campoNombre.setEditable(false);
        campoApodo.setText(personaje.getApodo());
        campoApodo.setEditable(false);
        ArrayList<Integer> anios2 = new ArrayList<>();
        for (Integer anio : anios) {
            anios2.add(anio);
        }
        elegirDia.setSelectedIndex(Integer.parseInt(personaje.getFechaNacimiento().getDiaNacimiento())-1);
        elegirMes.setSelectedIndex(Integer.parseInt(Meses.devolverFecha(personaje.getMesNacimiento()))-1);
        elegirAnio.setSelectedIndex(anios2.indexOf(Integer.parseInt(personaje.getAñoNacimiento())));
        elegirVelocidad.setSelectedIndex(personaje.getVelocidadPersonaje()-1);
        elegirDestreza.setSelectedIndex(personaje.getDestrezaPersonaje()-1);
        elegirFuerza.setSelectedIndex(personaje.getFuerzaPersonaje()-1);
        elegirArmadura.setSelectedIndex(personaje.getArmaduraPersonaje()-1);
        elegirNivel.setSelectedIndex(personaje.getNivelPersonaje()-1);
        elegirDia.setEnabled(false);
        elegirMes.setEnabled(false);
        elegirAnio.setEnabled(false);
        elegirVelocidad.setEnabled(false);
        elegirDestreza.setEnabled(false);
        elegirFuerza.setEnabled(false);
        elegirArmadura.setEnabled(false);
        elegirNivel.setEnabled(false);
    }
    public void resetearEntradas(){
        grupoPersonajes.clearSelection();
        campoApodo.setText("");
        campoNombre.setText("");
        campoTipo.setText("");
        campoSalud.setText("");
        elegirDia.setSelectedIndex(0);
        elegirAnio.setSelectedIndex(0);
        elegirMes.setSelectedIndex(0);
        elegirDestreza.setSelectedIndex(0);
        elegirArmadura.setSelectedIndex(0);
        elegirNivel.setSelectedIndex(0);
        elegirVelocidad.setSelectedIndex(0);
        elegirFuerza.setSelectedIndex(0);
        campoApodo.setEditable(true);
        campoNombre.setEditable(true);
        elegirDia.setEnabled(true);
        elegirAnio.setEnabled(true);
        elegirMes.setEnabled(true);
        elegirDestreza.setEnabled(true);
        elegirArmadura.setEnabled(true);
        elegirNivel.setEnabled(true);
        elegirVelocidad.setEnabled(true);
        elegirFuerza.setEnabled(true);
    }
    public void elegirPersonaje (){
        panelElegirPersonaje.setVisible(true);
        // TEXTO PERSONAJE --> CREADO
        if (personaje != null) {
            textoPersonaje.setText(personaje.getRazaPersonaje());
            ImageIcon iconoPersonajeRedimensionado = new ImageIcon(personaje.getImageIcon().getImage().getScaledInstance(284, 350, Image.SCALE_SMOOTH));
            textoPersonaje.setIcon(iconoPersonajeRedimensionado);
            textoPersonaje.setForeground(Color.black);
            textoPersonaje.setFont(new Font("Agency FB", Font.BOLD, 30));
            textoPersonaje.setBounds(50, -55, 290, 500);
            textoPersonaje.setVisible(false);
            textoPersonaje.setHorizontalTextPosition(JLabel.CENTER);
            textoPersonaje.setVerticalTextPosition(JLabel.TOP);


            textoJugador.setText("Jugador " + numJugador);
            textoJugador.setBounds(580, -5, 250, 50);
            textoJugador.setFont(new Font("Agency FB", Font.BOLD, 30));
            if (juego.getListaPersonajes().size() >= 3){
                textoJugador.setBounds(590, -5, 250, 50);
                textoJugador.setText("Rival " + numRival);
            }


            textoTipo.setBounds(350, 101, 80, 20);
            textoTipo.setFont(new Font(null, 1, 15));
            campoTipo.setBounds(430, 100, 110,25);
            campoTipo.setFont(new Font(null, 1, 15));
            campoTipo.setText(textoPersonaje.getText());
            campoTipo.setEditable(false);
            campoTipo.setVisible(true);

            textoNombre.setBounds(350, 151, 80, 20);
            textoNombre.setFont(new Font(null, 1, 15));
            campoNombre.setBounds(430, 150, 110,25);
            campoNombre.setFont(new Font(null, 1, 15));
            campoNombre.setVisible(true);


            textoApodo.setBounds(350, 201, 80, 20);
            textoApodo.setFont(new Font(null, 1, 15));
            campoApodo.setBounds(430, 200, 110,25);
            campoApodo.setFont(new Font(null, 1, 15));
            campoApodo.setVisible(true);

            textoSalud.setBounds(350, 251, 80, 20);
            textoSalud.setFont(new Font(null, 1, 15));
            campoSalud.setBounds(430, 250, 110,25);
            campoSalud.setFont(new Font(null, 1, 15));
            campoSalud.setText(String.valueOf(personaje.getSalud()));
            campoSalud.setEditable(false);
            campoSalud.setVisible(true);

            textoFechaNacimiento.setBounds(550, 101, 200, 20);
            textoFechaNacimiento.setFont(new Font(null, 1, 15));

            textoDia.setBounds(729,75,50,20);
            textoDia.setFont(new Font(null, 1, 15));
            elegirDia.setBounds(720, 100, 50, 25);

            textoMes.setBounds(815,75,50,20);
            textoMes.setFont(new Font(null, 1, 15));
            elegirMes.setBounds(785, 100, 100, 25);

            textoAnio.setBounds(912,75,50,20);
            textoAnio.setFont(new Font(null, 1, 15));
            elegirAnio.setBounds(900, 100, 60, 25);

            textoVelocidad.setBounds(590,152,200,20);
            textoVelocidad.setFont(new Font(null, 1, 15));
            elegirVelocidad.setBounds(720, 150, 50, 25);

            textoFuerza.setBounds(785,152,200,20);
            textoFuerza.setFont(new Font(null, 1, 15));
            elegirFuerza.setBounds(905, 150, 50, 25);

            textoNivel.setBounds(605,201,200,20);
            textoNivel.setFont(new Font(null, 1, 15));
            elegirNivel.setBounds(720, 200, 50, 25);
            elegirNivel.setForeground(Color.black);

            textoArmadura.setBounds(785,201,200,20);
            textoArmadura.setFont(new Font(null, 1, 15));
            elegirArmadura.setBounds(905, 200, 50, 25);

            textoDestreza.setBounds(590,251,200,20);
            textoDestreza.setFont(new Font(null, 1, 15));
            elegirDestreza.setBounds(720, 250, 50, 25);

            botonConfirmar.setVisible(true);

            panelElegirPersonaje.add(textoDestreza);
            panelElegirPersonaje.add(elegirDestreza);
            panelElegirPersonaje.add(textoNivel);
            panelElegirPersonaje.add(elegirNivel);
            panelElegirPersonaje.add(textoArmadura);
            panelElegirPersonaje.add(elegirArmadura);
            panelElegirPersonaje.add(textoFuerza);
            panelElegirPersonaje.add(elegirFuerza);
            panelElegirPersonaje.add(textoVelocidad);
            panelElegirPersonaje.add(elegirVelocidad);
            panelElegirPersonaje.add(textoSalud);
            panelElegirPersonaje.add(campoSalud);
            panelElegirPersonaje.add(botonConfirmar);
            panelElegirPersonaje.add(textoAnio);
            panelElegirPersonaje.add(textoMes);
            panelElegirPersonaje.add(textoDia);
            panelElegirPersonaje.add(elegirDia);
            panelElegirPersonaje.add(elegirAnio);
            panelElegirPersonaje.add(elegirMes);
            panelElegirPersonaje.add(textoFechaNacimiento);
            panelElegirPersonaje.add(textoJugador);
            panelElegirPersonaje.add(textoNombre);
            panelElegirPersonaje.add(textoPersonaje);
            panelElegirPersonaje.add(textoApodo);
            panelElegirPersonaje.add(textoTipo);
            panelElegirPersonaje.add(campoNombre);
            panelElegirPersonaje.add(campoApodo);
            panelElegirPersonaje.add(campoTipo);
        }
    }


    public String [] elegirDia(){
        return new String[] {"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15",
                "16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
    }
    public Integer [] elegirAnios(){
        Integer [] anios = new Integer[301];
        for (int i = 0; i <= 300; i++) {
            anios[i] = 2023 - i;
        }
        return anios;
    }
    public Integer [] elegirDe1a10(){
        return new Integer[] {1,2,3,4,5,6,7,8,9,10};
    }
    public Integer[] elegirDe1a5(){return new Integer[] {1,2,3,4,5};}

}

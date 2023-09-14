package pantallas;

import model.atributos.FechaNacimiento;
import model.atributos.Habilidades;
import model.enums.Genero;
import model.enums.Meses;
import model.enums.RazaPersonaje;
import model.personajes.Elfo;
import model.personajes.Humano;
import model.personajes.Orco;
import model.personajes.Personaje;
import utilidades.*;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class FrameChooseCharacter extends JFrame implements ActionListener {
    // ATRIBUTOS - COMPONENTES
    private ArchivoDeTexto archivoDeTexto = new ArchivoDeTexto();
    private Game juego = new Game();
    private JFrame pantallaPrincipal;
    private SimplifyCreation sC = new SimplifyCreation();
    private JComboBox elegirDia = new JComboBox<>(elegirDia()), elegirMes = new JComboBox<>(Meses.values()),
            elegirAnio = new JComboBox<>(elegirAnios()), elegirVelocidad = new JComboBox<>(elegirDe1a10()),
            elegirFuerza = new JComboBox<>(elegirDe1a10()), elegirNivel = new JComboBox<>(elegirDe1a10()),
            elegirArmadura = new JComboBox<>(elegirDe1a10()), elegirDestreza = new JComboBox<>(elegirDe1a5()),
            elegirGenero = new JComboBox<>(Genero.values());
    private JLabel textoPersonaje = new JLabel(),textoNombre = new JLabel("Nombre:"),textoApodo = new JLabel("Apodo:"),
            textoTipo = new JLabel("Tipo:"), textoDestreza = new JLabel("Destreza:"), textoSalud = new JLabel("Salud:"),
            textoFechaNacimiento = new JLabel("Fecha de Nacimiento:"),textoDia = new JLabel("Día:"), textoGenero = new JLabel("Género:"),
            textoMes = new JLabel("Mes: "),textoAnio = new JLabel("Año:"),textoVelocidad = new JLabel("Velocidad:"),
            textoFuerza = new JLabel("Fuerza:"),textoArmadura = new JLabel("Armadura:"),textoNivel = new JLabel("Nivel:");
    private JLabel labelCreacion1, labelCreacion2, labelAutomatico1, labelAutomatico2, logo, textoEscojaCreacion,
            textoEscojaPersonaje;
    private JRadioButton botonHumano, botonOrco, botonElfo;
    private JButton botonAutomatico, botonManual, botonConfirmar, botonVolver;
    private ButtonGroup grupoPersonajes;
    private Personaje personaje;
    private JPanelWithBackground panel;
    private TextField campoNombre = new TextField(), campoApodo = new TextField(), campoTipo = new TextField(),
            campoSalud = new TextField();
    private int numJugador = 1;
    private String horaActual;

    // CONSTRUCTOR
    public FrameChooseCharacter(JFrame pantallaPrincipal) {
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
                personajeManual();
                break;
            case "Automático":
                personajeAutomatico();
                break;
            case "Confirmar":
                if (juego.getCantidadPersonajes() != juego.getMaximoPersonaje()) {
                    confirmarPersonaje();
                }
                break;
            case "<< Volver":
                volver();
                break;
        }
        if (juego.getCantidadPersonajes() == juego.getMaximoPersonaje()) {
            labelCreacion2.setVisible(false);
            labelCreacion1.setVisible(false);
            JOptionPane.showMessageDialog(null, "TODOS LOS PERSONAJES FUERON CREADOS\n" +
                    "Regresará al 'Menú Principal' para comenzar la partida.");
            this.setVisible(false);
            pantallaPrincipal.setVisible(true);
        }
    }
    // INICIALIZACIÓN DE COMPONENTES
    private void iniciarComponentes() {
        horaActual = archivoDeTexto.iniciarPartida();
        // TEXTO INFORMATIVO --> Advertencia en la creación de personajes automáticos ...
        String text1 = "Al crear los personajes de forma automática, no podrá modificar sus atributos.";
        LabelParam labelParam1 = new LabelParam(text1, 525, 780, 1000, 100, Color.WHITE, new Font("Agency FB", 1, 20));
        labelAutomatico1 = sC.createLabel(labelParam1);
        labelAutomatico1.setVisible(false);

        String text2 = "Si el personaje no es de su agrado, presione 'Automático' hasta que lo sea.";
        LabelParam labelParam2 = new LabelParam(text2, 533, 810, 1000, 100, Color.WHITE, new Font("Agency FB", 1, 20));
        labelAutomatico2 = sC.createLabel(labelParam2);
        labelAutomatico2.setVisible(false);

        // TEXTO INFORMATIVO --> Creando personaje ...
        String text3 = "Creando personaje 1 ...";
        LabelParam labelParam3 = new LabelParam(text3, 1390, 200, 300, 100, Color.WHITE, new Font("Agency FB", 1, 30));
        labelCreacion1 = sC.createLabel(labelParam3);

        // TEXTO INFORMATIVO --> Jugador ...
        String text4 = "Jugador " + numJugador;
        LabelParam labelParam4 = new LabelParam(text4, 1470, 150, 300, 100, Color.WHITE, new Font("Agency FB", 1, 20));
        labelCreacion2 = sC.createLabel(labelParam4);

        // BOTÓN ORCO (830, 300, 75, 30)
        ButtonParam buttonParam1 = new ButtonParam("Orco", 830, 300, 75, 30, new Color(0,0,0,0), Color.WHITE);
        botonOrco = sC.createRadioButton(buttonParam1);
        botonOrco.addActionListener(this);
        botonOrco.setEnabled(false);

        // BOTÓN HUMANO (920, 300, 100, 30)
        ButtonParam buttonParam2 = new ButtonParam("Humano", 920, 300, 100, 30, new Color(0,0,0,0), Color.WHITE);
        botonHumano = sC.createRadioButton(buttonParam2);
        botonHumano.addActionListener(this);
        botonHumano.setEnabled(false);

        // BOTÓN ELFO (1020, 300, 100, 30)
        ButtonParam buttonParam3 = new ButtonParam("Elfo", 1020, 300, 100, 30, new Color(0,0,0,0), Color.WHITE);
        botonElfo = sC.createRadioButton(buttonParam3);
        botonElfo.addActionListener(this);
        botonElfo.setEnabled(false);

        // LOGO ORELMAN (1000, 100, 600, 100)
        LabelParam labelParam5 = new LabelParam("", 1000, 100, 600, 100, Color.BLACK, new Font(null));
        logo = sC.createLabel(labelParam5);
        ImageIcon imageIconLabel2 = new ImageIcon("src/img/logo.png");
        ImageIcon imagenRedimensionada2 = new ImageIcon(imageIconLabel2.getImage().getScaledInstance(271, 50, Image.SCALE_SMOOTH));
        logo.setIcon(imagenRedimensionada2);


        // TEXTO MEDIO --> CONTENIDO
        LabelParam labelParam8 = new LabelParam("1. Crear Personaje:", 530, 200, 300, 100, Color.WHITE, new Font("Agency FB", 1, 30));
        textoEscojaCreacion = sC.createLabel(labelParam8);

        LabelParam labelParam7 = new LabelParam("2. Escoja su Personaje:", 830, 200, 300, 100, Color.WHITE, new Font("Agency FB", 1, 30));
        textoEscojaPersonaje = sC.createLabel(labelParam7);

        // BOTÓN AUTOMÁTICO (520, 300, 100, 30)
        ButtonParam buttonParam4 = new ButtonParam("Automático", 520, 300, 100, 30, Color.BLACK, Color.WHITE);
        botonAutomatico = sC.createButton(buttonParam4);
        botonAutomatico.addActionListener(this);

        // BOTÓN MANUAL (640, 300, 100, 30)
        ButtonParam buttonParam6 = new ButtonParam("Manual", 640, 300, 100, 30, Color.BLACK, Color.WHITE);
        botonManual = sC.createButton(buttonParam6);
        botonManual.addActionListener(this);

        // BOTÓN CONFIRMAR (700, 750, 120, 40)
        ButtonParam buttonParam5 = new ButtonParam("Confirmar", 700, 750, 120, 40, Color.BLACK, Color.WHITE);
        botonConfirmar = sC.createButton(buttonParam5);
        botonConfirmar.addActionListener(this);
        botonConfirmar.setVisible(false);

        // BOTÓN SALIR (440, 130, 100, 30)
        ButtonParam buttonParam7 = new ButtonParam("<< Volver", 440, 130, 100, 30, Color.BLACK, Color.WHITE);
        botonVolver = sC.createButton(buttonParam7);
        botonVolver.addActionListener(this);


        grupoPersonajes = new ButtonGroup();
        grupoPersonajes.add(botonOrco);
        grupoPersonajes.add(botonElfo);
        grupoPersonajes.add(botonHumano);

        panel = sC.createPanelWithBackground("src/img/personajes.jpg", -370, -100, 1920, 1080);

        sC.añadirComponentes(panel, textoEscojaCreacion, botonAutomatico, botonManual, textoEscojaPersonaje, botonHumano, botonElfo);
        sC.añadirComponentes(panel, botonOrco, logo, textoPersonaje, botonConfirmar, labelCreacion1, labelCreacion2);
        panel.add(labelAutomatico1);
        panel.add(labelAutomatico2);
        panel.add(botonVolver);
    }

    // MÉTODOS BOTONES
    private void volver(){
        int inputt = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea volver al menú principal?\n" +
                "Los personajes creados se perderán.", "ADVERTENCIA", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (inputt == 0){
            // Si el usuario desea volver al menú principal, la partida iniciada se borra del txt.
            archivoDeTexto.borrarArchivoDeTxtAPartirDeLinea(archivoDeTexto.devolverUltimaPartida());
            juego.borrarPersonajes();
            this.setVisible(false);
            pantallaPrincipal.setVisible(true);
        }
    }
    private void personajeAutomatico(){
        labelAutomatico1.setVisible(true);
        labelAutomatico2.setVisible(true);
        personaje = GeneradorDePersonajesAleatorios.generar();
        botonHumano.setEnabled(false);
        botonElfo.setEnabled(false);
        botonOrco.setEnabled(false);
        elegirPersonaje();
        mostrarAtributosAutomaticos();
    }
    private void personajeManual(){
        resetearEntradas();
        botonHumano.setEnabled(true);
        botonElfo.setEnabled(true);
        botonOrco.setEnabled(true);
    }
    private void confirmarPersonaje() {
        int input = 1;
        // Si el campo de apodo o nombre, está vacío o contiene números, no te dejará confirmar.
        if ((campoNombre.getText().equals("") || campoApodo.getText().equals("")) ||
                (campoNombre.getText().matches(".*\\d+.*")) || campoApodo.getText().matches(".*\\d+.*")) {
            JOptionPane.showMessageDialog(null, "Los campos de nombre y/o apodo," +
                    " no pueden estar vacíos, ni contener números.");
        }
        else {
            // Se toma la fecha de nacimiento ingresada, y se valida.
            Meses m = (Meses) elegirMes.getSelectedItem();
            String dia = String.valueOf(elegirDia.getSelectedItem());
            String anio = String.valueOf(elegirAnio.getSelectedItem());
            FechaNacimiento f = new FechaNacimiento(dia, m, anio);
            // Si es igual a 3, significa que es posterior a la fecha actual.
            if (Personaje.fechaInvalida(f.toString()) == 3) {
                LocalDate fechaActual = LocalDate.now();
                JOptionPane.showMessageDialog(null, "No puede viajar al futuro. Ingrese una fecha anterior a " + fechaActual +
                        " (aaaa-mm-dd)");
            }
            // Si es igual a 2, significa que la fecha no existe (Ej: 30 de Febrero).
            if (Personaje.fechaInvalida(f.toString()) == 2) {
                JOptionPane.showMessageDialog(null, "Ingrese una fecha válida. " + m.name() +
                        " no tiene " + dia + " dias.");
            }
            // Si es igual a 1, significa que es correcta y anterior a la fecha actual. Se setean los atributos.
            if (Personaje.fechaInvalida(f.toString()) == 1) {
                personaje.setFechaNacimiento(f);
                personaje.setNombre(personaje.corregirTexto(campoNombre.getText()));
                personaje.setGenero((Genero) elegirGenero.getSelectedItem());
                personaje.setApodo(personaje.corregirTexto(campoApodo.getText()));
                Habilidades h = new Habilidades((int) elegirVelocidad.getSelectedItem(), (int) elegirDestreza.getSelectedItem(),
                        (int) elegirFuerza.getSelectedItem(), (int) elegirNivel.getSelectedItem(), (int) elegirArmadura.getSelectedItem());
                personaje.setHabilidades(h);
                personaje.setEdad();
                input = 0;
            }
        }
        // Si la fecha es correcta (input 0), y ningún atributo está sin completar, se le consulta al usuario
        // si está seguro de su elección.
        if (!personaje.atributosInvalidos() && input == 0) {
            input = JOptionPane.showConfirmDialog(null, "Usted eligió:\n\n" +
                            personaje + "\n¿Está seguro?", "CONFIRMAR PERSONAJE - JUGADOR " + numJugador ,
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (input == 0) {
                JOptionPane.showMessageDialog(null, "PERSONAJE  CREADO");
                archivoDeTexto.escribirArchivoDeTexto(personaje.imprimirPersonajeTxt(numJugador));
                numJugador = juego.addPersonajeJugador(personaje, numJugador);
                if (numJugador == 2){
                    labelCreacion1.setText("Creando personaje " + (juego.getPersonajesJugador2().size() + 1) + " ...");
                }
                if (numJugador == 1) {
                    labelCreacion1.setText("Creando personaje " + (juego.getPersonajesJugador1().size() + 1) + " ...");
                }
                labelCreacion2.setText("Jugador " + numJugador);
                textoGenero.setVisible(false);
                elegirGenero.setVisible(false);
                ocultarComponentes(textoTipo, campoTipo, textoNombre, campoNombre, textoApodo, campoApodo, textoSalud, campoSalud, textoDia);
                ocultarComponentes(elegirDia, textoMes, elegirMes, textoAnio, elegirAnio, textoVelocidad, elegirVelocidad, textoFuerza, elegirFuerza);
                ocultarComponentes(textoNivel, elegirNivel, textoFechaNacimiento, textoArmadura, elegirArmadura, textoDestreza, elegirDestreza, botonConfirmar, textoPersonaje);
                resetearEntradas();
            }
        }
    }
    private void elegirPersonaje() {
        // TEXTO PERSONAJE --> CREADO
        if (personaje != null) {
            textoPersonaje.setText(personaje.getRazaPersonaje());
            textoPersonaje.setBounds(1304,200,600,800);
            textoPersonaje.setIcon(personaje.getImageIcon());
            textoPersonaje.setForeground(Color.WHITE);
            textoPersonaje.setFont(new Font("Agency FB", Font.BOLD, 30));
            textoPersonaje.setHorizontalTextPosition(JLabel.CENTER);
            textoPersonaje.setVerticalTextPosition(JLabel.TOP);
            textoPersonaje.setVisible(true);


            sC.editarComponente(textoTipo, 450, 501, 80, 20, 15, Color.WHITE);
            sC.editarComponente(campoTipo, 530, 500, 110, 25, 15, Color.BLACK);
            campoTipo.setText(personaje.getRazaPersonaje());
            campoTipo.setEditable(false);
            mostrarComponentes(textoTipo, campoTipo);

            sC.editarComponente(textoNombre,450, 551, 80, 20, 15, Color.WHITE);
            sC.editarComponente(campoNombre, 530, 550, 110, 25, 15, Color.BLACK);
            mostrarComponentes(textoNombre, campoNombre);

            sC.editarComponente(textoApodo, 450, 601, 80, 20, 15, Color.WHITE);
            sC.editarComponente(campoApodo, 530, 600, 110, 25, 15, Color.BLACK);
            mostrarComponentes(textoApodo, campoApodo);

            sC.editarComponente(textoSalud, 450, 651, 80, 20, 15, Color.WHITE);
            sC.editarComponente(campoSalud, 530, 650, 110, 25, 15, Color.BLACK);
            campoSalud.setText(String.valueOf(personaje.getSalud()));
            campoSalud.setEditable(false);
            mostrarComponentes(textoSalud, campoSalud);

            sC.editarComponente(textoFechaNacimiento, 650, 501, 200, 20, 15, Color.WHITE);
            sC.editarComponente(textoDia, 829, 475, 50, 20, 15, Color.WHITE);
            elegirDia.setBounds(820, 500, 50, 25);
            sC.editarComponente(textoMes, 915, 475, 50, 20, 15, Color.WHITE);
            elegirMes.setBounds(885, 500, 100, 25);
            sC.editarComponente(textoAnio, 1012, 475, 50, 20, 15, Color.WHITE);
            elegirAnio.setBounds(1000, 500, 60, 25);
            mostrarComponentes(textoPersonaje, textoFechaNacimiento);
            mostrarComponentes(textoDia, elegirDia);
            mostrarComponentes(textoMes, elegirMes);
            mostrarComponentes(textoAnio, elegirAnio);

            sC.editarComponente(textoVelocidad, 690, 552, 200, 20, 15, Color.WHITE);
            elegirVelocidad.setBounds(820, 550, 50, 25);
            mostrarComponentes(textoVelocidad, elegirVelocidad);

            sC.editarComponente(textoFuerza, 885, 552, 200, 20, 15, Color.WHITE);
            elegirFuerza.setBounds(1005, 550, 50, 25);
            mostrarComponentes(textoFuerza, elegirFuerza);

            sC.editarComponente(textoNivel, 705, 601, 200, 20, 15, Color.WHITE);
            elegirNivel.setBounds(820, 600, 50, 25);
            mostrarComponentes(textoNivel, elegirNivel);

            sC.editarComponente(textoArmadura, 885, 601, 200, 20, 15, Color.WHITE);
            elegirArmadura.setBounds(1005, 600, 50, 25);
            mostrarComponentes(textoArmadura, elegirArmadura);

            sC.editarComponente(textoGenero, 885, 651, 200, 20, 15, Color.WHITE);
            elegirGenero.setBounds(980, 650, 75, 25);
            mostrarComponentes(textoGenero, elegirGenero);

            sC.editarComponente(textoDestreza, 690, 651, 200, 20, 15, Color.WHITE);
            elegirDestreza.setBounds(820, 650, 50, 25);
            mostrarComponentes(textoDestreza, elegirDestreza);

            botonConfirmar.setVisible(true);
            sC.añadirComponentes(panel, textoDestreza, elegirDestreza, textoNivel, elegirNivel, textoArmadura, elegirArmadura);
            sC.añadirComponentes(panel, textoFuerza, elegirFuerza, textoVelocidad, elegirVelocidad, textoFechaNacimiento, elegirAnio);
            sC.añadirComponentes(panel, textoAnio, elegirAnio, textoMes, elegirMes, textoDia, elegirDia);
            sC.añadirComponentes(panel, textoSalud, campoSalud, textoNombre, campoNombre, textoApodo, campoApodo);
            sC.añadirComponentes(panel, textoPersonaje, textoTipo, campoTipo, elegirGenero , textoGenero, textoGenero);
            panel.revalidate();
            panel.repaint();
        }
    }

    // MÉTODOS PARA AHORRAR LÍNEAS DE CÓDIGO
    private void resetearEntradas() {
        botonOrco.setEnabled(false);
        botonHumano.setEnabled(false);
        botonElfo.setEnabled(false);
        labelAutomatico1.setVisible(false);
        labelAutomatico2.setVisible(false);
        grupoPersonajes.clearSelection();
        campoApodo.setText("");
        campoNombre.setText("");
        campoTipo.setText("");
        campoSalud.setText("");
        campoApodo.setEditable(true);
        campoNombre.setEditable(true);
        resetearComboBox(elegirDia, elegirAnio, elegirMes, elegirDestreza, elegirArmadura, elegirNivel,
                elegirVelocidad, elegirFuerza);
        elegirGenero.setEnabled(true);
    }
    public void ocultarComponentes(Component c1, Component c2, Component c3, Component c4, Component c5,
                                   Component c6, Component c7, Component c8, Component c9){
        c1.setVisible(false);
        c2.setVisible(false);
        c3.setVisible(false);
        c4.setVisible(false);
        c5.setVisible(false);
        c6.setVisible(false);
        c7.setVisible(false);
        c8.setVisible(false);
        c9.setVisible(false);
    }
    private void mostrarComponentes(Component c1, Component c2){
        c1.setVisible(true);
        c2.setVisible(true);
    }
    private void mostrarAtributosAutomaticos() {
        grupoPersonajes.clearSelection();
        campoNombre.setText(personaje.getNombre());
        campoNombre.setEditable(false);
        campoApodo.setText(personaje.getApodo());
        campoApodo.setEditable(false);
        elegirDia.setSelectedItem(Integer.parseInt(personaje.getDiaNacimiento()));
        elegirMes.setSelectedIndex(Integer.parseInt(Meses.devolverFecha(personaje.getMesNacimiento()))-1);
        elegirAnio.setSelectedItem(Integer.parseInt(personaje.getAñoNacimiento()));
        elegirVelocidad.setSelectedItem(personaje.getVelocidadPersonaje());
        elegirDestreza.setSelectedItem(personaje.getDestrezaPersonaje());
        elegirFuerza.setSelectedItem(personaje.getFuerzaPersonaje());
        elegirArmadura.setSelectedItem(personaje.getArmaduraPersonaje());
        elegirNivel.setSelectedItem(personaje.getNivelPersonaje());
        elegirGenero.setSelectedIndex(personaje.getGenero().ordinal());
        desactivarComboBox(elegirDia, elegirMes, elegirAnio, elegirVelocidad, elegirDestreza, elegirFuerza,
                elegirArmadura, elegirNivel);
        elegirGenero.setEnabled(false);
    }
    private Integer[] elegirDia() {
        Integer[] anios = new Integer[31];
        int dia = 1;
        for (int i = 0; i < 31; i++) {
            anios[i] = dia;
            dia++;
        }
        return anios;
    }
    private Integer[] elegirAnios() {
        Calendar fecha = new GregorianCalendar();
        int año = fecha.get(Calendar.YEAR);
        Integer[] anios = new Integer[301];
        for (int i = 0; i <= 300; i++) {
            anios[i] = año - i;
        }
        return anios;
    }
    private Integer[] elegirDe1a10() {
        return new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    }
    private Integer[] elegirDe1a5() {
        return new Integer[]{1, 2, 3, 4, 5};
    }
    private void resetearComboBox(JComboBox boton1, JComboBox boton2, JComboBox boton3, JComboBox boton4, JComboBox boton5,
                                 JComboBox boton6, JComboBox boton7, JComboBox boton8) {
        boton1.setSelectedIndex(0);
        boton2.setSelectedIndex(0);
        boton3.setSelectedIndex(0);
        boton4.setSelectedIndex(0);
        boton5.setSelectedIndex(0);
        boton6.setSelectedIndex(0);
        boton7.setSelectedIndex(0);
        boton8.setSelectedIndex(0);
        boton1.setEnabled(true);
        boton2.setEnabled(true);
        boton3.setEnabled(true);
        boton4.setEnabled(true);
        boton5.setEnabled(true);
        boton6.setEnabled(true);
        boton7.setEnabled(true);
        boton8.setEnabled(true);
    }
    private void desactivarComboBox(JComboBox boton1, JComboBox boton2, JComboBox boton3, JComboBox boton4, JComboBox boton5,
                                   JComboBox boton6, JComboBox boton7, JComboBox boton8) {
        boton1.setEnabled(false);
        boton2.setEnabled(false);
        boton3.setEnabled(false);
        boton4.setEnabled(false);
        boton5.setEnabled(false);
        boton6.setEnabled(false);
        boton7.setEnabled(false);
        boton8.setEnabled(false);
    }
}

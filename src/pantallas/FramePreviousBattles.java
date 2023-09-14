package pantallas;

import utilidades.ArchivoDeTexto;
import utilidades.ButtonParam;
import utilidades.LabelParam;
import utilidades.SimplifyCreation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FramePreviousBattles extends JFrame implements ActionListener {
    // ATRIBUTOS
    private ArchivoDeTexto archivoDeTexto = new ArchivoDeTexto();
    private JComboBox elegirPartida = new JComboBox<>(archivoDeTexto.devolverPartidas());
    private JFrame pantallaPrincipal;
    private JPanel panel;
    private SimplifyCreation sC = new SimplifyCreation();
    private JButton botonVolver, botonBuscar, botonBorrarPartidas;
    private JLabel textoSeleccionarPartida;
    private JTextArea areaInformativa = new JTextArea(1, 20);

    // CONSTRUCTOR
    public FramePreviousBattles(JFrame pantallaPrincipal) {
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
        // Este método se encarga de recibir y asignar las acciones a los botones.
        String cmd = e.getActionCommand();
        switch (cmd) {
            // Si se presiona el botón volver, salta un menú de confirmación, y luego, si
            // el usuario confirma, se vuelve al menú principal.
            case "<< Volver":
                int input = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea volver al menú principal?", "ADVERTENCIA", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (input == 0) {
                    this.setVisible(false);
                    pantallaPrincipal.setVisible(true);
                }
                break;
            // Si se presiona el botón buscar buscamos la partida elegida en el txt, y la mostramos
            // dentro del TextArea.
            case "Buscar":
                String partidaBuscada = String.valueOf(elegirPartida.getSelectedItem());
                areaInformativa.setText("");
                archivoDeTexto.leerContenidoDePartida(partidaBuscada, areaInformativa);
                break;
            // Si se presiona el botón borrar partidas, salta un menú de confirmaciómn, y luego, si
            // el usuario confirma se borra todo el txt.
            case "Borrar Partidas":
                int inputt = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar todas las " +
                        "partidas guardads? Regresará al menú principal", "ADVERTENCIA", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (inputt == 0) {
                    archivoDeTexto.eliminarArchivoDeTexto();
                    this.setVisible(false);
                    pantallaPrincipal.setVisible(true);
                }
                break;

        }
    }
    // INICIALIZACIÓN DE COMPONENTES
    private void iniciarComponentes() {
        // PANEL PRINCIPAL (-370, -100, 1920, 1080)
        panel = sC.createPanelWithBackground("src/img/batallasAnteriores.jpg", -370, -100, 1920, 1080);

        // LOGO ORELMAN (1000, 100, 600, 100)
        LabelParam labelParam5 = new LabelParam("", 1000, 100, 600, 100, Color.BLACK, new Font(null));
        JLabel logo = sC.createLabel(labelParam5);
        ImageIcon imageIconLabel2 = new ImageIcon("src/img/logo.png");
        ImageIcon logoRedimensionado = new ImageIcon(imageIconLabel2.getImage().getScaledInstance(271, 50, Image.SCALE_SMOOTH));
        logo.setIcon(logoRedimensionado);

        // BOTÓN VOLVER (440, 130, 100, 30)
        ButtonParam buttonParam1 = new ButtonParam("<< Volver", 440, 130, 100, 30, Color.BLACK, Color.WHITE);
        botonVolver = sC.createButton(buttonParam1);
        botonVolver.addActionListener(this);
        botonVolver.setBorder(null);

        // BOTÓN BUSCAR (1500, 303, 100, 20)
        ButtonParam buttonParam2 = new ButtonParam("Buscar", 1500, 303, 100, 20, Color.BLACK, Color.WHITE);
        botonBuscar = sC.createButton(buttonParam2);
        botonBuscar.addActionListener(this);

        // BOTÓN BORRAR PARTIDAS (1610, 303, 140, 20)
        ButtonParam buttonParam3 = new ButtonParam("Borrar Partidas", 1610, 303, 140, 20, Color.BLACK, Color.WHITE);
        botonBorrarPartidas = sC.createButton(buttonParam3);
        botonBorrarPartidas.addActionListener(this);

        // TEXTO INFORMATIVO --> Seleccionar la partida que se desea mostrar
        LabelParam labelParam1 = new LabelParam("Seleccionar partida:", 547, 257, 1000, 100, Color.WHITE, new Font("Agency FB", 1, 40));
        textoSeleccionarPartida = sC.createLabel(labelParam1);
        textoSeleccionarPartida.setVisible(true);

        // TEXT AREA --> Donde se mostrará el contenido de la partida elegida.
        areaInformativa.setLineWrap(true);
        areaInformativa.setWrapStyleWord(true);
        areaInformativa.setFont(new Font("Agency FB", 1, 15));
        areaInformativa.setEditable(false);
        areaInformativa.setMargin(new Insets(5,5,5,5));

        // SCROLL PANE --> Funciona para poder subir y bajar en el TextArea.
        JScrollPane scrollPane = new JScrollPane(areaInformativa);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(550, 340, 1200, 550);

        // COMBOBOX --> Es el menú extensible donde se muestran las partidas disponibles a mostrar.
        sC.editarComponente(elegirPartida, 860, 303, 600, 20, 13, Color.BLACK);

        // Agregamos todo al panel principal para poder mostrarlo en pantalla.
        sC.añadirComponentes(panel, botonBorrarPartidas, botonBuscar, botonVolver, elegirPartida, textoSeleccionarPartida,
            logo);
        panel.add(scrollPane);

    }
}

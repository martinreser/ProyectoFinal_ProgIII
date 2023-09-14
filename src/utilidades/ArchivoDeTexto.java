package utilidades;

import javax.swing.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

// Este método se encarga de realizar el manejo de archivos.txt
public class ArchivoDeTexto {
    // ATRIBUTOS
    private File archivo;

    // Cada vez que se inicializa la clase, es decir, cada vez que se
    // comienza una partida, se guarda un nuevo texto con la fecha y el
    // horario de la partida iniciada.

    // CONSTRUCTOR
    public ArchivoDeTexto(){
        // Al momento de instanciar al constructor de la clase,
        // creamos un nuevo archivo (si ya está creado no hace nada).
        crearArchivoDeTexto();
    }

    // MÉTODOS
    public String iniciarPartida(){
        // Guardamos la fecha y el horario en que se llama al método.
        LocalDateTime now = LocalDateTime.now();
        // Formateamos la fecha y el horario con el formato que deseamos.
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        // Guardamos la fecha ya formateada en una variable.
        String fechaFormateada = now.format(formato);

        // Creamos un nuevo string el cual indicará dentro del txt que se inició
        // una nueva partida.
        String textoAGuardar = "[Partida comenzada el: " + fechaFormateada + "]\n" ;
        escribirArchivoDeTexto(textoAGuardar);
        return textoAGuardar;
    }
    public void eliminarArchivoDeTexto(){
        // Este método se encarga de eliminar por completo un archivo de texto.
        archivo.delete();
    }
    public void escribirArchivoDeTexto(String texto){
        try {
            // Este método se encarga de escribir en el archivo de texto
            // lo que le indiquemos como parámetro.
            FileWriter escritura = new FileWriter(archivo, true);
            escritura.write(texto + "\n");
            escritura.close();
        } catch (IOException exception){
            exception.printStackTrace(System.out);
        }
    }
    public void leerContenidoDePartida(String cadenaBuscada, JTextArea textArea) {
        // Inicializamos a partidaEncotrada como falso, ya que, valga la redundancia
        // no hemos encontrado la partida aún.
        boolean partidaEncontrada = false;
        String linea;
        try {
            FileReader lector = new FileReader(archivo);
            BufferedReader lectura = new BufferedReader(lector);
            // Mientras que la línea no sea nula (final del archivo)
            // seguimos buscando.
            while ((linea = lectura.readLine()) != null) {
                // Si la línea comienza con la cadena seleccionada, definimos
                // a partidaEncontrada como true.
                if (linea.startsWith(cadenaBuscada)) {
                    partidaEncontrada = true;
                }
                // Si la línea comienza con "[Partida comenzada el:" y es distinta de
                // la cadena que buscamos, salimos del bucle, ya que estaríamos accediendo
                // a la consola de otra partida.
                if (linea.startsWith("[Partida comenzada el:") && !linea.equals(cadenaBuscada) && partidaEncontrada == true){
                    break;
                }
                // Si la partida fue encontrada, mostramos la línea.
                if (partidaEncontrada){
                    textArea.append(linea + "\n");
                }
            }
        } catch (IOException exception){
            exception.printStackTrace();
        }
    }
    public Object[] devolverPartidas(){
        // Este método se encarga de mostrar las partidas guardadas en el txt. Para
        // luego poder elegir una y que se muestre todo el contenido.
        List<String> partidasEncontradas = new ArrayList<>();
        String linea;
        try {
            // Instanciamos un nuevo lector.
            FileReader lector = new FileReader(archivo);
            BufferedReader lectura = new BufferedReader(lector);
            // Mientras que la línea no sea nula (final del archivo)
            // seguimos buscando.
            while ((linea = lectura.readLine()) != null) {
                // Si la línea comienza con la cadena que buscamos, la añadimos a las partidas encontradas.
                if (linea.startsWith("[Partida comenzada el:")) {
                    partidasEncontradas.add(linea);
                }
            }
        } catch (IOException exception){
            exception.printStackTrace();
        }
        // Una vez finalizado el bucle, devolvemos las partidas que encontramos.
        return partidasEncontradas.toArray();
    }
    public String devolverUltimaPartida(){
        int ultimaPartida = devolverPartidas().length - 1;
        return (String) devolverPartidas()[ultimaPartida];
    }
    public boolean vacio(){
        // Este método se encarga de devolver 'true' si es que el archivo
        // está vacío, y 'false' si es que no lo está.
        return archivo.length() == 0;
    }
    public void borrarArchivoDeTxtAPartirDeLinea(String cadenaInicial){
        // Este método se encarga de borrar todo lo contenido en un txt, desde
        // una línea en específico.
        try {
            // Recibimos el parámetro cadenaInicial y le quitamos sus últimos 4 carácteres ('\n\r')
            String cadenaSinUltimos4 = cadenaInicial.substring(0, cadenaInicial.length() - 2);
            // Instanciamos un nuevo lector. Y un StringBuilder el cual irá guardando todo lo que
            // NO queremos borrar.
            FileReader lector = new FileReader(archivo);
            BufferedReader lectura = new BufferedReader(lector);
            StringBuilder nuevoContenido = new StringBuilder();

            String linea;
            boolean borrar = false;

            while ((linea = lectura.readLine()) != null) {
                // Si la línea encontrada contiene la cadenaInicial, volvemos 'true' a
                // la variable borrar.
                if (linea.contains(cadenaSinUltimos4)) {
                    borrar = true;
                }
                // Cuando borrar es 'false', vamos guardando todo dentro del StringBuilder para
                // mantenerlo en el txt. Si borrar es 'true', todo lo demás no se guardará.
                if (!borrar) {
                    nuevoContenido.append(linea).append("\n");
                }
            }

            lector.close();

            // Por último, sobreescribimos el archivo con el nuevo contenido que sí
            // queremos mantener.
            FileWriter escritor = new FileWriter(archivo);
            escritor.write(nuevoContenido.toString());
            escritor.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void crearArchivoDeTexto(){
        // Este método se encarga de crear un nuevo archivo de texto
        // con el nombre deseado.
        archivo = new File("batalla.txt");
        try {
            archivo.createNewFile();
        } catch (IOException exception){
            exception.printStackTrace(System.out);
        }
    }

}


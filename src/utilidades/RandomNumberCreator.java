package utilidades;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.SplittableRandom;

public class RandomNumberCreator { // ESTA CLASE FUNCIONA COMO SINGLETON
    // ATRIBUTOS
    private static RandomNumberCreator instance;
    private Random random;

    // CONSTRUCTOR
    private RandomNumberCreator() {
        random = new Random();
    }

    // MÉTODOS
    public static synchronized RandomNumberCreator getInstance() {
        if (instance == null) {
            instance = new RandomNumberCreator();
        }
        return instance;
    }
    public int generateRandomInt (int numMaximo) {
        return random.nextInt(numMaximo);
    }
    public int generateRandomInt (int numMinIncl, int numMaxExcl) {
        return random.nextInt(numMinIncl, numMaxExcl);
    }
    public double generateRandomDouble (double numMaxExcl) {
        return random.nextDouble(numMaxExcl);
    }
    public double generateRandomDouble (double numMinIncl, double numMaxExcl) {
        return random.nextDouble(numMinIncl, numMaxExcl);
    }
    public double reduceDecimal(double numero){
        DecimalFormat formato = new DecimalFormat("#.##");
        String numeroReducido = formato.format(numero);
        numeroReducido = numeroReducido.replace("," , ".");
        numero = Double.parseDouble(numeroReducido);
        return numero;
    }

}


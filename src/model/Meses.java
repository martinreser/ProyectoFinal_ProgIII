package model;

public enum Meses {
    Enero ("01"), Febrero ("02"), Marzo ("03"), Abril ("04"), Mayo ("05"), Junio ("06"), Julio ("07"),
    Agosto ("08"), Septiembre ("09"), Octubre ("10"), Noviembre ("11"), Diciembre ("12");

    private final String value;
    Meses (String value) {
        this.value = value;
    }

    public static Meses mesAleatorio(int mes){
        if ((mes == Enero.ordinal())){
            return Enero;
        }
        if ((mes == Febrero.ordinal())){
            return Febrero;
        }
        if ((mes == Marzo.ordinal())){
            return Marzo;
        }
        if ((mes == Abril.ordinal())){
            return Abril;
        }
        if ((mes == Mayo.ordinal())){
            return Mayo;
        }
        if ((mes == Junio.ordinal())){
            return Junio;
        }
        if ((mes == Julio.ordinal())){
            return Julio;
        }
        if ((mes == Agosto.ordinal())){
            return Agosto;
        }
        if ((mes == Septiembre.ordinal())){
            return Septiembre;
        }
        if ((mes == Octubre.ordinal())){
            return Octubre;
        }
        if ((mes == Noviembre.ordinal())){
            return Noviembre;
        }
        if ((mes == Diciembre.ordinal())){
            return Diciembre;
        }
        return null;
    }

    public static String devolverFecha(Meses mes){
        return mes.value;
    }
}

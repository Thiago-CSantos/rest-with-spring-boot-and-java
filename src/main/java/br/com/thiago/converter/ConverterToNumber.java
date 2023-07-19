package br.com.thiago.converter;

public class ConverterToNumber {

    public static Double convertToDouble(String numeroStr) {

        if (numeroStr == null) {
            return 0.0;
        }
        // BR 10,25 US 10.25
        String numero1 = numeroStr.replaceAll(",", ".");

        if (isNumeric(numero1)) {
            return Double.parseDouble(numero1);
        } else {
            return 0D;
        }
    }

    public static boolean isNumeric(String str) {

        if (str == null) {
            return false;
        }

        String numero = str.replaceAll(",", ".");

        return numero.matches("[-+]?[0-9]*\\.?[0-9]+");

    }

}

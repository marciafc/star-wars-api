package br.com.marcia.starwars.utils;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class StringUtils {

    public static String formatarValor(Double valor) {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        decimalFormat.setRoundingMode(RoundingMode.DOWN);

        return decimalFormat.format(valor);
    }
}

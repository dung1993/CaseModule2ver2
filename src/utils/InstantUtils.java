package src.utils;

import java.text.DecimalFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class InstantUtils {
    private static final String Pattern_Format = "HH:mm dd-MM-yyyy";

    public static String instantToString(Instant instant){
        return instantToString(instant, null);
    }

    public static String instantToString(Instant instant, String patternFormat){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(patternFormat != null ? patternFormat : Pattern_Format).withZone(ZoneId.systemDefault());
        return dateTimeFormatter.format(instant);
    }

    public static String doubleToVND(double value){
        String patternVND = ",###VND";
        DecimalFormat decimalFormat = new DecimalFormat(patternVND);
        return decimalFormat.format(value);
    }

    public static String quantityProduct(double value){
        String patternQuantity = "###";
        DecimalFormat decimalFormat = new DecimalFormat(patternQuantity);
        return decimalFormat.format(value);
    }
}

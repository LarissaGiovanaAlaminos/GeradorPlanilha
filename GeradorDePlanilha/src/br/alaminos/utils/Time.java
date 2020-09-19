package br.alaminos.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Time {

	public static String getTime(String format){

        if (format.isEmpty()) {
            throw new NullPointerException("A pattern não pode ser NULL!");
        }

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat formato = new SimpleDateFormat(format);
        Date data = calendar.getTime();

        return formato.format(data);
}
}

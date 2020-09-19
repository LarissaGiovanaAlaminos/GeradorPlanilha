package br.alaminos.utils;

public class StringValidation {
	
	public static boolean soContemNumeros(String texto) {
        if(texto == null)
            return false;
        for (char letra : texto.toCharArray())
            if(letra < '0' || letra > '9')
                return false;
        return true;
         
    }

}

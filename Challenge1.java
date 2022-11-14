package reto01;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Challenge1 {
	
	/* Crear un método que genere y retorne una cadena de texto (String) de forma 
	aleatoria con una longitud igual a 10 caracteres, y que serán únicamente
	números (0-9), por ejemplo “3845346790”. El método tendrá como entrada
	un valor tipo texto. La cadena de texto que se retorna tendrá las siguientes
	dos condiciones:
	i. Si el valor de entrada es igual a “Tipo A” la cadena de texto deberá iniciar en “54”
	ii. Si el valor de entrada es igual a “Tipo B” la cadena de texto deberá iniciar en “08”
	*/
		
	public static String return_string(String stringInput) {
		
		String stringOutput = null ;
		if(stringInput == "Tipo A") 
		{
			stringOutput = "54" ;
		}
		else if(stringInput == "Tipo B") 
		{
			stringOutput = "08";
		}
		else 
		{
			 throw new ArithmeticException("ERROR: DEBES INGRESAR UN TIPO DE STRING VÁLIDO");
		}
		
		for(int ii=1 ; ii <= 8 ; ii = ii + 1)
		{
		Random random = new Random();
		int digito = random.nextInt(10);
		stringOutput = stringOutput + Integer.toString(digito);
		}
		
		return stringOutput;	
		}
	
	public static boolean return_boolean(String string, List<String> list)
	{
		/* Crear un método que tenga como entrada un valor tipo String y una lista de 
		 * cadena List<String>. El método devuelve un valor de tipo booleano. El
		 * método verifica si el valor tipo String está contenido en la lista, si el valor se
		 * encuentra dentro de la lista deberá devolver un valor false, de lo contrario
		 * retorna un valor true. */
		
		boolean b = false;
		for (String s : list) {
			if(s == string) {
				b = true;
			}
		}
		return b;
	}
	
	}

	


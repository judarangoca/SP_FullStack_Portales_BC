package reto01;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Challenge1 challenge1 = new Challenge1();
		String stringOutput = Challenge1.return_string("Tipo A");
		System.out.println("El array de salida es: " + stringOutput);
		
		
		ArrayList<String> list = new ArrayList<String>();
		list.add("Juan");
		list.add("Juan Pablo");
		list.add("Pedro");
		list.add("Camilo");
		
		boolean b = Challenge1.return_boolean("Juan", list);
		System.out.println("El valor boolean de salida es: " + b);		

	}

}

package an.dpr.ecv.model;

import java.util.Random;

public enum Category {
	PROMESA, PROMESA_FEM, 
	PRINCIPIANTE, PRINCIPIANTE_FEM,
	ALEVIN, ALEVIN_FEM,
	INFANTIL, INFANTIL_FEM,
	CADETE, CADETE_FEM;
	
	public static Category random() {
		return Category.values()[new Random().nextInt(Category.values().length)];
	}
	
	public static void main (String...args) {
		System.out.println(Category.random().toString());
	}
}

/*
 * Luis Antonio Vázquez García
 * 153675
 * Estructuras de Datos
 * Date creation 23/Agosto/2016
 */
package GUI;

public class arreglo {
	private static int [] arreglo;
	private static int cont;
	private static int MAX;
	
	arreglo(){
		arreglo = new int[25];
		MAX=24;
		cont=0;
	}
	public void agregar(int num){
		if(cont==MAX){
			return;
		}else{
			arreglo[cont]=num;
			cont++;
		}
	}
	public int tamaño(){
		int i= arreglo.length;
		return i;
	}
	public int posicion(){
		int i;
		for(i=0; i<arreglo.length; i++){
		}
		return i;
	}
}
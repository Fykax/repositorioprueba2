package jcolonia.daw2020.tridata;

import java.util.Arrays;

public class TriánguloM {

	/**
	 * Ancho común de todas las cadenas de texto.
	 */
	public static final int ANCHO = 6;

	/**
	 * Formato «printf» empleado para representar los textos. Está ligado al
	 * {@link #ANCHO}.
	 */
	public static final String FORMATO = "%0" + ANCHO + "d";

	@SuppressWarnings("unused")
	private final String HUECO = (ANCHO % 2 == 1) ? " " : "  ";

	/**
	 * Estructura de almacenamiento tabular.
	 */
	private StringBuffer[][] tabla;

	/**
	 * Estructura de almacenamiento lineal.
	 */
	private StringBuffer[] lista;

	private final int altura;
	private final int númElementos;

	/**
	 * Prepara y completa el triángulo con valores numéricos consecutivos en todas
	 * las posiciones del triángulo.
	 */
	public TriánguloM(int altura) {
		int i, j, n = 0;
		int capacidad;
		StringBuffer sbTexto;
		StringBuffer[] nuevaFila;

		this.altura = altura;
		capacidad = altura * (altura + 1) / 2;

		tabla = new StringBuffer[altura][];
		lista = new StringBuffer[capacidad];

		for (i = 0; i < altura; i++) {
			nuevaFila = new StringBuffer[i + 1];
			tabla[i] = nuevaFila;
			for (j = 0; j <= i; j++) {
				sbTexto = new StringBuffer(ANCHO);
				sbTexto.append(String.format(FORMATO, n + 1));

				tabla[i][j] = sbTexto;
				lista[n] = sbTexto;
				n++;
			}
		}
		númElementos = n;
	}

	/**
	 * Facilita el número de filas del triángulo.
	 * 
	 * @return el valor correspondiente.
	 */
	public int getAltura() {
		return altura;
	}

	/**
	 * Facilita el número total de elementos de texto del triángulo.
	 * 
	 * @return el valor correspondiente.
	 */
	public int getNúmElementos() {
		return númElementos;
	}

	/**
	 * Consulta el texto almacenado en una posición. La posición viene indicada por
	 * filas y columnas.
	 * 
	 * @param fila    posición vertical, contando <strong>a partir de 1</strong> en
	 *                el vértice.
	 * @param columna posición horizontal, contando <strong>a partir de 1</strong>
	 *                en el borde izquierdo.
	 * @return el texto señalado.
	 */
	public String get(int fila, int columna) {
		return tabla[fila - 1][columna - 1].toString();
	}

	/**
	 * Consulta el texto almacenado en una posición. Las posiciones vienen dadas por
	 * su orden en la secuencia, <strong>a partir de 0</strong> en el vértice, por
	 * filas y de izquierda a derecha en cada fila.
	 * 
	 * @param posición el número de orden en la secuencia.
	 * @return el texto señalado.
	 */
	public String get(int posición) {
		return lista[posición].toString();
	}

	/**
	 * Modifica el texto almacenado en una posición. La posición viene indicada por
	 * filas y columnas.
	 * 
	 * @param fila       posición vertical, contando <strong>a partir de 1</strong>
	 *                   en el vértice.
	 * @param columna    posición horizontal, contando <strong>a partir de
	 *                   1</strong> en el borde izquierdo.
	 * @param nuevoTexto el nuevo texto a conservar.
	 */
	public void set(int fila, int columna, String nuevoTexto) {
		tabla[fila - 1][columna - 1].replace(0, ANCHO, nuevoTexto);
	}

	/**
	 * Modifica el texto almacenado en una posición. Las posiciones vienen dadas por
	 * su orden en la secuencia, <strong>a partir de 0</strong> en el vértice, por
	 * filas y de izquierda a derecha en cada fila.
	 * 
	 * @param posición   el número de orden en la secuencia.
	 * @param nuevoTexto el nuevo texto a conservar.
	 */
	public void set(int posición, String nuevoTexto) {
		lista[posición].replace(0, ANCHO, nuevoTexto);
	}

	/**
	 * Genera nuevos textos a partir de valores numéricos aleatorios en todas las
	 * posiciones del triángulo.
	 */
	public void batir() {
		int rnd;
		String s;

		for (int i = 0; i < númElementos; i++) {
			rnd = (int) (Math.random() * 1000.);
			s = String.format(FORMATO, rnd);
			lista[i].replace(0, ANCHO, s);
		}
	}

	/**
	 * Ordena de manera natural todos los textos, a partir del vértice y por filas
	 * de izquierda a derecha.
	 */
	public void sort() {
		int i, j, n = 0;

		Arrays.sort(lista);

		for (i = 0; i < altura; i++) {
			for (j = 0; j <= i; j++) {
				tabla[i][j] = lista[n++];
			}
		}
	}

	/**
	 * Produce una representación textual completa de la estructura con apariencia
	 * triangular simétrica.
	 */
	@Override
	public String toString() {
		String salida = "";

		int i, j;

		for (i = 0; i < altura; i++) {
			salida += prefijo(altura - i - 1);

			for (j = 0; j <= i; j++) {
				salida += ((j == 0) ? "" : HUECO) + tabla[i][j];
			}
			salida += "\n";
		}

		return salida;
	}

	/**
	 * Facilita texto vacío -espacios- en apoyo de {@link #toString()} para colocar
	 * a la izquierda de cada línea y construir la estética deseada.
	 * 
	 * @param fila el número de fila que determina el respectivo margen requerido.
	 * @return el texto correspondiente.
	 */
	private String prefijo(int fila) {
		StringBuffer salida;

		salida = new StringBuffer("");
		int ancho = (ANCHO / 2 + 1) * fila;

		for (int i = 0; i < ancho; i++) {
			salida.append(' ');
		}

		return salida.toString();
	}

	/**
	 * Ejecutable para exhibir algunas de las funcionalidades proporcionadas.
	 * 
	 * @param args no se usa
	 */
	public static void main(String[] args) {
		TriánguloM t;

		t = new TriánguloM(5);
		System.out.println(t);
		t.batir();
		System.out.println(t);
		t.sort();
		System.out.println(t);
		t.set(3, 1, "←←←←  ");
		t.set(5, "  →→→→");
		System.out.println(t);
	}
	
	
}

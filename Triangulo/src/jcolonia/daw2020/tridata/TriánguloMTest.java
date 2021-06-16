package jcolonia.daw2020.tridata;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;



class TriánguloMTest {

	@BeforeAll
	static void setUpBeforeClass() {
	}

	@AfterAll
	static void tearDownAfterClass() {
	}

	@BeforeEach
	void setUp() {
	}

	@AfterEach
	void tearDown() {
	}

	/**
	 * Verifica que la altura informada se corresponda con la altura demandada al
	 * constructor.
	 */
	@ParameterizedTest(name = "altura → {0}")
	@ValueSource(ints = { 1, 2, 3, 6, 9 })
	@DisplayName("Verificación alturas")
	void testAlturasParam(int altura) {
		TriánguloM t;
		t = new TriánguloM(altura);

		assertEquals(altura, t.getAltura());
	}

	/**
	 * Verifica que el número de elementos informado sea coherente con la altura
	 * demandada al constructor.
	 */
	@ParameterizedTest(name = "altura → {0}")
	@ValueSource(ints = { 1, 2, 3, 6, 9 })
	@DisplayName("Verificación número de elementos")
	void testTamaños(int altura) {
		int nElementosn;
		TriánguloM t;
		t = new TriánguloM(altura);

		nElementosn = (altura * (altura + 1)) / 2;
		assertEquals(nElementosn, t.getNúmElementos());
	}

	/**
	 * Verifica, con los datos iniciales insertados en el momento de la creación,
	 * que las consultas por coordenadas (fila, columna) y por posición (n) sean
	 * coincidentes.
	 * 
	 * @throws Exception NO esperada.
	 */
	@ParameterizedTest(name = "altura → {0}")
	@ValueSource(ints = { 1, 2, 3, 6, 9 })
	@DisplayName("Verificación acceso dual")
	void testAccesoDual0(int altura) throws Exception {
		int nElementos;
		TriánguloM t;
		t = new TriánguloM(altura);

		int i, fila = 1, columna = 1;

		nElementos = t.getNúmElementos();
		for (i = 0; i < nElementos; i++) {
			assertEquals(t.get(i), t.get(fila, columna), i + "→(" + fila + "-" + columna + ")");
			if (fila == columna) {
				fila++;
				columna = 1;
			} else {
				columna++;
			}
		}
	}

	/**
	 * Verifica que el tamaño de todos los textos coincida con el
	 * {@link TriánguloM#ANCHO ancho} designado.
	 * 
	 * @param altura intentos con triángulos de tamaños { 1, 2, 3, 6, 9 }
	 * @throws Exception NO esperada.
	 */
	@ParameterizedTest(name = "altura → {0}")
	@ValueSource(ints = { 1, 2, 3, 6, 9 })
	@DisplayName("Verificación tamaño textos")
	void testTamañoTextos(int altura) throws Exception {
		int nElementos;
		TriánguloM t;
		t = new TriánguloM(altura);

		String texto;

		int ancho = TriánguloM.ANCHO;

		nElementos = t.getNúmElementos();

		for (int i = 0; i < nElementos; i++) {
			texto = t.get(i);
			assertNotNull(texto, "Posición " + i);
			assertEquals(ancho, texto.length(), "Posición " + i);
		}
	}

	/**
	 * Comprueba el contenido inicial de los vértices del triángulo.
	 * 
	 * @param altura intentos con triángulos de tamaños { 1, 2, 3, 6, 9 }
	 * @param v2     valor esperado del vértice inferior izquierdo.
	 * @param v3     valor esperado del vértice inferior derecho.
	 * @throws Exception NO esperada.
	 */
	@ParameterizedTest(name = "altura → {0}")
	@CsvSource({ "1,1,1", "2,2,3", "3,4,6", "6,16,21", "9,37,45" })
	@DisplayName("Verificación vértices")
	void testVértices(int altura, int v2, int v3) throws Exception {
		TriánguloM t;
		t = new TriánguloM(altura);

		int v1 = 1; // Siempre
		String s1, s2, s3, texto;

		String formato = TriánguloM.FORMATO;
		s1 = String.format(formato, v1);
		s2 = String.format(formato, v2);
		s3 = String.format(formato, v3);

		texto = t.get(1, 1);
		assertEquals(s1, texto, "Vertice NN");
		texto = t.get(altura, 1);
		assertEquals(s2, texto, "Vertice SW");
		texto = t.get(altura, altura);
		assertEquals(s3, texto, "Vertice SE");
	}

	/**
	 * Modifica los tres vértices con valores aleatorios y analiza la veracidad de
	 * las tres consultas correspondientes.
	 * 
	 * @param altura intentos con triángulos de tamaños { 2, 3, 6, 9, 73 }
	 * @throws Exception NO esperada.
	 */
	@ParameterizedTest(name = "altura → {0}")
	@ValueSource(ints = { 2, 3, 6, 9, 73 })
	@DisplayName("Modificación vértices")
	void testModificaciónVértices(int altura) throws Exception {
		TriánguloM t;
		t = new TriánguloM(altura);

		int v1 = (int) (Math.random() * 1000);
		int v2 = (int) (Math.random() * 1000);
		int v3 = (int) (Math.random() * 1000);
		String s1, s2, s3, texto;

		String formato = TriánguloM.FORMATO;
		s1 = String.format(formato, v1);
		s2 = String.format(formato, v2);
		s3 = String.format(formato, v3);

		t.set(1, 1, s1);
		t.set(altura, 1, s2);
		t.set(altura, altura, s3);

		texto = t.get(1, 1);
		assertEquals(s1, texto, "Vertice NN");
		texto = t.get(altura, 1);
		assertEquals(s2, texto, "Vertice SW");
		texto = t.get(altura, altura);
		assertEquals(s3, texto, "Vertice SE");
	}

	/**
	 * Comprueba que al {@link TriánguloM#batir) los datos no se dé el caso
	 * -improbable en triángulos de tamaño medio- de que se mantengan en su sitio
	 * los tres vértices.
	 * 
	 * @param altura intentos con triángulos de tamaños { 3, 6, 9 }
	 * @throws Exception NO esperada.
	 */
	@ParameterizedTest(name = "altura → {0}")
	@CsvSource({ "3,4,6", "6,16,21", "9,37,45" })
	@DisplayName("Análisis mezclado")
	void testBatir(int altura, int v2, int v3) throws Exception {
		TriánguloM t;
		t = new TriánguloM(altura);

		int v1 = 1; // Siempre
		String s1, s2, s3;
		boolean bool1, bool2, bool3;

		String formato = TriánguloM.FORMATO;
		s1 = String.format(formato, v1);
		s2 = String.format(formato, v2);
		s3 = String.format(formato, v3);

		t.batir();

		bool1 = t.get(1, 1).compareTo(s1) == 0;
		bool2 = t.get(altura, 1).compareTo(s2) == 0;
		bool3 = t.get(altura, altura).compareTo(s3) == 0;

		assertFalse(bool1 && bool2 && bool3, "Coincidencia improbable");
	}

	/**
	 * Verifica si, tras {@link TriánguloM#batir) la lista, {@link TriánguloM#sort)
	 * la vuelve a dejar ordenada.
	 * 
	 * @param altura intentos con triángulos de tamaños { 6, 9, 73 }
	 * @throws Exception NO esperada.
	 */
	@ParameterizedTest(name = "altura → {0}")
	@ValueSource(ints = { 6, 9, 73 })
	@DisplayName("Verificación de ordenación")
	void testVerificaciónSort(int altura) throws Exception {
		int nElementos;
		TriánguloM t;
		t = new TriánguloM(altura);

		nElementos = t.getNúmElementos();

		t.batir();
		t.sort();

		for (int i = 0; i < nElementos - 1; i++) {
			boolean esMenorIgual = t.get(i).compareTo(t.get(i + 1)) <= 0;
			assertTrue(esMenorIgual, "Posiciones " + i + "-" + (i + 1));
		}
	}

	
}

Enlace al Repositiorio
https://github.com/ManuelPavonBuendia/PracticaGrep.git

# Grep en Java

En este ejercicio implemento un filtrado de texto similar al comando `grep`, donde le paso un bloque de texto al programa y filtro las líneas que contienen la palabra **PSP**.

Para ello, he creado la clase `Grep.java`, donde desarrollo la lógica del programa que realiza el filtrado de texto.

## Grep.java
### 1.Constantes
```java
public static final String[] COMANDOS = { "grep", "PSP" };
```
Definimos el comando que vamos a ejecutar, el cual busca las líneas que contienen la palabra **PSP**.

```java
private final static String TEXTO = """
            Me gusta PSP y java
            PSP se programa en java
            es un modulo de DAM
            y se programa de forma concurrente en PSP
            PSP es programación.
            """;
```
Definimos el texto sobre el que vamos aplciar el filtrado.
```java
private final static String errorGrep = "Error al ejecutar grep";
```
Definimos el texto de error en caso de que falle el grep.

### 2.Método ``Main``

```java
 Process proceso = Runtime.getRuntime().exec(COMANDOS);
           try {
            Process proceso = Runtime.getRuntime().exec(COMANDOS);
            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(proceso.getOutputStream()))) {
                writer.write(TEXTO);
                writer.flush();
            }
            String filtrado = leerSalida(new InputStreamReader(proceso.getInputStream()));
            System.out.println(filtrado);
            int exitVal = proceso.waitFor();
            if (exitVal != 0) {
                System.err.println(ERRORGREP);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            System.exit(34);
        }
    
```
Primero, dentro del bloque try, se ejecuta el comando externo ``grep PSP`` como un nuevo proceso. A continuación, enviamos el texto al proceso mediante la salida estándar. Seguidamente, se le spasa la salida del proceso al metodo ``leerSalida()``, que se encargara de filtrar y monstar solo aquellas frases que contengasn **PSP**,Después, esperamos a que el proceso externo finalice y, si devuelve un valor distinto de cero, mostramos el mensaje de error correspondiente. Finalmente, si ocurre alguna excepción durante la ejecución, el bloque catch captura el fallo y el programa termina mostrando el código de salida 34.


### 3.Método ``leerSalida()``




```java
public static String leerSalida(Reader input) {
        StringBuilder resultado = new StringBuilder();
        try (BufferedReader lectura = new BufferedReader(input)) {
            String linea;
            while ((linea = lectura.readLine()) != null) {
                resultado.append(linea).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultado.toString();
    }
```
- Recibe la salida del proceso.

- Lee línea por línea la salida del proceso y acumula en un StringBuilder las lienas que continen **PSP**.

- Devuelve el texto completo como un String.

## TEST JUnit
Los test de JUnit estan realizados en GrepTest.java, donde he hecho los siguientes test:
### 1.``escribirTextoAlProceso()``
```java
    @Test
    void testLeerVariasLineasDesdeProceso() throws Exception {
        String texto = "Línea 1\n" +
                "Línea 2\n" +
                "PSP Línea 3\n" +
                "PSP Línea 4";

        escribirTextoAlProceso(proceso, texto);
        String resultado = Grep.leerSalida(new InputStreamReader(proceso.getInputStream()));
        String esperado = "PSP Línea 3\nPSP Línea 4\n";

        assertEquals(esperado, resultado);
    }
``` 
Verifica que el método ``leerSalida()`` de la clase Grep pueda leer correctamente varias líneas desde la salida de un proceso externo y que solo devuelva las líneas que contengan la palabra **PSP**.

### 2.``testGrepSinCoincidencias()``
```java
@Test
    void testGrepSinCoincidencias() throws Exception {
        String texto = "Java\n" + 
                "Python\n" + 
                "DAM\n+" + 
                "Concurrente\n";

        escribirTextoAlProceso(proceso, texto);
        String resultado = Grep.leerSalida(new InputStreamReader(proceso.getInputStream()));
        String esperado = "";

        assertEquals(esperado, resultado);
    }
```
verifica que el método ``leerSalida()`` de la clase Grep pueda leer correctamente la saldia, donde comprobamos que no tendria que devolver nada porque no en ninguno pone **PSP**.




package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GrepTest {
    private Process proceso;

    void escribirTextoAlProceso(Process proceso, String texto) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(proceso.getOutputStream()))) {
            writer.write(texto);
            writer.flush();
        }
    }

    @BeforeEach
    void iniciarProceso() throws Exception {
        proceso = Runtime.getRuntime().exec(new String[] { "grep", "PSP" });
    }

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

}

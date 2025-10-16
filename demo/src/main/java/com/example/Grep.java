package com.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;

public class Grep {
    public static final String[] COMANDOS = { "grep", "PSP" };
    private final static String TEXTO = """
            Me gusta PSP y java
            PSP se programa en java
            es un modulo de DAM
            y se programa de forma concurrente en PSP
            PSP es programación.
            """;
    private final static String ERRORGREP = "Error al ejecutar grep";

    public static void main(String[] args) {
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
    }

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
}
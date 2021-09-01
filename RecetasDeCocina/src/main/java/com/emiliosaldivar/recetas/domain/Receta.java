package com.emiliosaldivar.recetas.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Receta implements Serializable {

    //atributos
    private String tituloReceta;
    private ArrayList<String> ingredientes = new ArrayList<>();
    private ArrayList<String> pasosElaboracion = new ArrayList<>();
    private int contadorIngredientes;
    private int contadorPasosElaboracion;

    //constructores
    public Receta() {
    }

    public Receta(String nombreReceta) {
        this.tituloReceta = nombreReceta;
    }

    //metodos
    public String getTituloReceta() {
        return this.tituloReceta;
    }

    public void setTituloReceta(String tituloReceta) {
        this.tituloReceta = tituloReceta;
    }

    private String listarIngredientes() {
        String next = "";
        String concat = "";
        for (Iterator<String> iterator = this.ingredientes.iterator(); iterator.hasNext();) {
            next = iterator.next();
            concat = concat + "\n" + next;

        }
        return concat;
    }

    private String listarPasosElaboracion() {
        String next = "";
        String concat = "";
        for (Iterator<String> iterator = this.pasosElaboracion.iterator(); iterator.hasNext();) {
            next = iterator.next();
            concat = concat + "\n" + next;

        }
        return concat;
    }

    //metodos especificos
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Receta\nTitulo: ").append(this.tituloReceta);
        sb.append("\nIgredientes: \n").append(this.listarIngredientes());//agregar metodo para listar los ingredientes
        sb.append("Pasos para al elaboracion:\n").append(this.listarPasosElaboracion());//se puede reutilizar el 
        //metodo utilizado para los ingredientes
        return sb.toString();
    }

    public void agregaraIngredietes() {
        Scanner consola = new Scanner(System.in);
        String ingrediente;
        int opcion = -1;
        System.out.println("Agregue por lo menos 1 y como maximo 10:\n");
        while (this.contadorIngredientes <= 10) {
            System.out.println("Ingresar ingrediente:\n");
            ingrediente = consola.nextLine();
            this.ingredientes.add(ingrediente);
            this.contadorIngredientes++;
            System.out.println("Desea agregar más ingredientes?\n 0. - No, continuar.\n1. - Si, agregar.\n");
            opcion = Integer.parseInt(consola.nextLine());
            if (opcion == 0) {
                break;
            } else {
                continue;
            }

        }
        if (this.contadorIngredientes >= 10) {
            System.out.println("Se llego a la capacidad maxima de ingredientes\nSi desea agregar mas ingredientes compre la version premium.\n");
        }
    }

    public void agregarPasosElaboracion() {
        Scanner consola = new Scanner(System.in);
        String pasosElaboracion;
        int opcion = -1;
        System.out.println("Agregando pasos, agregue por lo menos 1 y como maximo 10");
        while (this.contadorIngredientes <= 10) {
            System.out.println("Ingresar Paso de elaboracion:\n");
            pasosElaboracion = consola.nextLine();
            this.pasosElaboracion.add(pasosElaboracion);
            this.contadorPasosElaboracion++;
            System.out.println("Desea agregar mas pasos, ingrese una opcion?\n 0. - No, continuar.\n1. - Si, agregar.\n");
            opcion = Integer.parseInt(consola.nextLine());
            if (opcion == 0) {
                break;
            } else {
                continue;
            }

        }
        if (this.contadorPasosElaboracion >= 10) {
            System.out.println("Se llego a la capacidad maxima de pasos de elaboracion\nSi desea agregar mas pasos compre la version premium.\n");
        }
    }

}

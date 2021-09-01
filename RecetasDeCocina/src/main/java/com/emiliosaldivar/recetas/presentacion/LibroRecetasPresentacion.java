package com.emiliosaldivar.recetas.presentacion;

import com.emiliosaldivar.recetas.negocio.*;
import java.util.Scanner;

public class LibroRecetasPresentacion {

    public static void main(String[] args) {
        Scanner consola = new Scanner(System.in);
        int opcion = -1;
        ILibroDeRecetas libroDeRecetas = new LibroDeRecetasImplementacion();
        libroDeRecetas.iniciarLibroDeRecetas();
        while (opcion != 0) {
            System.out.println("Elige opcion: \n");
            System.out.println("1.- Agregar Receta\n"
                             + "2.- Buscar Receta Por Título\n"
                             + "3.- Listar Recetas disponibles\n"
                             + "4.- Detalles de Receta\n"
                             + "0.- Salir del sistema");
            opcion = Integer.parseInt(consola.nextLine());
            
            switch(opcion){
                case 1:
                    System.out.println("Ingrese el nombre de la receta: ");
                    String nombreReceta = consola.nextLine();
                    libroDeRecetas.agregarReceta(nombreReceta);
                    
                    
                    break;
                case 2:
                    
                        System.out.println("Introduce el nombre de la receta:\n");
                        String recetaBuscada = consola.nextLine();
                        libroDeRecetas.buscarRecetaPorTitulo( recetaBuscada);
                    
                    break;
                    
                case 3:
                    
                        System.out.println("Estas son las recetas disponibles:\n");
                        libroDeRecetas.listarTitulos();
                        
                    break;
                case 4:
                        System.out.println("Ingrese el nombre de la receta que desea detallar:\n");
                        String recetaDetalle = consola.nextLine();
                        libroDeRecetas.imprimirDetallesDeReceta(recetaDetalle);
                    break;
                    
                case 0:
                    System.out.println("Hasta pronto...\n");
                    libroDeRecetas.guardarLibroDeRecetas();
                
                break;
                    
                          
            }
        }
    }

}

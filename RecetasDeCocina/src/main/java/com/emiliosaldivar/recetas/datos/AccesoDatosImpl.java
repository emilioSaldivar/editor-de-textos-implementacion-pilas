package com.emiliosaldivar.recetas.datos;

import com.emiliosaldivar.recetas.domain.Receta;
import java.io.*;
import java.util.*;

public class AccesoDatosImpl implements IAccesoDatos, Serializable {

    HashMap<String, Receta> libroDeRecetas = new HashMap<>();

    @Override
    public boolean existe() {
        
        //se ve si el archivo existe en memoria 
        File archivo = new File("libroDeRecetas.obj");
        return archivo.exists();
        
    }

    private void borrar(String nombre) {
       
        File arcFile = new File(nombre);
        arcFile.delete();
        System.out.println("Archivo borrado de la memoria.");

    }

    @Override
    public void guardar(HashMap<String, Receta> libroReceta) {
        
        
        

        ObjectOutputStream salida = null;

        try {
            salida = new ObjectOutputStream(new FileOutputStream("libroDeRecetas.obj"));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }

        try {
            salida.writeObject(libroReceta);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }

        try {
            salida.close();
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
        System.out.println("Archivo crado con éxito.\n");

    }

    @Override
    public HashMap<String, Receta> recuperar() {
        
        if ( this.existe() ) {
            ObjectInputStream entrada = null;
            try {
                entrada = new ObjectInputStream(new FileInputStream("libroDeRecetas.obj"));
            } catch (FileNotFoundException ex) {
                ex.printStackTrace(System.out);
            } catch (IOException ex) {
                ex.printStackTrace(System.out);
            }

            HashMap<String, Receta> libroReceta = null;
            try {
                libroReceta = (HashMap) entrada.readObject();
            } catch (IOException ex) {
                ex.printStackTrace(System.out);
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace(System.out);
            }

            return libroReceta;
        }else{
            System.out.println("No se encontro archivo en memoria\n");
            return null;
        }

    }

}

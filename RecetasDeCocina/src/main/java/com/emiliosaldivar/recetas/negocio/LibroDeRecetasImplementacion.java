package com.emiliosaldivar.recetas.negocio;

import com.emiliosaldivar.recetas.datos.AccesoDatosImpl;
import com.emiliosaldivar.recetas.datos.IAccesoDatos;
import com.emiliosaldivar.recetas.domain.Receta;
import com.emiliosaldivar.recetas.excepciones.EscrituraDatosEx;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class LibroDeRecetasImplementacion implements ILibroDeRecetas, Serializable {

    private final IAccesoDatos datos;
    private HashMap< String, Receta> libroRecetas = new HashMap<>();

    public LibroDeRecetasImplementacion() {
        this.datos = new AccesoDatosImpl();
    }
    //implementacion de la interfaz

    @Override
    public void agregarReceta(String nombreReceta) {
        Receta nuevaReceta = new Receta(nombreReceta);

        System.out.println("Agregando Ingredientes:\n");
        nuevaReceta.agregaraIngredietes();
        System.out.println("Agregando pasos de elaboracion\n");
        nuevaReceta.agregarPasosElaboracion();

        this.libroRecetas.put(nombreReceta, nuevaReceta);

    }

    @Override
    public void buscarRecetaPorTitulo(String nombreReceta) {
            if (this.libroRecetas.containsKey(nombreReceta)) {
                System.out.println("Receta buscada:\n" + this.libroRecetas.get(nombreReceta).toString());
            } else {
                System.out.println("No se encontró la receta en el libro.\n");
        }
    }

    @Override
    public void listarTitulos() {

        for (Map.Entry<String, Receta> entry : libroRecetas.entrySet()) {
            String titulo = entry.getKey();
            System.out.println("Titulo:\t " + titulo + "\n");

        }

    }

    @Override
    public void iniciarLibroDeRecetas() {
        if (this.datos.existe()) {
            this.libroRecetas = this.datos.recuperar();
            System.out.println("Datos cargados\n");
        } else {
            System.out.println("No se encontro un libro de recetas.");
        }

    }

    @Override
    public void imprimirDetallesDeReceta(String nombreReceta) {

        if (this.libroRecetas.containsKey(nombreReceta)) {
            System.out.println("Detalles de receta:\n" + this.libroRecetas.get(nombreReceta).toString());
        } else {
            System.out.println("No se encontró dicha receta en el libro de recetas.\n");
        }

    }

    @Override
    public void guardarLibroDeRecetas() {
        try {
            this.datos.guardar(this.libroRecetas);
        } catch (EscrituraDatosEx ex) {
            ex.printStackTrace(System.out);
        }
    }

}


package com.emiliosaldivar.recetas.negocio;


public interface ILibroDeRecetas {
    
    public void agregarReceta(String nombreReceta);
    public void buscarRecetaPorTitulo(String nombreReceta);
    public void listarTitulos();
    public void iniciarLibroDeRecetas();
    public void imprimirDetallesDeReceta(String nombreReceta);
    public void guardarLibroDeRecetas ();
    
}
/*La interfaz continen las operaciones necesarias para la aplicacion del catalogo de recetas*/

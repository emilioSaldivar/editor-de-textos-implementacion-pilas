
package com.emiliosaldivar.recetas.datos;

import com.emiliosaldivar.recetas.domain.Receta;
import com.emiliosaldivar.recetas.excepciones.EscrituraDatosEx;
import java.util.*;


public interface IAccesoDatos{
    public HashMap < String, Receta> recuperar();
    public void guardar(HashMap<String, Receta> libroDeRecetas) throws EscrituraDatosEx;
    public boolean existe();
}
/*La interface contiene las operaciones a ser ejecutadas en el archivo "libroDeRecetas.obj"*/
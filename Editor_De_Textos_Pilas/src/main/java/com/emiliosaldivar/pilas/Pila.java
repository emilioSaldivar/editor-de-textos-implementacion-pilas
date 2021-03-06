package com.emiliosaldivar.pilas;

public class Pila<TipoDeDato> implements InterfazPila<TipoDeDato> {

    private NodoPila<TipoDeDato> cabecera;
    private int tamanoPila;
    
    /**
     * Constructor de la clase, se inicializa la pila
     */
    public Pila() {
        tamanoPila = 0;
        cabecera = new NodoPila<>();
        cabecera.siguiente = null;
    }
    
    @Override
    public void apilar(TipoDeDato nuevoDato){
    	NodoPila<TipoDeDato> nuevoNodo = new NodoPila<>();
        nuevoNodo.dato = nuevoDato;
        nuevoNodo.siguiente = cabecera.siguiente;
        cabecera.siguiente = nuevoNodo;
        tamanoPila = tamanoPila + 1;
    }
    
    @Override
    //Si se intenta desapilar algo de una lista vacia, retorna null
    public TipoDeDato desapilar(){
    	TipoDeDato ret = null;
    	if (!esVacia()) {
    	    ret = cabecera.siguiente.dato;
            cabecera.siguiente = cabecera.siguiente.siguiente;
            tamanoPila = tamanoPila - 1;
        }
        return ret;
    }
    
    @Override
    //Si se intenta obtener algo de una lista vacia, retorna null
    public TipoDeDato obtenerTope(){
    	TipoDeDato ret = null;
    	if (!esVacia()) {
    	    ret = cabecera.siguiente.dato;            
        }
        return ret;
    }
    
    @Override
    public void anular(){
    	cabecera.siguiente = null;
    	tamanoPila = 0;
    }
    
    @Override
    public int tamanoPila() {
        return tamanoPila;
    }

    @Override
    public boolean esVacia() {
        if (tamanoPila == 0) {
            return true;
        } else {
            return false;
        }
    }    
        
}

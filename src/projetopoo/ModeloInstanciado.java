/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo;

import java.util.ArrayList;

/**
 *
 * @author calos
 */
public class ModeloInstanciado {
    private ArrayList <ObjFluxo> atividade = new ArrayList<ObjFluxo>();
    private ArrayList <Integer> tid = new ArrayList<Integer>();
    private ArrayList <String> tipo = new ArrayList <String>();
    private ArrayList <Recurso> recursoAlocado = new ArrayList <Recurso>();
    private ArrayList <Boolean> terminado = new ArrayList <Boolean> ();  
    private ArrayList <String> dataHoraI = new ArrayList<String> ();
    private ArrayList <String> dataHoraF = new ArrayList<String>();
}

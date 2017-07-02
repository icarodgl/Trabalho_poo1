/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetopoo;

import java.util.ArrayList;



/**
 *
 * @author calos
 */
public class ListaInstanciados{
    private ArrayList<ModeloInstanciado> listaInstanciados = new ArrayList<ModeloInstanciado>();
 
    public String listarModeloI(){
        String imprimir="";
        for(int i = 0; i < listaInstanciados.size(); i++){
            imprimir= "Id:"+listaInstanciados.get(i).getId()+" | Nome:"+listaInstanciados.get(i).getNome()+"\n";
        }
        return(imprimir);
    }
    
    public int buscaModeloI(int id){
        int i;
        for(i = 0; i < listaInstanciados.size(); i++){
            if(listaInstanciados.get(i).getId() == id){
              return(i);  
            }
        }
        return(-1);
    }
    
     public void excluirModeloI(int id){
        int i;
        i=buscaModeloI(id);
        if(i==-1){
            System.out.println("Modelo Instanciado Inexistente");
            return;
        }
        listaInstanciados.remove(i);
     }
}

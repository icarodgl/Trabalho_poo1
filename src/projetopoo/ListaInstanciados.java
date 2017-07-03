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
public class ListaInstanciados extends ArrayList<ModeloInstanciado>{
    
    public String listarModeloI(){
        String imprimir="";
        for(int i = 0; i < this.size(); i++){
            imprimir= "Id:"+this.get(i).getId()+" | Nome:"+this.get(i).getNome()+"\n";
        }
        return(imprimir);
    }
    
    public int buscaModeloI(int id){
        int i;
        for(i = 0; i < this.size(); i++){
            if(this.get(i).getId() == id){
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
        this.remove(i);
     }
}

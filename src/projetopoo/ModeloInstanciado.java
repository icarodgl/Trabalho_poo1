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
public class ModeloInstanciado {
    private String nome;
    private int id;
    private ArrayList <ObjFluxo> atividade = new ArrayList<ObjFluxo>();
    private ArrayList <Integer> tid = new ArrayList<Integer>();
    private ArrayList <Recurso> recursoAlocado = new ArrayList <Recurso>();
    private ArrayList <String> terminado = new ArrayList <String> ();  
    private ArrayList <String> dataHoraI = new ArrayList<String> ();
    private ArrayList <String> dataHoraF = new ArrayList<String>();


    public String ImprimirRelatorio(){
        String imprimir = "|    Atividade   |   TID |   Tipo    | Recurso Alocado |   Terminado  |   Data-hora de Inicio | Data-hora de Termino  |\n";
        for(int i=0; i<atividade.size() ; i++){
          imprimir= imprimir+"| "+ atividade.get(i).getNome()+"  |";
          imprimir= imprimir+"  "+ tid.get(i)+" |";
          imprimir= imprimir+"  "+ atividade.get(i).getTipo()+" |";
          imprimir= imprimir+"  "+ recursoAlocado.get(i).getRecurso()+" |";
          if (terminado.get(i).equals("sim")){
              imprimir= imprimir+"  Sim |";
              imprimir= imprimir+"  "+ dataHoraI+" |";
              imprimir= imprimir+"  "+ dataHoraF+" |\n---------------------------------------------------------------------------------------------------------------------------------------------------|\n";
          }
          else{
              imprimir= imprimir+"  Não |";
              imprimir= imprimir+"  |               |   |               |\n---------------------------------------------------------------------------------------------------------------------------------------------------|\n";
          }
        }
        return(imprimir);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<ObjFluxo> getAtividade() {
        return atividade;
    }

    public void setAtividade(ObjFluxo atividade) {
        this.atividade.add(atividade);
    }

    public ArrayList<Integer> getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid.add(tid);
    }

    public ArrayList<Recurso> getRecursoAlocado() {
        return recursoAlocado;
    }

    public void setRecursoAlocado(Recurso recursoAlocado) {
        this.recursoAlocado.add(recursoAlocado);
    }

    public ArrayList<String> getTerminado() {
        return terminado;
    }

    public void setTerminado(String terminado) {
        this.terminado.add(terminado);
    }

    public ArrayList<String> getDataHoraI() {
        return dataHoraI;
    }

    public void setDataHoraI(String dataHoraI) {
        this.dataHoraI.add(dataHoraI);
    }

    public ArrayList<String> getDataHoraF() {
        return dataHoraF;
    }

    public void setDataHoraF(String dataHoraF) {
        this.dataHoraF.add(dataHoraF);
    }
    
    
    
}
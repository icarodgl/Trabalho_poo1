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


public class Dominio {
    private ArrayList <ObjFluxo> atividade = new ArrayList<ObjFluxo>();
    private ArrayList <Integer> tid = new ArrayList<Integer>();
    private ArrayList <String> tipo = new ArrayList <String>();
    private ArrayList <String> recurso = new ArrayList <String>();

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

    public ArrayList<String> getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo.add(tipo);
    }

    public ArrayList<String> getRecurso() {
        return recurso;
    }

    public void setRecurso(String recurso) {
        this.recurso.add(recurso);
    }
    
}

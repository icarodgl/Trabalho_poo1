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
    private int id;
    private ArrayList <Atividade> atividades = new ArrayList();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public ArrayList<Atividade> getAtividades() {
        ArrayList<Atividade> a = new ArrayList();
        a = this.atividades;
        return a;
    }

    public void setAtividades(Atividade a) {
        this.atividades.add(a);
    }

}
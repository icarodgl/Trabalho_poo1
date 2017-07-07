/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetopoo;

import java.util.ArrayList;

/**
 *
 * @author icaro
 */
public class Atividade {
    private int id;
    private String Nome;
    private String tipo;
    private String tiporecurso;
    private ArrayList <Recurso> recursos;
    private String inicio;
    private String fim;

    public String getTiporecurso() {
        return tiporecurso;
    }

    public void setTiporecurso(String tiporecurso) {
        this.tiporecurso = tiporecurso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public ArrayList<Recurso> getRecursos() {
        return recursos;
    }

    public void setRecursos(Recurso r) {
        this.recursos.add(r);
    }
    public void setRecursos(ArrayList<Recurso> r) {
        this.recursos = r;
    }
    public Atividade() {
        this.recursos = new ArrayList();
    }
    
    
}

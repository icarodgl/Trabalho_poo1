/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetopoo;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author icaro
 */
public class Atividade {
    private int id;
    private int id_modelo;
    private String Nome;
    private String tipo;
    private String tiporecurso;
    private String recursoAlocado;
    private ArrayList <Recurso> recursos;
    private Date inicio;
    private Date fim;

    public int getId_modelo() {
        return id_modelo;
    }

    public void setId_modelo(int id_modelo) {
        this.id_modelo = id_modelo;
    }

    public String getRecursoAlocado() {
        return recursoAlocado;
    }

    public void setRecursoAlocado(String recursoAlocado) {
        this.recursoAlocado = recursoAlocado;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFim() {
        return fim;
    }

    public void setFim(Date fim) {
        this.fim = fim;
    }

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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetopoo;

import java.util.ArrayList;

/**
 *
 * @author lucas
 */
public class ObjetoDeFLuxo {
    private int situacao;
    private ArrayList<Regra> regras;

    public ObjetoDeFLuxo() {
        this.regras = new ArrayList();
    }

    public int getSituacao() {
        return situacao;
    }

    public void setSituacao(int situacao) {
        this.situacao = situacao;
    }
    
}

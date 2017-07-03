package projetopoo;
import java.util.ArrayList;
import java.util.Collection;

public class Modelo {
    private int id;
    private String nome;
    private ArrayList<Regra> regra = new ArrayList<Regra>();
    private Dominio dominio;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Regra> getRegra() {
        return regra;
    }

    public void setRegra(ArrayList<Regra> regra) {
        this.regra = regra;
    }

    public Dominio getDominio() {
        return dominio;
    }

    public void setDominio(Dominio dominio) {
        this.dominio = dominio;
    }
	
    
    
}

package poo;


public class Tarefa extends ObjFluxo {
    private int tempo;
    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }
    public Tarefa(){
            this.setTipo("Tarefa");
    }
}

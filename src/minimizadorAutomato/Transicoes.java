package minimizadorAutomato;

public class Transicoes {
    private Estado saida;
    private Estado chegada;
    private char simb;

    public Transicoes() {
    }

    public Transicoes(Estado saida, char simb, Estado chegada) {
        this.saida = saida;
        this.chegada = chegada;
        this.simb = simb;
    }

    public Estado getSaida() {
        return saida;
    }

    public void setSaida(Estado saida) {
        this.saida = saida;
    }

    public Estado getChegada() {
        return chegada;
    }

    public void setChegada(Estado chegada) {
        this.chegada = chegada;
    }

    public char getSimb() {
        return simb;
    }

    public void setSimb(char simb) {
        this.simb = simb;
    }

    public String mostraChegada(Estado chegada){
        return chegada.getNome();
    }
    @Override
    public String toString() {
        return "Transicoes{" +
                "saida=" + saida +
                ", chegada=" + chegada +
                ", simb=" + simb +
                '}';
    }
}

package minimizadorAutomato;

// classe que define as transições
public class Transicoes implements Cloneable{
    private Estado saida;
    private Estado chegada;
    private char simb;

    public Transicoes() {
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

    @Override
    public String toString() {
        return "Transicoes{" +
                "saida=" + saida +
                ", chegada=" + chegada +
                ", simb=" + simb +
                '}';
    }

    @Override
    public Transicoes clone() {
        try {
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return (Transicoes) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}

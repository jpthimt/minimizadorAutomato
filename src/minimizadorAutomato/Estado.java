package minimizadorAutomato;
public class Estado implements Cloneable{
    private String nome;
    private boolean end = false;
    private boolean start = false;
    private int cod;
    public Estado() {
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public Estado(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    @Override
    public String toString() {
        return "Estado{" +
                "cod=" + cod + '\'' +
                ", nome='" + nome + '\'' +
                ", end=" + end +
                ", start=" + start +
                '}';
    }

    @Override
    public Estado clone() {
        try {
            Estado clone = (Estado) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}

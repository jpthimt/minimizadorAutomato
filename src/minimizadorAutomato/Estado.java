package minimizadorAutomato;
public class Estado {
    private String nome;
    private boolean end = false;
    private boolean start = false;
    public Estado() {
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
                "nome='" + nome + '\'' +
                ", end=" + end +
                ", start=" + start +
                '}';
    }
}

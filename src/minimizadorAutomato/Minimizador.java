package minimizadorAutomato;

import java.util.List;

public class Minimizador {
    private final List<Estado> listEst;
    private final List<Transicoes> listTr;

    public Minimizador(List<Estado> listEst, List<Transicoes> listTr) {
        this.listEst = listEst;
        this.listTr = listTr;
    }

    public int verificaIgualdade(Estado e1, Estado e2, List<Transicoes> listTr){
        int aux1, aux2, compara=0, res=0, comp1, comp2;
        if(Boolean.compare(e1.isEnd(),e2.isEnd())==0){ //compara os estados
            //compara os estados apos uma transição
            comp1 = Boolean.compare(listTr.get((e1.getCod()*2)).getChegada().isEnd(),listTr.get((e2.getCod()*2)).getChegada().isEnd());
            comp2 = Boolean.compare(listTr.get((e1.getCod()*2)+1).getChegada().isEnd(), listTr.get((e2.getCod()*2)+1).getChegada().isEnd());
            if(comp1 == 0 && comp2 == 0){
                //compara os estados apos duas transições
                aux1 = listTr.get((e1.getCod()*2)).getChegada().getCod();
                aux2 = listTr.get((e2.getCod()*2)).getChegada().getCod();
                if(Boolean.compare(listTr.get((aux1*2)).getChegada().isEnd(), listTr.get((aux2*2)).getChegada().isEnd())==0
                        && Boolean.compare(listTr.get((aux1*2)+1).getChegada().isEnd(), listTr.get((aux2*2)+1).getChegada().isEnd())==0){
                    compara++;
                }
                aux1 = listTr.get((e1.getCod()*2)+1).getChegada().getCod();
                aux2 = listTr.get((e2.getCod()*2)+1).getChegada().getCod();
                if(Boolean.compare(listTr.get((aux1*2)).getChegada().isEnd(), listTr.get((aux2*2)).getChegada().isEnd())==0
                        && Boolean.compare(listTr.get((aux1*2)+1).getChegada().isEnd(), listTr.get((aux2*2)+1).getChegada().isEnd())==0){
                    compara++;
                }
                if(compara == 2)
                    return 0; //os estados são equivalentes após as duas transições
                else
                    return 1; //os estados não são equivalentes após as duas transições
            }
            return 2; //os estados não são equivalentes após uma transição
        }else
            return 3; //os estados não são equivalentes;
    }

    public void mostraResultado(int res){
        if(res==0)
            System.out.println("Os estados são iguais");
        else if(res==1)
            System.out.println("Os estados não são equivalentes depois de 2 transições");
        else if(res==2)
            System.out.println("Os estados não são equivalentes depois de 1 transição");
        else
            System.out.println("Os estados não são equivalentes");
    }
}

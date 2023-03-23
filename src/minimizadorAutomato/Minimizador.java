package minimizadorAutomato;

import java.util.List;

public class Minimizador {
    private List<Estado> listEst;
    private List<Transicoes> listTr;

    public Minimizador(List<Estado> listEst, List<Transicoes> listTr) {
        this.listEst = listEst;
        this.listTr = listTr;
    }

    public int verificaIgualdade(Estado e1, Estado e2, List<Transicoes> listTr){
        int aux1, aux2, compara=0, res=0;
        if(e1.isEnd() == e2.isEnd()){ //compara os estados
            //compara os estados apos uma transição
            if((listTr.get((e1.getCod()*2)).getChegada().isEnd() == listTr.get((e2.getCod()*2)).getChegada().isEnd())
                && (listTr.get((e1.getCod()*2)+1).getChegada().isEnd() == listTr.get((e2.getCod()*2)+1).getChegada().isEnd())){
                //compara os estados apos duas transições
                aux1 = listTr.get((e1.getCod()*2)).getChegada().getCod();
                aux2 = listTr.get((e2.getCod()*2)).getChegada().getCod();
                if((listTr.get((aux1*2)).getChegada().isEnd() == listTr.get((aux2*2)).getChegada().isEnd())
                        && (listTr.get((aux1*2)+1).getChegada().isEnd() == listTr.get((aux2*2)+1).getChegada().isEnd())){
                    compara++;
                }
                aux1 = listTr.get((e1.getCod()*2)+1).getChegada().getCod();
                aux2 = listTr.get((e2.getCod()*2)+1).getChegada().getCod();
                if((listTr.get((aux1*2)).getChegada().isEnd() == listTr.get((aux2*2)).getChegada().isEnd())
                        && (listTr.get((aux1*2)+1).getChegada().isEnd() == listTr.get((aux2*2)+1).getChegada().isEnd())){
                    compara++;
                }
                if(compara == 2)
                    res=0; //os estados são equivalentes após as duas transições
                else
                    res=1; //os estados não são equivalentes após as duas transições
            }
            res=2; //os estados não são equivalentes após uma transição
        }else
            res=3; //os estados não são equivalentes;
        return res;
    }

}

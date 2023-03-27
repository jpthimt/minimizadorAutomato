package minimizadorAutomato;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Minimizador {
    private final List<Estado> listEst;
    private final List<Transicoes> listTr;
    private List<Estado> newListEst;
    private List<Transicoes> newListTr;
    private List<Estado> listAux = new ArrayList<>();
    private List<Transicoes> newListTransicoes = new ArrayList<>();

    public Minimizador(List<Estado> listEst, List<Transicoes> listTr) {
        this.listEst = listEst;
        this.listTr = listTr;
        this.newListEst = listEst;
        this.newListTr = listTr;

    }
    public int verificaIgualdade(Estado e1, Estado e2){
        int aux1, aux2, compara=0, comp1, comp2;
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
                if(compara == 2) {
                    System.out.println(e1.getNome()+"adicionado");
                    listAux.add(e1);
                    System.out.println((e2.getNome()+"adicionado"));
                    listAux.add(e2);
                    return 0; //os estados são equivalentes após as duas transições
                }else
                    return 1; //os estados não são equivalentes após as duas transições
            }
            return 2; //os estados não são equivalentes após uma transição
        }else
            return 3; //os estados não são equivalentes;
    }
    public void verifica(Estado e1, Estado e2){
        int aux1, aux2, compara=0, comp1, comp2;
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
                if(compara == 2) {
                    System.out.println(e1.getNome()+"adicionado");
                    listAux.add(e1);
                    System.out.println((e2.getNome()+"adicionado"));
                    listAux.add(e2);
                     //os estados são equivalentes após as duas transições
                }
                     //os estados não são equivalentes após as duas transições
            }
             //os estados não são equivalentes após uma transição
        }
         //os estados não são equivalentes;
    }

    public void alteraLista(List<Estado> listAux){
        int i;
        for(i=0; i<listAux.size(); i++){
            if(i==0 || i%2==0){
                for(int j=0; j<newListTr.size(); j++){
                    if (listTr.get(j).getSaida().equals(listAux.get(i+1))){
                        newListTr.get(j).setSaida(listAux.get(i));
                    }
                    if (listTr.get(j).getChegada().equals(listAux.get(i+1))){
                        newListTr.get(j).setChegada(listAux.get(i));
                    }
                    //newListTr.remove()
                }
                for(int j=0; j<newListEst.size(); j++){
                    if(newListEst.get(j).equals(listAux.get(i))){
                        newListEst.get(j).setNome(listAux.get(i+1).getNome()+"\\"+newListEst.get(j).getNome());
                    }
                }
            }else{
                newListEst.remove(listAux.get(i));
            }
        }
        for(i=0;i<newListEst.size();i++){
            newListEst.get(i).setCod(i);
        }
        int aux=0;
        for(Transicoes tr1 : newListTr){
            newListTransicoes.add(tr1);
            for(Transicoes tr2 : newListTransicoes){
                if(tr1.getSaida().equals(tr2.getSaida()) && tr1.getSimb()== tr2.getSimb() && tr1.getChegada().equals(tr2.getChegada())){
                    aux++;
                }
            }
            if(aux>1){
                newListTransicoes.remove(tr1);
            }
            aux=0;
        }
        System.out.println(newListTransicoes);
        newListTr = newListTransicoes;
        setNewListEst(newListEst);
        setNewListTr(newListTr);
    }

    public List<Estado> getNewListEst() {
        return newListEst;
    }

    public List<Transicoes> getNewListTr() {
        return newListTr;
    }

    public void setNewListEst(List<Estado> newListEst) {
        this.newListEst = newListEst;
    }

    public void setNewListTr(List<Transicoes> newListTr) {
        this.newListTr = newListTr;
    }

    public List<Estado> getListAux() {
        return listAux;
    }

}

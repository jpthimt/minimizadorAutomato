package minimizadorAutomato;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Minimizador {
    private List<Estado> listEst = new ArrayList<>();
    private List<Transicoes> listTr = new ArrayList<>();
    private List<Estado> newListEst = new ArrayList<>();
    private List<Transicoes> newListTr = new ArrayList<>();
    private List<Estado> listAux = new ArrayList<>();
    private List<Transicoes> newListTransicoes = new ArrayList<>();

    public Minimizador(List<Estado> listEst, List<Transicoes> listTr) {
        for (Estado estado : listEst){
            this.listEst.add((Estado) estado.clone());
            newListEst.add((Estado) estado.clone());
        }
        for (Transicoes transicoes : listTr){
            this.listTr.add((Transicoes) transicoes.clone());
            newListTr.add((Transicoes) transicoes.clone());
        }
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
                    if (listTr.get(j).getSaida().getCod() == listAux.get(i+1).getCod()){
                        newListTr.get(j).setSaida(listAux.get(i).clone());
                    }
                    if (listTr.get(j).getChegada().getCod() == listAux.get(i+1).getCod()){
                        newListTr.get(j).setChegada(listAux.get(i).clone());
                    }
                    //newListTr.remove()
                }
                for(int j=0; j<newListEst.size(); j++){
                    if(newListEst.get(j).getCod() == listAux.get(i).getCod()){
                        newListEst.get(j).setNome(listAux.get(i+1).getNome()+"\\"+newListEst.get(j).getNome());
                    }
                }
            }else{
                for(int j=0; j<newListEst.size(); j++){
                    if(newListEst.get(j).getCod() == listAux.get(i).getCod()){
                        newListEst.remove(j);
                    }
                }
            }
        }
        for(Estado estado : newListEst){
            for(Transicoes transicoes : newListTr){
                if(transicoes.getSaida().getCod() == estado.getCod()){
                    transicoes.setSaida(estado);
                }
                if(transicoes.getChegada().getCod()== estado.getCod()){
                    transicoes.setChegada(estado);
                }
            }
        }
        for(i=0;i<newListEst.size();i++){
            newListEst.get(i).setCod(i);
        }


        int aux=0;
        for(Transicoes tr1 : newListTr){
            newListTransicoes.add(tr1);
            for(Transicoes tr2 : newListTransicoes){
                if(tr1.getSaida().getCod() == tr2.getSaida().getCod() && tr1.getSimb() == tr2.getSimb() && tr1.getChegada().getCod() == tr2.getChegada().getCod()){
                    aux++;
                }
            }
            if(aux>1){
                newListTransicoes.remove(tr1);
            }
            aux=0;
        }
        System.out.println(newListTransicoes);
        newListTr.clear();
        for (Transicoes transicoes : newListTransicoes){
            newListTr.add((Transicoes) transicoes.clone());
        }
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

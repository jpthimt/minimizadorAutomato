package minimizadorAutomato;

import java.util.ArrayList;
import java.util.List;

// clase que minimiza o automato
public class Minimizador {
    private final List<Transicoes> listTr = new ArrayList<>();
    private List<Estado> newListEst = new ArrayList<>();
    private List<Transicoes> newListTr = new ArrayList<>();
    private final List<Estado> listAux = new ArrayList<>();
    private final List<Transicoes> newListTransicoes = new ArrayList<>();

    public Minimizador(List<Estado> listEst, List<Transicoes> listTr) {
        for (Estado estado : listEst){
            newListEst.add(estado.clone());
        }
        for (Transicoes transicoes : listTr){
            this.listTr.add(transicoes.clone());
            newListTr.add(transicoes.clone());
        }
    }

    // retorna a equivalência dos estados
    public int verificaIgualdade(Estado e1, Estado e2){
        int aux1, aux2, compara=0, comp1, comp2;
        if(Boolean.compare(e1.isEnd(),e2.isEnd())==0){ // compara se os estados são finais
            // compara os estados apos uma transição
            comp1 = Boolean.compare(listTr.get((e1.getCod()*2)).getChegada().isEnd(),listTr.get((e2.getCod()*2)).getChegada().isEnd());
            comp2 = Boolean.compare(listTr.get((e1.getCod()*2)+1).getChegada().isEnd(), listTr.get((e2.getCod()*2)+1).getChegada().isEnd());
            if(comp1 == 0 && comp2 == 0){
                // compara os estados apos duas transições
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
                if(compara == 2) { // adiciona os estados equivalentes numa lista auxiliar
                    listAux.add(e1);
                    listAux.add(e2);
                    return 0; //os estados são equivalentes após duas transições
                }else
                    return 1; //os estados não são equivalentes após duas transições
            }
            return 2; //os estados não são equivalentes após uma transição
        }else
            return 3; //os estados não são equivalentes;
    }

    // compara a equivalência dos estados sem retorno
    public void verifica(Estado e1, Estado e2){
        int aux1, aux2, compara=0, comp1, comp2;
        if(Boolean.compare(e1.isEnd(),e2.isEnd())==0){ //compara se os estados são finais
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
                if(compara == 2) { // adiciona os estados equivalentes numa lista auxiliar
                    listAux.add(e1);
                    listAux.add(e2);
                }
            }
        }
    }

// gera a lista minimizada
    public void alteraLista(List<Estado> listAux){
        int i;
        for(i=0; i<listAux.size(); i++){ // percore a lista auxiliar
            if(i % 2 == 0){ // caso i seja par, o estado nessa posição será o referenciado
                /* percorre a lista de transições alterando a referência do estado, por exemplo:
                se q0 e q1 são equivalentes e q0 está na posição par da lista auxiliar,
                q1 será substituido por q0 em todas as transições que aparecer */
                for(int j=0; j<newListTr.size(); j++){
                    if (listTr.get(j).getSaida().getCod() == listAux.get(i+1).getCod()){
                        newListTr.get(j).setSaida(listAux.get(i).clone());
                    }
                    if (listTr.get(j).getChegada().getCod() == listAux.get(i+1).getCod()){
                        newListTr.get(j).setChegada(listAux.get(i).clone());
                    }
                }
                // altera o nome do estado, juntando os estados equivalentes
                for (Estado estado : newListEst) {
                    if (estado.getCod() == listAux.get(i).getCod()) {
                        estado.setNome(listAux.get(i + 1).getNome() + "\\" + estado.getNome());
                    }
                }
            }else{
                // caso i seja ímpar, o estado será removido da nova lista
                for(int j=0; j<newListEst.size(); j++){
                    if(newListEst.get(j).getCod() == listAux.get(i).getCod()){
                        newListEst.remove(j);
                        break;
                    }
                }
            }
        }
        // altera os estados nas transições
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
        // altera os códigos dos estados na nova lista
        for(i=0;i<newListEst.size();i++){
            newListEst.get(i).setCod(i);
        }

        // remove as transições duplicadas
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
        newListTr.clear();
        for (Transicoes transicoes : newListTransicoes){
            newListTr.add(transicoes.clone());
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

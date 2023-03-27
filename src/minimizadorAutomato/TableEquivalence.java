package minimizadorAutomato;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TableEquivalence extends AbstractTableModel {

    private final List<Estado> listEst;
    private final List<Transicoes> listTr;
    private String[] nomeCol;
    private Minimizador minimizador;
    private String equivalente = "O";
    private String semiequivalente = "@";
    private String naoequivalente = "X";

    private static List<Estado> newListE = new ArrayList<>();
    private static List<Transicoes> newListT = new ArrayList<>();

    public Minimizador getMinimizador() {
        return minimizador;
    }

    public TableEquivalence(List<Estado> listEstado, List<Transicoes> listTransicoes){
        listEst = listEstado;
        listTr = listTransicoes;
        minimizador = new Minimizador(listEst, listTr);
        nomeCol = new String[listEst.size()];
    }


    @Override
    public int getRowCount() {
        return listEst.size()-1;
    }

    @Override
    public int getColumnCount() {
        return nomeCol.length;
    }


    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex){
            case 0:
                return listEst.get(rowIndex+1).getNome();
            default:
                if((columnIndex-rowIndex)>1){
                    return null;
                }else{
                    int ver = minimizador.verificaIgualdade(listEst.get(rowIndex+1), listEst.get(columnIndex-1));
                    if(columnIndex==getColumnCount()-1 && rowIndex==getRowCount()-1){
                        minimizador.alteraLista(minimizador.getListAux());
                        newListE = minimizador.getNewListEst();
                        newListT = minimizador.getNewListTr();

                    }
                    if(ver==0)
                        return equivalente;
                    else if(ver==3)
                        return naoequivalente;
                    else
                        return semiequivalente;
                }

        }
    }
    @Override
    public String getColumnName(int indice){
        for(int i=0;i<listEst.size();i++){
            if(i==0){
                nomeCol[i]=" ";
            }else{
                nomeCol[i] = listEst.get(i-1).getNome();
            }
        }
        return nomeCol[indice];
    }

    public List<Estado> getNewListE() {
        return newListE;
    }

    public List<Transicoes> getNewListT() {
        return newListT;
    }

    public void geraList(){
        for(int rowIndex=0; rowIndex<getRowCount();rowIndex++){
            for(int columnIndex=1; columnIndex<=listEst.size(); columnIndex++){
                if ((columnIndex <= rowIndex) ) {
                    minimizador.verifica(listEst.get(rowIndex+1), listEst.get(columnIndex-1));
                    if (columnIndex == listEst.size()-1 && rowIndex == listEst.size()-1) {
                        minimizador.alteraLista(minimizador.getListAux());
                        newListE = minimizador.getNewListEst();
                        newListT = minimizador.getNewListTr();
                    }
                }
            }
        }
    }
}

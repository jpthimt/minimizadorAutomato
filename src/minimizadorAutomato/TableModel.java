package minimizadorAutomato;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.List;

public class TableModel extends AbstractTableModel {

    Alfabeto alf = new Alfabeto();
    private final String[] nomeCol = {"Est x Simb", String.valueOf(alf.getSimb1()), String.valueOf(alf.getSimb2())};
    private final List<Estado> listEst;
    private final List<Transicoes> listTr;

    public TableModel(List<Estado> listEstado, List<Transicoes> listTransicoes){
        listEst = listEstado;
        listTr = listTransicoes;
    }
    @Override
    public int getRowCount() {
        if(null == listEst){
            return 0;
        }
        return listEst.size();
    }

    @Override
    public int getColumnCount() {
        return nomeCol.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return listEst.get(rowIndex).getNome();
            case 1:
                return listTr.get(2*rowIndex).getChegada().getNome();
            case 2:
                return listTr.get((2*rowIndex) + 1).getChegada().getNome();
            default:
                return 0;
        }
    }
    @Override
    public String getColumnName(int indice){

        return nomeCol[indice];
    }

    public Class getColClass(int coluna){
        switch (coluna){
            case 0:
                return Estado.class;
            case 1:
                return Estado.class;
            case 2:
                return Estado.class;
            default:
                return null;
        }
    }
    public Estado getEstado(int linha){
        Estado estado = new Estado();
        estado.setNome(listEst.get(linha).getNome());
        return estado;
    }
    public Transicoes getTransicoes(int linha){
        Transicoes transicoes = new Transicoes();
        transicoes.setChegada(listTr.get(linha).getChegada());
        return transicoes;
    }
}

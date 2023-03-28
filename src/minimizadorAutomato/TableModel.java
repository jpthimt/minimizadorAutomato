package minimizadorAutomato;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TableModel extends AbstractTableModel {

    Alfabeto alf = new Alfabeto();
    private final String[] nomeCol = {"Est x Simb", String.valueOf(alf.getSimb1()), String.valueOf(alf.getSimb2())};
    private List<Estado> listEst = new ArrayList<>();
    private List<Transicoes> listTr = new ArrayList();

    public TableModel(List<Estado> listEstado, List<Transicoes> listTransicoes){
        for (Estado estado : listEstado){
            listEst.add((Estado) estado.clone());
        }
        for (Transicoes transicoes : listTransicoes){
            listTr.add((Transicoes) transicoes.clone());
        }
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
                if(listEst.get(rowIndex).isStart()&&listEst.get(rowIndex).isEnd())
                    return ">*"+listEst.get(rowIndex).getNome();
                else if(listEst.get(rowIndex).isStart())
                    return ">"+listEst.get(rowIndex).getNome();
                else if(listEst.get(rowIndex).isEnd())
                    return "*"+listEst.get(rowIndex).getNome();
                else
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
}

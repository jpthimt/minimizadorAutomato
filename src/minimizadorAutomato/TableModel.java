package minimizadorAutomato;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

// modelo de tabela para as tabelas de transições
public class TableModel extends AbstractTableModel {

    Alfabeto alf = new Alfabeto();
    private final String[] nomeCol = {"δ", String.valueOf(alf.getSimb1()), String.valueOf(alf.getSimb2())};
    private final List<Estado> listEst = new ArrayList<>();
    private final List<Transicoes> listTr = new ArrayList<>();

    public TableModel(List<Estado> listEstado, List<Transicoes> listTransicoes){
        for (Estado estado : listEstado){
            listEst.add(estado.clone());
        }
        for (Transicoes transicoes : listTransicoes){
            listTr.add(transicoes.clone());
        }
    }
    @Override
    public int getRowCount() {
        return listEst.size();
    }

    @Override
    public int getColumnCount() {
        return nomeCol.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        // mostra os estados nas células corretas
        switch (columnIndex) {
            case 0 -> { // mostra todos os estados do automato na primeira coluna
                if (listEst.get(rowIndex).isStart() && listEst.get(rowIndex).isEnd())
                    return ">*" + listEst.get(rowIndex).getNome();
                else if (listEst.get(rowIndex).isStart())
                    return ">" + listEst.get(rowIndex).getNome();
                else if (listEst.get(rowIndex).isEnd())
                    return "*" + listEst.get(rowIndex).getNome();
                else
                    return listEst.get(rowIndex).getNome();
            }
            case 1 -> { // mostra os estados de chegada com o primeiro símbolo do alfabeto
                return listTr.get(2 * rowIndex).getChegada().getNome();
            }
            case 2 -> { // mostra os estados de chegada com o segundo símbolo do alfabeto
                return listTr.get((2 * rowIndex) + 1).getChegada().getNome();
            }
            default -> {
                return 0;
            }
        }
    }
    @Override
    public String getColumnName(int indice){
        return nomeCol[indice];
    }

}

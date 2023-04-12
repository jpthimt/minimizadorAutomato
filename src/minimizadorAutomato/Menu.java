package minimizadorAutomato;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Menu{
    private JPanel panel1;
    private JTable table1;
    private JTable table2;
    private JTable table3;
    private final List<Estado> estInicial = new ArrayList<>();
    private final List<Transicoes> trInicial = new ArrayList<>();
    private final List<Estado> listEst = new ArrayList<>();
    private final List<Transicoes> listTr = new ArrayList<>();
    private final List<Estado> newlistEst = new ArrayList<>();
    private final List<Transicoes> newListTr = new ArrayList<>();




    public JPanel getPanel1(){
        return panel1;
    }

    public Menu(List<Estado> listEst, List<Transicoes> listTr, List<Estado> newListEst, List<Transicoes> newListTr){

        // recebe as listas geradas do arquivo e as minimizadas
        for (Estado estado : listEst){
            estInicial.add(estado.clone());
        }
        for (Transicoes transicoes : listTr){
            trInicial.add(transicoes.clone());
        }
        for (Estado estado : listEst){
            this.listEst.add(estado.clone());
        }
        for (Transicoes transicoes : listTr){
            this.listTr.add(transicoes.clone());
        }
        for (Estado estado : newListEst){
            this.newlistEst.add(estado.clone());
        }
        for (Transicoes transicoes : newListTr){
            this.newListTr.add(transicoes.clone());
        }
        //gera as três tabelas
        createTable1();
        createTable2();
        createTable3();
    }

    // cria a tabela de transições inicial
    public void createTable1(){
        table1.setModel(new TableModel(estInicial, trInicial));
        JTableHeader header = table1.getTableHeader();
        header.setBackground(new Color(187,187,187));
        header.setFont(new Font("Arial", Font.BOLD, 11));
        header.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
        for(int i=0; i<table1.getColumnCount();i++) {
            table1.getColumnModel().getColumn(i).setCellRenderer(conteudo);
        }
        table1.getColumnModel().getColumn(0).setCellRenderer(estados);
    }

    // cria a tabela de equivalência
    public void createTable2(){
        table2.setModel(new TableEquivalence(listEst, listTr));
        JTableHeader header = table2.getTableHeader();
        header.setBackground(new Color(187,187,187));
        header.setFont(new Font("Arial", Font.BOLD, 11));
        header.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));

        for(int i=0; i<table2.getColumnCount();i++) {
            table2.getColumnModel().getColumn(i).setCellRenderer(conteudo);
        }
        table2.getColumnModel().getColumn(0).setCellRenderer(estados);

        // mostra o percurso de equivalencia dos estados ao clicar na célula
        table2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int rowIndex = table2.getSelectedRow();
                int columnIndex = table2.getSelectedColumn();
                int aux=0, auxF=0, auxNF=0;
                String text;
                if(table2.getValueAt(table2.getSelectedRow(), table2.getSelectedColumn())!=null && table2.getSelectedColumn()>0) {
                    text = listEst.get(rowIndex + 1).getNome();
                    if (listEst.get(rowIndex + 1).isEnd()) {
                        text += " (Final)\n";
                        auxF++;
                    } else {
                        text += " (Não final)\n";
                        auxNF++;
                    }
                    text += listEst.get(columnIndex - 1).getNome();
                    if (listEst.get(columnIndex - 1).isEnd()) {
                        text += " (Final)\n";
                        auxF++;
                    } else {
                        text += " (Não final)\n";
                        auxNF++;
                    }
                    if(auxF==2 || auxNF==2){
                        aux++;
                        auxF = 0;
                        auxNF = 0;
                    }
                    if(aux==1) {
                        text += "―――――――――――――――――――――――\n";
                        Estado e1 = listEst.get(rowIndex + 1);
                        Estado e2 = listEst.get(columnIndex - 1);
                        text += "δ(" + e1.getNome() + "," + listTr.get(e1.getCod() * 2).getSimb() + ") = " + listTr.get(e1.getCod() * 2).getChegada().getNome();
                        if (listTr.get(e1.getCod() * 2).getChegada().isEnd()) {
                            text += " (Final)\n";
                            auxF++;
                        } else {
                            text += " (Não final)\n";
                            auxNF++;
                        }
                        text += "δ(" + e2.getNome() + "," + listTr.get(e2.getCod() * 2).getSimb() + ") = " + listTr.get(e2.getCod() * 2).getChegada().getNome();
                        if (listTr.get(e2.getCod() * 2).getChegada().isEnd()) {
                            text += " (Final)\n\n";
                            auxF++;
                        } else {
                            text += " (Não final)\n\n";
                            auxNF++;
                        }
                        if(auxF==2 || auxNF==2){
                            aux++;
                            auxF = 0;
                            auxNF = 0;
                        }
                        text += "δ(" + e1.getNome() + "," + listTr.get((e1.getCod() * 2) + 1).getSimb() + ") = " + listTr.get((e1.getCod() * 2) + 1).getChegada().getNome();
                        if (listTr.get((e1.getCod() * 2) + 1).getChegada().isEnd()) {
                            text += " (Final)\n";
                            auxF++;
                        } else {
                            text += " (Não final)\n";
                            auxNF++;
                        }
                        text += "δ(" + e2.getNome() + "," + listTr.get((e2.getCod() * 2) + 1).getSimb() + ") = " + listTr.get((e2.getCod() * 2) + 1).getChegada().getNome();
                        if (listTr.get((e2.getCod() * 2) + 1).getChegada().isEnd()) {
                            text += " (Final)\n";
                            auxF++;
                        } else {
                            text += " (Não final)\n";
                            auxNF++;
                        }
                        if(auxF==2 || auxNF==2){
                            aux++;
                        }
                    }
                    if(aux==3) {
                        text += "―――――――――――――――――――――――\n";
                        Estado e1 = listEst.get(rowIndex + 1);
                        Estado e2 = listEst.get(columnIndex - 1);
                        Estado e11 = listTr.get(e1.getCod() * 2).getChegada();
                        Estado e22 = listTr.get(e2.getCod() * 2).getChegada();
                        text += "δ(" + e1.getNome() + "," + listTr.get(e1.getCod() * 2).getSimb() + listTr.get(e11.getCod() * 2).getSimb() +
                                ") = " + listTr.get(e11.getCod() * 2).getChegada().getNome();
                        if (listTr.get(e11.getCod() * 2).getChegada().isEnd()) {
                            text += " (Final)\n";
                        } else {
                            text += " (Não final)\n";
                        }
                        text += "δ(" + e2.getNome() + "," + listTr.get((e2.getCod() * 2)).getSimb() + listTr.get(e22.getCod() * 2).getSimb() +
                                ") = " + listTr.get(e22.getCod() * 2).getChegada().getNome();
                        if (listTr.get(e22.getCod() * 2).getChegada().isEnd()) {
                            text += " (Final)\n\n";
                        } else {
                            text += " (Não final)\n\n";
                        }
                        text += "δ(" + e1.getNome() + "," + listTr.get(e1.getCod() * 2).getSimb() + listTr.get((e11.getCod() * 2) + 1).getSimb() +
                                ") = " + listTr.get((e11.getCod() * 2) + 1).getChegada().getNome();
                        if (listTr.get((e11.getCod() * 2) + 1).getChegada().isEnd()) {
                            text += " (Final)\n";
                        } else {
                            text += " (Não final)\n";
                        }
                        text += "δ(" + e2.getNome() + "," + listTr.get(e2.getCod() * 2).getSimb() + listTr.get((e22.getCod() * 2) + 1).getSimb() +
                                ") = " + listTr.get((e22.getCod() * 2) + 1).getChegada().getNome();
                        if (listTr.get((e22.getCod() * 2) + 1).getChegada().isEnd()) {
                            text += " (Final)\n\n";
                        } else {
                            text += " (Não final)\n\n";
                        }
                        e11 = listTr.get((e1.getCod() * 2) + 1).getChegada();
                        e22 = listTr.get((e2.getCod() * 2) + 1).getChegada();
                        text += "δ(" + e1.getNome() + "," + listTr.get((e1.getCod() * 2) + 1).getSimb() + listTr.get(e11.getCod() * 2).getSimb() +
                                ") = " + listTr.get(e11.getCod() * 2).getChegada().getNome();
                        if (listTr.get(e11.getCod() * 2).getChegada().isEnd()) {
                            text += " (Final)\n";
                        } else {
                            text += " (Não final)\n";
                        }
                        text += "δ(" + e2.getNome() + "," + listTr.get((e2.getCod() * 2) + 1).getSimb() + listTr.get(e22.getCod() * 2).getSimb() +
                                ") = " + listTr.get(e22.getCod() * 2).getChegada().getNome();
                        if (listTr.get(e22.getCod() * 2).getChegada().isEnd()) {
                            text += " (Final)\n\n";
                        } else {
                            text += " (Não final)\n\n";
                        }
                        text += "δ(" + e1.getNome() + "," + listTr.get((e1.getCod() * 2) + 1).getSimb() + listTr.get((e11.getCod() * 2) + 1).getSimb() +
                                ") = " + listTr.get((e11.getCod() * 2) + 1).getChegada().getNome();
                        if (listTr.get((e11.getCod() * 2) + 1).getChegada().isEnd()) {
                            text += " (Final)\n";
                        } else {
                            text += " (Não final)\n";
                        }
                        text += "δ(" + e2.getNome() + "," + listTr.get((e2.getCod() * 2) + 1).getSimb() + listTr.get((e22.getCod() * 2) + 1).getSimb() +
                                ") = " + listTr.get((e22.getCod() * 2) + 1).getChegada().getNome();
                        if (listTr.get((e22.getCod() * 2) + 1).getChegada().isEnd()) {
                            text += " (Final)\n";
                        } else {
                            text += " (Não final)\n";
                        }
                    }
                    text += "―――――――――――――――――――――――\n";

                    if(table2.getValueAt(table2.getSelectedRow(), table2.getSelectedColumn())=="◯")
                        text += "ESTADOS EQUIVALENTES";
                    else if(table2.getValueAt(table2.getSelectedRow(), table2.getSelectedColumn())=="⨂")
                        text += "ESTADOS SEMI-EQUIVALENTE";
                    else if(table2.getValueAt(table2.getSelectedRow(), table2.getSelectedColumn())=="✕")
                        text += "ESTADOS NÃO-EQUIVALENTES";
                    text += "\n―――――――――――――――――――――――\n";
                    JOptionPane.showMessageDialog(null, text);
                }
            }
        });
    }

    // cria a tabela de transições final
    public void createTable3(){
        table3.setModel(new TableModel(newlistEst, newListTr));
        table3.getColumnModel().getColumn(0).setHeaderValue("δ'");
        JTableHeader header = table3.getTableHeader();
        header.setBackground(new Color(187,187,187));
        header.setFont(new Font("Arial", Font.BOLD, 11));
        header.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));

        for(int i=0; i<table3.getColumnCount();i++) {
            table3.getColumnModel().getColumn(i).setCellRenderer(conteudo);
        }
        table3.getColumnModel().getColumn(0).setCellRenderer(estados);
    }

    // faz alterações na cor, fonte e alinhamento das tabelas
    DefaultTableCellRenderer estados = new DefaultTableCellRenderer() {
        private static final Border border = BorderFactory.createSoftBevelBorder(SoftBevelBorder.RAISED);

        @Override
         public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JComponent c = (JComponent) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            c.setBackground(new Color(187,187,187));
            c.setBorder(border);
            setHorizontalAlignment(CENTER);
            c.setFont(new Font("Arial", Font.BOLD, 11));
            return c;
        }
    };

    DefaultTableCellRenderer conteudo = new DefaultTableCellRenderer() {

        private static final Border border = BorderFactory.createEmptyBorder();
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JComponent c = (JComponent) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            setHorizontalAlignment(CENTER);
            c.setBorder(border);
            return c;
        }
    };

}

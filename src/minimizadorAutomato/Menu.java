package minimizadorAutomato;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
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
    }

    // cria a tabela de transições final
    public void createTable3(){
        table3.setModel(new TableModel(newlistEst, newListTr));
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

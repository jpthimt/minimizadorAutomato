package minimizadorAutomato;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import minimizadorAutomato.Minimizador;

public class Menu{
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JTable table1;
    private JTable table2;
    private JTable table3;
    private final List<Estado> listEst;
    private final List<Transicoes> listTr;
    private List<Estado> newlistEst = new ArrayList<>();
    private List<Transicoes> newListTr = new ArrayList<>();
    TableEquivalence tableEquivalence;



    public JPanel getPanel1(){
        return panel1;
    }

    public Menu(List<Estado> listEst, List<Transicoes> listTr, List<Estado> newListEst, List<Transicoes> newListTr){
        this.listEst = listEst;
        this.listTr = listTr;
        this.newlistEst = newListEst;
        this.newListTr = newListTr;
        tableEquivalence = new TableEquivalence(listEst, listTr);
        createTable2();
        createTable3();
        createTable1();
        JPanel jPanel = getPanel1();
        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setContentPane(jPanel);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }

    public void createTable1(){
        table1.setModel(new TableModel(listEst, listTr));
    }
    public void createTable2(){
        table2.setModel(tableEquivalence);
    }
    public void createTable3(){
        table3.setModel(new TableModel(tableEquivalence.getNewListE(), tableEquivalence.getNewListT()));
    }

}

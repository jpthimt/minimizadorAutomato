package minimizadorAutomato;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import minimizadorAutomato.Minimizador;

public class Menu{
    private JPanel panel1;
    private JTable table1;
    private JTable table2;
    private JTable table3;
    private List<Estado> estInicial = new ArrayList<>();
    private List<Transicoes> trInicial = new ArrayList<>();
    private List<Estado> listEst = new ArrayList<>();
    private List<Transicoes> listTr = new ArrayList<>();
    private List<Estado> newlistEst = new ArrayList<>();
    private List<Transicoes> newListTr = new ArrayList<>();
    TableEquivalence tableEquivalence;



    public JPanel getPanel1(){
        return panel1;
    }

    public Menu(List<Estado> listEst, List<Transicoes> listTr, List<Estado> newListEst, List<Transicoes> newListTr){
        for (Estado estado : listEst){
            estInicial.add((Estado) estado.clone());
        }
        for (Transicoes transicoes : listTr){
            trInicial.add((Transicoes) transicoes.clone());
        }
        for (Estado estado : listEst){
            this.listEst.add((Estado) estado.clone());
        }
        for (Transicoes transicoes : listTr){
            this.listTr.add((Transicoes) transicoes.clone());
        }
        for (Estado estado : newListEst){
            this.newlistEst.add((Estado) estado.clone());
        }
        for (Transicoes transicoes : newListTr){
            this.newListTr.add((Transicoes) transicoes.clone());
        }
        createTable1();
        createTable2();
        createTable3();
    }

    public void createTable1(){
        table1.setModel(new TableModel(estInicial, trInicial));
    }
    public void createTable2(){
        table2.setModel(new TableEquivalence(listEst, listTr));
    }
    public void createTable3(){
        table3.setModel(new TableModel(newlistEst, newListTr));
    }

}

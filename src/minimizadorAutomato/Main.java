package minimizadorAutomato;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// TRABALHO PRÁTICO 1 — Implementação de um minimizador de autômatos
// ALUNO: João Pedro Thim Tarossi
public class Main{
    public static void main(String[] args) {
        String path = "/home/jpthimt/IdeaProjects/minimizadorAutomato/automato.dat"; // caminho do arquivo
        // cria listas de estados e transições, uma lista para cada etapa
        List<Estado> estInicial = new ArrayList<>();
        List<Transicoes> trInicial = new ArrayList<>();
        List<Estado> listEst = new ArrayList<>();
        List<Transicoes> listTr = new ArrayList<>();
        List<Estado> newListEst = new ArrayList<>();
        List<Transicoes> newListTr = new ArrayList<>();
        Alfabeto alf = new Alfabeto();

        // leitura do arquivo
        try (BufferedReader br = new BufferedReader(new FileReader(path))){
            //pega primeira linha do arq
            String line = br.readLine();
            //faz leitura até o fim do arquivo
            while(line != null){
                String[] vet = line.split(","); // divide a linha em partes separadas por ','
                int i;
                for(i=0; i<vet.length; i++) { // adiciona os estados
                    Estado est = new Estado(vet[i]);
                    est.setCod(i);
                    listEst.add(est);
                }

                line = br.readLine(); // pega a próxima linha
                vet = line.split(","); // divide a linha em partes separadas por ','
                // define os simbolos do alfabeto
                alf.setSimb1(vet[0].charAt(0));
                alf.setSimb2(vet[1].charAt(0));

                line = br.readLine();
                vet = line.split(",");
                // adiciona as transições
                while(vet[0].charAt(0) != '>') { // trabalha com a linha até a linha do estado inicial
                    Transicoes tr = new Transicoes();
                    for (Estado e : listEst) {
                        if (vet[0].equals(e.getNome())) { // primeira parte do vetor é o estado de saida
                            tr.setSaida(e);
                        }
                        if (vet[2].equals(e.getNome())) { // terceira parte do vetor é o estado de chegada
                            tr.setChegada(e);
                        }
                        if (vet[1].charAt(0) == alf.getSimb1()) { // segunda parte do vetor é o simbolo do alf
                            tr.setSimb(alf.getSimb1());
                        } else if (vet[1].charAt(0) == alf.getSimb2()) {
                            tr.setSimb(alf.getSimb2());
                        }
                    }
                    listTr.add(tr);
                    line = br.readLine();
                    vet = line.split(",");
                }
                // define qual o estado inicial
                for(Estado e : listEst){
                    if(vet[0].substring(1).equals(e.getNome())){
                        e.setStart(true);
                    }
                }
                line = br.readLine();
                vet = line.split(",");
                // define os estados finais
                for(i=1;i<vet.length;i++){
                    for(Estado e : listEst){
                        if(vet[0].substring(1).equals(e.getNome())){
                            e.setEnd(true);
                        }
                        if(vet[i].equals(e.getNome())){
                            e.setEnd(true);
                        }
                    }
                }
                line = br.readLine();
            }

            // confere se o automato é determinístico ou não
            for(int i=1;i<listTr.size();i++){
                if((listTr.get(i-1).getSaida().equals(listTr.get(i).getSaida())) && (listTr.get(i-1).getSimb() == listTr.get(i).getSimb())){
                    JOptionPane.showMessageDialog(null, "O Automato é não determinístico!", "Erro!", JOptionPane.ERROR_MESSAGE);
                    System.exit(0); // se for não determinístico ele avisa e fecha o programa
                }
            }

            // faz uma cópia das listas
            for (Estado estado : listEst){
                estInicial.add(estado.clone());
            }
            for (Transicoes transicoes : listTr){
                trInicial.add(transicoes.clone());
            }

            // gera uma lista com o automato minimizado
            TableEquivalence tableEquivalence = new TableEquivalence(estInicial, trInicial);
            tableEquivalence.geraList();
            newListEst.addAll(tableEquivalence.getNewListE());
            newListTr.addAll(tableEquivalence.getNewListT());
            estInicial.clear();
            trInicial.clear();
            for (Estado estado : listEst){
                estInicial.add(estado.clone());
            }
            for (Transicoes transicoes : listTr){
                trInicial.add(transicoes.clone());
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // mostra as tabelas
        Menu menu = new Menu(listEst, listTr, newListEst, newListTr);
        JPanel jPanel = menu.getPanel1();
        JFrame jFrame = new JFrame("Minimizador");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setContentPane(jPanel);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(false);
        jFrame.setVisible(true);
    }
}
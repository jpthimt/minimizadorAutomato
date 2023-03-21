package minimizadorAutomato;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//TRABALHO PRATICO 1 - Implementação de um minimizador de autômatos
//ALUNO: João Pedro Thim Tarossi
public class Main {
    public static void main(String[] args) {
        String path = "/home/jpthimt/IdeaProjects/minimizadorAutomato/automato.dat";
        List<Estado> listEst = new ArrayList<Estado>();
        List<Transicoes> listTr = new ArrayList<Transicoes>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))){

            String line = br.readLine();

            while(line != null){
                String[] vet = line.split(",");
                int numEstados = vet.length;
                int i;
                for(i=0; i<numEstados; i++) {
                    Estado est = new Estado(vet[i]);
                    listEst.add(est);
                }
                line = br.readLine();
                vet = line.split(",");
                Alfabeto alf = new Alfabeto();
                alf.setSimb1(vet[0].charAt(0));
                alf.setSimb2(vet[1].charAt(0));
                line = br.readLine();
                vet = line.split(",");
                while(vet[0].charAt(0) != '>') {
                    Transicoes tr = new Transicoes();
                    for (Estado e : listEst) {
                        if (vet[0].equals(e.getNome())) {
                            tr.setSaida(e);
                        }
                        if (vet[2].equals(e.getNome())) {
                            tr.setChegada(e);
                        }
                        if (vet[1].charAt(0) == alf.getSimb1()) {
                            tr.setSimb(alf.getSimb1());
                        } else if (vet[1].charAt(0) == alf.getSimb2()) {
                            tr.setSimb(alf.getSimb2());
                        }
                    }
                    listTr.add(tr);
                    line = br.readLine();
                    vet = line.split(",");
                }
                for(Estado e : listEst){
                    if(vet[0].substring(1).equals(e.getNome())){
                        e.setStart(true);
                    }
                }
                line = br.readLine();
                vet = line.split(",");
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
            for(Estado e : listEst){
                System.out.println(e);
            }
            for(Transicoes t : listTr){
                System.out.println(t);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }



    }
}
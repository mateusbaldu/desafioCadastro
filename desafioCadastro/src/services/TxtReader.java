package services;

import models.Pet;

import javax.swing.*;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class TxtReader {
    public static void manipularFormulario() {
        File file = new File("formulario.txt");
        String formulario = "";
        try (FileReader fr = new FileReader(file); BufferedReader br = new BufferedReader(fr)) {
            String linha = null;
            while ((linha = br.readLine()) != null) {
                formulario += linha + "\n";
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(formulario);
    }

    public static void salvarPet(Pet pet) {
        LocalTime agora = LocalTime.now();
        LocalDate hoje = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HHmm");

        String dataFormatada = hoje.format(formatter);
        String horaFormatada = agora.format(formatter2);

        String nomeDoArquivo = dataFormatada + "T" + horaFormatada + "-" +
                pet.getNome().toUpperCase() + pet.getSobrenome().toUpperCase() + ".txt";

        File file = new File("C:\\Users\\samue\\OneDrive\\Documentos\\GitHub\\desafioCadastro\\desafioCadastro\\petsCadastrados", nomeDoArquivo);
        try (FileWriter fr = new FileWriter(file) ; BufferedWriter bw = new BufferedWriter(fr)) {
            bw.write("1 - " + pet.getNome() + " " + pet.getSobrenome() + "\n");
            bw.write("2 - " + pet.getTipo() + "\n");
            bw.write("3 - " + pet.getSexo() + "\n");
            bw.write("4 - " + pet.getEndereco().getRua() + ", " + pet.getEndereco().getNumeroCasa()
                    + ", " + pet.getEndereco().getCidade() + "\n");
            bw.write("5 - " + pet.getIdade() + "\n");
            bw.write("6 - " + pet.getPeso() + "\n");
            bw.write("7 - " + pet.getRaca() + "\n");

            System.out.println("Cadastrado com sucesso!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void pesquisarPet(String tipoPesquisa, String criterioPesquisaUm,
                                    String criterioPesquisaDois) {
        File pastaPetsCadastrados = new File("petsCadastrados");
        File[] arquivosCadastro = pastaPetsCadastrados.listFiles();
        String[] arrayAuxiliar = new String[7];
        String regexVazia = "^\\s+$";
        boolean possuiCriterioUm = !criterioPesquisaUm.matches(regexVazia);
        boolean possuiCriterioDois = !criterioPesquisaDois.matches(regexVazia);
        String auxiliar = "";
        String linha = "";
        boolean[] criterios = {true, true};

        for (File arquivo : arquivosCadastro) {
           int contador = 1;
           auxiliar = contador + " - ";
           try (BufferedReader br = new BufferedReader(new FileReader(arquivo))){
               while ((linha = br.readLine()) != null) {
                   for (int i = 0; i < 7; i++) {
                       arrayAuxiliar[i] = linha;
                   }
                   if (!tipoPesquisa.equalsIgnoreCase(arrayAuxiliar[1].replaceAll("[^a-zA-Z]", ""))) {
                       break;
                   }
               }
           } catch (Exception e) {
               e.printStackTrace();
           }
           try (BufferedReader br2 = new BufferedReader(new FileReader(arquivo))) {
               while ((linha = br2.readLine()) != null) {
                   if (!(possuiCriterioUm && possuiCriterioDois)) {
                       break;
                   }
                   if (!criterioPesquisaUm.matches(regexVazia)) {
                       String[] auxUm = criterioPesquisaUm.split("-");
                       switch (auxUm[0].toLowerCase()) {
                           case "nome", "sobrenome":
                               if (!auxUm[1].equals(arrayAuxiliar[0].replaceAll("[^a-zA-Z]", ""))) {
                                   criterios[0] = false;
                               }
                               break;
                           case "tipo":
                               if (!auxUm[1].equals(arrayAuxiliar[1].replaceAll("[^a-zA-Z]", ""))) {
                                   criterios[0] = false;
                               }
                               break;
                           case "sexo":
                               if (!auxUm[1].equals(arrayAuxiliar[2].replaceAll("[^a-zA-Z]", ""))) {
                                   criterios[0] = false;
                               }
                               break;
                           case "endereco":
                               if (!auxUm[1].equals(arrayAuxiliar[3])) {
                                   criterios[0] = false;
                               }
                               break;
                           case "idade":
                               if (!auxUm[1].equals(arrayAuxiliar[4])) {
                                   criterios[0] = false;
                               }
                               break;
                           case "peso":
                               if (!auxUm[1].equals(arrayAuxiliar[5])) {
                                   criterios[0] = false;
                               }
                               break;
                           case "raca":
                               if (!auxUm[1].equals(arrayAuxiliar[6])) {
                                   criterios[0] = false;
                               }
                               break;
                           default: break;
                       }
                       if (!criterios[0]) {
                           break;
                       }
                   }
                   if (!criterioPesquisaDois.matches(regexVazia)) {
                       String[] auxDois = criterioPesquisaDois.split("-");
                       switch (auxDois[0].toLowerCase()) {
                           case "nome", "sobrenome":
                               if (!auxDois[1].equals(arrayAuxiliar[0].replaceAll("[^a-zA-Z]", ""))) {
                                   criterios[1] = false;
                               }
                               break;
                           case "tipo":
                               if (!auxDois[1].equals(arrayAuxiliar[1].replaceAll("[^a-zA-Z]", ""))) {
                                   criterios[1] = false;
                               }
                               break;
                           case "sexo":
                               if (!auxDois[1].equals(arrayAuxiliar[2].replaceAll("[^a-zA-Z]", ""))) {
                                   criterios[1] = false;
                               }
                               break;
                           case "endereco":
                               if (!auxDois[1].equals(arrayAuxiliar[3])) {
                                   criterios[1] = false;
                               }
                               break;
                           case "idade":
                               if (!auxDois[1].equals(arrayAuxiliar[4])) {
                                   criterios[1] = false;
                               }
                               break;
                           case "peso":
                               if (!auxDois[1].equals(arrayAuxiliar[5])) {
                                   criterios[1] = false;
                               }
                               break;
                           case "raca":
                               if (!auxDois[1].equals(arrayAuxiliar[6])) {
                                   criterios[1] = false;
                               }
                               break;
                           default: break;
                       }
                       if (!criterios[0]) {
                           break;
                       }
                   }
                   auxiliar += linha + " - ";
                   System.out.println(auxiliar);
               }
           } catch (Exception e) {
               e.printStackTrace();
           }
        }
    }
}

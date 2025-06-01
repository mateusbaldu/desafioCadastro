package services;

import models.Pet;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


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

    public static void pesquisarPetPorCriterios(String tipoPesquisa, String criterioPesquisaUm,
                                    String criterioPesquisaDois) {
        File pastaPetsCadastrados = new File("petsCadastrados");
        File[] arquivosCadastro = pastaPetsCadastrados.listFiles();
        String[] arrayAuxiliar = new String[7];
        String regexVazia = "^\\s+$";
        String auxiliar = "";
        boolean[] criterios = {true, true};

        assert arquivosCadastro != null;
        String linha = "";
        for (File arquivo : arquivosCadastro) {
           int contador = 1;
           try (BufferedReader br = new BufferedReader(new FileReader(arquivo))){
               while ((linha = br.readLine()) != null) {
                   arrayAuxiliar[0] = linha;
                   for (int i = 1; i < 7; i++) {
                       arrayAuxiliar[i] = br.readLine();
                   }
                   if (!tipoPesquisa.equalsIgnoreCase(arrayAuxiliar[1].replaceAll("[^a-zA-Z]", "").trim())) {
                       System.out.println("Tipo do animal vazio!");
                       break;
                   }
                   if (!criterioPesquisaUm.matches(regexVazia)) {
                       String[] auxUm = criterioPesquisaUm.split("-");
                       switch (auxUm[0].toLowerCase()) {
                           case "nome", "sobrenome":
                               if (!auxUm[1].equals(arrayAuxiliar[0].substring(4))) {
                                   criterios[0] = false;
                               }
                               break;
                           case "tipo":
                               if (!auxUm[1].equals(arrayAuxiliar[1].substring(4))) {
                                   criterios[0] = false;
                               }
                               break;
                           case "sexo":
                               if (!auxUm[1].equals(arrayAuxiliar[2].substring(4))) {
                                   criterios[0] = false;
                               }
                               break;
                           case "endereco":
                               if (!auxUm[1].equals(arrayAuxiliar[3].substring(4))) {
                                   criterios[0] = false;
                               }
                               break;
                           case "idade":
                               if (!auxUm[1].equals(arrayAuxiliar[4].substring(4))) {
                                   criterios[0] = false;
                               }
                               break;
                           case "peso":
                               if (!auxUm[1].equals(arrayAuxiliar[5].substring(4))) {
                                   criterios[0] = false;
                               }
                               break;
                           case "raca":
                               if (!auxUm[1].equals(arrayAuxiliar[6].substring(4))) {
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
                               if (!auxDois[1].equals(arrayAuxiliar[0].substring(4))) {
                                   criterios[1] = false;
                               }
                               break;
                           case "tipo":
                               if (!auxDois[1].equals(arrayAuxiliar[1].substring(4))) {
                                   criterios[1] = false;
                               }
                               break;
                           case "sexo":
                               if (!auxDois[1].equals(arrayAuxiliar[2].substring(4))) {
                                   criterios[1] = false;
                               }
                               break;
                           case "endereco":
                               if (!auxDois[1].equals(arrayAuxiliar[3].substring(4))) {
                                   criterios[1] = false;
                               }
                               break;
                           case "idade":
                               if (!auxDois[1].equals(arrayAuxiliar[4].substring(4))) {
                                   criterios[1] = false;
                               }
                               break;
                           case "peso":
                               if (!auxDois[1].equals(arrayAuxiliar[5].substring(4))) {
                                   criterios[1] = false;
                               }
                               break;
                           case "raca":
                               if (!auxDois[1].equals(arrayAuxiliar[6].substring(4))) {
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
                   for (int i = 1; i < 6; i++) {
                       auxiliar += arrayAuxiliar[i].substring(4) + " - ";
                   }
                   auxiliar += arrayAuxiliar[6].substring(4);
               }
               System.out.println(auxiliar);
               auxiliar = "";
               for (int i = 0; i < 6; i++) {
                   arrayAuxiliar[i] = "";
               }
           } catch (Exception e) {
               e.printStackTrace();
           }
        }
    }

    public static void listarTodosOsPets() {
        File pastaPetsCadastrados = new File("petsCadastrados");
        File[] files = pastaPetsCadastrados.listFiles();
        String[] arrayAuxiliar = new String[7];
        String auxiliarUm = "";
        int contador = 1;
        String auxiliarDois = "";
        if (files == null) {
            System.out.println("Não existe nenhum pet cadastrado.");
            return;
        }
        for (File file : files) {
            try (FileReader fr = new FileReader(file) ; BufferedReader br = (new BufferedReader(fr))) {
                while ((auxiliarUm = br.readLine()) != null) {
                    arrayAuxiliar[0] = contador + " - " + auxiliarUm.replaceAll("[^a-zA-Z\\s]", "") + " - ";
                    for (int i = 1; i < 6; i++) {
                        arrayAuxiliar[i] = br.readLine().substring(4) + " - ";
                    }
                    arrayAuxiliar[6] = br.readLine().replaceAll("[^a-zA-Z]", "").trim();
                    for (int i = 0; i < 6; i++) {
                        auxiliarDois += arrayAuxiliar[i];
                    }
                    auxiliarDois += arrayAuxiliar[6].replaceAll("[^a-zA-Z]", "").trim();
                }
                System.out.println(auxiliarDois);
                for (int i = 0; i < 7; i++) {
                    arrayAuxiliar[i] = "";
                }
                auxiliarDois = "";
                auxiliarUm = "";
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            contador++;
        }
    }

    public static void pesquisarPetPorNumero(String tipoPesquisa, String criterioPesquisaUm,
                                             String criterioPesquisaDois) {
    }
}

package services;

import models.Pet;

import java.io.*;
import java.time.LocalDate;
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
        try (FileWriter fr = new FileWriter(file); BufferedWriter bw = new BufferedWriter(fr)) {
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
        File[] petsTxt = pastaPetsCadastrados.listFiles();
        String[] arrayAux = new String[7];
        String regexVazia = "^\\s*$";
        String petImpresso = "";
        int petsEncontrados = 0;

        if (petsTxt == null) {
            System.out.println("Nenhum pet encontrado na pasta!");
            return;
        }
        String linha = "";
        for (File arquivo : petsTxt) {
            String nomePetLimpo = "";
            petImpresso = "";

            try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
                linha = br.readLine();
                if (linha == null) {
                    continue;
                }
                nomePetLimpo = linha.substring(linha.indexOf("- ") + 2).replaceAll("[^a-zA-Z0-9\\sÀ-ú]", "").trim();
                arrayAux[0] = nomePetLimpo;
                for (int i = 1; i < 7; i++) {
                    arrayAux[i] = br.readLine();
                }
                String tipoPetNoArquivo = arrayAux[1].substring(arrayAux[1].indexOf("- ") + 2).trim();
                if (!tipoPesquisa.equalsIgnoreCase(tipoPetNoArquivo)) {
                    continue;
                }

                if (!criterioPesquisaUm.matches(regexVazia)) {
                    String[] auxUm = criterioPesquisaUm.split("-", 2);
                    String chaveUm = auxUm[0].toLowerCase().trim();
                    String valorUm = auxUm[1].trim();
                    boolean criterioUmAtendidoSwitch = true;
                    switch (chaveUm) {
                        case "nome": case "sobrenome":
                            if (!valorUm.equalsIgnoreCase(arrayAux[0])) criterioUmAtendidoSwitch = false;
                            break;
                        case "tipo":
                            if (!valorUm.equalsIgnoreCase(arrayAux[1].substring(arrayAux[1].indexOf("- ") + 2).trim())) criterioUmAtendidoSwitch = false;
                            break;
                        case "sexo":
                            if (!valorUm.equalsIgnoreCase(arrayAux[2].substring(arrayAux[2].indexOf("- ") + 2).trim())) criterioUmAtendidoSwitch = false;
                            break;
                        case "endereco":
                            if (!valorUm.equalsIgnoreCase(arrayAux[3].substring(arrayAux[3].indexOf("- ") + 2).trim())) criterioUmAtendidoSwitch = false;
                            break;
                        case "idade":
                            if (!valorUm.equalsIgnoreCase(arrayAux[4].substring(arrayAux[4].indexOf("- ") + 2).trim())) criterioUmAtendidoSwitch = false;
                            break;
                        case "peso":
                            if (!valorUm.equalsIgnoreCase(arrayAux[5].substring(arrayAux[5].indexOf("- ") + 2).trim())) criterioUmAtendidoSwitch = false;
                            break;
                        case "raca":
                            if (!valorUm.equalsIgnoreCase(arrayAux[6].substring(arrayAux[6].indexOf("- ") + 2).trim())) criterioUmAtendidoSwitch = false;
                            break;
                        default:
                            break;
                    }
                    if (!criterioUmAtendidoSwitch) {
                        continue;
                    }
                }

                if (!criterioPesquisaDois.matches(regexVazia)) {
                    String[] auxDois = criterioPesquisaDois.split("-", 2);
                    String chaveDois = auxDois[0].toLowerCase().trim();
                    String valorDois = auxDois[1].trim();

                    boolean criterioDoisAtendidoSwitch = true;
                    switch (chaveDois) {
                        case "nome": case "sobrenome":
                            if (!valorDois.equalsIgnoreCase(arrayAux[0])) criterioDoisAtendidoSwitch = false;
                            break;
                        case "tipo":
                            if (!valorDois.equalsIgnoreCase(arrayAux[1].substring(arrayAux[1].indexOf("- ") + 2).trim())) criterioDoisAtendidoSwitch = false;
                            break;
                        case "sexo":
                            if (!valorDois.equalsIgnoreCase(arrayAux[2].substring(arrayAux[2].indexOf("- ") + 2).trim())) criterioDoisAtendidoSwitch = false;
                            break;
                        case "endereco":
                            if (!valorDois.equalsIgnoreCase(arrayAux[3].substring(arrayAux[3].indexOf("- ") + 2).trim())) criterioDoisAtendidoSwitch = false;
                            break;
                        case "idade":
                            if (!valorDois.equalsIgnoreCase(arrayAux[4].substring(arrayAux[4].indexOf("- ") + 2).trim())) criterioDoisAtendidoSwitch = false;
                            break;
                        case "peso":
                            if (!valorDois.equalsIgnoreCase(arrayAux[5].substring(arrayAux[5].indexOf("- ") + 2).trim())) criterioDoisAtendidoSwitch = false;
                            break;
                        case "raca":
                            if (!valorDois.equalsIgnoreCase(arrayAux[6].substring(arrayAux[6].indexOf("- ") + 2).trim())) criterioDoisAtendidoSwitch = false;
                            break;
                        default:
                            break;
                    }
                    if (!criterioDoisAtendidoSwitch) {
                        continue;
                    }
                }
                petsEncontrados++;
                petImpresso = petsEncontrados + " - " + arrayAux[0];
                for (int i = 1; i < 7; i++) {
                    petImpresso += " - " + arrayAux[i].substring(arrayAux[i].indexOf("- ") + 2).trim();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            if (!petImpresso.isEmpty()) {
                System.out.println(petImpresso);
            }
            for (int i = 0; i < 7; i++) {
                arrayAux[i] = "";
            }
        }
    }

    public static void listarTodosOsPets() {
        File pastaPetsCadastrados = new File("petsCadastrados");
        File[] petsTxt = pastaPetsCadastrados.listFiles();
        String[] arrayAux = new String[7];
        String petImpresso = "";
        int contador = 1;
        String aux = "";
        if (petsTxt == null) {
            System.out.println("Não existe nenhum pet cadastrado.");
            return;
        }
        for (File file : petsTxt) {
            try (FileReader fr = new FileReader(file); BufferedReader br = (new BufferedReader(fr))) {
                while ((petImpresso = br.readLine()) != null) {
                    arrayAux[0] = contador + " - " + petImpresso.replaceAll("[^a-zA-Z\\s]", "") + " - ";
                    for (int i = 1; i < 6; i++) {
                        arrayAux[i] = br.readLine().substring(4) + " - ";
                    }
                    arrayAux[6] = br.readLine().replaceAll("[^a-zA-Z]", "").trim();
                    for (int i = 0; i < 6; i++) {
                        aux += arrayAux[i];
                    }
                    aux += arrayAux[6].replaceAll("[^a-zA-Z]", "").trim();
                }
                System.out.println(aux);
                for (int i = 0; i < 7; i++) {
                    arrayAux[i] = "";
                }
                aux = "";
                petImpresso = "";
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

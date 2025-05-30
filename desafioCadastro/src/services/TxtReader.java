package services;

import models.Pet;

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

    public static void pesquisarPet() {
    }
}

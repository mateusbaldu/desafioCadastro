package services;

import models.Pet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Scanner;

import static services.TxtReader.manipularFormulario;

public class Menu {
    public void chamarMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("-----SISTEMA DE CADASTRO DE PETS-----");
        System.out.println("Digite uma opção: ");
        System.out.println("1. Cadastrar um novo pet\n" + "2. Alterar os dados do pet cadastrado\n" +
                "3. Deletar um pet cadastrado\n" + "4. Listar todos os pets cadastrados\n" +
                "5. Listar pets por algum critério (idade, nome, raça)\n" + "6. Sair");
        System.out.print("Opção: ");
        int opcao = sc.nextInt();
        if (opcao <= 0 || opcao > 6) {
            System.out.println("Opção inválida!");
            chamarMenu();
        }
        switch (opcao) {
            case 1:
                manipularFormulario();
                Pet petInserido = chamarOpcaoUm();
                TxtReader.salvarPet(petInserido);
                break;
            case 2:

                break;
        }
    }
    public static Pet chamarOpcaoUm() {
        System.out.println("---CADASTRAR NOVO PET---");
        Pet pet = new Pet();
        Endereco endereco = new Endereco();
        Scanner sc = new Scanner(System.in);

        System.out.println("Nome: ");
        pet.setNome(sc.nextLine());

        System.out.println("Sobrenome: ");
        pet.setSobrenome(sc.nextLine());

        System.out.println("Tipo: ");
        pet.setTipo(sc.nextLine());

        System.out.println("Sexo: ");
        pet.setSexo(sc.nextLine());

        System.out.println("Logradouro: ");
        endereco.setRua(sc.nextLine());
        System.out.println("Numero: ");
        endereco.setNumeroCasa(sc.nextLine());
        System.out.println("Cidade:");
        endereco.setCidade(sc.nextLine());
        pet.setEndereco(endereco);

        System.out.println("Idade em anos: ");
        pet.setIdade(sc.nextLine());

        System.out.println("Peso em kilos: ");
        pet.setPeso(sc.nextLine());

        System.out.println("Raça: ");
        pet.setRaca(sc.nextLine());

        return pet;
    }

}

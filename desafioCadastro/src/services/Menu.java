package services;

import models.Endereco;
import models.Pet;

import java.io.File;
import java.util.Scanner;

import static services.TxtController.*;

public class Menu {
    public static void chamarMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("-----SISTEMA DE CADASTRO DE PETS-----");
        System.out.println("Digite uma opção: ");
        System.out.println("1. Cadastrar um novo pet\n" + "2. Listar pets por algum critério (idade, nome, raça)\n" +
                "3. Listar todos os pets cadastrados\n" + "4. Alterar os dados do pet cadastrado\n" +
                "5. Deletar um pet cadastrado\n" + "6. Sair");
        System.out.print("Opção: ");
        int opcao = sc.nextInt();
        if (opcao <= 0 || opcao > 6) {
            System.out.println("Opção inválida!");
            chamarMenu();
        }
        switch (opcao) {
            case 1:
                manipularFormulario();
                chamarOpcaoUm();
                chamarMenu();
                break;
            case 2:
                chamarOpcaoDois();
                chamarMenu();
                break;
            case 3:
                TxtController.listarTodosOsPets();
                chamarMenu();
                break;
            case 4:
                chamarOpcaoQuatro();
                chamarMenu();
                break;
            case 5:
                chamarOpcaoCinco();
                chamarMenu();
                break;
            case 6:
                System.out.println("Encerrando o sistema...");
                break;
            default:
                break;
        }
    }

    public static void chamarOpcaoUm() {
        System.out.println("---CADASTRAR NOVO PET---");
        Pet pet = new Pet();
        Endereco endereco = new Endereco();
        Scanner sc = new Scanner(System.in);

        System.out.print("Nome: ");
        pet.setNome(sc.nextLine());

        System.out.print("Sobrenome: ");
        pet.setSobrenome(sc.nextLine());

        System.out.print("Tipo: ");
        pet.setTipo(sc.nextLine());

        System.out.print("Sexo: ");
        pet.setSexo(sc.nextLine());

        System.out.print("Logradouro: ");
        endereco.setRua(sc.nextLine());
        System.out.print("Numero: ");
        endereco.setNumeroCasa(sc.nextLine());
        System.out.print("Cidade:");
        endereco.setCidade(sc.nextLine());
        pet.setEndereco(endereco);

        System.out.print("Idade em anos (x anos): ");
        pet.setIdade(sc.nextLine());

        System.out.print("Peso em kilos (xkg): ");
        pet.setPeso(sc.nextLine());

        System.out.print("Raça: ");
        pet.setRaca(sc.nextLine());

        TxtController.salvarPet(pet);
    }

    public static void chamarOpcaoDois() {
        Scanner sc = new Scanner(System.in);
        System.out.println("----PESQUISAR PET----");
        System.out.println("Digite o tipo do pet (Obrigatorio): ");
        System.out.print("Cachorro ou Gato - ");
        String tipoPesquisa = sc.nextLine().toLowerCase();
        if (tipoPesquisa.isEmpty() || tipoPesquisa.equals(" ")) {
            System.out.println("Tipo do pet não inserido! Tente novamente");
            chamarOpcaoDois();
        }

        System.out.println("\n--Insira informaçoes que serão utilizadas para buscar o pet--");
        System.out.println("Importante! Digite-os separados somente por um traço! Exemplo: Nome-Luiz");
        System.out.println("Caso não deseje adicionar, aperte Space+Enter");
        System.out.print("Insira o primeiro critério: ");
        String criterioPesquisaUm = sc.nextLine();
        System.out.print("Insira o segundo critério: ");
        String criterioPesquisaDois = sc.nextLine();

        TxtController.pesquisarPetPorCriterios(tipoPesquisa, criterioPesquisaUm, criterioPesquisaDois);
    }

    public static void chamarOpcaoQuatro() {
        Scanner sc = new Scanner(System.in);
        System.out.println("---EDITAR DADOS DE PET---");
        System.out.println("Digite o tipo do pet (Obrigatorio): ");
        System.out.print("Cachorro ou Gato - ");
        String tipoPesquisa = sc.nextLine().toLowerCase();
        if (tipoPesquisa.isEmpty() || tipoPesquisa.equals(" ")) {
            System.out.println("Tipo do pet não inserido! Tente novamente");
            chamarOpcaoQuatro();
        }

        System.out.println("\nInsira informaçoes para esoclher um pet editável");
        System.out.println("Importante! Digite separado por um traço! Exemplo: Nome-Luiz");
        System.out.println("Caso não deseje adicionar, aperte Space+Enter");
        System.out.print("Insira o primeiro critério: ");
        String criterioPesquisaUm = sc.nextLine();
        System.out.print("Insira o segundo critério: ");
        String criterioPesquisaDois = sc.nextLine();

        File[] apoio = pesquisarPetPorNumero(tipoPesquisa, criterioPesquisaUm, criterioPesquisaDois);
        if (apoio == null) {
            System.out.println("Nenhum pet encontrado! Tente novamente");
            chamarMenu();
        }
        TxtController.editarPets(apoio);
    }

    public static void chamarOpcaoCinco() {
        Scanner sc = new Scanner(System.in);
        System.out.println("---DELETAR UM PET---");
        System.out.println("Digite o tipo do pet (Obrigatorio): ");
        System.out.print("Cachorro ou Gato - ");
        String tipoPesquisa = sc.nextLine().toLowerCase();
        if (tipoPesquisa.isEmpty() || tipoPesquisa.equals(" ")) {
            System.out.println("Tipo do pet não inserido! Tente novamente");
            chamarOpcaoQuatro();
        }

        System.out.println("\nInsira informaçoes para escolher um pet deletavel");
        System.out.println("Importante! Digite separado por um traço! Exemplo: Nome-Luiz");
        System.out.println("Caso não deseje adicionar, aperte Space+Enter");
        System.out.print("Insira o primeiro critério: ");
        String criterioPesquisaUm = sc.nextLine();
        System.out.print("Insira o segundo critério: ");
        String criterioPesquisaDois = sc.nextLine();

        File[] apoio = pesquisarPetPorNumero(tipoPesquisa, criterioPesquisaUm, criterioPesquisaDois);
        if (apoio == null) {
            System.out.println("Nenhum pet encontrado! Tente novamente");
            chamarMenu();
        }
        TxtController.deletarPets(apoio);
    }
}

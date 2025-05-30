package models;

import services.Endereco;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Pet {
    public enum Tipo {
        CACHORRO, GATO;
    }
    public enum Sexo {
        MACHO, FEMEA;
    }

    private String nome;
    private String sobrenome;
    private Tipo tipo;
    private Sexo sexo;
    private Endereco endereco;
    private String idade;
    private String peso;
    private String raca;
    public static final String NAO_INFORMADO = "NÃO INFORMADO";

    public Pet() {
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public String getIdade() {
        return idade;
    }

    public String getPeso() {
        return peso;
    }

    public String getRaca() {
        return raca;
    }



    public void setNome(String nome) {
        String regex = "^[a-zA-Z]+$";
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("Erro! Nome não pode ser vazio");
        }
        if (!Pattern.matches(regex, nome)) {
            throw new IllegalArgumentException("Erro! Caractere inválido inserido");
        }
        this.nome = nome;
    }

    public void setSobrenome(String sobrenome) {
        String regex = "^[a-zA-Z\\s]+$";
        if (sobrenome == null || sobrenome.isEmpty()) {
            throw new IllegalArgumentException("Erro! Sobrenome não pode ser vazio");
        }
        if (!Pattern.matches(regex, sobrenome)) {
            throw new IllegalArgumentException("Erro! Caractere inválido inserido");
        }
        this.sobrenome = sobrenome;
    }

    public void setTipo(String tipo) {
        this.tipo = Tipo.valueOf(tipo.toUpperCase());
    }

    public void setSexo(String sexo) {
        this.sexo = Sexo.valueOf(sexo.toUpperCase());
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public void setIdade(String idade) {
        String regexNumeros = "\\d+";
        Pattern pattern = Pattern.compile(regexNumeros);
        Matcher matcher = pattern.matcher(idade);

        if (matcher.find()) {
            int idadeNumero = Integer.parseInt(matcher.group());
            if (idadeNumero > 20) {
                throw new IllegalArgumentException("Erro! Idade inválida");
            } else {
                this.idade = idade;
            }
        }

        String regexVazia = "^\\s+$";
        if (Pattern.matches(regexVazia, idade)) {
            this.idade = NAO_INFORMADO;
        } else {
            this.idade = idade;
        }
    }

    public void setPeso(String peso) {
        String regexNumeros = "\\d+";
        Pattern pattern = Pattern.compile(regexNumeros);
        Matcher matcher = pattern.matcher(peso);

        if (matcher.find()) {
            int pesoNumero = Integer.parseInt(matcher.group());
            if (pesoNumero > 60 || pesoNumero < 0.5) {
                throw new IllegalArgumentException("Erro! Idade inválida");
            } else {
                this.peso = peso;
            }
        }

        String regexVazia = "^\\s+$";
        if (Pattern.matches(regexVazia, peso)) {
            this.peso = NAO_INFORMADO;
        } else {
            this.peso = peso;
        }
    }

    public void setRaca(String raca) {
        String regex = "^[a-zA-Z]+$";
        String regexVazia = "^\\s+$";
        if (!Pattern.matches(regex, raca)) {
            throw new IllegalArgumentException("Erro! Caractere inválido inserido");
        }
        if (Pattern.matches(regexVazia, raca)) {
            this.raca = NAO_INFORMADO;
        }
        this.raca = raca;
    }
}

package services;

import java.util.regex.Pattern;
import static models.Pet.NAO_INFORMADO;

public class Endereco {
    String rua;
    String numeroCasa;
    String cidade;

    public Endereco(String rua, String numeroCasa, String cidade) {
        this.rua = rua;
        this.numeroCasa = numeroCasa;
        this.cidade = cidade;
    }

    public Endereco() {
    }

    public String getRua() {
        return rua;
    }

    public String getNumeroCasa() {
        return numeroCasa;
    }

    public String getCidade() {
        return cidade;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public void setNumeroCasa(String numeroCasa) {
        String regexVazia = "^\\s+$";
        if (Pattern.matches(regexVazia, numeroCasa)) {
            this.numeroCasa = NAO_INFORMADO;
        }
        this.numeroCasa = numeroCasa;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    @Override
    public String toString() {
        return "Endereço{" +
                "rua='" + rua + '\'' +
                ", numeroCasa='" + numeroCasa + '\'' +
                ", cidade='" + cidade + '\'' +
                '}';
    }
}

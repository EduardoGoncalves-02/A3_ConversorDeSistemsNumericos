package com.unisul.conversor;

public class ConversorService {

    // Recebe o valor em Decimal (String) e retorna um array com: [Binário, Octal, Hexadecimal]
    public String[] converterDeDecimal(String valorDecimal) {
        // TODO: A equipe deve implementar a conversão de Decimal para as outras bases aqui
        String binario = ""; 
        String octal = "";
        String hexadecimal = "";
        
        return new String[]{"teste_bin", "teste_oct", "teste_hex"};
    }

    // Recebe o valor em Binário (String) e retorna: [Decimal, Octal, Hexadecimal]
    public String[] converterDeBinario(String valorBinario) {
        // TODO: A equipe deve implementar a conversão de Binário para as outras bases aqui
        return new String[]{"", "", ""};
    }

    // Recebe o valor em Octal (String) e retorna: [Decimal, Binário, Hexadecimal]
    public String[] converterDeOctal(String valorOctal) {
        // TODO: A equipe deve implementar a conversão de Octal para as outras bases aqui
        return new String[]{"", "", ""};
    }

    // Recebe o valor em Hexadecimal (String) e retorna: [Decimal, Binário, Octal]
    public String[] converterDeHexadecimal(String valorHex) {
        // TODO: A equipe deve implementar a conversão de Hexadecimal para as outras bases aqui
        return new String[]{"", "", ""};
    }
}
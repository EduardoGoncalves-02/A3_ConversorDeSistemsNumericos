package com.unisul.conversor;

public class ConversorService {

    // Recebe o valor em Decimal (String) e retorna um array com: [Binário, Octal, Hexadecimal]
    public String[] converterDeDecimal(String valorDecimal) {
        // TODO: A equipe deve implementar a conversão de Decimal para as outras bases aqui
        String binario = ""; 
        String octal = "";
        String hexadecimal = "";
        
        return new String[]{"teste_bin", "teste_oct", "teste_hexa"};
    }

    // Recebe o valor em Binário (String) e retorna: [Decimal, Octal, Hexadecimal]
    public String[] converterDeBinario(String valorBinario) {
        // TODO: A equipe deve implementar a conversão de Binário para as outras bases aqui
        // Verifica se o campo está vazio
        if (valorBinario == null || valorBinario.isEmpty()) {
            return new String[]{"", "", ""};
        }

        // Variável que armazenará o valor decimal
        long decimal = 0;

        // Converte o binário para decimal 
        for (int i = 0; i < valorBinario.length(); i++) {

            char digito = valorBinario.charAt(i);

            // Verifica se o caractere é válido
            if (digito != '0' && digito != '1') {
                return new String[]{"Binário inserido é inválido", "Binário inserido é inválido", "Binário inserido é inválido"};
            }

            // Multiplica o resultado anterior por 2 e soma o dígito atual
            decimal = decimal * 2 + (digito - '0');
        }

        // Converte o decimal para octal 
        String octal = "";
        long numero = decimal;

        if (numero == 0) {
            octal = "0";
        } else {
            while (numero > 0) {
                long resto = numero % 8;
                octal = resto + octal;
                numero = numero / 8;
            }
        }

        // Converte o decimal para hexadecimal
        String hexadecimal = "";
        numero = decimal;

        if (numero == 0) {
            hexadecimal = "0";
        } else {
            while (numero > 0) {
                long resto = numero % 16;

                if (resto < 10) {
                    hexadecimal = resto + hexadecimal;
                } else {
                    hexadecimal = (char) ('A' + resto - 10) + hexadecimal;
                }

                numero = numero / 16;
            }
        }

        // Retorna os resultados na ordem esperada pelo controller
        return new String[]{
            String.valueOf(decimal),
            octal,
            hexadecimal
        };
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
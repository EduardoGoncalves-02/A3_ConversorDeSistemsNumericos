package com.unisul.conversor;

public class ConversorService {

    // Recebe o valor em Decimal (String) e retorna um array com: [Binário, Octal, Hexadecimal]
    public String[] converterDeDecimal(String valorDecimal) {
        long entradaConvertidaNumero;
        String binario = "";
        String octal = "";
        String hexadecimal = "";

        //Verifica se o campo está vazio
        if (valorDecimal == null || valorDecimal.isEmpty()) {
            return new String[]{"", "", ""};
        }

        try {
            //tenta converter o texto recebido para long.
            entradaConvertidaNumero = Long.parseLong(valorDecimal);
            if(entradaConvertidaNumero < 0){
            return new String[]{"Decimal Inserido é inválido", "Decimal Inserido é inválido", "Decimal Inserido é inválido"};
            }
        } catch (NumberFormatException e) {
            return new String[]{"Decimal Inserido é inválido", "Decimal Inserido é inválido", "Decimal Inserido é inválido"};
        }
        
        //Se o valor for 0, todos são 0
        if(entradaConvertidaNumero == 0){
        return new String[]{"0","0","0"};
        }
        

        //Decimal para Binario
        long numero = entradaConvertidaNumero;
        while(numero > 0){
          binario = (numero % 2) + binario;  
          numero /= 2;}
            
        //Decimal para Octal
        numero = entradaConvertidaNumero;
        while(numero > 0){
          octal = (numero % 8) + octal;  
          numero /= 8;}
        
        //Decimal para Hexadecimal
        numero = entradaConvertidaNumero;
        while(numero > 0){
            long resto = numero % 16;
            if(resto < 10){
             hexadecimal = resto + hexadecimal;
        }else{
            hexadecimal = (char) ('A' + resto - 10) + hexadecimal;
            }
        numero = numero / 16;
        }
          return new String[]{binario,octal,hexadecimal};
        }

    // Recebe o valor em Binário (String) e retorna: [Decimal, Octal, Hexadecimal]
    public String[] converterDeBinario(String valorBinario) {

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

        // tira espaços do começo e do fim
        if (valorOctal != null) {
            valorOctal = valorOctal.trim();
        }
        //passo 1: validaçao
        if (valorOctal == null || valorOctal.isEmpty()) {
            return new String[]{"", "", ""};

        }
        //limite de 20 digitos pra evitar overflow
        if (valorOctal.length() > 20) {
            return new String[]{"Octal muito grande", "Octal muito grande", "Octal muito grande"};
        }

        // no octal só existem os dígitos de 0 a 7 
        for (int i = 0; i < valorOctal.length(); i++) {
            char digito = valorOctal.charAt(i);
            if (digito < '0' || digito > '7') {
                return new String[]{"Octal inserido é inválido", "Octal inserido é inválido", "Octal inserido é inválido"};
            }
        }

        // passo 2: Octal -> Decimal (método posicional / polinômio)
        // lendo da esquerda para a direita: multiplica o acumulado por 8 e soma o dígito.
        long decimal = 0;
        for (int i = 0; i < valorOctal.length(); i++) {
            int digito = valorOctal.charAt(i) - '0'; // '7' - '0' = 7
            decimal = decimal * 8 + digito;
        }

        // passo 3: octal -> binário (agrupamento de bits)
        // 8 = 2³, então cada dígito octal vira exatamente 3 bits.
        // ex.: 175(8) -> 001 111 101 -> 1111101(2)
        String[] tabela = {"000", "001", "010", "011", "100", "101", "110", "111"};
        String binario = "";
        for (int i = 0; i < valorOctal.length(); i++) {
            int digito = valorOctal.charAt(i) - '0';
            binario = binario + tabela[digito];
        }
        // remove zeros a esquerda, mantendo pelo menos um dígito
        int primeiroUm = 0;
        while (primeiroUm < binario.length() - 1 && binario.charAt(primeiroUm) == '0') {
            primeiroUm++;
        }
        binario = binario.substring(primeiroUm);

        // passo 4: decimal > hexadecimal (divisões sucessivas por 16)
        // os restos, lidos de trás para frente formam o resultado.
        // ex.: 125 / 16 = 7 resto 13 (D); 7 / 16 = 0 resto 7 -> "7D"
        String hexadecimal = "";
        long numero = decimal;

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

        // retorna na ordem esperada pelo controller, podendo ser decimal, binário, hexadecimal
        return new String[]{String.valueOf(decimal), binario, hexadecimal};
    }

    // Recebe o valor em Hexadecimal (String) e retorna: [Decimal, Binário, Octal]
    public String[] converterDeHexadecimal(String valorHex) {
        
        String digitosHex = "0123456789ABCDEF";
        String decimal = "";
        String binario = "";
        String octal = "";
        
        
        //Hexadecimal para decimal
        
        
        
        return new String[]{decimal, binario, octal};
    }
}

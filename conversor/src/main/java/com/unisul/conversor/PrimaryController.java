package com.unisul.conversor;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

public class PrimaryController {

    // 1. Ligação com os fx:id definidos no Scene Builder
    @FXML private TextField txtDecimal;
    @FXML private TextField txtBinario;
    @FXML private TextField txtOctal;
    @FXML private TextField txtHexadecimal;

    // Instância da classe onde ficarão os cálculos da equipe
    private final ConversorService service = new ConversorService();

    // Trava de segurança para evitar loop infinito quando os campos atualizarem sozinhos
    private boolean atualizando = false;

    // 2. Método executado automaticamente ao abrir a janela
    @FXML
    public void initialize() {
        
        // Ouvinte do campo DECIMAL
        txtDecimal.textProperty().addListener((observable, oldValue, newValue) -> {
            if (atualizando) return;
            atualizando = true;
            
            if (newValue.isEmpty()) {
                limparTodos();
            } else {
                String[] resultados = service.converterDeDecimal(newValue);
                txtBinario.setText(resultados[0]);
                txtOctal.setText(resultados[1]);
                txtHexadecimal.setText(resultados[2]);
            }
            
            atualizando = false;
        });

        // Ouvinte do campo BINÁRIO
        txtBinario.textProperty().addListener((observable, oldValue, newValue) -> {
            if (atualizando) return;
            atualizando = true;
            
            if (newValue.isEmpty()) {
                limparTodos();
            } else {
                String[] resultados = service.converterDeBinario(newValue);
                txtDecimal.setText(resultados[0]);
                txtOctal.setText(resultados[1]);
                txtHexadecimal.setText(resultados[2]);
            }
            
            atualizando = false;
        });

        // Ouvinte do campo OCTAL
        txtOctal.textProperty().addListener((observable, oldValue, newValue) -> {
            if (atualizando) return;
            atualizando = true;
            
            if (newValue.isEmpty()) {
                limparTodos();
            } else {
                String[] resultados = service.converterDeOctal(newValue);
                txtDecimal.setText(resultados[0]);
                txtBinario.setText(resultados[1]);
                txtHexadecimal.setText(resultados[2]);
            }
            
            atualizando = false;
        });

        // Ouvinte do campo HEXADECIMAL
        txtHexadecimal.textProperty().addListener((observable, oldValue, newValue) -> {
            if (atualizando) return;
            atualizando = true;
            
            if (newValue.isEmpty()) {
                limparTodos();
            } else {
                String[] resultados = service.converterDeHexadecimal(newValue);
                txtDecimal.setText(resultados[0]);
                txtBinario.setText(resultados[1]);
                txtOctal.setText(resultados[2]);
            }
            
            atualizando = false;
        });
    }

    // 3. Ações dos Botões (On Action definidos no Scene Builder)
    @FXML
    private void onRestabelecer() {
        atualizando = true;
        limparTodos();
        atualizando = false;
    }

    @FXML private void onCopiarDecimal()     { copiarParaAreaTransferencia(txtDecimal.getText()); }
    @FXML private void onCopiarBinario()     { copiarParaAreaTransferencia(txtBinario.getText()); }
    @FXML private void onCopiarOctal()       { copiarParaAreaTransferencia(txtOctal.getText()); }
    @FXML private void onCopiarHexadecimal() { copiarParaAreaTransferencia(txtHexadecimal.getText()); }

    // Métodos auxiliares internos
    private void limparTodos() {
        txtDecimal.clear();
        txtBinario.clear();
        txtOctal.clear();
        txtHexadecimal.clear();
    }

    private void copiarParaAreaTransferencia(String texto) {
        if (texto != null && !texto.isEmpty()) {
            ClipboardContent content = new ClipboardContent();
            content.putString(texto);
            Clipboard.getSystemClipboard().setContent(content);
        }
    }
}
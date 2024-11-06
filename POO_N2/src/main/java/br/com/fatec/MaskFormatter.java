package br.com.fatec;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author Vitor Aurélio Saccone Mimaki
 */
public class MaskFormatter {

    /*Declaração dos componentes.*/
    private TextField textField;
    private DatePicker datePicker;
    
    /*Variavel que vai contem a mascara selecionada para caso seja exibido
    o formato da mascara.*/
    private int maskSelecionada;
    
    /*Variavel para verificação se passou no construtor um DatePicker*/
    private boolean usarDatePicker;
    
    /*Declaração de constantes que vão representar os tipos de mascaras*/
    public static final int TEL_8DIG = 0;
    public static final int TEL_9DIG = 1;
    public static final int CPF = 2;
    public static final int RG = 3;
    public static final int CEP = 4;
    public static final int DATA_BARRA = 5;
    public static final int DATA_TRACO = 6;

    public MaskFormatter(TextField textField) {
        this.textField = textField;

    }

    public MaskFormatter(DatePicker datePicker) {
        this.datePicker = datePicker;
        /*Informa que foi passado um DatePicker*/
        this.usarDatePicker = true;
    }

    /**
     * Passe o tipo da Mascara. Ex: setMask(MaskFormatter.TEL_8DIG);
     *
     * @param maskType
     */
    public void setMask(int maskType) {
        this.maskSelecionada = maskType;
        if (!usarDatePicker) {

            switch (maskType) {
                case TEL_8DIG:
                    maskTel8Dig();
                    break;
                case TEL_9DIG:
                    maskTel9Dig();
                    break;
                case CPF:
                    maskCpf();
                    break;
                case RG:
                    maskRg();
                    break;
                case CEP:
                    maskCep();
                    break;
                default:
                    break;
            }
        } else {
            switch (maskType) {
                case DATA_BARRA:
                    maskDataBarra();
                    break;
                case DATA_TRACO:
                    maskDataTraco();
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Método que iniciar a mascara.
     */
    private void maskTel8Dig() {
        /*evento que captura os dados digitados*/
        textField.setOnKeyTyped((KeyEvent evento) -> {

            /*verifico se o que está sendo digitado é um numero*/
            if (!"0123456789".contains(evento.getCharacter())) {
                /*usando o método consume,e como se o que o usuario digitou não tivesse efeito, ex
                ele digitou uma letra, mas essa letra não apareçe pq bloqueamos o evento que faz ela aperecer na tela.*/
                evento.consume();
            }
            /*verificamos se o caracter foi digitado ou apagado se caso apagado ele é igual a zero.*/
            if (evento.getCharacter().trim().length() == 0) {

                switch (textField.getText().length()) {
                    case 9:
                        /*subString so retornar os caracteres entre aquelas posições
                        fazemos isso para apagar o caractere - que colocamos na mascara.*/
                        textField.setText(textField.getText().substring(0, 7));
                        textField.positionCaret(textField.getText().length());
                        break;
                    case 3:
                        textField.setText(textField.getText().substring(0, 2));
                        textField.positionCaret(textField.getText().length());
                        break;
                    case 1:
                        textField.setText("");
                        textField.positionCaret(textField.getText().length());
                        break;
                    default:
                        break;
                }

            } else if (textField.getText().length() == 14) {
                /*verificamos se já chegou o limite de numeros digitados
                lembrando que estamos contando todos os caracteres que estão visiveis*/

                evento.consume();
            }
            switch (textField.getText().length()) {
                case 1:
                    /*Adicionamos o parentese no primeiro caracter.Obs:
                    lembrando que cada caractere digitado e como se estivesse em um array
                    então o primeiro caracter fica na posição 0*/
                    textField.setText("(" + textField.getText());
                    /*movemos sempre a letra para o ultimo lugar, sem isso a letra voltaria para o primeiro*/
                    textField.positionCaret(textField.getText().length());
                    break;
                case 3:
                    /*como adicionamos um caractere parentese, aumenta mais um, então o segundo
                    numero digitado é o 3*/
                    textField.setText(textField.getText() + ")");
                    textField.positionCaret(textField.getText().length());
                    break;
                case 9:
                    textField.setText(textField.getText() + "-");
                    textField.positionCaret(textField.getText().length());
                    break;
            }

        });
    }

    private void maskTel9Dig() {
        textField.setOnKeyTyped((KeyEvent evento) -> {
            if (!"0123456789".contains(evento.getCharacter())) {
                evento.consume();
            }

            if (evento.getCharacter().trim().length() == 0) {

                switch (textField.getText().length()) {
                    case 1:
                        textField.setText("");
                        textField.positionCaret(textField.getText().length());
                        break;
                    case 3:
                        textField.setText(textField.getText().substring(0, 2));
                        textField.positionCaret(textField.getText().length());
                        break;
                    case 9:
                        textField.setText(textField.getText().substring(0, 8));
                        textField.positionCaret(textField.getText().length());
                        break;
                }
            } else if (textField.getText().length() == 15) {
                evento.consume();
            }
            switch (textField.getText().length()) {
                case 1:
                    textField.setText("(" + textField.getText());
                    textField.positionCaret(textField.getText().length());
                    break;
                case 3:
                    textField.setText(textField.getText() + ")");
                    textField.positionCaret(textField.getText().length());
                    break;
                case 9:
                    textField.setText(textField.getText() + "-");
                    textField.positionCaret(textField.getText().length());
                    break;
            }

        });
    }

    private void maskCpf() {

        textField.setOnKeyTyped((KeyEvent evento) -> {
            if (!"0123456789".contains(evento.getCharacter())) {
                evento.consume();
            }

            if (evento.getCharacter().trim().length() == 0) {

                switch (textField.getText().length()) {
                    case 11:
                        textField.setText(textField.getText().substring(0, 10));
                        textField.positionCaret(textField.getText().length());
                        break;
                    case 7:
                        textField.setText(textField.getText().substring(0, 6));
                        textField.positionCaret(textField.getText().length());
                        break;
                    case 3:
                        textField.setText(textField.getText().substring(0, 2));
                        textField.positionCaret(textField.getText().length());
                        break;
                }

            } else if (textField.getText().length() == 14) {
                evento.consume();
            }
            switch (textField.getText().length()) {
                case 3:
                    textField.setText(textField.getText() + ".");
                    textField.positionCaret(textField.getText().length());
                    break;
                case 7:
                    textField.setText(textField.getText() + ".");
                    textField.positionCaret(textField.getText().length());
                    break;
                case 11:
                    textField.setText(textField.getText() + "-");
                    textField.positionCaret(textField.getText().length());
                    break;
            }

        });
    }

    private void maskRg() {
        textField.setOnKeyTyped((KeyEvent evento) -> {
            if (!"0123456789".contains(evento.getCharacter())) {
                evento.consume();
            }

            if (evento.getCharacter().trim().length() == 0) {

                switch (textField.getText().length()) {
                    case 2:
                        textField.setText(textField.getText().substring(0, 1));
                        textField.positionCaret(textField.getText().length());
                        break;
                    case 6:
                        textField.setText(textField.getText().substring(0, 5));
                        textField.positionCaret(textField.getText().length());
                        break;
                    case 10:
                        textField.setText(textField.getText().substring(0, 9));
                        textField.positionCaret(textField.getText().length());
                        break;
                }

            } else if (textField.getText().length() == 12) {
                evento.consume();
            }
            switch (textField.getText().length()) {
                case 2:
                    textField.setText(textField.getText() + ".");
                    textField.positionCaret(textField.getText().length());
                    break;
                case 6:
                    textField.setText(textField.getText() + ".");
                    textField.positionCaret(textField.getText().length());
                    break;
                case 10:
                    textField.setText(textField.getText() + "-");
                    textField.positionCaret(textField.getText().length());
                    break;
            }

        });

    }
    
    private void maskCep() {
        textField.setOnKeyTyped(evento -> {
            // Impede a entrada de caracteres não numéricos
            if (!"0123456789".contains(evento.getCharacter())) {
                evento.consume();
            }
        
            if (evento.getCharacter().trim().length() == 0) {

                switch (textField.getText().length()) {
                    case 5:
                        textField.setText(textField.getText().substring(0, 4));
                        textField.positionCaret(textField.getText().length());
                        break;
                }

            }
        
            // Limita o campo a 9 caracteres, incluindo o hífen
            if (textField.getText().length() >= 9) {
                evento.consume();
                return;
            }

            // Insere o hífen após o quinto caractere
            if (textField.getText().length() == 5) {
                textField.setText(textField.getText() + "-");
                textField.positionCaret(textField.getText().length());
            }
        });
    }

    private void maskDataBarra() {
        datePicker.getEditor().setOnKeyTyped((KeyEvent evento) -> {

            if (!"0123456789".contains(evento.getCharacter())) {
                evento.consume();
            }

            if (evento.getCharacter().trim().length() == 0) {
                switch (datePicker.getEditor().getText().length()) {
                    case 2:
                        datePicker.getEditor().setText(datePicker.getEditor().getText().substring(0, 1));
                        datePicker.getEditor().positionCaret(datePicker.getEditor().getText().length());
                        break;
                    case 5:
                        datePicker.getEditor().setText(datePicker.getEditor().getText().substring(0, 4));
                        datePicker.getEditor().positionCaret(datePicker.getEditor().getText().length());
                        break;
                }
            } else if (datePicker.getEditor().getText().length() == 10) {
                evento.consume();
            }
            switch (datePicker.getEditor().getText().length()) {
                case 2:
                    datePicker.getEditor().setText(datePicker.getEditor().getText() + "/");
                    datePicker.getEditor().positionCaret(datePicker.getEditor().getText().length());
                    break;
                case 5:
                    datePicker.getEditor().setText(datePicker.getEditor().getText() + "/");
                    datePicker.getEditor().positionCaret(datePicker.getEditor().getText().length());
                    break;
            }

        });
    }
    
    private void maskDataTraco() {
        datePicker.getEditor().setOnKeyTyped((KeyEvent evento) -> {

            if (!"0123456789".contains(evento.getCharacter())) {
                evento.consume();
            }

            if (evento.getCharacter().trim().length() == 0) {
                switch (datePicker.getEditor().getText().length()) {
                    case 2:
                        datePicker.getEditor().setText(datePicker.getEditor().getText().substring(0, 1));
                        datePicker.getEditor().positionCaret(datePicker.getEditor().getText().length());
                        break;
                    case 5:
                        datePicker.getEditor().setText(datePicker.getEditor().getText().substring(0, 4));
                        datePicker.getEditor().positionCaret(datePicker.getEditor().getText().length());
                        break;
                }
            } else if (datePicker.getEditor().getText().length() == 10) {
                evento.consume();
            }
            switch (datePicker.getEditor().getText().length()) {
                case 2:
                    datePicker.getEditor().setText(datePicker.getEditor().getText()+"-");
                    datePicker.getEditor().positionCaret(datePicker.getEditor().getText().length());
                    break;
                case 5:
                    datePicker.getEditor().setText(datePicker.getEditor().getText()+"-");
                    datePicker.getEditor().positionCaret(datePicker.getEditor().getText().length());
                    break;
            }

        });
    }
    
    /**
     * Exibi no componente o formato de máscara selecionado. Obs: Utilizar
     * somente depois do método setMask()
     */
    public void showMask() {
        switch (this.maskSelecionada) {
            case TEL_8DIG:
                textField.setPromptText("(__)____-____");
                break;
            case TEL_9DIG:
                textField.setPromptText("(__)_____-____");
                break;
            case CPF:
                textField.setPromptText("___.___.___-__");
                break;
            case RG:
                textField.setPromptText("__.___.___-_");
                break;
            case DATA_BARRA:
                datePicker.setPromptText("DD/MM/AAAA");
                break;
            case DATA_TRACO:
                datePicker.setPromptText("DD-MM-AAAA");
                break;
            default:
                break;
        }
    }
    
    public static String tirarFormatacao(TextField textField, int formato) {
        String novo = "";
        
        if(formato == MaskFormatter.CEP) {
            novo = textField.getText().substring(0, 5);
            novo += textField.getText().substring(6, 9);
        } else if (formato == MaskFormatter.CPF) {
            novo = textField.getText().substring(0, 3);
            novo += textField.getText().substring(4, 7);
            novo += textField.getText().substring(8, 11);
            novo += textField.getText().substring(12, 14);
        //}  else if (formato == MaskFormatter.RG) {
        //    novo = textField.getText().substring(1, 3);
        //    novo += textField.getText().substring(4, 9);
        //   novo += textField.getText().substring(10, 14);
        }  else if (formato == MaskFormatter.TEL_9DIG) {
            novo = textField.getText().substring(1, 3);
            novo += textField.getText().substring(4, 9);
            novo += textField.getText().substring(10, 14);
        //} else if (formato == MaskFormatter.TEL_8DIG) {
        //    novo = textField.getText().substring(1, 3);
        //    novo += textField.getText().substring(4, 9);
        //    novo += textField.getText().substring(10, 14);
        } else {
            return null;
        }
        return novo;
    }
    
    public static String tirarFormatacao(DatePicker datePicker) {
        String novo = "";
        TextField editor = datePicker.getEditor();
        
        novo = editor.getText().substring(0,2);
        novo += editor.getText().substring(3,5);
        novo += editor.getText().substring(6,10);

        return novo;
    }
    
}
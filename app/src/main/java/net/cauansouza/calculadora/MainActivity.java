package net.cauansouza.calculadora;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

// A biblioteca exp4j, é a responsável por interpretar uma string como uma expressão e retornar o resultado dos calculos
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

    }

    // Definindo botões numéricos

    public void onClick_zero(View view) {
        AcrescentarUmaExpressao("0",true);
    }
    public void onClick_um(View view) {
        AcrescentarUmaExpressao("1",true);
    }
    public void onClick_dois(View view) {
        AcrescentarUmaExpressao("2",true);
    }
    public void onClick_tres(View view) {
        AcrescentarUmaExpressao("3",true);
    }
    public void onClick_quatro(View view) {
        AcrescentarUmaExpressao("4",true);
    }
    public void onClick_cinco(View view) {
        AcrescentarUmaExpressao("5",true);
    }
    public void onClick_seis(View view) {
        AcrescentarUmaExpressao("6",true);
    }
    public void onClick_sete(View view) {
        AcrescentarUmaExpressao("7",true);
    }
    public void onClick_oito(View view) {
        AcrescentarUmaExpressao("8",true);
    }
    public void onClick_nove(View view) {
        AcrescentarUmaExpressao("9",true);
    }

    // Definindo botões operadores

    public void onClick_multiplicacao(View view) {AcrescentarUmaExpressao("x",false); }
    public void onClick_divisao(View view) {
        AcrescentarUmaExpressao("/",false);
    }
    public void onClick_adicao(View view) {AcrescentarUmaExpressao("+",false); }
    public void onClick_subtracao(View view) {AcrescentarUmaExpressao("-",false); }

    // Definindo outros botões

    public void onClick_limpar(View view) {
        TextView resultado = findViewById(R.id.resultado);
        TextView expressao = findViewById(R.id.expressao);
        // Quando pressionado, o botão limpa o texto dos TextViews resultado e expresssao
        resultado.setText("");
        expressao.setText("");
    }


    public void onClick_del(View view) {
        TextView expressao = findViewById(R.id.expressao);
        String string = expressao.getText().toString();
        // Se a string não for vazia, irá pegar o comprimento da string e remover 1 caractere
        if (string.length() > 0) {
            string = string.substring(0, string.length() - 1);
            expressao.setText(string);
        }
    }

    public void onClick_igual(View view) {
        TextView resultado = findViewById(R.id.resultado);
        TextView expressao = findViewById(R.id.expressao);
        // stringexp recebe o conteúdo de expressao convertido para string e substitui vírgulas por pontos
        String stringexp = expressao.getText().toString().replace(",",".");
        // stringexp recebe o conteúdo dele mesmo substituindo a letra "X" por asterisco (*)
        stringexp = stringexp.replace("x","*");
        try {
            // Instanciando um objeto Expression através da ferramenta exp4j
            Expression valor = new ExpressionBuilder(stringexp).build();
            Double resultadoexpr = (valor).evaluate();
            // Caso o valor seja um inteiro ele será convertido para o formato int
            if (resultadoexpr == Math.rint(resultadoexpr)) {
                int dx2 = (resultadoexpr).intValue();
                // Caso o valor exceda o limite suportado por uma varíavel int
                if (dx2<2147483647){
                    resultado.setText(Integer.toString(dx2));
                } else {
                    expressao.setText("");
                    resultado.setText("VALOR EXCEDENTE");
                }
            } else {
                int length = resultadoexpr.toString().length() - 1;
                System.out.println(resultadoexpr.toString()+"------"+length);
                // Caso o valor tenha mais de 8 algarismos ele será convertido para um formato que limite o tamanho de decimais
                if (length > 8) {
                    DecimalFormat fDecimais = new DecimalFormat("0.00000000");
                    // Substituindo pontos por vírgulas para exibição
                    String rdecimal = fDecimais.format(resultadoexpr).replace(".",",");
                    resultado.setText(rdecimal);
                }else {
                    String valorfinal = resultadoexpr.toString().replace(".",",");
                    resultado.setText(valorfinal);
                }
            }
        } catch (Exception e){
            expressao.setText("");
            resultado.setText("EXPRESSÃO INVÁLIDA");
        }
    }

    public void onClick_ponto(View view) {AcrescentarUmaExpressao(",",true);}

    // Método para acrescentar caracteres a expressão
    public void AcrescentarUmaExpressao(String string, boolean limpar_dados){
        TextView resultado = findViewById(R.id.resultado);
        TextView expressao = findViewById(R.id.expressao);

        // Se o resultado for vazio, o texto da expressão será limpo
        if(resultado.length() != 0){
            expressao.setText("");
        }

        // Se a variável limpar_dados for verdadeira e a expressao não conter a mensagem de "Erro", o texto do resultado
        // será limpo e a expressão receberá o caractere contido na string
        if((limpar_dados) && (expressao.getText() != "Erro")){
            resultado.setText("");
            expressao.append(string);
        } else {
            // Se não, a expressao receberá o conteúdo do resultado e o caractere contido na string
            // Em seguida, o resultado será limpado
            expressao.append(resultado.getText());
            expressao.append(string);
            resultado.setText("");
        }
    }

}
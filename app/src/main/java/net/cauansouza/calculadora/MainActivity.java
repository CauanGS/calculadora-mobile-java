package net.cauansouza.calculadora;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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

    // Números

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

    // Operadores

    public void onClick_multiplicacao(View view) {AcrescentarUmaExpressao("x",false); }
    public void onClick_divisao(View view) {
        AcrescentarUmaExpressao("/",false);
    }
    public void onClick_adicao(View view) {AcrescentarUmaExpressao("+",false); }
    public void onClick_subtracao(View view) {AcrescentarUmaExpressao("-",false); }

    // Outros

    public void onClick_limpar(View view) {
        TextView resultado = findViewById(R.id.resultado);
        TextView expressao = findViewById(R.id.expressao);
        resultado.setText("");
        expressao.setText("");
    }


    public void onClick_del(View view) {
        TextView expressao = findViewById(R.id.expressao);
        String string = expressao.getText().toString();
        if (string.length() > 0) {
            string = string.substring(0, string.length() - 1);
            expressao.setText(string);
        }
    }

    public void onClick_igual(View view) {
        TextView resultado = findViewById(R.id.resultado);
        TextView expressao = findViewById(R.id.expressao);
        String stringexp = expressao.getText().toString().replace(",",".");
        stringexp = stringexp.replace("x","*");
        try {
            Expression valor = new ExpressionBuilder(stringexp).build();
            Double resultadoexpr = (valor).evaluate();
            if (resultadoexpr == Math.rint(resultadoexpr)) {
                int dx2 = (resultadoexpr).intValue();
                resultado.setText(Integer.toString(dx2));
            } else {
                int length = resultadoexpr.toString().length() - 1;
                System.out.println(resultadoexpr.toString()+"------"+length);
                if (length > 8) {
                    DecimalFormat fDecimais = new DecimalFormat("0.00000000");
                    String rdecimal = fDecimais.format(resultadoexpr).replace(".",",");
                    resultado.setText(rdecimal);
                }else {
                    String valorfinal = resultadoexpr.toString().replace(".",",");
                    resultado.setText(valorfinal);
                }
            }
        } catch (Exception e){
            System.out.println("Erro ao realizar o calculo: "+e);
            expressao.setText("");
            resultado.setText("Expressão inválida");
        }
    }

    public void onClick_ponto(View view) {AcrescentarUmaExpressao(",",true);}

    public void AcrescentarUmaExpressao(String string, boolean limpar_dados){
        TextView resultado = findViewById(R.id.resultado);
        TextView expressao = findViewById(R.id.expressao);

        if(resultado.length() != 0){ // Testando se "resultado" é vazio
            expressao.setText("");
        }
        if((limpar_dados) && (expressao.getText() != "Erro")){ // Testando se é necessário limpar os dados
            resultado.setText("");
            expressao.append(string);
        } else {
            expressao.append(resultado.getText());
            expressao.append(string);
            resultado.setText("");
        }
    }

}
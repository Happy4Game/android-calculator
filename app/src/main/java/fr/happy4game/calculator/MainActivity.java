package fr.happy4game.calculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import fr.dufeutrelthibaut.calculator.R;
import fr.happy4game.calculator.State;

public class MainActivity extends AppCompatActivity {

    private Button zero, one, two, three, four, five, six, seven, eight, nine, dot, plus, moins, fois, diviser, equal, c;
    private String varOne;
    private State state;
    private TextView result;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        zero = findViewById(R.id.zero);
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);
        seven = findViewById(R.id.seven);
        eight = findViewById(R.id.eight);
        nine = findViewById(R.id.nine);
        dot = findViewById(R.id.dot);
        result = findViewById(R.id.result);

        plus = findViewById(R.id.plus);
        moins = findViewById(R.id.moins);
        fois = findViewById(R.id.fois);
        diviser = findViewById(R.id.diviser);
        equal = findViewById(R.id.equal);
        c = findViewById(R.id.c);

        Button[] buttonList = {zero, one, two, three, four, five, six, seven, eight, nine, dot};


        for (Button btn: buttonList){
            btn.setOnClickListener(view -> {
                if (state == State.NOTHING){
                    result.setText("");
                    state = null;
                }
                while (result.getText().toString().startsWith("0") && !btn.getText().toString().equals(".")
                        && !result.getText().toString().contains(".")){
                    result.setText(result.getText().toString().replace("0", ""));
                }
                if (!(btn.getText().toString().contains(".") && result.getText().toString().contains("."))){
                    result.setText(result.getText().toString() + btn.getText().toString());
                }
            });
        }

        plus.setOnClickListener(view -> {
            if (state != null){
                result.setText(String.valueOf(equalOperation()));
            }
            state = State.PLUS;
            varOne = result.getText().toString();
            result.setText("");

        });
        moins.setOnClickListener(view -> {
            if (state != null){
                result.setText(String.valueOf(equalOperation()));
            }
            state = State.MINUS;
            varOne = result.getText().toString();
            result.setText("");
        });
        fois.setOnClickListener(view -> {
            if (state != null){
                result.setText(String.valueOf(equalOperation()));
            }
            state = State.MULTIPLY;
            varOne = result.getText().toString();
            result.setText("");
        });
        diviser.setOnClickListener(view -> {
            if (state != null){
                result.setText(String.valueOf(equalOperation()));
            }
            state = State.DIVIDE;
            varOne = result.getText().toString();
            result.setText("");
        });

        equal.setOnClickListener(view -> {
            double resultNumber = equalOperation();
            if (resultNumber == (int) resultNumber){
                result.setText(String.valueOf((int)resultNumber));
            }
            else{
                result.setText(String.valueOf(resultNumber));
            }
            state = null;
        });

        c.setOnClickListener(view -> {
            varOne = "";
            state = null;
            result.setText("0");
        });

    }

    private double equalOperation(){
        double resultNumber = 0;
        if (state == State.PLUS){
            resultNumber = Double.parseDouble(varOne) + Double.parseDouble(result.getText().toString());
        }
        else if (state == State.MINUS){
            resultNumber = Double.parseDouble(varOne) - Double.parseDouble(result.getText().toString());
        }
        else if (state == State.MULTIPLY){
            resultNumber = Double.parseDouble(varOne) * Double.parseDouble(result.getText().toString());
        }
        else if (state == State.DIVIDE){
            resultNumber = Double.parseDouble(varOne) / Double.parseDouble(result.getText().toString());
        }
        return resultNumber;
    }

}
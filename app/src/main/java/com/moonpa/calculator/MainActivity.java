package com.moonpa.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Stack;
import com.moonpa.calculator.CalculatorLogical;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    String inputS = new String("0");
    TextView input;
    TextView output;
    CalculatorLogical cal = new CalculatorLogical();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = (TextView)findViewById(R.id.input);
        output = (TextView)findViewById(R.id.output);

        Button btn0 = (Button)findViewById(R.id.btn0);
        Button btn1 = (Button)findViewById(R.id.btn1);
        Button btn2 = (Button)findViewById(R.id.btn2);
        Button btn3 = (Button)findViewById(R.id.btn3);
        Button btn4 = (Button)findViewById(R.id.btn4);
        Button btn5 = (Button)findViewById(R.id.btn5);
        Button btn6 = (Button)findViewById(R.id.btn6);
        Button btn7 = (Button)findViewById(R.id.btn7);
        Button btn8 = (Button)findViewById(R.id.btn8);
        Button btn9 = (Button)findViewById(R.id.btn9);
        Button btne = (Button)findViewById(R.id.btne);
        Button btndel = (Button)findViewById(R.id.btndel);
        Button btndiv = (Button)findViewById(R.id.btndiv);
        Button btnmul = (Button)findViewById(R.id.btnmul);
        Button btnplus = (Button)findViewById(R.id.btnplus);
        Button btnminus = (Button)findViewById(R.id.btnminus);
        Button btnp = (Button)findViewById(R.id.btnp);
        Button btnclr = (Button)findViewById(R.id.btnclr);

        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btne.setOnClickListener(this);
        btndel.setOnClickListener(this);
        btndiv.setOnClickListener(this);
        btnmul.setOnClickListener(this);
        btnplus.setOnClickListener(this);
        btnminus.setOnClickListener(this);
        btnp.setOnClickListener(this);
        btnclr.setOnClickListener(this);

        //長按清除鈕
        btndel.setOnLongClickListener(new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View view)
            {
                inputS = "0";
                input.setTextSize(50);
                input.setText(inputS);
                output.setText(inputS);

                Toast.makeText(MainActivity.this,"清空完成!",Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    @Override
    public void onClick(View view)
    {
        Button btndel = (Button)findViewById(R.id.btndel);
        Button btnclr = (Button)findViewById(R.id.btnclr);

        int id = view.getId();

        switch(id)
        {
            case R.id.btn0:
                setNumInput("0");
                break;
            case R.id.btn1:
                setNumInput("1");
                break;
            case R.id.btn2:
                setNumInput("2");
                break;
            case R.id.btn3:
                setNumInput("3");
                break;
            case R.id.btn4:
                setNumInput("4");
                break;
            case R.id.btn5:
                setNumInput("5");
                break;
            case R.id.btn6:
                setNumInput("6");
                break;
            case R.id.btn7:
                setNumInput("7");
                break;
            case R.id.btn8:
                setNumInput("8");
                break;
            case R.id.btn9:
                setNumInput("9");
                break;
            case R.id.btne:
                try
                {
                    if(!cal.toPostfix(inputS).equals("wrong"))
                    {
                        Double answer = cal.getAnswer(inputS);
                        inputS = answer + "";
                        input.setText(answer + "");
                        output.setText(answer + "");

                        btndel.setVisibility(Button.INVISIBLE);
                        btnclr.setVisibility(Button.VISIBLE);
                    }
                    else
                        Toast.makeText(MainActivity.this,"算式錯誤!",Toast.LENGTH_SHORT).show();
                }
                catch(ArithmeticException e)
                {
                    Toast.makeText(MainActivity.this,"除數不可為零!",Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btndel:
                if(!inputS.equals("0"))
                {
                    StringBuffer temp = new StringBuffer(inputS);
                    temp.deleteCharAt(temp.length() - 1);
                    inputS = temp.toString();
                    if(inputS.isEmpty())
                        inputS = "0";
                    if(inputS.length() <= 12)
                        input.setTextSize(50);
                    input.setText(inputS);
                }
                break;
            case R.id.btnclr:
                inputS = "0";
                input.setTextSize(50);
                input.setText(inputS);
                output.setText(inputS);

                btnclr.setVisibility(Button.INVISIBLE);
                btndel.setVisibility(Button.VISIBLE);
                Toast.makeText(MainActivity.this,"清空完成!",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btndiv:
                setEleInput("/");
                break;
            case R.id.btnmul:
                setEleInput("*");
                break;
            case R.id.btnplus:
                setEleInput("+");
                break;
            case R.id.btnminus:
                setEleInput("-");
                break;
            case R.id.btnp:
                setEleInput(".");
                break;
        }
    }

    private void setNumInput(String S)
    {
        if(!inputS.equals("0"))
        {
            if(inputS.length() == 40)
                Toast.makeText(MainActivity.this,"已達上限!",Toast.LENGTH_SHORT).show();
            else
            {
                inputS += S;
                if (inputS.length() > 12)
                    input.setTextSize(30);
            }
        }
        else
            inputS = S;
        input.setText(inputS);
    }

    private void setEleInput(String S)
    {
        if(inputS.charAt(inputS.length()-1) != '.' && inputS.charAt(inputS.length()-1) != '+'
                && inputS.charAt(inputS.length()-1) != '-' && inputS.charAt(inputS.length()-1) != '*'
                && inputS.charAt(inputS.length()-1) != '/')
        {
            if(inputS.length() == 40)
                Toast.makeText(MainActivity.this,"已達上限!",Toast.LENGTH_SHORT).show();
            else
            {
                inputS += S;
                if (inputS.length() > 12)
                    input.setTextSize(30);
            }
            input.setText(inputS);
        }

    }
}

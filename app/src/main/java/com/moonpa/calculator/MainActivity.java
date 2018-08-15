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
    TextView intput;
    TextView output;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CalculatorLogical cal = new CalculatorLogical();

        intput = (TextView)findViewById(R.id.input);
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

        //長按清除鈕
        btndel.setOnLongClickListener(new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View view)
            {


                Toast.makeText(MainActivity.this,"清空完成!",Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    @Override
    public void onClick(View view)
    {
        int id = view.getId();

        switch(id)
        {
            case R.id.btn0:

                break;
            case R.id.btn1:

                break;
            case R.id.btn2:

                break;
            case R.id.btn3:

                break;
            case R.id.btn4:

                break;
            case R.id.btn5:

                break;
            case R.id.btn6:

                break;
            case R.id.btn7:

                break;
            case R.id.btn8:

                break;
            case R.id.btn9:

                break;
            case R.id.btne:

                break;
            case R.id.btndel:

                break;
            case R.id.btndiv:

                break;
            case R.id.btnmul:

                break;
            case R.id.btnplus:

                break;
            case R.id.btnminus:
                
                break;
        }
    }
}

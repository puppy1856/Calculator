package com.moonpa.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.xw.repo.BubbleSeekBar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    String inputS = new String("");
    double answer;
    TextView input;
    TextView output;
    BubbleSeekBar seekBar;
    CalculatorLogical cal = new CalculatorLogical();
    private YoYo.YoYoString rope,inRope,outRope;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = (TextView) findViewById(R.id.input);
        output = (TextView) findViewById(R.id.output);
        seekBar = (BubbleSeekBar)findViewById(R.id.seekBar);

        Button btn0 = (Button) findViewById(R.id.btn0);
        Button btn1 = (Button) findViewById(R.id.btn1);
        Button btn2 = (Button) findViewById(R.id.btn2);
        Button btn3 = (Button) findViewById(R.id.btn3);
        Button btn4 = (Button) findViewById(R.id.btn4);
        Button btn5 = (Button) findViewById(R.id.btn5);
        Button btn6 = (Button) findViewById(R.id.btn6);
        Button btn7 = (Button) findViewById(R.id.btn7);
        Button btn8 = (Button) findViewById(R.id.btn8);
        Button btn9 = (Button) findViewById(R.id.btn9);
        Button btne = (Button) findViewById(R.id.btne);
        Button btndel = (Button) findViewById(R.id.btndel);
        Button btndiv = (Button) findViewById(R.id.btndiv);
        Button btnmul = (Button) findViewById(R.id.btnmul);
        Button btnplus = (Button) findViewById(R.id.btnplus);
        Button btnminus = (Button) findViewById(R.id.btnminus);
        Button btnp = (Button) findViewById(R.id.btnp);
        Button btnclr = (Button) findViewById(R.id.btnclr);

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

        seekBar.setOnProgressChangedListener(new BubbleSeekBar.OnProgressChangedListener()
        {
            @Override
            public void onProgressChanged(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat, boolean fromUser)
            {
                input.setTextSize(progress);
            }

            @Override
            public void getProgressOnActionUp(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat)
            {

            }

            @Override
            public void getProgressOnFinally(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat, boolean fromUser)
            {

            }
        });

        //長按清除鈕
        btndel.setOnLongClickListener(new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View view)
            {
                if(rope != null)
                {
                    rope.stop();
                    rope = null;
                    inputS = "";
                    input.setText("");
                    output.setText("");
                }
                else
                    rope = YoYo.with(Techniques.FadeOutDown).duration(600).playOn(findViewById(R.id.put_layout));

                return true;
            }
        });
    }

    @Override
    public void onClick(View view)
    {
        Button btndel = (Button) findViewById(R.id.btndel);
        Button btnclr = (Button) findViewById(R.id.btnclr);

        int id = view.getId();

        switch (id)
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
                    if (!cal.toPostfix(inputS).equals("wrong"))
                    {
                        answer = cal.getAnswer(inputS);
                        inputS = output.getText() + "";
                        if (inputS.length() > 10 && input.length() <= 15)
                        {
                            input.setTextSize(40);
                            seekBar.setProgress(40);
                        }
                        else if (inputS.length() > 15)
                        {
                            input.setTextSize(35);
                            seekBar.setProgress(35);
                        }
                        input.setText(inputS);
                        outRope = YoYo.with(Techniques.FadeOutUp).duration(650).playOn(output);
                        inRope = YoYo.with(Techniques.FadeInUp).duration(600).playOn(input);

                        btndel.setVisibility(Button.INVISIBLE);
                        btnclr.setVisibility(Button.VISIBLE);
                    } else
                        inRope = YoYo.with(Techniques.Shake).duration(600).playOn(input);

                } catch (ArithmeticException e)
                {
                    inRope = YoYo.with(Techniques.Shake).duration(600).playOn(input);
                    output.setText("除數不得為零");
                }

                break;
            case R.id.btndel:
                if (!inputS.isEmpty())
                {
                    if (!output.getText().equals(""))
                        output.setText("");
                    if (inputS.length() == 1)
                        inputS = "";
                    else
                    {
                        StringBuffer temp = new StringBuffer(inputS);
                        temp.deleteCharAt(temp.length() - 1);
                        inputS = temp.toString();
                        if (!cal.toPostfix(inputS).equals("wrong"))
                        {
                            try
                            {
                                answer = cal.getAnswer(inputS);

                                if (checkInt(answer))
                                {
                                    int answerInt = (int) answer;
                                    setOutput(output,answerInt + "");
                                } else
                                    setOutput(output,answer + "");
                            }catch (ArithmeticException e)
                            { output.setText(""); }
                        }
                        if(inputS.length() == 15)
                        {
                            input.setTextSize(40);
                            seekBar.setProgress(40);
                        }

                        if (inputS.length() == 10)
                        {
                            input.setTextSize(50);
                            seekBar.setProgress(50);
                        }
                    }
                    input.setText(inputS);
                }
                break;
            case R.id.btnclr:
                rope = YoYo.with(Techniques.FadeOutDown).duration(600).playOn(findViewById(R.id.put_layout));
                btnclr.setVisibility(Button.INVISIBLE);
                btndel.setVisibility(Button.VISIBLE);
                break;
            case R.id.btndiv:
                setEleInput("÷");
                break;
            case R.id.btnmul:
                setEleInput("×");
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
        if(rope != null)
        {
            rope.stop();
            rope = null;
            inputS = "";
            input.setTextSize(50);
            seekBar.setProgress(50);
            input.setText(inputS);
            output.setText("");
        }

        if(outRope != null)
        {
            outRope.stop();
            outRope = null;
            output.setText("");
        }

        if (inputS.isEmpty())
            inputS += S;

        else
        {
            if (inputS.length() >= 30)
                inRope = YoYo.with(Techniques.Shake).duration(600).playOn(input);
            else
            {
                inputS += S;
                if (inputS.length() == 11)
                {
                    input.setTextSize(40);
                    seekBar.setProgress(40);
                }
                if (inputS.length() == 16)
                {
                    input.setTextSize(35);
                    seekBar.setProgress(35);
                }
            }
        }
        input.setText(inputS);

        if (!cal.toPostfix(inputS).equals("wrong"))
        {
            try
            {
                answer = cal.getAnswer(inputS);
                if (checkInt(answer))
                {
                    int answerInt = (int) answer;
                    setOutput(output,answerInt + "");
                } else
                    setOutput(output,answer + "");
            }catch (ArithmeticException e)
            { output.setText(""); }
        }
        else
            output.setText("");
    }

    private void setEleInput(String S)
    {
        if(rope != null)
        {
            rope.stop();
            rope = null;
            inputS = "";
            input.setTextSize(50);
            seekBar.setProgress(50);
            input.setText(inputS);
            output.setText("");
        }

        if (inputS.isEmpty() && S.equals("-"))
        {
            inputS += S;
            input.setText(inputS);
        }
        else if (!inputS.isEmpty()
                && Character.isDigit(inputS.charAt(inputS.length() - 1)))
        {
            if (inputS.length() >= 30)
                inRope = YoYo.with(Techniques.Shake).duration(600).playOn(input);
            else
            {
                inputS += S;
                if (inputS.length() == 11)
                {
                    input.setTextSize(40);
                    seekBar.setProgress(40);
                }
                if (inputS.length() == 16)
                {
                    input.setTextSize(35);
                    seekBar.setProgress(35);
                }
            }
            input.setText(inputS);
        }

    }

    private boolean checkInt(double check)
    {
        int temp = (int) check;
        if (check - temp != 0)
            return false;
        else
            return true;
    }

    private void setOutput(TextView V,String S)
    {
        if(S.length() >= 10)
            V.setTextSize(30);
        else
            V.setTextSize(35);

        V.setText(S);
    }
}

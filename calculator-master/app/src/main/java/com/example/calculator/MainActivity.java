package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button[] buttons = new Button[24];
    private int[] buttonIds = new int[]{R.id.ce,R.id.backspace,R.id.remainder,R.id.involution,R.id.leftbracket,R.id.rbracket,
            R.id.p,R.id.e, R.id.divide,R.id.n7,R.id.n8,R.id.n9,R.id.multiply,R.id.n4,
            R.id.n5,R.id.n6,R.id.subtract,R.id.n1,R.id.n2,R.id.n3,R.id.equal,R.id.zero,R.id.point, R.id.add};


    private TextView equationText,resultText;
    private CheckInput checkInput;
    private CounterByEquation counterByEquation;
    private String equation = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//引用布局

        int length = buttons.length;
        for(int i=0;i<length;i++){
            buttons[i] = findViewById(buttonIds[i]);
            buttons[i].setOnClickListener(this);
        }
        equationText = (TextView)findViewById(R.id.equation);
        resultText = (TextView)findViewById(R.id.result);
        checkInput = new CheckInput();
        counterByEquation = new CounterByEquation(equation);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        Button button = (Button)findViewById(id);//强制转换
        String text = button.getText().toString();
        /*
        数字按钮
         */
        switch (id){
            case R.id.n1:
            case R.id.n2:
            case R.id.n3:
            case R.id.n4:
            case R.id.n5:
            case R.id.n6:
            case R.id.n7:
            case R.id.n8:
            case R.id.n9:
            case R.id.zero:
                checkInput.setEquation(equation);
                if(checkInput.checkNumberInput()){
                    equation = checkInput.getEquation();
                    equation += text;
                }
                break;
            /*
             * 点击 + - * /
             * */
            case R.id.add:
            case R.id.subtract:
            case R.id.multiply:
            case R.id.divide:
                checkInput.setEquation(equation);
                if(checkInput.checkOperationsInput()){
                    equation = checkInput.getEquation();
                    equation += text;
                }
                break;
            /*
             * 点击退格键
             *
             * */
            case R.id.backspace:
                checkInput.setEquation(equation);
                checkInput.backSpace();
                equation = checkInput.getEquation();
                resultText.setText("");
                break;
            /*
             * 点击 x^y
             * */
            case R.id.involution:
                checkInput.setEquation(equation);
                if(checkInput.checkInvolutionInput())
                    equation+="^";
                break;

            /*
             * 左括号
             * */

            case R.id.leftbracket:
                checkInput.setEquation(equation);
                if(checkInput.checkLBracketInput())
                    equation+="(";
                break;
            /*
             * 右括号
             * */
            case R.id.rbracket:
                checkInput.setEquation(equation);
                if(checkInput.checkRBracketInput())
                    equation+=')';
                break;
            /*
             * 取余
             * */
            case R.id.remainder:
                checkInput.setEquation(equation);
                if(checkInput.checkRemainderInput())
                    equation+='%';
                break;

            /*
             * 清空按钮
             * */
            case R.id.ce:
                equation = "";
                equationText.setText("");
                resultText.setText("");
                break;

            /*
             * 小数点按钮
             * */
            case R.id.point:
                checkInput.setEquation(equation);
                if(checkInput.checkPointInput()){
                    equation = checkInput.getEquation();
                    equation+=text;
                }
                break;

            /*
             * 点击π
             * */
            case R.id.p:
                checkInput.setEquation(equation);
                if (checkInput.checkSpecialSymbolBack()){
                    equation+="π";
                }
                break;

            /*
             * 点击e
             * */
            case R.id.e:
                checkInput.setEquation(equation);
                if (checkInput.checkSpecialSymbolBack()){
                    equation+="e";
                }
                break;
            /*
             * 点击=
             * */
            case R.id.equal:
                resultText.setText(new CounterByEquation(equation).solveEquation());
                break;
        }
        equationText.setText(equation);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}

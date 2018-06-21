package com.example.kevinschweitzer.myapplication.mvp.model;

import android.util.Log;

import com.example.kevinschweitzer.myapplication.util.operators.Operator;

public class CalculatorModel {

    private String number1;
    private String number2;
    private Operator operator;

    public CalculatorModel() {
        number1 = "";
        number2 = "";
    }

    public void setNewNumber(String str){
        if(operator == null){
            number1+=str;
        }
        else{
            number2+=str;
        }
    }

    public void setOperator(Operator operator){
        this.operator = operator;
    }

    public Integer getResult(){
        if(number1 != "" && number2 != ""){
            int num1 = Integer.parseInt(number1);
            int num2 = Integer.parseInt(number2);
            Log.i("Numero",num2+"");
            return operator.operate(num1,num2);
        }

        return null;
    }

    public void clear(){
        number1="";
        number2="";
        operator=null;
    }

}

package com.example.kevinschweitzer.myapplication.mvp.model;

import android.text.TextUtils;
import android.util.Log;

import com.example.kevinschweitzer.myapplication.util.operators.Operator;

public class CalculatorModel {

    private String number1;
    private String number2;
    private Operator operator;

    public void setNewNumber(String str){
        if(operator == null){
            if(number1==null)
                number1=str;
            else
                number1+=str;
        }
        else{
            if(number2==null)
                number2=str;
            else
                number2+=str;
        }
    }

    public void setOperator(Operator operator){
        this.operator = operator;
    }

    public Integer getResult(){
        if( !TextUtils.isEmpty(number1) && !TextUtils.isEmpty(number2)){
            int num1 = Integer.parseInt(number1);
            int num2 = Integer.parseInt(number2);
            return operator.operate(num1,num2);
        }

        return null;
    }

    public String getStringResult(){
        Integer result = getResult();
        return result!=null?String.valueOf(result):null;
    }

    public void clear(){
        number1="";
        number2="";
        operator=null;
    }

}

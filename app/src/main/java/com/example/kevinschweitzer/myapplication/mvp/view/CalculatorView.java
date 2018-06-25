package com.example.kevinschweitzer.myapplication.mvp.view;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.kevinschweitzer.myapplication.R;
import com.example.kevinschweitzer.myapplication.util.bus.RxBus;
import com.example.kevinschweitzer.myapplication.util.bus.obervers.ClearObserver;
import com.example.kevinschweitzer.myapplication.util.bus.obervers.EqualsObserver;
import com.example.kevinschweitzer.myapplication.util.bus.obervers.NumberObserver;
import com.example.kevinschweitzer.myapplication.util.bus.obervers.OperatorObserver;
import com.example.kevinschweitzer.myapplication.util.operators.DivisionOperator;
import com.example.kevinschweitzer.myapplication.util.operators.MinusOperator;
import com.example.kevinschweitzer.myapplication.util.operators.MultiplicationOperator;
import com.example.kevinschweitzer.myapplication.util.operators.PlusOperator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CalculatorView extends ActivityView {

    @BindView(R.id.text_operation) TextView operation;
    @BindView(R.id.text_result) TextView result;

    public CalculatorView(Activity activity){
        super(activity);
        ButterKnife.bind(this, activity);
    }

    public void concatNumber(String str){
        operation.setText(operation.getText() + str);
    }

    public void concatOperator(String str){
        operation.setText(operation.getText() + " " + str + " ");
    }

    public void setResult(String value){
        this.result.setText(value);
    }

    public void clear(){
        operation.setText("");
        result.setText("");
    }

    @OnClick({R.id.button_number0, R.id.button_number1, R.id.button_number2, R.id.button_number3, R.id.button_number4, R.id.button_number5,
            R.id.button_number6, R.id.button_number7, R.id.button_number8,R.id.button_number9})
    public void numberClicked(View view){
        Button numberButton = (Button)view;
        RxBus.post(new NumberObserver.Number(numberButton.getText().toString()));
    }

    @OnClick(R.id.button_plus)
    public void plusClicked(View view){
        RxBus.post(new OperatorObserver.OperatorClass(new PlusOperator()));
    }

    @OnClick(R.id.button_minus)
    public void minusClicked(View view){
        RxBus.post(new OperatorObserver.OperatorClass(new MinusOperator()));
    }

    @OnClick(R.id.button_multiplication)
    public void multiplicationClicked(View view){
        RxBus.post(new OperatorObserver.OperatorClass(new MultiplicationOperator()));
    }

    @OnClick(R.id.button_division)
    public void divisionClicked(View view){
        RxBus.post(new OperatorObserver.OperatorClass(new DivisionOperator()));
    }



    @OnClick(R.id.button_equals)
    public void equalsClicked(View view){
        RxBus.post(new EqualsObserver.Equals());
    }

    @OnClick(R.id.button_clear)
    public void clearClicked(View view){
        RxBus.post(new ClearObserver.Clear());
    }

}

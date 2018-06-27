package com.example.kevinschweitzer.myapplication.mvp.presenter;

import android.app.Activity;
import android.util.Log;

import com.example.kevinschweitzer.myapplication.MainActivity;
import com.example.kevinschweitzer.myapplication.R;
import com.example.kevinschweitzer.myapplication.mvp.model.CalculatorModel;
import com.example.kevinschweitzer.myapplication.mvp.view.CalculatorView;
import com.example.kevinschweitzer.myapplication.util.bus.RxBus;
import com.example.kevinschweitzer.myapplication.util.bus.obervers.ClearObserver;
import com.example.kevinschweitzer.myapplication.util.bus.obervers.EqualsObserver;
import com.example.kevinschweitzer.myapplication.util.bus.obervers.NumberObserver;
import com.example.kevinschweitzer.myapplication.util.bus.obervers.OperatorObserver;
import com.example.kevinschweitzer.myapplication.util.operators.Operator;

public class CalculatorPresenter {

    private CalculatorView view;
    private CalculatorModel model;

    public CalculatorPresenter(CalculatorView view, CalculatorModel model){
        this.view = view;
        this.model = model;
    }

    /**
     * Save the number clicked in the model and refresh the view
     * If number 1 to 9 is clicked this action must be done
     */
    public void onNumberClicked(String str){
        model.setNewNumber(str);
        view.concatNumber(str);
    }

    /**
     * Set the operator in the view and take the first number complete
     * If one operator +, -, *, / is clicked this action must be done
     */
    public void onOperatorClicked(Operator operator){
        model.setOperator(operator);
        view.concatOperator(operator.getSymbol());
    }

    /**
     * Resolve the calculation
     * If the equals symbol is clicked, this action must be done
     */
    public void onEqualsClicked() {
        Activity activity = view.getActivity();
        if (activity == null){
            return;
        }
        String result = model.getStringResult();
        view.setResult(result!=null?result:activity.getResources().getString(R.string.error));
    }

    /**
     * Clear the fields
     * If the clear button ins clicked, this actions must be done
     */
    public void onClearClicked(){
        model.clear();
        view.clear();
    }

    public void register(){
        Activity activity = view.getActivity();

        if (activity==null){
            return;
        }

        RxBus.subscribe(activity, new NumberObserver() {
            @Override
            public void onEvent(NumberObserver.Number value) {
                onNumberClicked(value.getString());
            }
        });

        RxBus.subscribe(activity, new OperatorObserver() {
            @Override
            public void onEvent(OperatorClass value) {
                onOperatorClicked(value.getOperator());
            }
        });

        RxBus.subscribe(activity, new EqualsObserver() {
            @Override
            public void onEvent(Equals value) {
                onEqualsClicked();
            }
        });

        RxBus.subscribe(activity, new ClearObserver() {
            @Override
            public void onEvent(Clear value) {
                onClearClicked();
            }
        });
    }

    public void unregister(){
        Activity activity = view.getActivity();

        if (activity==null){
            return;
        }
        RxBus.clear(activity);
    }
}

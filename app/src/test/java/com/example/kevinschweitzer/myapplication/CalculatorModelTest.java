package com.example.kevinschweitzer.myapplication;

import com.example.kevinschweitzer.myapplication.mvp.model.CalculatorModel;
import com.example.kevinschweitzer.myapplication.util.operators.DivisionOperator;
import com.example.kevinschweitzer.myapplication.util.operators.MinusOperator;
import com.example.kevinschweitzer.myapplication.util.operators.MultiplicationOperator;
import com.example.kevinschweitzer.myapplication.util.operators.PlusOperator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class CalculatorModelTest {

    @Test
    public void sumTest(){
        CalculatorModel model = new CalculatorModel();

        model.setNewNumber("1");
        model.setOperator(new PlusOperator());
        model.setNewNumber("1");
        assertThat(model.getResult(),is(2));
    }

    @Test
    public void substractionTest(){
        CalculatorModel model = new CalculatorModel();

        /*Substraction with positive result*/
        model.setNewNumber("2");
        model.setOperator(new MinusOperator());
        model.setNewNumber("1");
        assertThat(model.getResult(),is(1));


        model.clear();

        /*Substraction with negative result*/
        model.setNewNumber("1");
        model.setOperator(new MinusOperator());
        model.setNewNumber("5");
        assertThat(model.getResult(),is(-4));

    }

    @Test
    public void multiplicationTest(){
        CalculatorModel model = new CalculatorModel();

        model.setNewNumber("3");
        model.setOperator(new MultiplicationOperator());
        model.setNewNumber("5");
        assertThat(model.getResult(),is(15));
    }

    @Test
    public void divisionTest(){
        CalculatorModel model = new CalculatorModel();

        /*Division of even number*/
        model.setNewNumber("4");
        model.setOperator(new DivisionOperator());
        model.setNewNumber("2");
        assertThat(model.getResult(),is(2));

        model.clear();

        /*Division by 0*/
        model.setNewNumber("3");
        model.setOperator(new DivisionOperator());
        model.setNewNumber("0");
        assertThat(model.getResult(), nullValue());

        model.clear();

        /*Division of an odd number*/
        model.setNewNumber("5");
        model.setOperator(new DivisionOperator());
        model.setNewNumber("2");
        assertThat(model.getResult(),is(2));

    }
}

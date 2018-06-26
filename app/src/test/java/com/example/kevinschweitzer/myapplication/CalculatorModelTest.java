package com.example.kevinschweitzer.myapplication;

import com.example.kevinschweitzer.myapplication.mvp.model.CalculatorModel;
import com.example.kevinschweitzer.myapplication.mvp.view.CalculatorView;
import com.example.kevinschweitzer.myapplication.util.operators.DivisionOperator;
import com.example.kevinschweitzer.myapplication.util.operators.MinusOperator;
import com.example.kevinschweitzer.myapplication.util.operators.MultiplicationOperator;
import com.example.kevinschweitzer.myapplication.util.operators.PlusOperator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class CalculatorModelTest {

    private CalculatorModel model;

    @Before
    public void setUp(){
        model = new CalculatorModel();
    }

    @Test
    public void sumTest(){
        model.setNewNumber("1");
        model.setOperator(new PlusOperator());
        model.setNewNumber("1");
        assertThat(model.getResult(),is(2));
    }

    @Test
    public void substractionPositiveResultTest(){
        /*Substraction with positive result*/
        model.setNewNumber("2");
        model.setOperator(new MinusOperator());
        model.setNewNumber("1");
        assertThat(model.getResult(),is(1));
    }

    @Test
    public void substractionNegativeResultTest(){
        /*Substraction with negative result*/
        model.setNewNumber("1");
        model.setOperator(new MinusOperator());
        model.setNewNumber("5");
        assertThat(model.getResult(),is(-4));
    }

    @Test
    public void multiplicationTest(){
        model.setNewNumber("3");
        model.setOperator(new MultiplicationOperator());
        model.setNewNumber("5");
        assertThat(model.getResult(),is(15));
    }

    @Test
    public void evenNumberDivisionTest(){
        /*Division of even number*/
        model.setNewNumber("4");
        model.setOperator(new DivisionOperator());
        model.setNewNumber("2");
        assertThat(model.getResult(),is(2));
    }

    @Test
    public void oddNumberDivisionTest(){
        /*Division of an odd number*/
        model.setNewNumber("5");
        model.setOperator(new DivisionOperator());
        model.setNewNumber("2");
        assertThat(model.getResult(),is(2));
    }

    @Test
    public void divisionByZeroTest(){
        /*Division by 0*/
        model.setNewNumber("3");
        model.setOperator(new DivisionOperator());
        model.setNewNumber("0");
        assertThat(model.getResult(), nullValue());
    }
}

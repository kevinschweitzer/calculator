package com.example.kevinschweitzer.myapplication;

import android.app.Activity;

import com.example.kevinschweitzer.myapplication.mvp.model.CalculatorModel;
import com.example.kevinschweitzer.myapplication.mvp.presenter.CalculatorPresenter;
import com.example.kevinschweitzer.myapplication.mvp.view.ActivityView;
import com.example.kevinschweitzer.myapplication.mvp.view.CalculatorView;
import com.example.kevinschweitzer.myapplication.util.bus.RxBus;
import com.example.kevinschweitzer.myapplication.util.bus.obervers.BusObserver;
import com.example.kevinschweitzer.myapplication.util.bus.obervers.ClearObserver;
import com.example.kevinschweitzer.myapplication.util.bus.obervers.EqualsObserver;
import com.example.kevinschweitzer.myapplication.util.bus.obervers.NumberObserver;
import com.example.kevinschweitzer.myapplication.util.bus.obervers.OperatorObserver;
import com.example.kevinschweitzer.myapplication.util.operators.Operator;
import com.example.kevinschweitzer.myapplication.util.operators.PlusOperator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.internal.matchers.Any;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.api.mockito.internal.configuration.PowerMockitoInjectingAnnotationEngine;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Observer;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.validateMockitoUsage;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.verifyStatic;

@RunWith(PowerMockRunner.class)
public class CalculatorPresenterTest {

    private final String NEW_NUMBER = "7";
    private final String SYMBOL = "+";
    private final String SAMPLE_RESULT = "5";
    private final int NO_INVOCATIONS = 0;
    private final int TWO_INVOCATIONS = 2;
    private @Mock CalculatorModel model;
    private @Mock CalculatorView view;
    private @Mock Operator operator;
    private @Mock Activity activityRegister;
    private @Captor ArgumentCaptor<NumberObserver> numberObserverArgumentCaptor;
    private @Captor ArgumentCaptor<OperatorObserver> operatorObserverArgumentCaptor;
    private @Captor ArgumentCaptor<EqualsObserver> equalsObserverArgumentCaptor;
    private @Captor ArgumentCaptor<ClearObserver> clearObserverArgumentCaptor;
    private CalculatorPresenter presenter;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        presenter = new CalculatorPresenter(view,model);
    }

    @Test
    public void numberClickedTest(){
        presenter.onNumberClicked(NEW_NUMBER);

        verify(model).setNewNumber(NEW_NUMBER);
        verify(view).concatNumber(NEW_NUMBER);
    }

    @Test
    public void operatorClickedTest(){
        Mockito.when(operator.getSymbol()).thenReturn(SYMBOL);

        presenter.onOperatorClicked(operator);
        verify(model).setOperator(operator);
        verify(view).concatOperator(operator.getSymbol());
    }

    @Test
    public void equalsClickedWithNullActivityTest(){
        /*With Activity null*/
        Mockito.when(view.getActivity()).thenReturn(null);

        presenter.onEqualsClicked();
        verify(view,times(NO_INVOCATIONS)).setResult(anyString());
        verify(model,times(NO_INVOCATIONS)).getResult();
    }

    @Test
    public void equalsClickedWithNotNullActivityTest(){
        /*With activity not null*/
        Mockito.when(model.getStringResult()).thenReturn(SAMPLE_RESULT);
        Activity activity = Mockito.mock(Activity.class);
        Mockito.when(view.getActivity()).thenReturn(activity);

        presenter.onEqualsClicked();

        verify(view).setResult(model.getStringResult());
        verify(model,times(TWO_INVOCATIONS)).getStringResult();

    }

    @Test
    public void clearClickedTest(){
        presenter.onClearClicked();

        verify(model).clear();
        verify(view).clear();
        Mockito.verifyNoMoreInteractions(model,view);
    }

    @Test
    @PrepareForTest(RxBus.class)
    public void registerWithNullActivityTest(){
        PowerMockito.mockStatic(RxBus.class);

        /*With null activity*/
        Mockito.when(view.getActivity()).thenReturn(null);
        presenter.register();
        verifyStatic(times(NO_INVOCATIONS));
        RxBus.subscribe(any(Activity.class),any(BusObserver.class));
    }

    @Test
    @PrepareForTest(RxBus.class)
    public void registerWithNotNullActivityTest(){
        /*With not null activity*/
        PowerMockito.mockStatic(RxBus.class);
        Activity activity = mock(Activity.class);
        Mockito.when(view.getActivity()).thenReturn(activity);
        presenter.register();

        verifyStatic();
        RxBus.subscribe(eq(activity),any(NumberObserver.class));

        verifyStatic();
        RxBus.subscribe(eq(activity),any(OperatorObserver.class));

        verifyStatic();
        RxBus.subscribe(eq(activity),any(EqualsObserver.class));

        verifyStatic();
        RxBus.subscribe(eq(activity),any(ClearObserver.class));
    }

    @Test
    @PrepareForTest(RxBus.class)
    public void unregisterWithNullActivityTest(){
        PowerMockito.mockStatic(RxBus.class);

        /*With null activity*/
        Mockito.when(view.getActivity()).thenReturn(null);
        presenter.unregister();
        verifyStatic(times(NO_INVOCATIONS));
        RxBus.clear(any(Activity.class));
    }

    @Test
    @PrepareForTest(RxBus.class)
    public void unregisterWithNotNullActivityTest(){
        /*With not null activity*/
        Activity activity = mock(Activity.class);
        Mockito.when(view.getActivity()).thenReturn(activity);
        presenter.unregister();
        verifyStatic();
        RxBus.clear(activity);
    }

}

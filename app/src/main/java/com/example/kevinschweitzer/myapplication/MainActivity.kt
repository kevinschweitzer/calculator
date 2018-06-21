package com.example.kevinschweitzer.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.kevinschweitzer.myapplication.mvp.model.CalculatorModel
import com.example.kevinschweitzer.myapplication.mvp.presenter.CalculatorPresenter
import com.example.kevinschweitzer.myapplication.mvp.view.CalculatorView

class MainActivity : AppCompatActivity() {

    private lateinit var presenter: CalculatorPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter  = CalculatorPresenter(CalculatorView(this), CalculatorModel())
    }

    override fun onResume() {
        super.onResume()
        presenter.register()
    }

    override fun onStop() {
        super.onStop()
        presenter.unregister()
    }
}

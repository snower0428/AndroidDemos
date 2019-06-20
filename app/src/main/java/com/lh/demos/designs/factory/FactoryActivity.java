package com.lh.demos.designs.factory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.lh.demos.R;
import com.lh.demos.base.BaseAppCompatActivity;
import com.lh.demos.base.BaseConstants;
import com.lh.demos.designs.factory.pizza.Pizza;
import com.lh.demos.designs.factory.pizzastore.ChicagoPizzaStore;
import com.lh.demos.designs.factory.pizzastore.NYPizzaStore;
import com.lh.demos.designs.factory.pizzastore.PizzaStore;

public class FactoryActivity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factory);

        setTitle(getIntent().getStringExtra(BaseConstants.NAVIGATION_TITLE_KEY));

        PizzaStore nyStore = new NYPizzaStore();
        PizzaStore chicagoStore = new ChicagoPizzaStore();

        Pizza pizza = nyStore.orderPizza("cheese");
        Log.d("lh123", "Ethan ordered a " + pizza.getName());

        pizza = chicagoStore.orderPizza("cheese");
        Log.d("lh123", "Joel ordered a " + pizza.getName());
    }
}

package com.example.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    int numberOfCoffee = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitOrder(View view)
    {
        String message = "Total Price : $" + numberOfCoffee * 5 + "\nThankYou";
        displayMessage(message);
    }

    private void dispaly(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);

        quantityTextView.setText("" + number);
    }

    public void incrementQuantity(View view)
    {
        numberOfCoffee++;
        dispaly(numberOfCoffee);
        dispalyPrice(numberOfCoffee);

    }

    public void decrementQuantity(View view)
    {
        numberOfCoffee--;
        dispaly(numberOfCoffee);
        dispalyPrice(numberOfCoffee);
    }
    private void dispalyPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number * 5 ));
    }

    private  void displayMessage(String message)
    {
        TextView pricetextView = (TextView) findViewById(R.id.price_text_view);

        pricetextView.setText(message);
    }
}

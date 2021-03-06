package com.example.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    int numberOfCoffee = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitOrder(View view)
    {


        CheckBox wippedCreamCheckbox  = (CheckBox) findViewById(R.id.wipped_cream_checkbox_view);
        boolean hasWippedCream = wippedCreamCheckbox.isChecked();

        CheckBox chocolateCheckbox  = (CheckBox) findViewById(R.id.choclate_checkbox_view);
        boolean hasChocolate = chocolateCheckbox.isChecked();

        EditText nameEditText = (EditText) findViewById(R.id.name_edit_text);
        String name = nameEditText.getText().toString();


        int price = calclulatePrice(hasWippedCream,hasChocolate);

        String message = createOrderSummery(name,price,hasWippedCream,hasChocolate);


        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT,"Just Java App by Devinder");
        intent.putExtra(Intent.EXTRA_TEXT,message);


//        if(intent.resolveActivity(getPackageManager()) != null){
//            startActivity(intent);
//        }
        displayMessage(message);
    }

    private void displayQunatity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);

        quantityTextView.setText("" + number);
    }

    public void incrementQuantity(View view)
    {
        if(numberOfCoffee == 100)
        {
            Toast.makeText(getApplicationContext(),"Length cannot be graeter Than 100",Toast.LENGTH_SHORT).show();
            return;
        }
        numberOfCoffee++;
        displayQunatity(numberOfCoffee);
    }


    public void decrementQuantity(View view)
    {
        if(numberOfCoffee == 1){
            Toast.makeText(getApplicationContext(),"Length cannot be negtive",Toast.LENGTH_SHORT).show();
            return;
        }

        numberOfCoffee--;
        displayQunatity(numberOfCoffee);

    }


    private int calclulatePrice(boolean hasWippedCream,boolean hasChocolate)
    {
        int price = 5;

        if(hasWippedCream)
            price++;

        if(hasChocolate)
            price += 2;

        price = numberOfCoffee * price;
        return price;
    }

    private String createOrderSummery(String name,int price,boolean hasWippedCream,boolean hasChocolate)
    {
        String priceMessage = getString(R.string.create_order_summery_name,name);
        priceMessage += "\n" + getString(R.string.create_order_summery_quantity,numberOfCoffee);
        priceMessage += "\n" + getString(R.string.create_order_summery_add_whipped,hasWippedCream);
        priceMessage += "\n" + getString(R.string.create_order_summery_add_chocolate,hasChocolate);
        priceMessage += "\n" + getString(R.string.create_order_summery_total,price);
        priceMessage += "\n" + getString(R.string.thankYou);

        return priceMessage;
    }



    private  void displayMessage(String message)
    {
        TextView orderSummeryView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummeryView.setText(message);
    }
}

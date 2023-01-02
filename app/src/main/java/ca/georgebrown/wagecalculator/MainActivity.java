package ca.georgebrown.wagecalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Find button
        Button calculate = findViewById(R.id.btn_calc);

        calculate.setOnClickListener(view -> {
            // Declare UI elements
            TextView txtHours;
            TextView txtWage;
            TextView txt_gross;
            TextView txt_tax;
            TextView txt_net;

            // State Vars
            int hours;
            double wage;
            final double tax = 0.18;
            final double overtime = 1.5;
            // gross is money I earn before tax deduction
            double gross = 0;
            // net is money I have after tax deduction
            double net;
            double taxes;

            // Catch error when filling up fields
            try {
                // Find UI elements
                txtHours = findViewById(R.id.txt_hours);
                txtWage = findViewById(R.id.txt_wage);
                // Get Values and convert to wage to double and hours to int
                wage = Double.parseDouble(txtWage.getText().toString());
                hours = Integer.parseInt(txtHours.getText().toString());
                // Check for Overtime retribution
                if(hours > 40.00){
                    gross = overtime * hours * wage;
                }
                else{
                    gross = hours * wage;
                }
            }catch (Exception e){
                e.printStackTrace();
            }

            // Calculate taxes
            taxes = gross * tax;
            // Calculate net
            net = gross - taxes;

            // Get UI elements for displaying numbers
            txt_gross = findViewById(R.id.txt_gross_pay);
            txt_tax = findViewById(R.id.txt_tax);
            txt_net = findViewById(R.id.txt_net);

            // check if there is already some text in UI elements
            if(!txt_gross.getText().toString().matches("") /* and */ &&
                !txt_tax.getText().toString().matches("") /* and */ &&
                !txt_net.getText().toString().matches("")){
                // Not null Not Empty
                txt_gross.setText(R.string.gross);
                txt_tax.setText(R.string.tax);
                txt_net.setText(R.string.net);
            }

            // Display gross taxes and net
            txt_gross.append(" " + Math.round(gross * 100.00)/100.00 + " $");
            txt_tax.append(" " + Math.round(taxes * 100.00)/100.00 + " $" );
            txt_net.append(" " + Math.round(net * 100.00)/100.00 + " $" );

        });
    }

    // MENU
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.about) {
            Intent intent = new Intent(getApplicationContext(), AboutActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
package com.oussama.bmi;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // UI Elements
    private EditText weightInput;
    private EditText heightInput;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        weightInput = findViewById(R.id.weightInput);
        heightInput = findViewById(R.id.heightInput);
        resultText = findViewById(R.id.resultText);
        Button calculateButton = findViewById(R.id.calculateButton);

        // Set onClickListener for calculate button
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });
    }

    // Method to calculate BMI
    private void calculateBMI() {
        String weightStr = weightInput.getText().toString();
        String heightStr = heightInput.getText().toString();

        // Input validation
        if (TextUtils.isEmpty(weightStr) || TextUtils.isEmpty(heightStr)) {
            Toast.makeText(this, "Please enter both weight and height", Toast.LENGTH_SHORT).show();
            return;
        }

        float weight = Float.parseFloat(weightStr);
        float height = Float.parseFloat(heightStr);

        // Ensure height is not zero to avoid division by zero
        if (height == 0) {
            Toast.makeText(this, "Height cannot be zero", Toast.LENGTH_SHORT).show();
            return;
        }

        // Calculate BMI
        float bmi = weight / (height * height);

        // Interpret and display BMI result
        String bmiResult = interpretBMI(bmi);
        resultText.setText("Your BMI is: " + bmi + "\n" + bmiResult);
    }

    // Method to interpret the BMI result
    private String interpretBMI(float bmi) {
        if (bmi < 18.5) {
            return "You are underweight.";
        } else if (bmi >= 18.5 && bmi <= 24.9) {
            return "You have a normal weight.";
        } else if (bmi >= 25 && bmi <= 29.9) {
            return "You are overweight.";
        } else {
            return "You are obese.";
        }
    }
}

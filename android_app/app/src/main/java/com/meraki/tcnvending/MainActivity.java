package com.meraki.tcnvending;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;
import android.view.View;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScrollView scrollView = new ScrollView(this);
        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.setPadding(32, 32, 32, 32);

        TextView title = new TextView(this);
        title.setText("Kiosco Meraki Smart Vending TCN v9.0");
        title.setTextSize(22);
        mainLayout.addView(title);

        TextView status = new TextView(this);
        status.setText("Estado:\n- Zona 1: 4.2 °C\n- Zona 2: 14.0 °C\n- Puerto: /dev/ttyS1 a 9600 bps");
        status.setTextSize(16);
        status.setPadding(0, 16, 0, 16);
        mainLayout.addView(status);

        String[] paymentMethods = {"Yape", "Plin", "Mercado Pago", "Culqi", "POS NFC"};

        for (int slot = 11; slot <= 32; slot++) {
            final int slotNum = slot;
            TextView slotLabel = new TextView(this);
            slotLabel.setText("Ranura " + slotNum + ":");
            mainLayout.addView(slotLabel);

            LinearLayout row = new LinearLayout(this);
            row.setOrientation(LinearLayout.HORIZONTAL);

            for (final String method : paymentMethods) {
                Button btn = new Button(this);
                btn.setText(method);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        reqShip(slotNum, method);
                    }
                });
                row.addView(btn);
            }
            mainLayout.addView(row);
        }

        Button techButton = new Button(this);
        techButton.setText("Panel Técnico Oculto (PIN 2580)");
        techButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Panel Técnico Oculto abierto con PIN 2580", Toast.LENGTH_SHORT).show();
            }
        });
        mainLayout.addView(techButton);

        scrollView.addView(mainLayout);
        setContentView(scrollView);
    }

    private void reqShip(int slot, String paymentMethod) {
        Toast.makeText(this, "Dispensando ranura " + slot + " con " + paymentMethod + " (reqShip)", Toast.LENGTH_SHORT).show();
    }
}

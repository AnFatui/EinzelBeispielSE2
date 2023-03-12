package com.example.einzelbeispiel;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;
import com.example.einzelbeispiel.TCP.Network;
import com.example.einzelbeispiel.calculation.CalculateCheckSum;

    public class MainActivity extends AppCompatActivity {
        NetworkRunnable n;
        Thread t2;


        @SuppressLint("WrongViewCast")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            newMain();





        }
        public void newMain(){
            EditText numberInput = findViewById(R.id.editTextNumber);
            TextView resultField = findViewById(R.id.textView2);

            InputFilter[] filterArray = new InputFilter[1];
            filterArray[0] = new InputFilter.LengthFilter(8);
            numberInput.setFilters(filterArray);
            numberInput.setOnKeyListener((v, keyCode, event) -> {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP) {
                    System.out.println("Started");
                    String input = numberInput.getText().toString();
                    int converted =Integer.parseInt(input);
                    n = new NetworkRunnable(converted);
                    t2 = new Thread(n);
                    t2.start();
                    try{
                        t2.join();

                    }catch(InterruptedException ie){

                    }
                    resultField.setText(n.getResult());
                    System.out.println("Bidding done" );
                    return true;
                }
                return false;
            });


        }



        class NetworkRunnable implements Runnable{
            int numberInput;
            String result = "";
            public NetworkRunnable(int number){
                this.numberInput = number;
            };

            public String getResult(){
                return result;
            }

            @Override
            public void run() {
                Network n = new Network();
                n.doBidding(numberInput);
                CalculateCheckSum ccm = new CalculateCheckSum();
                result = n.getModifiedSentence() +"\n" + ccm.alternatingChecksums(numberInput);
                System.out.println(n.getModifiedSentence() + " " + result );

            }
        }

    }





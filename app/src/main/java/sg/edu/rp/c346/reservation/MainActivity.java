package sg.edu.rp.c346.reservation;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

   EditText etn, etph,etpa,etDate,etTime;
   CheckBox cbs;
   Button bc,br;
   int theyear,themonth,theday, thehour,theminute;
   Calendar currentTime, currentDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etn = findViewById(R.id.editTextName);
        etph = findViewById(R.id.editTextPhone);
        etpa = findViewById(R.id.editTextPax);
        cbs = findViewById(R.id.checkBoxSmoking);
        bc = findViewById(R.id.buttonConfirm);
        br = findViewById(R.id.buttonReset);
        etDate=findViewById(R.id.editTextDate);
        etTime=findViewById(R.id.editTextTime);

        currentDate    = Calendar.getInstance();
        theyear = currentDate.get(Calendar.YEAR);
        themonth = currentDate.get(Calendar.MONTH);
        theday = currentDate.get(Calendar.DAY_OF_MONTH);

        currentTime    = Calendar.getInstance();
        thehour                = currentTime.get(Calendar.HOUR_OF_DAY) ;
        theminute              = currentTime.get(Calendar.MINUTE) ;

        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        etDate.setText("Date: "+ dayOfMonth +"/" + (monthOfYear+1) + "/" + year);
                        theyear = year;
                        themonth = monthOfYear;
                        theday = dayOfMonth;
                    }
                };
                //Create the Date Picker Dialog\

                DatePickerDialog myDateDialog = new DatePickerDialog(MainActivity.this,
                        myDateListener,theyear,themonth,theday);
                myDateDialog.show();
            }
        });

        etTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog.OnTimeSetListener myTimeListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {

                        etTime.setText("Time: "+hourOfDay+ ":" + minute);
                        thehour = hourOfDay;
                        theminute = minute;
                    }
                };
                //Create the time picker dailog

                TimePickerDialog myTimeDialog = new TimePickerDialog(MainActivity.this,
                        myTimeListener,thehour ,theminute,true);
                myTimeDialog.show();
            }
        });



        bc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name =  etn.getText().toString().trim();
                String ph = etph.getText().toString().trim();
                String pax = etpa.getText().toString().trim();
                String date = etDate.getText().toString().trim();
                String time = etTime.getText().toString().trim();

                if(name.length()!=0 || ph.length()!=0 || pax.length()!=0 || date.length()!=0||time.length()!=0) {
                    String status = "";
                    if (cbs.isChecked()) {
                        status = "Yes";
                    } else {
                        status = "No";
                    }

                    String output = String.format("New Reservation \nName: %s\nSmoking: %s\nSize: %s\nDate %s\nTime: %s\n", name, status,pax,date,time);

                    AlertDialog.Builder myBuilder = new AlertDialog.Builder(MainActivity.this);
                    myBuilder.setTitle("Confirm Your Order");
                    myBuilder.setMessage(output);
                    myBuilder.setCancelable(false);
                    myBuilder.setPositiveButton("CONFIRM", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText( MainActivity.this,"Your Reservation has been confirm",Toast.LENGTH_LONG).show();
                        }
                    });

                    myBuilder.setNeutralButton("Cancel",null);

                    AlertDialog myDialog = myBuilder.create();
                    myDialog.show();

                }else {
                    Toast.makeText( MainActivity.this,"Please ensure all info are fill up!",Toast.LENGTH_LONG).show();
                }
            }
        });

        br.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etn.setText(null);
                etph.setText(null);
                etpa.setText(null);
                cbs.setChecked(false);
                etDate.setText(null);
                etTime.setText(null);
                theyear = currentDate.get(Calendar.YEAR);
                themonth = currentDate.get(Calendar.MONTH);
                theday = currentDate.get(Calendar.DAY_OF_MONTH);
                thehour = currentTime.get(Calendar.HOUR_OF_DAY) ;
                theminute = currentTime.get(Calendar.MINUTE) ;

            }
        });


    }
}

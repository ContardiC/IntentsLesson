package it.spacecoding.testintent;

import android.content.Intent;
import java.util.Calendar;
import android.provider.CalendarContract;

import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button btnSendSMS;
    Button btnDial;
    Button btnSetAlarm;
    Button btnSetEvent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnSendSMS = findViewById(R.id.btn_send_sms);
        btnDial = findViewById(R.id.btn_dial);
        btnSetAlarm = findViewById(R.id.btn_set_alarm);
        btnSetEvent = findViewById(R.id.btn_set_event);
        btnSendSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MainActivity", "Button SendSMS clicked");
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("sms: +39 23112345"));
                intent.putExtra("sms_body","Ciao Gino arrivo appena finisco");
                if(intent.resolveActivity(getPackageManager()) != null){
                    startActivity(intent);
                     Log.d("MainActivity", "SendSMS avviato");
                }
            }
        });
        btnDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MainActivity", "Button Dial clicked");
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel: +39 21323456"));
                if(intent.resolveActivity(getPackageManager())!=null){
                    startActivity(intent);
                }else{
                    Log.d("MainActivity", "Nessun app disponibile per questo intent");
                }
            }
        });
        btnSetAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MainActiovity", "Button set alarm clicked");
                Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
                intent.putExtra(AlarmClock.EXTRA_MESSAGE, "Andare a casa ");
                intent.putExtra(AlarmClock.EXTRA_HOUR, 14);
                intent.putExtra(AlarmClock.EXTRA_MINUTES, 00);

                if(intent.resolveActivity(getPackageManager()) != null){
                    startActivity(intent);
                }else{
                    //TODO: gestire
                }
            }
        });
        btnSetEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MainActivity", "Button Set Event Clicke");
                Calendar beginTime = Calendar.getInstance();
                beginTime.set(2026, Calendar.NOVEMBER, 3, 15,0 );
                Calendar endTime = Calendar.getInstance();
                endTime.set(2026, Calendar.NOVEMBER, 3, 17 ,0 );

                Intent intent = new Intent(Intent.ACTION_INSERT);
                intent.setData(CalendarContract.Events.CONTENT_URI);
                intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis());
                intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis());
                intent.putExtra(CalendarContract.Events.TITLE, "Riunione al Mc");
                intent.putExtra(CalendarContract.Events.DESCRIPTION, "Salutare Paini e Sacchi");
                intent.putExtra(CalendarContract.Events.ACCESS_LEVEL, CalendarContract.Events.ACCESS_PRIVATE);



                if(intent.resolveActivity(getPackageManager())!= null){
                    startActivity(intent);// TODO: gestire
                }else{

                }
            }
        });
    }
}
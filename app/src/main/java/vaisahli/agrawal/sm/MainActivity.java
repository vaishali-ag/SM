package vaisahli.agrawal.sm;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Button Save;
    EditText note,location;
    Switch Time,StickeyNote,Weather,Notification,News,Calendar;
    RadioButton analog,digital;
    CheckBox day,event,date,call,msg;
    RadioGroup rGrup;

    public String timeID="0";
    public String analogId="0";
    public String digitalId="0";
    public String calendarId="0";
    public String dayId="0";
    public String dateId="0";
    public String eventId="0";
    public String newsId="0";
    public String msgId="0";
    public String callId="0";
    public String nOtifyId="0";
    public String Todo="";
    public String weathId="0";
    public String noteId="0";
    public String weatherLocation="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Save = findViewById(R.id.Save);
        note = findViewById(R.id.Task);
        Time = findViewById(R.id.Time);
        Calendar = findViewById(R.id.Cal);
        StickeyNote = findViewById(R.id.Stickey_note);
        Weather = findViewById(R.id.Weather);
        News = findViewById(R.id.News);
        Notification = findViewById(R.id.Notification);
        location = findViewById(R.id.location);
        analog = findViewById(R.id.radioButton);
        digital = findViewById(R.id.radioButton2);
        date = findViewById(R.id.Date);
        day=findViewById(R.id.Day);
        event = findViewById(R.id.Event);
        call = findViewById(R.id.Call);
        msg = findViewById(R.id.Msg);
        rGrup = findViewById(R.id.radiogrup);

        Time.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(Time.isChecked()) {

                    analog.setEnabled(true);
                    digital.setEnabled(true);
                    timeID="1";


                }
                else
                {
                    analog.setEnabled(false);
                    digital.setEnabled(false);
                    timeID="0";
                }
                rGrup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        if(checkedId==R.id.radioButton)
                        {
                            analogId="1";
                            digitalId="0";

                        }
                        else if (checkedId == R.id.radioButton2)
                        {
                            analogId="0";
                            digitalId="1";
                        }

                    }
                });
            }
        });

//calendar switch disable
        Calendar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (Calendar.isChecked())
                {
                    date.setEnabled(true);
                    day.setEnabled(true);
                    event.setEnabled(true);
                    calendarId="1";

                }
                else
                {
                    date.setEnabled(false);
                    day.setEnabled(false);
                    event.setEnabled(false);
                    calendarId="0";

                }
                if(date.isChecked())
                {
                    dateId="1";
                }
                else
                {
                    dateId="0";
                }
                if(day.isChecked())
                {
                    dayId="1";
                }
                else
                {
                    dayId="0";
                }
                if(event.isChecked())
                {
                    eventId="1";
                }
                else
                {
                    eventId="0";
                }
            }
        });
        //WEATHER DISABLE
        Weather.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (Weather.isChecked()) {

                    location.setEnabled(true);
                    weathId="1";
                }
                else
                {
                    location.setEnabled(false);
                    weathId="0";
                }
                weatherLocation=location.getText().toString().trim();



            }
        });
        //to do list
        StickeyNote.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(StickeyNote.isChecked()) {

                    note.setEnabled(true);
                    noteId="1";
                }
                else{
                    note.setEnabled(false);
                    noteId="0";
                }
                Todo=note.getText().toString().trim();


            }
        });

        //notification
        Notification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(Notification.isChecked())
                {
                    call.setEnabled(true);
                    msg.setEnabled(true);
                    nOtifyId="1";

                }
                else
                {   call.setEnabled(false);
                    msg.setEnabled(false);
                    nOtifyId="0";
                }
                if(call.isChecked())
                {
                    callId="1";
                }
                else
                {
                    callId="0";
                }
                if(msg.isChecked())
                {
                    msgId="1";
                }
                else
                {
                    msgId="0";
                }
            }
        });

        //news
        News.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(News.isChecked())
                    newsId="1";
                else
                    newsId="0";
            }
        });




        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               doGet();



            }
        });
    }



    public void doGet()
    {
        Toast.makeText(MainActivity.this, "Running", Toast.LENGTH_SHORT).show();
        RequestQueue getQueue = Volley.newRequestQueue(this);
        String url = "http://spiculate-dachshund-5820.dataplicity.io/cgi-bin/index.py";
        StringRequest getRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        //Log.d("Error.Response", response);
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("timeid",timeID);
                params.put("analogid",analogId);
                params.put("digitalid",digitalId);
                params.put("calendarid",calendarId);
                params.put("dateid",dateId);
                params.put("dayid",dayId);
                params.put("eventid",eventId);
                params.put("weatherid",weathId);
                params.put("weatherlocation",weatherLocation);
                params.put("todolistid",noteId);
                params.put("todonote",Todo);
                params.put("notificationid",nOtifyId);
                params.put("callid",callId);
                params.put("msgid",msgId);
                params.put("newsid",newsId);
                return params;

            }
        };
        getQueue.add(getRequest);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences sharedPreferences=getSharedPreferences("vaisahli.agrawal.smartmirror",MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();

        editor.putString("note",note.getText().toString().trim());
        editor.putString("weather",location.getText().toString().trim());
        editor.putBoolean("Time",Time.isChecked());
        editor.putBoolean("bool",true);


        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences("vaisahli.agrawal.smartmirror", MODE_PRIVATE);
        String s1=sharedPreferences.getString("note","");
        Boolean remember=sharedPreferences.getBoolean("bool",false);
        note.setText(s1);

        String s2=sharedPreferences.getString("weather","");
        Boolean re=sharedPreferences.getBoolean("bool",false);
        location.setText(s2);
        Boolean b1=  sharedPreferences.getBoolean("Time", Boolean.parseBoolean(null));
        Time.setEnabled(b1);
//HELLOIDF

    }

}


package com.forward.slash.duolingojournal;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int defaultValue = getResources().getInteger(R.integer.default_time);
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        int time = sharedPref.getInt(getString(R.string.saved_time), defaultValue);


        final EditText editTextHour = (EditText)findViewById(R.id.edit_text_hour);
        final EditText editTextMinute = (EditText)findViewById(R.id.edit_text_minute);
        final EditText editTextMinuteResult = (EditText)findViewById(R.id.edit_text_minute_result);
        final TextView minuteTextView = (TextView)findViewById(R.id.minute_text_view);


        minuteTextView.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                editTextMinuteResult.setText(minuteTextView.getText().toString());
            }
        });

        //[0]: focus state of minute result text view
        //[1]: foucus state of edit Text Hour
        //[2]: foucus state of edit Text Minute
        final String focusedView[] = new String[]{"false", "false", "false"};

        editTextMinuteResult.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus){
                if(hasFocus){
                    focusedView[0] = "true";
                } else {
                    focusedView[0]  = "false";
                }
            }
        });
        editTextHour.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus){
                if(hasFocus){
                    focusedView[1] = "true";
                } else {
                    focusedView[1]  = "false";
                }
            }
        });
        editTextMinute.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus){
                if(hasFocus){
                    focusedView[2] = "true";
                } else {
                    focusedView[2]  = "false";
                }
            }
        });

        editTextHour.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
//                // TODO Auto-generated method stub
//                Toast.makeText(getApplicationContext(), "after text changed",
//                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                // TODO Auto-generated method stub
//                Toast.makeText(getApplicationContext(), "before text changed",
//                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String hh = editTextHour.getText().toString();
                String mm = editTextMinute.getText().toString();
                boolean focused = Boolean.parseBoolean(focusedView[1]); //true if focused on edit text hour
                if (Utility.tryParseInt(hh) && Utility.tryParseInt(mm) && focused) {
                    int hour = Integer.parseInt(hh);
                    int minute = Integer.parseInt(mm);
                    int resultMinute = Utility.hhmmtomm(hour, minute);
                    editTextMinuteResult.setText(Integer.toString(resultMinute));
                }
            }
        });

        editTextMinute.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
//                // TODO Auto-generated method stub
//                Toast.makeText(getApplicationContext(), "after text changed",
//                        Toast.LENGTH_LONG).show();
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                // TODO Auto-generated method stub
//                Toast.makeText(getApplicationContext(), "before text changed",
//                        Toast.LENGTH_LONG).show();
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String hh = editTextHour.getText().toString();
                String mm = editTextMinute.getText().toString();
                boolean focused = Boolean.parseBoolean(focusedView[2]); //true if focused on textViewMinuteResult
                if (Utility.tryParseInt(hh) && Utility.tryParseInt(mm) && focused) {
                    int hour = Integer.parseInt(hh);
                    int minute = Integer.parseInt(mm);
                    int resultMinute = Utility.hhmmtomm(hour,minute);
                    editTextMinuteResult.setText(Integer.toString(resultMinute));
                }
            }
        });

        editTextMinuteResult.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
//                Toast.makeText(getApplicationContext(), "after text changed",
//                        Toast.LENGTH_LONG).show();
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
//                Toast.makeText(getApplicationContext(), "before text changed",
//                        Toast.LENGTH_LONG).show();
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String mm = editTextMinuteResult.getText().toString();
                boolean focused = Boolean.parseBoolean(focusedView[0]);
                boolean allNotFocused = Utility.isAllFalse(focusedView); // true if non of the view is focused
                if (Utility.tryParseInt(mm) && (focused || allNotFocused)) {
                    int minute = Integer.parseInt(mm);
                    int resultHour = Utility.mmtohh(minute);
                    int resultMinuteResidual = Utility.mmtommResidual(minute);
                    editTextHour.setText(Integer.toString(resultHour));
                    editTextMinute.setText(Integer.toString(resultMinuteResidual));
                }
            }
        });
        updateTime(time);
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        View view = super.onCreateView(parent, name, context, attrs);

        return view;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void updateTime(int minute) {
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(getString(R.string.saved_time), minute);
        editor.commit();

        TextView minuteTextView = (TextView)findViewById(R.id.minute_text_view);
        minuteTextView.setText(Integer.toString(minute));
    }

    public void addXP (View view) {
        EditText XPEditText = (EditText)this.findViewById(R.id.XP_edit_text);
        String XP = XPEditText.getText().toString();
        if (Utility.tryParseInt(XP)) {
            int receivedMinute = XPToMinute(Integer.parseInt(XP));

            int defaultValue = getResources().getInteger(R.integer.default_time);
            SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
            int originalTime = sharedPref.getInt(getString(R.string.saved_time), defaultValue);

            updateTime(originalTime + receivedMinute);
            XPEditText.setText("");
        }
    }

    public int XPToMinute(int XP) {
        return (int) (XP * 1.5 + 0.5);
    }

    public void subtractMinute (View view) {
        EditText timePlayedEditText = (EditText)this.findViewById(R.id.time_played_edit_text);
        String playedTimeString = timePlayedEditText.getText().toString();
        if (Utility.tryParseInt(playedTimeString)) {
            int playedTime = Integer.parseInt(playedTimeString);
            int defaultValue = getResources().getInteger(R.integer.default_time);
            SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
            int originalTime = sharedPref.getInt(getString(R.string.saved_time), defaultValue);

            updateTime(originalTime - playedTime);
            timePlayedEditText.setText("");
        }
    }

    public void remind (View view) {
        Toast.makeText(getApplicationContext(), "Không lo học mà nghịch gì đấy!",
                Toast.LENGTH_LONG).show();
    }
}

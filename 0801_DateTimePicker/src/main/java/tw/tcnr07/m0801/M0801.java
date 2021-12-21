package tw.tcnr07.m0801;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class M0801 extends AppCompatActivity  implements View.OnClickListener {

    private DatePicker mdate;
    private TimePicker mtime;
    private Button b001;
    private TextView uselect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m0801);
        setupViewcomponent();
    }

    private void setupViewcomponent() {
        uselect = (TextView) findViewById(R.id.m0801_t001);
        mdate = (DatePicker) findViewById(R.id.m0801_date01);
        mtime = (TimePicker) findViewById(R.id.m0801_time01);
        b001 = (Button) findViewById(R.id.m0801_b001);
        b001.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String s = getString(R.string.m0801_t001);
        String ss = Convert24to12(mtime.getHour() + ":" + mtime.getMinute() + ":00");
        uselect.setText(s + "\n" +
                mdate.getYear() + "年" +
                (mdate.getMonth() + 1) + "月" +
                mdate.getDayOfMonth() + "日" + "\n" +ss);
//                mtime.getHour() + "時" +
//                mtime.getMinute() + "分");

    }

    private String Convert24to12(String time) {
        String cTime = "";
        try {
            SimpleDateFormat it = new SimpleDateFormat("HH:mm:ss");
            SimpleDateFormat ot = new SimpleDateFormat("hh:mm a");
            Date dd = it.parse(time);
            cTime = ot.format(dd);
        } catch (final ParseException e) {
            e.printStackTrace();
        }
        return cTime;
    }
}
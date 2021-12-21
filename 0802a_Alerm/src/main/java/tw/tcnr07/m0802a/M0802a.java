package tw.tcnr07.m0802a;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.CollationElementIterator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class M0802a extends AppCompatActivity  implements View.OnClickListener {

    private DatePicker mdate;
    private TimePicker mtime;
    private Button b001;
    private TextView ans01;
    private TextView text001;
    private MediaPlayer startmusic;
    private DatePicker date01;
    private TimePicker time01;
    private int years01;
    private int months01;
    private Calendar cg;
    private int dates01;
    private int hours01;
    private TextView time;
    private int minius01;
    private long endTime;
    private Handler handler=new Handler();
    private long spenttime;
    private long hours;
    private long mins;
    private long seconds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m0802a);
        setupViewcomponent();
    }

    private void setupViewcomponent() {
        time=(TextView)findViewById(R.id.m0802_timer);
        ans01 = (TextView) findViewById(R.id.m0802_ans01);
        date01 = (DatePicker) findViewById(R.id.m0802_date01);
        time01 = (TimePicker) findViewById(R.id.m0802_time01);
        b001 = (Button) findViewById(R.id.m0802_b001);
        text001=(TextView)findViewById(R.id.m0802_t001);


        startmusic= MediaPlayer.create(M0802a.this, R.raw.s01);

        b001.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String s = getString(R.string.m0802a_t001);
        years01 = date01.getYear();//取得畫面的"年"
        months01 = date01.getMonth();//取得畫面的"月"
        dates01 = date01.getDayOfMonth();//取得畫面的"日"
        hours01 = time01.getHour();// 取得畫面的"小時"
        minius01 = time01.getMinute();// 取得畫面的"分鐘"

        // 顯示選擇的日期和時間
        ans01.setText(s +
                years01 + getString(R.string.n_yy) +
                (months01 + 1) + getString(R.string.n_mm)     +
                dates01 + getString(R.string.m_dd) +
                hours01 + getString(R.string.d_hh) +
                minius01      + getString(R.string.d_mm));
        //--------------------------------------
            cg= Calendar.getInstance();
            cg.set(years01, months01, dates01, hours01, minius01);
            endTime=cg.getTimeInMillis();

            handler.postDelayed(updateTimer, 100);
    }


    private Runnable updateTimer=new Runnable() {
        @Override
        public void run() {
            //經過時間
            spenttime=endTime-System.currentTimeMillis();
            hours=(spenttime/1000)/60/60;
            mins=(spenttime/1000)/60%60;
            seconds=(spenttime/1000)%60;

            if(spenttime<0 || hours>999){
                Toast.makeText(getApplication(), getString(R.string.m0802_err), Toast.LENGTH_LONG).show();
                time.setText(R.string.m0802_timer);
                text001.setText(R.string.m0802a_t001);
                handler.removeCallbacks(updateTimer);
            }else{
                text001.setText(R.string.m0802_start);
                music_set(); //音樂重設
                time.setText(String.format("%02d", hours)+":"+String.format("%02d", mins)+":"+String.format("%02d", seconds));
                handler.postDelayed(this, 1000);

                if(hours==0 && mins==0 && seconds==0){
                    startmusic.start();
                    text001.setText(R.string.m0802_play);
                    handler.removeCallbacks(updateTimer);
                }

            }
        }
    };

    private void music_set() {
        if(startmusic!=null&&startmusic.isPlaying()) startmusic.stop();
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
    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        Toast.makeText(getApplication(), "禁用返回鍵", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
//        handler.removeCallbacks(updateTime);
        this.finish();
    }
}
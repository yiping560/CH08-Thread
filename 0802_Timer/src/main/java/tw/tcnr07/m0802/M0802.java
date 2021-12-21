package tw.tcnr07.m0802;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

public class M0802 extends AppCompatActivity {

    private TextView t001;
    private long startTime;
    private long sptime;
    private Handler handler=new Handler();
    private long mins;
    private long seconds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m0802);
        setupViewcomponent();
    }

    private void setupViewcomponent() {

        t001=(TextView)findViewById(R.id.m0802_timer);
        startTime=System.currentTimeMillis();  //取得目前手機時間
        t001.setText(System.currentTimeMillis()/1000+"");
        handler.postDelayed(updateTime, 100);  //執行緒
    }



    private Runnable updateTime=new Runnable() {  //工作命令單，執行要做的內容
        @Override
        public void run() {
            //經過時間
            sptime=System.currentTimeMillis()-startTime;
            mins=(sptime/1000)/60;
            seconds=(sptime/1000)%60;
            t001.setText(String.format("%02d", mins)+":"+String.format("%02d", seconds));
            handler.postDelayed(this, 1000);
        }
    };
    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        Toast.makeText(getApplication(), "禁用返回鍵", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        handler.removeCallbacks(updateTime);
        this.finish();
    }
}
package tw.tcnr07.m0803;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.Toast;

public class M0803 extends AppCompatActivity {

    private ProgressBar proBar;
    private Handler mHandler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m0803);
        setupViewcomponent();
    }

    private void setupViewcomponent() {
        proBar=(ProgressBar)findViewById(R.id.m0803_p001);

        DoLengthyWork work=new DoLengthyWork();
        work.setHandler(mHandler);
        work.setProgressBar(proBar);
        work.start();
        work.checkAccess();
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        Toast.makeText(getApplication(), "禁用返回鍵", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onStop() {
        super.onStop();
        this.finish();
    }
}
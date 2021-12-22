package tw.tcnr07.m0804;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.UserHandle;
import android.widget.ImageView;

public class M0804 extends AppCompatActivity {


    private ImageView imgSwi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m0804);
        setupViewcomponent();
    }

    private void setupViewcomponent() {
            imgSwi=(ImageView)findViewById(R.id.m0804_img01);
        UserThread myThread=new UserThread(this);
        myThread.start();
//        myThread.start();
    }
    Handler myHandler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    imgSwi.setImageResource(R.drawable.flow01b);
                    break;
                case 1:
                    imgSwi.setImageResource(R.drawable.flow02b);
                    break;
                case 2:
                    imgSwi.setImageResource(R.drawable.flow03b);
                    break;
                case 3:
                    imgSwi.setImageResource(R.drawable.flow04b);
                    break;
            }

        }
    };
}
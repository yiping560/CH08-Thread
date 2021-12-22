package tw.tcnr07.m0805;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;

public class M0805 extends AppCompatActivity {

    private SeekBar seekBar;
    private TextView t001;
    private RatingBar ratBar;
    private TextView rat01;
    private TextView rat02;
    private Button b001,b002;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m0805);
        setupViewcomponent();
    }

    private void setupViewcomponent() {
        seekBar=(SeekBar)findViewById(R.id.m0805_seekBar);
        t001=(TextView)findViewById(R.id.m0805_t001);
        ratBar=(RatingBar)findViewById(R.id.m0805_ratBar);
        rat01=(TextView)findViewById(R.id.m0805_Rat01);
        rat02=(TextView)findViewById(R.id.m0805_Rat02);
        b001=(Button)findViewById(R.id.m0805_b001);
        b002=(Button)findViewById(R.id.m0805_b002);

        seekBar.setOnSeekBarChangeListener(s001);
        ratBar.setOnRatingBarChangeListener(r001);
    }
    SeekBar.OnSeekBarChangeListener s001=new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            String s = getString(R.string.m0805_SeekBar);
            t001.setText(s+Integer.toString(progress));
            b001.getBackground().setAlpha(progress*255/100);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    RatingBar.OnRatingBarChangeListener r001 = new RatingBar.OnRatingBarChangeListener(){

        @Override
        public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
            String s = getString(R.string.m0805_RatBar1);
            rat01.setText(s + Float.toString(rating));
            s = getString(R.string.m0805_RatBar2);
            rat02.setText(s + Integer.toString(ratBar.getProgress()));
            b002.getBackground().setAlpha(ratBar.getProgress() * 255 / 10);
        }
    };

}
package com.candamlalari.denememusic;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private Context mContext;
    private Activity mActivity;

    private Button mButtonPlay;
    private Button mButtonPause;
    private Button mButtonStop;

    private Button mButtonPlay1;
    private Button mButtonPause1;
    private TextView mPass1;
    private TextView mDuration1;
    private TextView mDue1;
    private SeekBar mSeekBar1;
    private CheckBox yapildi1;

    private SeekBar mSeekBar;

    private TextView mPass;
    private TextView mDuration;
    private TextView mDue;
    private TextView hergun;

    private MediaPlayer mPlayer;
    private Handler mHandler;
    private Runnable mRunnable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the application context
        mContext = getApplicationContext();
        mActivity = MainActivity.this;

        // Get the widget reference from xml layout
        mButtonPlay = findViewById(R.id.btn_baslat);
        mButtonPause = findViewById(R.id.btn_duraklat);
        hergun = (TextView) findViewById(R.id.hergun);
        mButtonStop = findViewById(R.id.btn_durdur);
        mSeekBar = findViewById(R.id.seek_bar);
        mPass = findViewById(R.id.tv_pass);
        mDuration = findViewById(R.id.tv_duration);
        mDue = findViewById(R.id.tv_due);

        yapildi1=(CheckBox)findViewById(R.id.yapildi1);
        mSeekBar1 = findViewById(R.id.seek_bar1);
        mPass1 = findViewById(R.id.tv_pass1);
        mDuration1 = findViewById(R.id.tv_duration1);
        mDue1 = findViewById(R.id.tv_due1);
        mButtonPlay1 = findViewById(R.id.btn_baslat1);
        mButtonPause1 = findViewById(R.id.btn_duraklat1);
        // Initialize the handler
        mHandler = new Handler();
        hergun.setText("Her gün bir kötü huyundan vazgeçsen, her gün bir zaafını, bir erdemle değiştirsen, her yeni gün başka bir yetimin başını okşasan, bir zalime meydan okusan, kendin sürekli fakirleşirken, her geçen gün daha da cömertleşsen, her gün insanların yolları üzerinden bir taşı kaldırsan veyahut bir yeteneğe kendini gerçekleştirmesi için yardım etsen, her gün büyüklerin sözlerinden küçüklere bir şeyler öğretsen, her yeni gün büyük bir adamın bir sözüne hayat versen…\n" +
                "\n" +
                "Ah her sabah kendine bir iyilikler listesi hazırlasan ve her akşam kendini acımasızca sorguya çeksen, o kadar çok sorgulasan ve eleştirsen ki kendini başka hiç kimseye ve hiçbir şeye zaman kalmasa… Öyle meşgul olsan ki nefsini ezmekle, her çekiç darbesiyle ruhuna kıvılcımlar sıçrasa ve tenin nurunu gizleyemeyecek kadar incelse bile farkında olmasan…\n" +
                "\n" +
                "Gözlerin kızarmış okumaktan, alnın kırışmış düşünmekten, bacakların yorulmuş hakikatin yollarında yürümekten, ellerinde çekiçlerden nasırlar, sırtında kamburlar insanlığın acılarından, taliplerin birer birer ümidi kesip uzaklaşırken, yolunu gözleyen çaresizler her geçen gün artmış, ölümün soluğu ensende gezerken, yüz yıl sonra için planların hazırlanmış ve Azrail kabzetmeye geldiğinde canını hala yapılacak işlerin kalmış ve aksakallı başın önüne düşse ama ağır çekicin düşmese elinden…\n" +
                "\n" +
                "İsterse cenazende kimse olmasın, cesedin kimsesizler mezarlığında, isimsiz bir çukura indirilsin, sen ki ölümü yenmiş ve dirisin.\n" +
                "\n" +
                "Rafet Elçi\n");


        // Click listener for playing button
        mButtonPlay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // If media player another instance already running then stop it first
                stopPlaying();

                // Initialize media player
                mPlayer = MediaPlayer.create(mContext,R.raw.h1);

                // Start the media player
                mPlayer.start();
                Toast.makeText(mContext,"Ses kaydı çalınıyor.",Toast.LENGTH_SHORT).show();
                if(yapildi1.isChecked()){Toast.makeText(mContext,"Şükrolsun Efendimin Himmetleriyle Yapıldı.",Toast.LENGTH_SHORT).show();}
                // Get the current audio stats
                getAudioStats();
                // Initialize the seek bar
                initializeSeekBar();

            }
        });

        // Set a click listener for top playing button
        mButtonPause.setOnClickListener(new View.OnClickListener() {
            int i;
            @Override
            public void onClick(View view) {
                i+=1;
                if(i==1){
                    mButtonPause.setText("Devam");
                    // Get the current audio stats
                    getAudioStats();
                    // Initialize the seek bar
                    initializeSeekBar();
                    mPlayer.pause();
                    Toast.makeText(mContext,"Ses kaydı durduruldu.",Toast.LENGTH_SHORT).show();
                }
                if(i==2){
                    mButtonPause.setText("Durdur");
                    // Get the current audio stats
                    getAudioStats();
                    // Initialize the seek bar
                    initializeSeekBar();
                    mPlayer.start();
                    Toast.makeText(mContext,"Ses kaydı devam ediyor.",Toast.LENGTH_SHORT).show();
                    i=0;
                }
            }
        });
        mButtonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });

        mButtonPlay1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                stopPlaying();

                mPlayer = MediaPlayer.create(mContext,R.raw.h2);

                mPlayer.start();
                Toast.makeText(mContext,"Ses kaydı çalınıyor.",Toast.LENGTH_SHORT).show();
                if(yapildi1.isChecked()) {
                 Toast.makeText(mContext,"Şükrolsun Efendimin Himmetleriyle Yapıldı.",Toast.LENGTH_SHORT).show();
                }
                getAudioStats1();
                initializeSeekBar1();

            }
        });

        mButtonPause1.setOnClickListener(new View.OnClickListener() {
            int i;
            @Override
            public void onClick(View view) {
                i+=1;
                if(i==1){
                    mButtonPause1.setText("Devam");
                    // Get the current audio stats
                    getAudioStats1();
                    // Initialize the seek bar
                    initializeSeekBar1();
                    mPlayer.pause();
                    Toast.makeText(mContext,"Ses kaydı durduruldu.",Toast.LENGTH_SHORT).show();
                }
                if(i==2){
                    mButtonPause1.setText("Durdur");
                    // Get the current audio stats
                    getAudioStats1();
                    // Initialize the seek bar
                    initializeSeekBar1();
                    mPlayer.start();
                    Toast.makeText(mContext,"Ses kaydı devam ediyor.",Toast.LENGTH_SHORT).show();
                    i=0;
                }
            }
        });


        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(mPlayer!=null && b){

              mPlayer.seekTo(i*1000);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    protected void stopPlaying(){
        // If media player is not null then try to stop it
        if(mPlayer!=null){
            mPlayer.stop();
            mPlayer.release();
            mPlayer = null;
            if(mHandler!=null){
                mHandler.removeCallbacks(mRunnable);
            }
        }
    }



    protected void getAudioStats(){
        int duration  = mPlayer.getDuration()/1000; // In milliseconds
        int due = (mPlayer.getDuration() - mPlayer.getCurrentPosition())/1000;
        int pass = duration - due;

        mPass.setText("" + pass + " saniye");
        mDuration.setText("" + duration + " saniye");
        mDue.setText("" + due + " saniye");
    }

    protected void initializeSeekBar(){
        mSeekBar.setMax(mPlayer.getDuration()/1000);

        mRunnable = new Runnable() {
            @Override
            public void run() {
                if(mPlayer!=null){
                    int mCurrentPosition = mPlayer.getCurrentPosition()/1000; // In milliseconds
                    mSeekBar.setProgress(mCurrentPosition);
                    getAudioStats();
                }
                mHandler.postDelayed(mRunnable,1000);
            }
        };
        mHandler.postDelayed(mRunnable,1000);
    }


    protected void getAudioStats1(){
        int duration1  = mPlayer.getDuration()/1000; // In milliseconds
        int due1 = (mPlayer.getDuration() - mPlayer.getCurrentPosition())/1000;
        int pass1 = duration1 - due1;

        mPass1.setText("" + pass1 + " saniye");
        mDuration1.setText("" + duration1 + " saniye");
        mDue1.setText("" + due1 + " saniye");
    }
    protected void initializeSeekBar1(){
        mSeekBar1.setMax(mPlayer.getDuration()/1000);

        mRunnable = new Runnable() {
            @Override
            public void run() {
                if(mPlayer!=null){
                    int mCurrentPosition = mPlayer.getCurrentPosition()/1000; // In milliseconds
                    mSeekBar1.setProgress(mCurrentPosition);
                    getAudioStats1();
                }
                mHandler.postDelayed(mRunnable,1000);
            }
        };
        mHandler.postDelayed(mRunnable,1000);

    }
}
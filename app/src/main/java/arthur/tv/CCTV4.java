package arthur.tv;

import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import static com.google.android.exoplayer2.SimpleExoPlayer.*;

public class CCTV4 extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cctv4);
        //创建默认的TrackSelector
        //创建带宽
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        //创建轨道选择工厂
        TrackSelection.Factory videoTrackSelectionFactory =
                new AdaptiveTrackSelection.Factory(bandwidthMeter);
        //实例化轨道选择器
        TrackSelector trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);
        //实例化播放器player
        SimpleExoPlayer player = ExoPlayerFactory.newSimpleInstance(this,trackSelector);
        //实例化playerView
        PlayerView playerView = findViewById(R.id.cctv4_playerView);
        //将播放器player附加到View上
        playerView.setPlayer(player);

        /**准备播放器
         * 在ExoPlayer中，每个media都由MediaSource表示。要播放一段media，
         * 您必须先创建一个相应的MediaSource，然后将该对象传递给ExoPlayer.prepare。
         */
        //在播放期间测量带宽。如果不需要，则设为null
        DefaultBandwidthMeter bandwidthMeter1 = new DefaultBandwidthMeter();
        //实例化用于加载媒体数据的DataSource
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(
                this,Util.getUserAgent(this,"CCTV4"),bandwidthMeter1);
        //这是要播放媒体的MediaSource
        MediaSource videoSource = new HlsMediaSource.Factory(dataSourceFactory).
                createMediaSource(Uri.parse("http://ivi.bupt.edu.cn/hls/cctv4.m3u8"));
        //准备要播放的资源
        player.prepare(videoSource);
        //开始播放
        player.setPlayWhenReady(true);


    }
        //退出时释放播放器
//        @Override
//        protected void onDestroy(){
//            super.onDestroy();
//            if (player != null){
//                player.release();
//            }
//        }

        //离开界面时暂停播放
//    @Override
//    protected void onPause(){
//        super.onPause();
//        if (player != null){
//            player.setPlayWhenReady(false);
//        }
//    }
}

package arthur.tv;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView movieList;
    private MovieListAdapter movieAdapter;
    private List<movieDataList> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        movieList = findViewById(R.id.movieList);
        movieAdapter = new MovieListAdapter(this.data, (view, position) -> {
            Log.i("DDTV", "Clicked "+view+" on "+position);
            if (position < data.size()){
                movieDataList dataList = data.get(position);
                Intent intent = new Intent(MainActivity.this,PlayerInterface.class);
                intent.putExtra("dataList",dataList);
                startActivity(intent);
            }else {
                Toast.makeText(MainActivity.this, "无效的频道",Toast.LENGTH_SHORT).show();
            }
        });
        movieList.setLayoutManager(new LinearLayoutManager(this));
        movieList.setAdapter(movieAdapter);
        movieList.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
    }
    private void initData() {
        MovieLab lab = new MovieLab(this);
        this.data = lab.getChannels("data.json");
    }
}

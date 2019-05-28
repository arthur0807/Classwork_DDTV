package arthur.tv;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView movieList;
    private MovieListAdapter movieAdapter;
    private List<movieDataList> data;
    FloatingActionButton floatingActionButton;
    BottomAppBar bottomAppBar;
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
        floatingActionButton = findViewById(R.id.fab);
        bottomAppBar = findViewById(R.id.bottomAppBar);
        setSupportActionBar(bottomAppBar);

        Log.e("test",String.valueOf(getTitle()));

        floatingActionButton.setOnClickListener(v -> Toast.makeText(MainActivity.this,"174020300康乔",Toast.LENGTH_LONG).show());
    }
    private void initData() {
        MovieLab lab = new MovieLab(this);
        this.data = lab.getChannels("data.json");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.bottom_app_bar,menu);
        return true;
    }
}

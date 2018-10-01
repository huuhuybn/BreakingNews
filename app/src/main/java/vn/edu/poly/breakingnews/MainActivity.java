package vn.edu.poly.breakingnews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.edu.poly.breakingnews.adapter.AdapterNewspaper;
import vn.edu.poly.breakingnews.model.Newspaper;

public class MainActivity extends AppCompatActivity {

    private RecyclerView lvListNewspaper;

    private AdapterNewspaper adapterNewspaper;
    private List<Newspaper> newspapers;
    private LinearLayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvListNewspaper = findViewById(R.id.lvListNewspaper);
        // tao data

        newspapers = new ArrayList<>();
        Newspaper vnexpress = new Newspaper("Vnexpress","https://vnexpress.net","http://docbao.vn/rss/export/home.rss");
        Newspaper dantri = new Newspaper("Dantri","https://dantri.com.vn","http://docbao.vn/rss/export/home.rss");
        Newspaper vietnamnet = new Newspaper("VietnamNet","https://vietnamnet.com","http://vietnamnet.vn/rss/home.rss");

        newspapers.add(vnexpress);
        newspapers.add(dantri);
        newspapers.add(vietnamnet);
        // khai bao

        adapterNewspaper = new AdapterNewspaper(this,newspapers);
        layoutManager = new LinearLayoutManager(this);
        lvListNewspaper.setAdapter(adapterNewspaper);
        lvListNewspaper.setLayoutManager(layoutManager);




    }
}

package id.ac.ukdw.recycleview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView listFilm;
    private FilmAdapter filmAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listFilm = (RecyclerView)findViewById(R.id.listFilm);
        List<Film> dataFim = new ArrayList<Film>();
        filmAdapter = new FilmAdapter(dataFim, this);

        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        listFilm.setLayoutManager(lm);
        listFilm.setItemAnimator(new DefaultItemAnimator());
        listFilm.setAdapter(filmAdapter);

        dataFim.add(new Film("Iron Man 3 (2014)", "Action", "Robert Downey Jr."));
        dataFim.add(new Film("Suicied Squad (2017)", "Action", "Will Smith"));
        dataFim.add(new Film("IT (2017)", "Horor", "Badut Sekaten"));
        dataFim.add(new Film("Despicable Me (2017)", "Animation", "Minion"));
        filmAdapter.notifyDataSetChanged();
    }
}

package id.ac.ukdw.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by mahasiswa on 9/18/17.
 */

public class FilmAdapter extends RecyclerView.Adapter <FilmAdapter.FilmHolder> {
    private List<Film> listFilm;
    private Context mContext;

    public FilmAdapter(List<Film> listFilm, Context mContext){
        this.listFilm = listFilm;
        this.mContext = mContext;
    }

    public class FilmHolder extends RecyclerView.ViewHolder{
        private TextView txtTitle, txtGenre, txtActor;
        public FilmHolder(View itemView) {
            super(itemView);

            txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
            txtGenre = (TextView) itemView.findViewById(R.id.txtGenre);
            txtActor = (TextView) itemView.findViewById(R.id.txtActor);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, txtTitle.getText().toString(),Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public FilmHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_film, parent, false);

        return new FilmHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FilmHolder holder, int position) {
        Film film = listFilm.get(position);
        holder.txtTitle.setText(film.title);
        holder.txtGenre.setText(film.genre);
        holder.txtActor.setText(film.actor);

    }

    @Override
    public int getItemCount() {
        return listFilm.size();
    }
}

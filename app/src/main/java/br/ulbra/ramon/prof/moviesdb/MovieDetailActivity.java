package br.ulbra.ramon.prof.moviesdb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class MovieDetailActivity extends AppCompatActivity {

    Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        movie = (Movie)getIntent().getExtras().getSerializable("movie");
        if(movie == null) {
            movie = new Movie();
            Log.d("MovieDetailActivity()","objeto nulo da intent");
        } else {
            Log.d("MovieDetailActivity()","objeto: " + movie);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        String basePath = "https://image.tmdb.org/t/p/w300_and_h450_bestv2";
        ((TextView)findViewById(R.id.titulo)).setText(movie.getTitle());
        ((TextView)findViewById(R.id.popularidade)).setText(getResources().getText(R.string.popularity)  + ": " + movie.getPopularity());
        ((TextView)findViewById(R.id.descricao)).setText(movie.getOverview());
        ImageView imageView = findViewById(R.id.imagem);
        Picasso.get().load(basePath + movie.getThumb()).into(imageView);
    }
}

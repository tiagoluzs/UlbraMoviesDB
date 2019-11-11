package br.ulbra.ramon.prof.moviesdb.ui.favorites;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;

import br.ulbra.ramon.prof.moviesdb.Movie;
import br.ulbra.ramon.prof.moviesdb.MovieService;
import br.ulbra.ramon.prof.moviesdb.R;

public class FavoritesFragment extends Fragment {

    ArrayList<Movie> favorites;
    private FavoritesViewModel favoritesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        favoritesViewModel =
                ViewModelProviders.of(this).get(FavoritesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_favorites, container, false);

        return root;
    }



    public class MyAsyncTask extends AsyncTask<String, Integer, Integer> {
        @Override
        protected Integer doInBackground(String... strings) {

            MovieService service = new MovieService();
            favorites = service.getFavorites();


            Log.d("MainActivity","API Favorites received");
            return 0;
        }
    }


    @Override
    public void onResume() {
        super.onResume();

        MyAsyncTask task = new MyAsyncTask();
        task.execute("");

    }
}
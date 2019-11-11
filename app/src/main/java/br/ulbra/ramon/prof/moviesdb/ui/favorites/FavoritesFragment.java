package br.ulbra.ramon.prof.moviesdb.ui.favorites;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;

import br.ulbra.ramon.prof.moviesdb.Movie;
import br.ulbra.ramon.prof.moviesdb.MovieDetailActivity;
import br.ulbra.ramon.prof.moviesdb.MovieListAdapter;
import br.ulbra.ramon.prof.moviesdb.MovieService;
import br.ulbra.ramon.prof.moviesdb.R;

public class FavoritesFragment extends Fragment {

    private FavoritesViewModel favoritesViewModel;
    ArrayList<Movie> favorites = new ArrayList<Movie>();

    ListView listView;
    MovieListAdapter adapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        favoritesViewModel =
                ViewModelProviders.of(this).get(FavoritesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_favorites, container, false);

        listView = root.findViewById(R.id.lista);
        adapter = new MovieListAdapter(favorites,getActivity());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Movie movie = (Movie)((MovieListAdapter)adapterView.getAdapter()).getItem(i);
                Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
                intent.putExtra("movie",movie);
                startActivity(intent);
            }
        });

        return root;
    }



    public class MyAsyncTask extends AsyncTask<String, Integer, Integer> {
        public ArrayList<Movie> lista = new ArrayList<Movie>();
        @Override
        protected Integer doInBackground(String... strings) {

            MovieService service = new MovieService();
            lista = service.getFavorites();


            Log.d("MainActivity","API Favorites received");
            return 0;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            favorites = lista;
            if(favorites == null || favorites.size() == 0) {
                Toast.makeText(getContext(),"Sem internet.",Toast.LENGTH_LONG).show();
            } else {
                adapter.setItens(favorites);
                adapter.notifyDataSetChanged();
            }
        }
    }


    @Override
    public void onResume() {
        super.onResume();

        MyAsyncTask task = new MyAsyncTask();
        task.execute("");

    }
}
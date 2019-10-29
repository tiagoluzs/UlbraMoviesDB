package br.ulbra.ramon.prof.moviesdb.ui.toprated;

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

public class TopratedFragment extends Fragment {

    private TopratedViewModel homeViewModel;
    ArrayList<Movie> toprated;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(TopratedViewModel.class);
        View root = inflater.inflate(R.layout.fragment_toprated, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }


    public class MyAsyncTask extends AsyncTask<String, Integer, Integer> {
        @Override
        protected Integer doInBackground(String... strings) {

            MovieService service = new MovieService();
            toprated = service.getToprated();


            Log.d("MainActivity","API Top Rated received");
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
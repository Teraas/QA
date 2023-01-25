package com.example.familytree;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.familytree.adapters.AdapterPosts;
import com.example.familytree.models.Post;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    TextView textView;
    String myuid;
    RecyclerView recyclerView;
    List<Post> posts;
    AdapterPosts adapterPosts;

    public HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        //textView=view.findViewById(R.id.home_fragment_text1);
        //String sTitle=getArguments().getString("title");
        //textView.setText("Home Fragment");
        recyclerView = view.findViewById(R.id.posts_home);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
        posts = new ArrayList<>();
        loadPosts();
        return view;
    }

    private void loadPosts() {
        Post post1 = new Post("Hi there! asdddsdfsdfsfdsfsdfsdfsdfdsfdsfdfdfsdfsdfsdfsdfsdfdfdccdscdscdc", "123","1672241740","22","","","harish@gmail.com","121232","","Harish Kumar","1");
        Post post2 = new Post("Hi there! 2", "124","1672241940","11","","","harish@gmail.com","121232","","Harish Kumar","5");
        Post post3 = new Post("Hi there! 2 dfdfdfsdsfdsfsdfsffsdfsdfsdfsdfsdfsdfdsfdfsdfsdfsdfsdfsdfsdfdsfsfs", "124","1672241940","10000","","","harish@gmail.com","121232","","Harish Kumar","5");
        Post post4 = new Post("Hi there! 2", "124","1672241940","2","","","harish@gmail.com","121232","","Harish Kumar","5");
        Post post5 = new Post("Hi there! 2dfdfdfsdsfdsfsdfsffsdfsdfsdfsdfsdfsdfdsfdfsdfsdfsdfsdfsdfsdfdsfsfsasdddsdfsdfsfdsfsdfsdfsdfdsfdsfdfdfsdfsdfsdfsdfsdfdfdccdscdscdc", "124","1672241940","123","","","harish@gmail.com","121232","","Kirti Swami","5");
        Post post6 = new Post("Hi there! 22dfdfdfsdsfdsfsdfsffsdfsdfsdfsdfsdfsdfdsfdfsdfsdfsdfsdfsdfsdfdsfsfsasdddsdfsdfsfdsfsdfsdfsdfdsfdsfdfdfsdfsdfsdfsdfsdfdfdccdscdscdc2dfdfdfsdsfdsfsdfsffsdfsdfsdfsdfsdfsdfdsfdfsdfsdfsdfsdfsdfsdfdsfsfsasdddsdfsdfsfdsfsdfsdfsdfdsfdsfdfdfsdfsdfsdfsdfsdfdfdccdscdscdc", "124","1672241940","432","","","harish@gmail.com","121232","","Kirti Swami","5");

        posts.add(post1);
        posts.add(post2);
        posts.add(post3);
        posts.add(post4);
        posts.add(post5);
        posts.add(post6);
        adapterPosts = new AdapterPosts(getActivity(), posts);
        recyclerView.setAdapter(adapterPosts);
    }
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        Bundle args = getArguments();
//        textView=view.findViewById(R.id.home_fragment_text1);
//        //String sTitle=getArguments().getString("title");
//        textView.setText("Home Fragment");
//    }
}
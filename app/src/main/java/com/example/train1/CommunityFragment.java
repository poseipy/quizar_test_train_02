package com.example.train1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CommunityFragment extends Fragment {

    private ListViewModel listViewModel;

    private ListAdapter adapter;

    private ListAdapter listAdapter;

    private NavController navController;

    private TextView community_title, community_slogan;
    private Button community_play, community_create, community_profile, community_back;

    public CommunityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_community, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        community_title = view.findViewById(R.id.community_title);
        community_slogan = view.findViewById(R.id.menu_slogan);
        community_play = view.findViewById(R.id.community_play);
        community_create = view.findViewById(R.id.community_create);
        community_profile = view.findViewById(R.id.community_profile);
        community_back = view.findViewById(R.id.community_back);

        community_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_communityFragment_to_quizCreationFragment);
            }
        });

        community_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_communityFragment_to_listFragment);

            }
        });

    }
}

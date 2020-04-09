package com.example.train1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends Fragment {


    TextView menu_title, menu_slogan;
    Button menu_official, menu_community, menu_setting, menu_logout;
    NavController navController;

    public MenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        menu_title = view.findViewById(R.id.menu_title);
        menu_slogan = view.findViewById(R.id.menu_slogan);
        menu_official = view.findViewById(R.id.menu_official);
        menu_community = view.findViewById(R.id.menu_comuunity);
        menu_setting = view.findViewById(R.id.menu_setting);
        menu_logout = view.findViewById(R.id.menu_logout);

        menu_community.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_menuFragment_to_communityFragment);
            }
        });

    }
}

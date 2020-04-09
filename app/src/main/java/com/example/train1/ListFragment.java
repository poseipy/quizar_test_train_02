package com.example.train1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment implements ListAdapter.OnQuizListItemClicked {

    private NavController navController;

    private Button button;

    private ListAdapter adapter;
    private ListViewModel listViewModel;

    private FirebaseFirestore mRef = FirebaseFirestore.getInstance();
    private CollectionReference mInfoRef = mRef.collection("quiz_info");

    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mInfoRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    task.getResult().toObjects(ListModel.class);
                }else{

                    Toast.makeText(getContext(), ""+task.getException().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });


        ListViewModel listViewModel;
        listViewModel = new ViewModelProvider(getActivity()).get(ListViewModel.class);
        listViewModel.getListModelData().observe(getViewLifecycleOwner(), new Observer<List<ListModel>>() {
            @Override
            public void onChanged(List<ListModel> listModelList) {
                adapter.setListModels(listModelList);
                adapter.notifyDataSetChanged();
            }
        });

        return inflater.inflate(R.layout.fragment_list, container, false);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();

        updateData();
    }

    private void updateData() {
        listViewModel = new ViewModelProvider(getActivity()).get(ListViewModel.class);
        listViewModel.getListModelData().observe(getViewLifecycleOwner(), new Observer<List<ListModel>>() {
            @Override
            public void onChanged(List<ListModel> listModelList) {
                adapter.notifyDataSetChanged();
                adapter.setListModels(listModelList);
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        TextView main_slogan = view.findViewById(R.id.list_community_slogan);
        TextView main_tittle = view.findViewById(R.id.list_community_title);
        final RecyclerView recyclerView = view.findViewById(R.id.recycler_view);

        button = view.findViewById(R.id.button);

        adapter = new ListAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.hasFixedSize();
        recyclerView.setAdapter(adapter);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listViewModel = new ViewModelProvider(getActivity()).get(ListViewModel.class);
                listViewModel.getListModelData().observe(getViewLifecycleOwner(), new Observer<List<ListModel>>() {
                    @Override
                    public void onChanged(List<ListModel> listModelList) {
                        adapter.setListModels(listModelList);
                        adapter.notifyDataSetChanged();

                        System.out.println(listModelList.toArray());
                    }
                });
            }
        });


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public void onItemClicked(int position) {

    }
}

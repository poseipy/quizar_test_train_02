package com.example.train1;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class ListViewModel1 extends ViewModel implements FirebaseRepository.OnFireStoreTaskComplete {

    private MutableLiveData<List<ListModel>> listModelData  = new MutableLiveData<>();

    public MutableLiveData<List<ListModel>> getListModelData() {
        return listModelData;
    }

    private FirebaseRepository firebaseRepository = new FirebaseRepository(this);

    public ListViewModel1(){
        firebaseRepository.getQuizData();
    }

    @Override
    public void listDataAdded(List<ListModel> listModelList) {

    }

    @Override
    public void onError(Exception e) {

    }
}

package com.example.train1;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class ListViewModel extends ViewModel implements FirebaseRepository.OnFireStoreTaskComplete{

    private MutableLiveData<List<ListModel>> listModelData = new MutableLiveData<>();

    public LiveData<List<ListModel>> getListModelData() {
        return listModelData;
    }

    private FirebaseRepository FirebaseRepository = new FirebaseRepository(this);

    public ListViewModel(){
        FirebaseRepository.getQuizData();
    }

    @Override
    public void listDataAdded(List<ListModel> listModelList) {
        listModelData.setValue(listModelList);
    }

    @Override
    public void onError(Exception e) {

    }

}

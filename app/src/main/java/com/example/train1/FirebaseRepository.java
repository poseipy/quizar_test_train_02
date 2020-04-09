package com.example.train1;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class FirebaseRepository {

    private OnFireStoreTaskComplete onFireStoreTaskComplete;

    private FirebaseFirestore mRef = FirebaseFirestore.getInstance();
    private CollectionReference mInfoRef = mRef.collection("quiz_info");

    public FirebaseRepository(OnFireStoreTaskComplete onFireStoreTaskComplete){
        this.onFireStoreTaskComplete = onFireStoreTaskComplete;
    }

    public void getQuizData(){
        mInfoRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    onFireStoreTaskComplete.listDataAdded(task.getResult().toObjects(ListModel.class));
                }else{
                    onFireStoreTaskComplete.onError(task.getException());
                }
            }
        });
    }

    public interface OnFireStoreTaskComplete {
        void listDataAdded(List<ListModel> listModelList);
        void onError(Exception e);


    }

}

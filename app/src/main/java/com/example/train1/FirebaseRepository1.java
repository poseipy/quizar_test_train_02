package com.example.train1;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class FirebaseRepository1 {

    OnFireStoreTaskComplete onFireStoreTaskComplete;

    public FirebaseRepository1(OnFireStoreTaskComplete onFireStoreTaskComplete){
        this.onFireStoreTaskComplete = onFireStoreTaskComplete;
    }

    private FirebaseFirestore mRootRef = FirebaseFirestore.getInstance();
    private CollectionReference mQuizInfoRef = mRootRef.collection("quiz_info");

    public void getData(){
        mQuizInfoRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    onFireStoreTaskComplete.listDataAdded(task.getResult().toObjects(ListModel.class));
                }else{
                    onFireStoreTaskComplete.onError(task.getException());
                }
            }
        });
    }

    public interface OnFireStoreTaskComplete{
        void listDataAdded(List <ListModel> listModels);
        void onError(Exception e);
    }

}

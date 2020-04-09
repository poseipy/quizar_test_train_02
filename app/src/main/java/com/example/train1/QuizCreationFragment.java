package com.example.train1;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.PixelCopy;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import static androidx.core.provider.FontsContractCompat.FontRequestCallback.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuizCreationFragment extends Fragment {

    private EditText creation_name, creation_description, creation_level, creation_question_count;
    private TextView creation_title;
    private Button creation_choose_image, creation_next;

    private FirebaseFirestore mRootRef = FirebaseFirestore.getInstance();
    private CollectionReference mQuizInfoRef = mRootRef.collection("quiz_info");

    private StorageReference mStorageRef;

    private static final int PICK_IMAGE_REQUEST = 1;

    private Uri mImageUri;

    private String auto_id;

    private String quiz_name, quiz_description, quiz_level, quiz_image;
    private Long quiz_question_count;

    private StorageTask mUploadTask;

    public QuizCreationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz_creation, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        creation_name = view.findViewById(R.id.creation_name);
        creation_description = view.findViewById(R.id.creation_description);
        creation_level = view.findViewById(R.id.creation_level);
        creation_question_count = view.findViewById(R.id.creation_number);
        creation_title = view.findViewById(R.id.creation_title);
        creation_choose_image = view.findViewById(R.id.creation_choose_image);
        creation_next = view.findViewById(R.id.creation_next);

        mStorageRef = FirebaseStorage.getInstance().getReference();

        creation_choose_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenFileChooser();
            }
        });

        creation_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadFile();
            }
        });

    }

    private void OpenFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST
                && data != null && data.getData() != null) {
            mImageUri = data.getData();
        }else {
            Toast.makeText(getContext(), "no", Toast.LENGTH_SHORT).show();
        }

    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContext().getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));

    }

    private void uploadFile(){
        if (mImageUri != null) {
            final StorageReference fileReference = mStorageRef.child("images/" + System.currentTimeMillis()
                    + "." + getFileExtension(mImageUri));

            mUploadTask = fileReference.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Uri downloadUrl = uri;
                            quiz_image = downloadUrl.toString();
                        }
                    });

                    quiz_description = creation_description.getText().toString();
                    quiz_name = creation_name.getText().toString();
                    quiz_level = creation_level.getText().toString();
                    quiz_question_count = Long.parseLong(creation_question_count.getText().toString());

                    ListModel listModel = new ListModel(auto_id,quiz_name,quiz_image,quiz_description,quiz_level,quiz_question_count);

                    DocumentReference documentReference = mQuizInfoRef.document();

                    auto_id = documentReference.getId();

                    mQuizInfoRef.document(auto_id).set(listModel);

                    System.out.println(auto_id);

                    Toast.makeText(getContext(), "Upload Success", Toast.LENGTH_SHORT).show();
                }
            });


        }else{
            Toast.makeText(getContext(), "failed", Toast.LENGTH_SHORT).show();
        }
    }
}

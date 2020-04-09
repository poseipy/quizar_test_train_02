package com.example.train1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


/**
 * A simple {@link Fragment} subclass.
 */
public class StartFragment extends Fragment {

    TextView start_title;
    TextView start_slogan;
    EditText start_email;
    EditText start_password;
    Button start_signUp;
    Button start_signIn;

    String email, password;

    NavController navController;

    private FirebaseAuth firebaseAuth;


    public StartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        start_title = view.findViewById(R.id.menu_title);
        start_slogan = view.findViewById(R.id.menu_slogan);
        start_email = view.findViewById(R.id.start_email);
        start_password = view.findViewById(R.id.start_password);
        start_signIn = view.findViewById(R.id.start_button_signin);
        start_signUp = view.findViewById(R.id.start_button_signup);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getUid()!= null){
            navController.navigate(R.id.action_startFragment_to_menuFragment);
            Toast.makeText(getContext(), "Logged in with the last session", Toast.LENGTH_LONG).show();
        }



        start_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();

            }
        });

        start_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
    }

    private void signIn() {
            email = start_email.getText().toString();
            password = start_password.getText().toString();

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(getContext(), "Please Enter Email", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(password)) {
                Toast.makeText(getContext(), "Please Enter Password", Toast.LENGTH_SHORT).show();
                return;
            }

            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(getContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                        navController.navigate(R.id.action_startFragment_to_menuFragment);

                    } else {
                        Toast.makeText(getContext(), task.getException().toString(), Toast.LENGTH_SHORT).show();
                    }
                }


            });
    }


    private void signUp() {
        email = start_email.getText().toString();
        password = start_password.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getContext(), "Please Enter Email", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getContext(), "Please Enter Password", Toast.LENGTH_SHORT).show();
            return;
        }

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getContext(), "Registration Successful", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), task.getException().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            ;
        });
    }

}


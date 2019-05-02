package com.fourninetyfour.makeplans;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginFragment extends Fragment {

    View view;
    Button loginButton;
    Button signUpButton;
    FirebaseAuth auth = FirebaseAuth.getInstance();

    private EditText email, password;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.content_main, container, false);
        setHasOptionsMenu(true);
        loginButton = (Button) view.findViewById(R.id.login_button);
        email = (EditText) view.findViewById(R.id.login_email);
        password = (EditText) view.findViewById(R.id.login_password);
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!validateForm()) {
                    return;
                }
                //if (!email.getText().toString().isEmpty() && !password.getText().toString().isEmpty()) {
                    //signIn(email.getText().toString(), password.getText().toString());
                //}
                else {
                    signIn(email.getText().toString(), password.getText().toString());
                }
            }
        });
        signUpButton = (Button) view.findViewById(R.id.new_user_button);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SignUpActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void openFragment(final Fragment fragment)  {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.MainFragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }

    private void signIn(String email, String password) {

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    openFragment(new PlansActivity());
                }
                else {
                    // for debugging, delete later
                    System.out.println("BAD LOGIN");
                    System.out.println(view.findViewById(R.id.login_password).toString());
                }
            }
        });
    }

    private boolean validateForm() {
        boolean valid = true;

        if (TextUtils.isEmpty(email.getText().toString())) {
            email.setError("Required.");
            valid = false;
        } else {
            email.setError(null);
        }

        if (TextUtils.isEmpty(password.getText().toString())) {
            password.setError("Required.");
            valid = false;
        } else {
            password.setError(null);
        }

        return valid;
    }
}

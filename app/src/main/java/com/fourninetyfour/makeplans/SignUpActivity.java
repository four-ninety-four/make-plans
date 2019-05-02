package com.fourninetyfour.makeplans;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseFirestore database;

    EditText email, password;
    EditText firstName, lastName;
    EditText city, state;
    EditText phone;
    Button signUp;
    Button loginButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        auth = FirebaseAuth.getInstance();

        email = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);
        firstName = findViewById(R.id.first_name);
        lastName = findViewById(R.id.last_name);
        city = findViewById(R.id.city);
        state = findViewById(R.id.state);
        phone = findViewById(R.id.phone);
        signUp = findViewById(R.id.signup_button);
        loginButton = findViewById(R.id.login_button);

        signUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!validateForm()) {
                    return;
                }
                else {
                    signUp(email.getText().toString(), password.getText().toString());
                }
            }
        });
        final Intent intent = new Intent(this, MainActivity.class);
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }

    public void signUp(final String email, String password) {
        final Intent intent = new Intent(this, MainActivity.class);
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    database = FirebaseFirestore.getInstance();

                    String uid = auth.getCurrentUser().getUid();
                    Map<String, String> usersMap = new HashMap<>();
                    usersMap.put("userID", uid);
                    usersMap.put("email", email.toString());
                    usersMap.put("first", firstName.getText().toString());
                    usersMap.put("last", lastName.getText().toString());
                    usersMap.put("image", "blank_profile.jpg");
                    usersMap.put("city", city.getText().toString());
                    usersMap.put("state", state.getText().toString());
                    usersMap.put("phone", phone.getText().toString());

                    database.collection("users").document(uid).set(usersMap);

                    startActivity(intent);
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

        if (TextUtils.isEmpty(firstName.getText().toString())) {
            firstName.setError("Required.");
            valid = false;
        } else {
            firstName.setError(null);
        }

        if (TextUtils.isEmpty(lastName.getText().toString())) {
            lastName.setError("Required.");
            valid = false;
        } else {
            lastName.setError(null);
        }

        if (TextUtils.isEmpty(city.getText().toString())) {
            city.setError("Required.");
            valid = false;
        } else {
            city.setError(null);
        }

        if (TextUtils.isEmpty(state.getText().toString())) {
            state.setError("Required.");
            valid = false;
        } else {
            state.setError(null);
        }

        if (TextUtils.isEmpty(phone.getText().toString())) {
            phone.setError("Required.");
            valid = false;
        } else {
            phone.setError(null);
        }

        return valid;
    }
}

package com.parse.starter.Login.Login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.parse.starter.R;

/**
 * Created by vamnoize on 29/10/2558.
 */
public class SignupActivity extends Activity {

    Button signup;
    String usernametxt;
    String passwordtxt;
    String repasswordtxt;
    EditText password;
    EditText repassword;
    EditText username;

    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from main.xml
        setContentView(R.layout.signup);
        // Locate EditTexts in main.xml
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);

        // Locate Buttons in main.xml
        signup = (Button) findViewById(R.id.signup);

        // Sign up Button Click Listener
        signup.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                // Retrieve the text entered from the EditText
                usernametxt = username.getText().toString();
                passwordtxt = password.getText().toString();
                repasswordtxt = repassword.getText().toString();

                // Force user to fill up the form
                if (usernametxt.equals("") && passwordtxt.equals("") && repasswordtxt.equals("") ) {
                    Toast.makeText(getApplicationContext(),
                            "Please complete the sign up form",
                            Toast.LENGTH_LONG).show();

                } else {
                    // Save new user data into Parse.com Data Storage
                    if (passwordtxt.equals(repasswordtxt)) {
                        ParseUser user = new ParseUser();
                        user.setUsername(usernametxt);
                        user.setPassword(passwordtxt);
                        user.signUpInBackground(new SignUpCallback() {
                            public void done(ParseException e) {
                                if (e == null) {
                                    // Show a simple Toast message upon successful registration
                                    Toast.makeText(getApplicationContext(),
                                            "Successfully Signed up, please log in.",
                                            Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(
                                            SignupActivity.this,
                                            LoginSignupActivity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(getApplicationContext(),
                                            "Sign up Error", Toast.LENGTH_LONG)
                                            .show();
                                }
                            }
                        });
                    } else {
                        Toast.makeText(getApplicationContext(),
                                "Password not match, please Sign up again.",
                                Toast.LENGTH_LONG).show();
                    }
                }

            }
        });

    }
}


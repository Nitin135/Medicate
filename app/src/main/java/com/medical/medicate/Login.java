package com.medical.medicate;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Login extends AppCompatActivity
{
    private Button LoginButton,RegistrationButton ;
    private EditText login_Username, login_Password ;
    private String login_Username_value,login_Password_value;
    private ProgressDialog progressDialog;
    AlertDialogManager alert = new AlertDialogManager();
    SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        LoginButton = (Button) findViewById(R.id.LoginButton );
        RegistrationButton = (Button)findViewById(R.id.RegisterButton);
        login_Username= (EditText) findViewById(R.id.login_Username);
        login_Password = (EditText) findViewById(R.id.login_Password);

        LoginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v)
            {
                login_Username_value = login_Username.getText().toString();
                login_Password_value = login_Password.getText().toString();
                if(isNetworkConnected()!=true)
                {
                    Toast.makeText(getApplication(), "No Internet Connection", Toast.LENGTH_LONG).show();
                }
                else
                {
                    boolean isValidRegistration = validateUser();
                    if (isValidRegistration)
                    {
                        checkLogin(login_Username_value, login_Password_value);
                    }
                }

            }
        });
        RegistrationButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent register = new Intent(Login.this,Register.class);
                Login.this.startActivity(register);
            }
        });
    }
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }
    public boolean validateUser()
    {
        boolean isValid = true;
        String userPattern ="[a-zA-Z0-9]+";

        if ("".equals( login_Username_value))
        {
            login_Username.setError("Empty Field");
            login_Username.requestFocus();
            return false;
        }
        if ("".equals( login_Password_value))
        {
            login_Password.setError("Empty Field");
            login_Password.requestFocus();
            return false;
        }
        return true;
    }

    private void checkLogin(final String login_Username_value , final String login_Password_value ) {
        String tag_string_req = "login";
        progressDialog.setMessage("Logging in ...");
        showDialog();
        StringRequest strReq = new StringRequest(Request.Method.GET, AppURLs.URL+"?tag=login&Username="+login_Username_value+"&Password="+login_Password_value , new Response.Listener<String>(){
            @Override
            public void onResponse(String response)
            {
                hideDialog();
                try
                {
                    JSONObject jObj = new JSONObject(response.toString());
                    boolean error = jObj.getBoolean("error");
                    if(!error)
                    {
                        Intent login = new Intent(Login.this,module_navigation.class);
                        Login.this.startActivity(login);
                        String name=jObj.getString("name");
                        String username=jObj.getString("username");
                        String email=jObj.getString("email");
                        session.createLoginSession(name, username ,email);

                    }
                    else
                    {
                        Toast.makeText(getApplication(), "Username/Password Incorrect", Toast.LENGTH_LONG).show();
                    }
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),
                        "Network Error" , Toast.LENGTH_LONG).show();
                hideDialog();
            }
        });
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    private void showDialog() {
        if (!progressDialog.isShowing())
            progressDialog.show();
    }

    private void hideDialog() {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }
}


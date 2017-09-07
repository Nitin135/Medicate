package com.medical.medicate;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

public class Register extends AppCompatActivity
{
    @Override
    public void onCreate(Bundle savedinstancestate)
    {
        super.onCreate(savedinstancestate);
        Intent regsister=getIntent();
        setContentView(R.layout.registeration_form);
    }
    public void submit_register(View view)
    {
        EditText reg_FullName = (EditText) findViewById(R.id.reg_FullName);
        EditText reg_Username = (EditText) findViewById(R.id.reg_Username);
        EditText reg_Email = (EditText) findViewById(R.id.reg_Email);
        EditText reg_Password = (EditText) findViewById(R.id.reg_Password);
        EditText reg_ConfirmPassword = (EditText) findViewById(R.id.reg_ConfirmPassword);
        String reg_FullName_value = reg_FullName.getText().toString();
        String reg_Username_value = reg_Username.getText().toString();
        String reg_Email_value = reg_Email.getText().toString();
        String reg_Password_value = reg_Password.getText().toString();
        String reg_ConfirmPassword_value = reg_ConfirmPassword.getText().toString();
        /*Log.d("Full Name",reg_FullName_value);
        Log.d("Username",reg_Username_value);
        Log.d("Email",reg_Email_value);
        Log.d("Password",reg_Password_value);
        Log.d("Confirm Passowrd",reg_ConfirmPassword_value);*/
    }
}
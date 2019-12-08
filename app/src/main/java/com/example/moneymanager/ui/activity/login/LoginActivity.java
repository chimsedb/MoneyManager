package com.example.moneymanager.ui.activity.login;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.moneymanager.Comon.Comon;
import com.example.moneymanager.R;
import com.example.moneymanager.model.User;
import com.example.moneymanager.ui.activity.MainActivity;
import com.example.moneymanager.ui.activity.register.RegisterActivity;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;
import es.dmoral.toasty.Toasty;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_sign_up;
    private Button btn_sign_in;
    private TextInputEditText edt_email, edt_password;
    private ViewModelLogin viewModelLogin;
    private AlertDialog dialog;

    private List<User> listUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        listUser = new ArrayList<>();
        viewModelLogin = ViewModelProviders.of(this).get(ViewModelLogin.class);

        edt_email = findViewById(R.id.edt_email);
        edt_password = findViewById(R.id.edt_password);
        btn_sign_up = findViewById(R.id.btn_sign_up);
        btn_sign_in = findViewById(R.id.btn_sign_in);

        btn_sign_up.setOnClickListener(this);
        btn_sign_in.setOnClickListener(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == Comon.REQUEST_LOGIN){
            edt_email.setText(data.getStringExtra("email"));
            edt_password.setText(data.getStringExtra("password"));
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sign_up:
                startActivityForResult(new Intent(LoginActivity.this, RegisterActivity.class), Comon.REQUEST_LOGIN);
                break;
            case R.id.btn_sign_in:
                signIn();
                break;
        }
    }
    private void signIn() {
        dialog = new SpotsDialog.Builder().setContext(this).setMessage("Vui lòng chờ ...").setCancelable(false).build();
        dialog.show();
        viewModelLogin.getMutableLiveDataUser().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                listUser = users;
                if (checkUser(edt_email.getText().toString(), edt_password.getText().toString())) {
                    Comon.CURRENT_USER = getUser(edt_email.getText().toString(), edt_password.getText().toString());
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }else{
                    Toasty.error(getApplicationContext(), "Mật khẩu và tài khoản không chính xác !", Toast.LENGTH_LONG, true).show();
                }
                dialog.dismiss();
            }
        });

    }

    private boolean checkUser(String email, String password) {
        for (User user : listUser) {
            if (email.equals(user.getEmail()) && password.equals(user.getPassword())) {
                return true;
            }
        }
        return false;
    }

    private User getUser(String email, String password) {
        for (User user : listUser) {
            if (email.equals(user.getEmail()) && password.equals(user.getPassword())) {
                return user ;
            }
        }
        return new User();
    }
}

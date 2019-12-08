package com.example.moneymanager.ui.activity.register;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.moneymanager.Comon.Comon;
import com.example.moneymanager.R;
import com.example.moneymanager.model.User;
import com.example.moneymanager.ui.activity.login.ViewModelLogin;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import dmax.dialog.SpotsDialog;
import es.dmoral.toasty.Toasty;

public class RegisterActivity extends AppCompatActivity {

    private TextView tv_sign_in;
    private MaterialButton btn_register;
    private TextInputEditText edt_user_name, edt_email, edt_password;
    private ViewModelRegister viewModelRegister;
    private ViewModelLogin viewModelLogin;
    private List<User> users;

    private AlertDialog dialog;
    private String username,email,password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        dialog = new SpotsDialog.Builder().setContext(this).setMessage("Vui lòng chờ ...").setCancelable(false).build();
        tv_sign_in = findViewById(R.id.tv_sign_in);
        edt_user_name = findViewById(R.id.edt_user_name);
        edt_email = findViewById(R.id.edt_email);
        edt_password = findViewById(R.id.edt_password);
        btn_register = findViewById(R.id.btn_register);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
                register(edt_user_name.getText().toString(), edt_email.getText().toString(), edt_password.getText().toString());

            }
        });

        SpannableString ss = new SpannableString("Bạn đã có tài khoản? Đăng nhập");
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                sendResult();
                finish();
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
            }
        };

        ss.setSpan(clickableSpan, 21, 30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv_sign_in.setText(ss);
        tv_sign_in.setHighlightColor(Color.TRANSPARENT);
        tv_sign_in.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void register(final String username, final String email, final String password) {
        viewModelLogin = ViewModelProviders.of(this).get(ViewModelLogin.class);
        viewModelRegister = ViewModelProviders.of(this).get(ViewModelRegister.class);

        viewModelRegister.getMutableLiveDataUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                dialog.dismiss();
                Toasty.success(getApplicationContext(), "Đăng ký thành công :))", Toast.LENGTH_LONG, true).show();
            }
        });

        viewModelLogin.getMutableLiveDataUser().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> userList) {
                users = userList;
                Comon.sizeUserList = userList.size();
                if (checkUser(email)) {
                    User user = new User(username,email,password);
                    viewModelRegister.setMutableLiveDataUser(user);
                    sendResult();
                    finish();
                }else{
                    dialog.dismiss();
                    Toasty.error(getApplicationContext(), "Email đã tồn tại , vui lòng chọn Email khác !", Toast.LENGTH_LONG, true).show();
                }
            }
        });

    }

    private void sendResult() {
        Intent intent = new Intent();
        intent.putExtra("email",email);
        intent.putExtra("password",password);
        setResult(Comon.REQUEST_LOGIN,intent);
    }

    private boolean checkUser(String email) {
        for (User user : users) {
            if (email.equals(user.getEmail())) {
                return false;
            }
        }
        return true;
    }
}

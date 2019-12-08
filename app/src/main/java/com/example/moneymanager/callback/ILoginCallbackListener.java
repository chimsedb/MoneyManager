package com.example.moneymanager.callback;

import com.example.moneymanager.model.User;

import java.util.List;

public interface ILoginCallbackListener {
    void onLoginLoadSucces(List<User> userList);
    void onLoginLoadFaild(String error);
}

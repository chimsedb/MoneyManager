package com.example.moneymanager.callback;

import com.example.moneymanager.model.User;

public interface IRegisterCallbackListener {
    void onRegisterSucces(User user);
    void onRegisterFaild(String message);
}

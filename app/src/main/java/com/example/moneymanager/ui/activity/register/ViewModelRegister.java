package com.example.moneymanager.ui.activity.register;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.moneymanager.Comon.Comon;
import com.example.moneymanager.callback.IRegisterCallbackListener;
import com.example.moneymanager.model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ViewModelRegister extends ViewModel implements IRegisterCallbackListener {
    private MutableLiveData<User> mutableLiveDataUser;
    private MutableLiveData<String> mutableLiveDataError;
    private IRegisterCallbackListener listener;

    public ViewModelRegister() {
        listener = this;
    }

    public MutableLiveData<User> getMutableLiveDataUser() {
        if (mutableLiveDataUser == null) {
            mutableLiveDataUser = new MutableLiveData<>();
            mutableLiveDataError = new MutableLiveData<>();
        }
        return mutableLiveDataUser;
    }

    public void setMutableLiveDataUser(final User user) {
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("user").child(Comon.sizeUserList+"");
        myRef.setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                listener.onRegisterSucces(user);
            }
        })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                listener.onRegisterFaild(e.getMessage());
            }
        });
    }


    @Override
    public void onRegisterSucces(User user) {
        mutableLiveDataUser.setValue(user);
    }

    @Override
    public void onRegisterFaild(String message) {
        mutableLiveDataError.setValue(message);
    }
}

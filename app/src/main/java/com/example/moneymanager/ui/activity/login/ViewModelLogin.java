package com.example.moneymanager.ui.activity.login;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.moneymanager.callback.ILoginCallbackListener;
import com.example.moneymanager.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewModelLogin extends ViewModel implements ILoginCallbackListener {
    MutableLiveData<List<User>> mutableLiveDataUser;
    MutableLiveData<String> mutableLiveDataError;
    ILoginCallbackListener listener;

    public ViewModelLogin() {
        listener = this;
    }

    public MutableLiveData<List<User>> getMutableLiveDataUser() {
        if(mutableLiveDataUser == null){
            mutableLiveDataUser = new MutableLiveData<>();
            mutableLiveDataError= new MutableLiveData<>();
        }
        loadUserCatagory();
        return mutableLiveDataUser;
    }

    private void loadUserCatagory() {
        final List<User> temp = new ArrayList<>();
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("user");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot item : dataSnapshot.getChildren()){
                    User user = item.getValue(User.class);
                    temp.add(user);
                }
                listener.onLoginLoadSucces(temp);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                listener.onLoginLoadFaild(databaseError.getMessage());
            }
        });
    }

    @Override
    public void onLoginLoadSucces(List<User> userList) {
        mutableLiveDataUser.setValue(userList);
    }

    @Override
    public void onLoginLoadFaild(String error) {
        mutableLiveDataError.setValue(error);
    }
}

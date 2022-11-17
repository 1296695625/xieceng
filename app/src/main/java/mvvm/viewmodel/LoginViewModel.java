package mvvm.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import jetpack.room.User;
import jetpack.room.UserDataSource;
import mvvm.bean.Name;
import mvvm.livedata.LoginLiveData;

public class LoginViewModel extends ViewModel {
    private LoginLiveData<String> data;
    private MutableLiveData<Name> nameLiveData;
    private MutableLiveData<User> userMutableLiveData;
    private Executor executor;
    private UserDataSource userDataSource;
    private Context context;
    private long i = 0;

    public LoginViewModel(Context context) {
        this.context = context;
        userDataSource = UserDataSource.getInstance(context);
        executor = Executors.newFixedThreadPool(3);
    }

    public LoginLiveData<String> getData() {
        if (data == null) {
            data = new LoginLiveData<>();
        }
        return data;
    }

    public MutableLiveData<Name> getNameLiveData() {
        if (nameLiveData == null) {
            nameLiveData = new MediatorLiveData<Name>();
        }
        return nameLiveData;
    }

    public MutableLiveData<User> getUserMutableLiveData() {
        if (userMutableLiveData == null) {
            userMutableLiveData = new MediatorLiveData<User>();
        }
        return userMutableLiveData;
    }

    User user;

    public void insert() {
        executor.execute(new Thread() {
            @Override
            public void run() {
                userDataSource.insertorupdate(new User("" + i++, "aa", new Date()));
            }
        });
    }

    public void selectData() {
        executor.execute(new Thread() {
            @Override
            public void run() {
                user = userDataSource.getUser();
            }
        });
                userMutableLiveData.setValue(user);
    }
}

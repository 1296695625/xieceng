package mvvm.view;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import jetpack.room.User;

public class NameViewModel extends ViewModel {
    private MutableLiveData<User> userMutableLiveData;

    public MutableLiveData<User> getUserMutableLiveData() {
        if(null==userMutableLiveData){
            userMutableLiveData=new MutableLiveData<User>();
        }
        return userMutableLiveData;
    }
}

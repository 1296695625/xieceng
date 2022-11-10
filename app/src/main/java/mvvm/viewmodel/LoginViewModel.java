package mvvm.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import mvvm.bean.Name;
import mvvm.livedata.LoginLiveData;

public class LoginViewModel extends ViewModel {
    private LoginLiveData<String> data;
    private MutableLiveData<Name> nameLiveData;

    public LoginLiveData<String> getData() {
        if(data==null){
            data=new LoginLiveData<>();
        }
        return data;
    }

    public MutableLiveData<Name> getNameLiveData() {
        if(nameLiveData==null){
            nameLiveData=new MediatorLiveData<Name>();
        }
        return nameLiveData;
    }
}

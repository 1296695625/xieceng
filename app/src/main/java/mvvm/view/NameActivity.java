package mvvm.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.xieceng.R;

import java.util.Date;
import java.util.concurrent.Executors;

import jetpack.room.User;
import jetpack.room.UserDataSource;
import jetpack.room.UserDatadabase;
import mvvm.bean.Name;
import mvvm.viewmodel.LoginViewModel;

public class NameActivity extends AppCompatActivity {
    private LoginViewModel viewModel;
    private TextView textView, usertext;
    private Button change, insert, select;
    private long i = 0;
    private Name name = new Name();

    private User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);
        textView = findViewById(R.id.livedata_tv);
        change = findViewById(R.id.change);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setName(("name-" + (i++)));
                viewModel.getNameLiveData().setValue(name);
            }
        });
//        viewModel = new ViewModelProvider(this,new ).get(LoginViewModel.class);
        viewModel = new LoginViewModel(this);
        Observer<Name> observer = new Observer<Name>() {
            @Override
            public void onChanged(Name name) {
                textView.setText(name.getName());
            }
        };
        viewModel.getNameLiveData().observe(this, observer);


        Observer<User> userObserver = new Observer<User>() {
            @Override
            public void onChanged(User user) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                if (null!=user) usertext.setText("username==" + user.getId()+"--"+user.getUsername());
                    }
                });
            }
        };
        viewModel.getUserMutableLiveData().observe(this, userObserver);
        usertext = findViewById(R.id.usertext);
        insert = findViewById(R.id.insert);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.insert();
            }
        });
        select = findViewById(R.id.select);
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               viewModel.selectData();
//                if (null!=user) usertext.set+Text("username==" + user.getId()+"--"+user.getUsername());
            }
        });

    }

}
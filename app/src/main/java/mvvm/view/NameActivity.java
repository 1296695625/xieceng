package mvvm.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.xieceng.R;

import mvvm.bean.Name;
import mvvm.viewmodel.LoginViewModel;

public class NameActivity extends AppCompatActivity {
    private LoginViewModel viewModel;
    private TextView textView;
    private Button change;
    private long i=0;
    private Name name=new Name();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);
        textView=findViewById(R.id.livedata_tv);
        change=findViewById(R.id.change);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setName(("name-"+(i++)));
                viewModel.getNameLiveData().setValue(name);
            }
        });
//        viewModel = new ViewModelProvider(this,new ).get(LoginViewModel.class);
        viewModel=new LoginViewModel();
        Observer<Name> observer=new Observer<Name>() {
            @Override
            public void onChanged(Name name) {
                textView.setText(name.getName());
            }
        };
        viewModel.getNameLiveData().observe(this,observer);
    }
}
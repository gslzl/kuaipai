package com.example.myproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.myproject.fragment.LoginFragment;
import com.example.myproject.fragment.RegisterFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class LoginActivity extends AppCompatActivity {

    TextView tv_sl_login;
    TextView tv_sl_register;
    @InjectView(R.id.left)
    TextView left;
    @InjectView(R.id.right)
    TextView right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
        replaceFragment(new LoginFragment());
    }

    //      登录注册
    public void selector(View view) {
        tv_sl_login = findViewById(R.id.tv_sl_login);
        tv_sl_register = findViewById(R.id.tv_sl_register);

        switch (view.getId()) {
            case R.id.tv_sl_login:
                right.setVisibility(View.INVISIBLE);
                left.setVisibility(View.VISIBLE);
                replaceFragment(new LoginFragment());
                break;
            case R.id.tv_sl_register:
                left.setVisibility(View.INVISIBLE);
                right.setVisibility(View.VISIBLE);
                replaceFragment(new RegisterFragment());
        }
    }

    //    切换碎片
    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.login_fragment, fragment);
        fragmentTransaction.commit();
    }


}


package com.example.myproject.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.example.myproject.LoginActivity;
import com.example.myproject.R;
import com.example.myproject.bean.GetVerCode;
import com.example.myproject.bean.UserBean;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {

    EditText et_phone;
    EditText et_pass;
    EditText et_checkpass;
    EditText et_checknumber;
    EditText et_name;
    Button btn_getchecknumber;
    Button btn_register;
    String str_name;
    String str_checknumber;
    String str_checkpass;
    String str_pass;
    String str_phone;
    private TimeCount time;
    View view;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_register, container, false);
        time = new TimeCount(60000, 1000);

        btn_getchecknumber = view.findViewById(R.id.btn_getchecknumber);
        btn_register = view.findViewById(R.id.btn_register);
        time.btn = btn_getchecknumber;
        btn_getchecknumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_phone = view.findViewById(R.id.et_phone);
                str_phone = et_phone.getText().toString().trim();
                String vercodeUrl = "http://120.79.87.68:5000/getVerCode";
                OkGo.<String>post(vercodeUrl)
                        .params("phone_number",str_phone)
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                GetVerCode getverCode = new Gson().fromJson(response.body(),GetVerCode.class);
                                if(getverCode.code.equals("1")){
                                    ToastUtils.showShort("验证码发送成功！");
                                    btn_register.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                            et_pass = view.findViewById(R.id.et_pass);
                                            et_checkpass = view.findViewById(R.id.et_checkpass);
                                            et_checknumber = view.findViewById(R.id.et_checknumber);
                                            et_name = view.findViewById(R.id.et_name);

                                            str_pass = et_pass.getText().toString().trim();
                                            str_checkpass = et_checkpass.getText().toString().trim();
                                            str_checknumber = et_checknumber.getText().toString().trim();
                                            str_name = et_name.getText().toString().trim();
                                            if(str_pass.equals(str_checkpass)) {
                                                String postUrl = "http://120.79.87.68:5000/insertUser";
                                                OkGo.<String>post(postUrl)
                                                        .params("phone_number",str_phone)
                                                        .params("password",str_pass)
                                                        .params("name",str_name)
                                                        .params("check_number",str_checknumber)
                                                        .execute(new StringCallback() {
                                                            @Override
                                                            public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                                                                UserBean userbean = new Gson().fromJson(response.body(),UserBean.class);
                                                                if(userbean.code.equals("1")) {
                                                                    ToastUtils.showShort("注册成功！");
                                                                    ((LoginActivity) getActivity()).replaceFragment(new LoginFragment());
                                                                }
                                                                else{
                                                                    ToastUtils.showShort("验证码输入有误！");
                                                                }
                                                            }
                                                        });

                                            }
                                            else {
                                                Toast.makeText(getActivity(),"两次密码输入有误",Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                }
                                else {
                                    ToastUtils.showShort("验证码发送失败！");
                                }
                            }
                        });
                time.start();
            }
        });
        return view;
    }

     class TimeCount extends CountDownTimer {

        Button btn;

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            btn.setBackgroundColor(Color.parseColor("#B52500"));
            btn.setClickable(false);
            btn.setTextColor(Color.parseColor("#FFFFFF"));
            btn.setText("("+millisUntilFinished / 1000 +") 秒后可重新发送");
        }

        @Override
        public void onFinish() {
            btn.setText("重新获取验证码");
            btn.setClickable(true);
            btn.setTextColor(Color.parseColor("#FFFFFF"));
            btn.setBackgroundColor(Color.parseColor("#D81B60"));

        }
    }

}

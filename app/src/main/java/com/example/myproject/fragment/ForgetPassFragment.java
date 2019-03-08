package com.example.myproject.fragment;


import android.os.Bundle;
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
public class ForgetPassFragment extends Fragment {

    View view;
    RegisterFragment fragment;
    EditText et_newphone;
    EditText et_newpass;
    EditText et_newcheckpass;
    EditText et_newchecknumber;
    Button btn_newgetchecknumber;
    Button btn_newpass;
    String str_checknumber;
    String str_checkpass;
    String str_pass;
    String str_phone;
    RegisterFragment.TimeCount time;

    public ForgetPassFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_forget_pass, container, false);
        fragment = new RegisterFragment();
        time = fragment.new TimeCount(60000,10000);
        btn_newpass = view.findViewById(R.id.btn_newpass);
        btn_newgetchecknumber = view.findViewById(R.id.btn_newgetchecknumber);
        time.btn = btn_newgetchecknumber;
        btn_newgetchecknumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time.start();
                et_newphone = view.findViewById(R.id.et_newphone);
                str_phone = et_newphone.getText().toString().trim();
                String postUrl = "http://120.79.87.68:5000/getVerCode";
                final String resetUrl ="http://120.79.87.68:5000/insertUser";
                OkGo.<String>post(postUrl)
                        .params("phone_number",str_phone)
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                GetVerCode getVerCode = new Gson().fromJson(response.body(),GetVerCode.class);
                                switch (getVerCode.code){
                                    case "1":
                                        ToastUtils.showShort("验证码发送成功！");
                                        btn_newpass.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                et_newpass = view.findViewById(R.id.et_newpass);
                                                et_newcheckpass = view.findViewById(R.id.et_newcheckpass);
                                                et_newchecknumber = view.findViewById(R.id.et_newchecknumber);

                                                str_pass = et_newpass.getText().toString().trim();
                                                str_checkpass = et_newcheckpass.getText().toString().trim();
                                                str_checknumber = et_newchecknumber.getText().toString().trim();
                                                if(str_pass.equals(str_checkpass)) {
                                                    OkGo.<String>post(resetUrl)
                                                            .params("phone_number",str_phone)
                                                            .params("password",str_phone)
                                                            .params("check_number",str_checknumber)
                                                            .execute(new StringCallback() {
                                                                @Override
                                                                public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                                                                    UserBean userBean = new Gson().fromJson(response.body(),UserBean.class);
                                                                    if (userBean.code.equals("1")) {
                                                                        ToastUtils.showShort("密码重置成功！");
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
                                        break;
                                    default:
                                }
                            }
                        });
            }
        });
        return view;
    }

}

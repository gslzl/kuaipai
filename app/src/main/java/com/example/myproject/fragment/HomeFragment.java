package com.example.myproject.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myproject.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    View view;
    String getUrl;
    String inforUrl;
    List<String> imgesUrl = new ArrayList<>();
    List<String> imgesID = new ArrayList<>();
//    @InjectView(R.id.banner)
//    FlyBanner banner;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
//        ButterKnife.inject(this, view);
//        initBanner();
        return view;
    }

//    private void initBanner() {
//        getUrl = "http://120.79.87.68:5000/getBanner";
//        inforUrl = "http://120.79.87.68:5000/getProduct";
//        OkGo.get(getUrl)
//                .execute(new StringCallback() {
//                    @Override
//                    public void onSuccess(String s, Call call, Response response) {
//                        BannerBean bannerBean = new Gson().fromJson(s, BannerBean.class);
//                        for (BannerBean.bannerBean element : bannerBean.getData()) {
//                            imgesUrl.add(element.getPicture());
//                            imgesID.add(element.getID());
//                        }
//                        banner.setImagesUrl(imgesUrl);
//                    }
//                });
//        banner.setOnItemClickListener(new FlyBanner.OnItemClickListener() {
//            @Override
//            public void onItemClick(int position) {
//                OkGo.post(inforUrl)
//                        .params("ID", imgesID.get(position))
//                        .execute(new StringCallback() {
//                            @Override
//                            public void onSuccess(String s, Call call, Response response) {
//                                LogUtils.i(s);
//                            }
//                        });
//            }
//        });
//    }

//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        ButterKnife.reset(this);
//    }
//}
}

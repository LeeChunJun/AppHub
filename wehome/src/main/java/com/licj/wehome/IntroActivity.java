package com.licj.wehome;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.licj.wehome.adapter.GlideImageLoader;
import com.licj.wehome.adapter.IndexVRHomeAdapter;
import com.licj.wehome.bean.VRHomeBean;
import com.licj.wehome.commons.DividerItemDecoration;
import com.licj.wehome.commons.OnItemClickListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends AppCompatActivity {

    private Banner indexBanner;
    private RecyclerView mIndexVRHomeRV;
    private IndexVRHomeAdapter mIndexVRHomeAdapter;
    private List<VRHomeBean> VRHomeList;

    private String[] houseUrls = new String[]{"", "", "", "", "", "", "", "", "", "", "", ""};
    private String[] houseNames = new String[]{"联邦高登房户Ⅰ型", "联邦高登房户Ⅱ型", "联邦高登房户Ⅲ型", "联邦高登房户Ⅳ型", "联邦高登房户Ⅴ型", "联邦高登房户Ⅵ型", "联邦高登房户Ⅶ型", "联邦高登房户Ⅷ型", "联邦高登房户Ⅸ型", "联邦高登房户Ⅹ型", "联邦高登房户Ⅺ型", "联邦高登房户Ⅻ型"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        // 首页广告条
        indexBanner = findViewById(R.id.index_banner);
        initIndexBanner();

        // 首页VRHome
        mIndexVRHomeRV = findViewById(R.id.rv_vr_home);
        initIndexVRHome();
    }

    public void initIndexBanner() {
        // 设置接收的图片资源
        List<String> images = new ArrayList<>();
        // 资源文件
        images.add("file:///android_asset/pic/banner1.jpg");
        images.add("file:///android_asset/pic/banner2.jpg");
        images.add("file:///android_asset/pic/banner3.jpg");
        //设置banner样式
        indexBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        indexBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        indexBanner.setImages(images);
        //设置banner动画效果
        indexBanner.setBannerAnimation(Transformer.DepthPage);
        //设置自动轮播，默认为true
        indexBanner.isAutoPlay(true);
        //设置轮播时间
        indexBanner.setDelayTime(1500);
        //设置指示器位置（当banner模式中有指示器时）
        indexBanner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        indexBanner.start();
    }

    public void initIndexVRHome() {
        // 设置当前recyclerview
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        mIndexVRHomeRV.setLayoutManager(layoutManager);

        VRHomeList = new ArrayList<>();
        VRHomeList.add(new VRHomeBean(houseNames[0]));
        VRHomeList.add(new VRHomeBean(houseNames[1]));
        VRHomeList.add(new VRHomeBean(houseNames[2]));
        VRHomeList.add(new VRHomeBean(houseNames[3]));
        VRHomeList.add(new VRHomeBean(houseNames[4]));
        VRHomeList.add(new VRHomeBean(houseNames[5]));
        VRHomeList.add(new VRHomeBean(houseNames[6]));
        VRHomeList.add(new VRHomeBean(houseNames[7]));
        VRHomeList.add(new VRHomeBean(houseNames[8]));
        VRHomeList.add(new VRHomeBean(houseNames[9]));
        VRHomeList.add(new VRHomeBean(houseNames[10]));
        VRHomeList.add(new VRHomeBean(houseNames[11]));
        mIndexVRHomeAdapter = new IndexVRHomeAdapter(getApplicationContext(), VRHomeList);
        mIndexVRHomeAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(IntroActivity.this, WebActivity.class);
                intent.putExtra("HOME_URL", houseUrls[position]);
                startActivity(intent);
            }
        });
        mIndexVRHomeRV.setAdapter(mIndexVRHomeAdapter);
//        mIndexVRHomeRV.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL_LIST));
    }

}

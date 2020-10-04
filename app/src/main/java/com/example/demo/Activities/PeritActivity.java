package com.example.demo.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.demo.Adapters.ViewPagerAdapter;
import com.example.demo.Classes.Perit;
import com.example.demo.R;
import com.example.demo.Utils.ZoomOutPageTransformer;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class PeritActivity extends FragmentActivity {

    private Perit perit;
    // tab titles
    private String[] titles = new String[]{"Info", "Especialitats", "Zones Actuació", "Web"};

    private ViewPager2 viewPager2;

    private ViewPagerAdapter pagerAdapter;

    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        NestedScrollView scrollView = (NestedScrollView) findViewById(R.id.nest_scrollview);
        //enable horizontal scroll to match viewpager properties
        scrollView.setFillViewport(true);

        tabLayout = findViewById(R.id.tab_layout);
        perit = (Perit) getIntent().getSerializableExtra(EspecialitatsPeritsActivity.PERIT_EXTRA);
        toolbar.setTitle(perit.getNom());
        tabLayout.setBackgroundColor(getResources().getColor(R.color.colorAccent));

        viewPager2 = findViewById(R.id.pager);
        pagerAdapter = new ViewPagerAdapter(this, perit);
        viewPager2.setAdapter(pagerAdapter);
        viewPager2.setPageTransformer(new ZoomOutPageTransformer());
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                String title;
                int resId;
                switch (position) {
                    case 0:
                        title = "Info";
                        resId = R.drawable.ic_info;
                        break;
                    case 1:
                        title = "Especialitats";
                        resId = R.drawable.ic_round_build_24;
                        break;
                    case 2:
                        title = "Zones Actuació";
                        resId = R.drawable.ic_pin;
                        break;
                    case 3:
                        title = "Web";
                        resId = R.drawable.ic_baseline_language_24;
                        //resId = R.drawable.ic_link;
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + position);
                }
                tab.setText(title);
                tab.setIcon(resId);
            }
        });
        tabLayoutMediator.attach();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ContactPerit.class);
                startActivity(intent);
            }
        });

    }

}
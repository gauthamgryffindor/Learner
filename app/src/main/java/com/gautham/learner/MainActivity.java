package com.gautham.learner;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.gautham.learner.Fragments.MentorFragment;
import com.gautham.learner.Fragments.SFragmentprofile;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TabLayout tabLayout = findViewById(R.id.Tablayout);
        ViewPager viewpager = findViewById(R.id.view_pager);
        MainActivity.ViewPagerAdapter vpA = new MainActivity.ViewPagerAdapter(getSupportFragmentManager());
        vpA.addfragments(new MentorFragment(),"Mentor");
        vpA.addfragments(new SFragmentprofile(), "profile");
        viewpager.setAdapter(vpA);
        tabLayout.setupWithViewPager(viewpager);

        ;

    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainActivity.this,Mainpage.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                finish();
                return true;

        }
        return false;
    }

    public class ViewPagerAdapter extends FragmentPagerAdapter {
        private ArrayList<Fragment> fragments;
        private ArrayList<String> titles;

        public ViewPagerAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);

            this.fragments=new ArrayList<>();
            this.titles=new ArrayList<>();
        }

        /**
         * Return the Fragment associated with a specified position.
         *
         * @param position
         */
        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        /**
         * Return the number of views available.
         */
        @Override
        public int getCount() {
            return fragments.size();
        }
        public void addfragments(Fragment fragment,String title){
            fragments.add(fragment);
            titles.add(title);

        }

        public CharSequence getPageTitle(int position){
            return titles.get(position);

        }


    }
}
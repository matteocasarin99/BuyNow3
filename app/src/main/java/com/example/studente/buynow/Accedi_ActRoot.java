package com.example.studente.buynow;

import android.content.Intent;
import android.os.StrictMode;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Accedi_ActRoot extends AppCompatActivity {
    static Utenti_Password ut;
    int idUt = 0;
    String password;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            super.onCreate(savedInstanceState);
            setContentView(R.layout.interfaccia_root);
            ut = (Utenti_Password) getIntent().getExtras().getSerializable("Utenti");
            idUt = (Integer) getIntent().getExtras().getSerializable("Id");
            password = (String) getIntent().getExtras().getSerializable("Password");
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
            mViewPager = (ViewPager) findViewById(R.id.container);
            mViewPager.setAdapter(mSectionsPagerAdapter);
            TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
            mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
            tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_accedi__act_root, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settingsRoot) {
            Intent i4 = new Intent(Accedi_ActRoot.this, AccountSettings.class);
            i4.putExtra("Id", idUt);
            i4.putExtra("Utenti", ut);
            i4.putExtra("Password", password);
            startActivity(i4);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static class PlaceholderFragment extends Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_accedi__act_root, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: {
                    TabRoot1 tab1 = new TabRoot1();

                    return tab1;
                }
                case 1: {
                    TabRoot2 tab2 = new TabRoot2();
                    return tab2;
                }
                case 2: {
                    TabRoot3 tab3 = new TabRoot3();
                    return tab3;
                }
                case 3: {
                    TabRoot4 tab4 = new TabRoot4();
                    return tab4;
                }
            }
            return null;
        }

        @Override
        public int getCount() {
            return 4;
        }
    }
}

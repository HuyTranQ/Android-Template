package huytranq.template.views.activities;

import android.content.Context;
import android.os.Handler;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import huytranq.template.R;
import huytranq.template.models.User;
import huytranq.template.presenters.asyncs.InsertUserThread;
import huytranq.template.presenters.utilities.Resource;
import huytranq.template.presenters.utilities.UserDatabase;
import huytranq.template.views.fragments.BaseFragment;
import huytranq.template.views.fragments.FuckFragment;
import huytranq.template.views.fragments.HomeFragment;

public class MainActivity extends AppCompatActivity {

    private static boolean exit = false;
    private DrawerLayout drawerLayout;
    private FrameLayout frameLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapseToolbar;
    private CircleImageView avatar;
    private ImageView background;
    private FragmentManager fragmentManager;
    HashMap<Integer , BaseFragment> mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
    }

    private void initialize() {
        toolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.activity_main_drawerLayout);
        frameLayout = (FrameLayout) findViewById(R.id.activity_main_frameLayout);
        navigationView = (NavigationView) findViewById(R.id.activity_main_navigationView);
        View header = navigationView.getHeaderView(0);
        background = (ImageView) header.findViewById(R.id.background);
        avatar = (CircleImageView) header.findViewById(R.id.avatar);
        fragmentManager = getSupportFragmentManager();
        collapseToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapse);

        setUp();
    }

    private void setUp() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        collapseToolbar.setTitle("Shit");

        mapFragment = BaseFragment.mapFragment(this);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                drawerLayout.closeDrawers();
                replaceFragment(mapFragment.get(item.getItemId()));
                return true;
            }
        });
        navigationView.setCheckedItem(R.id.hello);
        addFragment(mapFragment.get(R.id.hello));
        Picasso.with(this).load(R.drawable.background).into(background);
        Picasso.with(this).load(R.drawable.diablo).into(avatar);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this , drawerLayout , toolbar , R.string.drawer_open , R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (exit) {
            super.onBackPressed();
            UserDatabase.getInstance(MainActivity.this).close();
            return;
        }

        exit = true;
        Toast.makeText(MainActivity.this , "Press \"Back\" again to exit" , Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                exit = false;
            }
        }, 3000);
    }

    void replaceFragment(BaseFragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.activity_main_frameLayout, fragment);
        transaction.commit();
        toolbar.setTitle(fragment.getTitle());
    }

    void addFragment(BaseFragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.activity_main_frameLayout , fragment);
        transaction.commit();
        toolbar.setTitle(fragment.getTitle());
    }
}

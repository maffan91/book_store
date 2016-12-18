package com.app.maffan.bookbank;


import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener,AddUserFragment.OnFragmentInteractionListener,AddBookFragment.OnBookFragmentInteractionListener,AddAuthorFragment.OnAddAuthorFragmentInteractoin {


    private Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.activity_main);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId()){


            case R.id.nav_add_author: {


                fragment = new AddAuthorFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content,fragment).commit();
                break;
            }
            case R.id.nav_add_book:{

                fragment = new AddBookFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content,fragment).commit();
                break;
            }
            case R.id.nav_add_publisher: {


                fragment = new AddUserFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content,fragment).commit();

                break;
            }
            case R.id.nav_all_authors:{

                fragment = new AllAuthorFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content,fragment).commit();

                break;
            }
            case R.id.nav_all_books: {


                break;
            }
            case R.id.nav_all_publishers: {


                fragment = new AllPublisherFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content,fragment).commit();
                break;
            }

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.activity_main);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.activity_main);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onBookFragmentInteractionListener(Uri uri) {

    }

    @Override
    public void onAddAuthorFragmentInteraction(Uri uri) {

    }

//    public void insertPublisher(Publisher publisher){
//
//         publisherDao = daoSession.getPublisherDao();
//        publisherDao.insert(publisher);
//        Log.d("INSERT", "Publisher Inserted!");
//    }
//
//    public void insertBook(Book book){
//
//        BookDao bookDao = daoSession.getBookDao();
//        publisherDao = daoSession.getPublisherDao();
//        bookDao.insert(book);
//
//    }


}

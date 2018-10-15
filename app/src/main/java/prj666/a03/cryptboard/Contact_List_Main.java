package prj666.a03.cryptboard;

import android.content.Intent;
import android.os.Build;
import android.os.Parcelable;
import android.provider.Contacts;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.io.Serializable;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import prj666.a03.cryptboard.ContacBase.Contact;
import prj666.a03.cryptboard.ContacBase.DatabaseHandler;
import prj666.a03.cryptboard.RSAStrings.RSAStrings;
import prj666.a03.cryptboard.cryptboard.frontEndHelper;

public class Contact_List_Main extends AppCompatActivity  {

//    Contact contact_Names;
    String[] all_contacts;
    public static List<Contact> clist;

//    private List<Contact> cont = new ArrayList<Contact>();

//    private ArrayList<Contact> mlist;

    private SectionsPagerAdapter mSectionsPagerAdapter;


    private ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact__list__main);
        getIntent();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DatabaseHandler database = new DatabaseHandler(getApplicationContext());
        Contact tmp1 = new Contact("aleja", false, null, null, null);
        database.insertContact(tmp1);

  //      ArrayList<Contact> all = database.getContactListNames();


//        Bundle bundle = new Bundle();
//        //Contact anyObjectParcelable = new Contact(30, "Thirty");
//        Intent allintent = new Intent(Contact_List_Main.this, Contact_List_Main.class);
//        allintent.putExtra("names", bundle);
//
//        bundle.putStringArray("ContactsName",  all_contacts);
//        Contact_List_Fav fragment = new Contact_List_Fav();
//        fragment.setArguments(bundle);

//
//        Bundle bundle = new Bundle();
//        bundle.putParcelableArrayList("allnames", all);
//
//        // set Fragmentclass Arguments
//        Contact_List_Fav allFragment = new Contact_List_Fav();
//
//        allFragment.setArguments(bundle);



        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));



        //add contact button not implementerd here yet,is on master
        FloatingActionButton add_contact = (FloatingActionButton) findViewById(R.id.fab);
        add_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Create contact not implemented", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    //create menu function
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contact__list__main, menu);
        return true;
    }

    //SEARCH CONTACT
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_search) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

//    private void populateListView() {
//        ArrayAdapter<Contact> adapter = new Contact_List_Adapter(getBaseContext(), cont);
//        //add the list to the adapter
//        for(Contact p: cont)
//        {
//            adapter.add(p);
//        }
//        ListView contactlist = findViewById(android.R.id.list);
//        contactlist.setAdapter(adapter);
//
//    }


    //DatabaseHandler ContactsDB = new DatabaseHandler(getApplicationContext());
//
//    public class SectionsPagerAdapter extends FragmentPagerAdapter {
//
//        public SectionsPagerAdapter(FragmentManager fm) {
//            super(fm);
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//            // getItem is called to instantiate the fragment for the given page.
//            // Return a PlaceholderFragment (defined as a static inner class below).
//
//            switch (position) {
//
//                case 0:
//
//                    //List<Contact> names = ContactsDB.getContactList();
//
//
////                    Contact_List_Fav contact_fav = new Contact_List_Fav();
////
////
////                    return contact_fav;
//                case 1:
//                    Contact_List_All contact_all = new Contact_List_All();
//
//
//                    return contact_all;
//                case 2:
//                    Contact_List_Quick contact_quick = new Contact_List_Quick();
//
//                    return contact_quick;
//
//                default:
//                    return null;
//
//            }
//
//        }
//
//        @Override
//        public int getCount() {
//            // Show 3 total pages.
//            return 3;
//        }
//    }
}
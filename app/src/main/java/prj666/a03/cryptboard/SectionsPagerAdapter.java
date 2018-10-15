package prj666.a03.cryptboard;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Contacts;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import prj666.a03.cryptboard.ContacBase.Contact;
import prj666.a03.cryptboard.ContacBase.DatabaseHandler;
import prj666.a03.cryptboard.cryptboard.frontEndHelper;

public class SectionsPagerAdapter extends FragmentPagerAdapter {




    public SectionsPagerAdapter(FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).

        switch (position) {

            case 0:
               Contact_List_Fav contact_fav = new Contact_List_Fav();


                return contact_fav;
            case 1:
                Contact_List_All contact_all = new Contact_List_All();

                return contact_all;
            case 2:
                Contact_List_Quick contact_quick = new Contact_List_Quick();

                return contact_quick;

            default:
                return null;

        }

    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
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


}

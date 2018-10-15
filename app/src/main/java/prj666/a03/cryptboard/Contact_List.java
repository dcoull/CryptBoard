package prj666.a03.cryptboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.List;

import prj666.a03.cryptboard.ContacBase.Contact;
import prj666.a03.cryptboard.ContacBase.DatabaseHandler;

public class Contact_List extends AppCompatActivity {

    ListView contacts;
    ImageButton favs;
    ImageButton all_co;
    ImageButton lasts;

    String[] namesAll;
    String[] favorites;
    String[] Quick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact__list);

        DatabaseHandler database = new DatabaseHandler(getApplicationContext());
//        Contact tmp1 = new Contact("Emile", true, "3dfas12", "hdtgr", "asdfgas");
//        Contact tmp2 = new Contact("Alejandra", false, "asdf", "hedtgr", "asdfxxgas");
//        Contact tmp3 = new Contact("Dillon", false, "asdf", "hdtddgr", "asdfvgas");
//        Contact tmp4 = new Contact("Thomas", false, "3dfcvsfsas12", "hdzxtgr", "basdfgas");
//        Contact tmp5 = new Contact("Ryan", false, "3dfassadf12", "hdctgr", "zasdfgas");
//        Contact tmp6 = new Contact("muchtar", false, "3dfafggs12", "hdtdgr", "asdrgzfgas");
//
//        database.insertContact(tmp1);
//        database.insertContact(tmp2);
//        database.insertContact(tmp3);
//        database.insertContact(tmp4);
//        database.insertContact(tmp5);
//        database.insertContact(tmp6);



        List<String> All_c = database.getContactListNames();
        namesAll = All_c.toArray(new String[0]);

        List<String> fav_c = database.getContactListNamesFav();
        favorites = fav_c.toArray(new String[0]);

        Contact_List_Adapter adapter = new Contact_List_Adapter(this, namesAll);
        contacts = findViewById(R.id.Contact_List);
        contacts.setAdapter(adapter);


        favs = findViewById(R.id.Favorites);
        all_co = findViewById(R.id.All);
        lasts = findViewById(R.id.Quick);

        contacts = findViewById(R.id.Contact_List);

        favs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contact_List_Adapter a = new Contact_List_Adapter(Contact_List.this, favorites);
                contacts = findViewById(R.id.Contact_List);
                contacts.setAdapter(a);
            }
        });


        all_co.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contact_List_Adapter a = new Contact_List_Adapter(Contact_List.this, namesAll);
                contacts = findViewById(R.id.Contact_List);
                contacts.setAdapter(a);
            }
        });


        lasts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contact_List_Adapter a = new Contact_List_Adapter(Contact_List.this, namesAll);
                contacts = findViewById(R.id.Contact_List);
                contacts.setAdapter(a);
            }
        });



    }
}

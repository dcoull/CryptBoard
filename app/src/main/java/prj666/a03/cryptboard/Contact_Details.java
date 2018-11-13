package prj666.a03.cryptboard;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.WriterException;

import prj666.a03.cryptboard.ContactBase.Contact;
import prj666.a03.cryptboard.ContactBase.DatabaseHandler;

public class Contact_Details extends AppCompatActivity {
    Contact tmp;
    TextView name;
    TextView date;
    ImageView img, favourite;
    Button showQRButton;
    Button editContactButton;
    Button deleteContactButton;

    frontEndHelper frontEndH;
    DatabaseHandler dbH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tmp = (Contact) getIntent().getSerializableExtra("contact");
        setContentView(R.layout.activity_contact__details);
        name = (TextView)findViewById(R.id.Contact_Deatil_name);
        date = (TextView)findViewById(R.id.Contact_key_date);
        img = (ImageView)findViewById(R.id.Contact_Detail_Picture);
        showQRButton = (Button) findViewById(R.id.showQRButton);
        editContactButton = (Button) findViewById(R.id.editContactButton);
        deleteContactButton = (Button) findViewById(R.id.deleteContactButton);
        favourite = findViewById(R.id.imageView3);

        if(tmp.getContactPubKey()!=null){
        try {
            img.setImageBitmap(QRCodeGenerator.encodeAsBitmap(tmp.getContactPubKey()));
        } catch (WriterException e) {
            e.printStackTrace();
        }}
        name.setText(tmp.getName());
        date.setText(tmp.getDateCreated());

        if (tmp.isFavourite() == true){
            favourite.setImageResource(R.drawable.favourite_selected_24dp);
        } else {
            favourite.setImageResource(R.drawable.favourite_unselected_24dp);
        }


        showQRButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // show QR code
                // TO DO:
                //       1) launch activity to show QR
                //           (make sure uses current value for tmp in  case it was changed
            }
        });

        editContactButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent editContactIntent = new Intent(Contact_Details.this, Contact_Edit_Details.class);
                editContactIntent.putExtra("contactToEdit", tmp);
                startActivityForResult(editContactIntent, 1);
            }
        });

        deleteContactButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // delete contact code
                // TO DO:
                //       1) "are you sure" type popup
                //       2) delete contact from database
                //       3) return to previous activity

                frontEndH = frontEndHelper.getInstance();
                frontEndH.deleteContact(tmp);
                finish();
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                if ((boolean) data.getSerializableExtra("changedStatus") == true) {

                    tmp = (Contact) data.getSerializableExtra("updatedContactInfo");

                    frontEndH = frontEndHelper.getInstance();
                    frontEndH.updateName(tmp, name.getText().toString());
                    frontEndH.updateContact(tmp);

                    //dbH = DatabaseHandler.getInstance(this);
                    //dbH.updateContact(tmp);
                    //finish();

                    name.setText(tmp.getName());
                    date.setText(tmp.getDateCreated());

                    if (tmp.isFavourite() == true){
                        favourite.setImageResource(R.drawable.favourite_selected_24dp);
                    }
                    else {
                        favourite.setImageResource(R.drawable.favourite_unselected_24dp);
                    }

                    Toast.makeText(this, "Contact Updated", Toast.LENGTH_SHORT).show();
                }
                else if ((boolean) data.getSerializableExtra("changeStatus") == false){
                    Toast.makeText(this, "Contact Unchanged", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this, "Error processing change", Toast.LENGTH_SHORT).show();
                }


            }
            if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this, "RESULT_CANCELED", Toast.LENGTH_SHORT).show();
            }
        }
    }//onActivityResult

}

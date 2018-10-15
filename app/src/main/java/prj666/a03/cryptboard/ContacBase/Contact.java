package prj666.a03.cryptboard.ContacBase;


import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Contact implements Parcelable  {
    // keyFile contains the name of the file containing public and private key information .it is not the key itself. file extraction code must be implemented.
    public String name;
    private boolean favourite;
    private String myPrivKey;
    private String contactPubKey;
    private String dateCreated;


    public Contact() {
        this.name = null;
    }

    /*
        // used for contact creation
        public Contact(String name, Boolean favourite) {
            this.name = name;
            this.favourite = favourite;
        }
    */
    // used for contact creation
    public Contact(String name, Boolean favourite, String myKeyFile, String theirKeyFile) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();

        this.name = name;
        this.favourite = favourite;
        this.myPrivKey = myKeyFile;
        this.contactPubKey = theirKeyFile;
        this.dateCreated = dateFormat.format(date);
    }

    // used for fetching from database
    public Contact(String name, Boolean favourite, String myPrivKey, String contactPubKey, String date) {
        this.name = name;
        this.favourite = favourite;
        this.myPrivKey = myPrivKey;
        this.contactPubKey = contactPubKey;
        this.dateCreated = date;
    }
//original
    public Contact(Parcel source) {

        this.name = source.readString();

    }
//    public Contact(Parcel source) {
//
//        super();
//        readFromParcel(source);
//
//    }
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFavourite() {
        return this.favourite;
    }

    public int isFavouriteInt() {
        int f;
        if (isFavourite()) {
            f = 1;
        } else {
            f = 0;
        }
        return f;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }

    public String getMyPrivKey() {
        return this.myPrivKey;
    }

    public void setMyPrivKey(String keyFile) {
        this.myPrivKey = keyFile;
    }

    public String getContactPubKey() {
        return this.contactPubKey;
    }

    public void setContactPubKey(String keyFile) {
        this.contactPubKey = keyFile;
    }

//  no set date because creation date should remain the same

    public String getDateCreated() {
        return this.dateCreated;
    }


    public static final Parcelable.Creator<Contact> CREATOR = new Parcelable.Creator<Contact>() {
        public Contact createFromParcel(Parcel in) {

            return new Contact(in);
        }

        public Contact[] newArray(int size) {

            return new Contact[size];
        }

    };
//
    @Override
    public int describeContents() {
        return 0;
    }

    public void readFromParcel(Parcel in) {
        name = in.readString();


    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }
}
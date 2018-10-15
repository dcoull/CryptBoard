package prj666.a03.cryptboard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddContact extends AppCompatActivity {

    Button keyExchange, doneButton;
    EditText contactName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        keyExchange = findViewById(R.id.keyButton);
        doneButton = findViewById(R.id.doneButton);
        contactName = findViewById(R.id.contactName);

        keyExchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = Intent(this, )
            }
        });
    }
}

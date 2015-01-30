package com.polidea.parceler;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;


public class RegistrationActivity extends ActionBarActivity {

    EditText firstNameEditText, lastNameEditText, howOldEditText;

    CheckBox isHumanCheckbox;

    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        firstNameEditText = (EditText) findViewById(R.id.name);
        lastNameEditText = (EditText) findViewById(R.id.lastname);
        howOldEditText = (EditText) findViewById(R.id.how_old_are_you);
        isHumanCheckbox = (CheckBox) findViewById(R.id.is_human);
        registerButton = (Button) findViewById(R.id.register);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParcelerRegistrationForm parcelerRegistrationForm = createParcelerRegistrationForm();
                ParcelableRegistrationForm parcelableRegistrationForm = createParcelableRegistrationForm();
                startActivity(RegistrationConfirmationActivity.IntentFactory
                        .forRegistrationForm(RegistrationActivity.this, parcelableRegistrationForm, parcelerRegistrationForm));
            }
        });
    }

    private ParcelerRegistrationForm createParcelerRegistrationForm() {
        ParcelerRegistrationForm parcelerRegistrationForm = new ParcelerRegistrationForm();
        parcelerRegistrationForm.age = getAge();
        parcelerRegistrationForm.firstName = firstNameEditText.getText().toString();
        parcelerRegistrationForm.lastName = lastNameEditText.getText().toString();
        parcelerRegistrationForm.isHuman = isHumanCheckbox.isChecked();
        return parcelerRegistrationForm;
    }

    private ParcelableRegistrationForm createParcelableRegistrationForm() {
        ParcelableRegistrationForm parcelableRegistrationForm = new ParcelableRegistrationForm();
        parcelableRegistrationForm.age = getAge();
        parcelableRegistrationForm.firstName = firstNameEditText.getText().toString();
        parcelableRegistrationForm.lastName = lastNameEditText.getText().toString();
        parcelableRegistrationForm.isHuman = isHumanCheckbox.isChecked();
        return parcelableRegistrationForm;
    }

    private int getAge() {
        String ageString = howOldEditText.getText().toString();
        return TextUtils.isEmpty(ageString) ? 0 : Integer.valueOf(ageString);
    }
}

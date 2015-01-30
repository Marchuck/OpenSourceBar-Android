package com.polidea.parceler;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;
import org.parceler.Parcels;

public class RegistrationConfirmationActivity extends ActionBarActivity {

    private static final String PARCELABLE = "PARCELABLE";

    private static final String PARCELER = "PARCELER";

    public static class IntentFactory {

        public static Intent forRegistrationForm(Context context, ParcelableRegistrationForm parcelable, ParcelerRegistrationForm parceler) {
            Intent intent = new Intent(context, RegistrationConfirmationActivity.class);
            intent.putExtra(PARCELABLE, parcelable);
            intent.putExtra(PARCELER, Parcels.wrap(parceler));
            return intent;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_confirmation);

        Bundle extras = getIntent().getExtras();

        ParcelableRegistrationForm parcelableRegistrationForm = extras.getParcelable(PARCELABLE);
        TextView parcelableTextView = (TextView) findViewById(R.id.parcelable);
        parcelableTextView.setText(parcelableRegistrationForm.toString());

        ParcelerRegistrationForm parcelerRegistrationForm = Parcels.unwrap(extras.getParcelable(PARCELER));
        TextView parcelerTextView = (TextView) findViewById(R.id.parceler);
        parcelerTextView.setText(parcelerRegistrationForm.toString());
    }
}

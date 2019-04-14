package io.github.neelkamath.mits;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    CheckBox mit1CheckBox;
    CheckBox mit2CheckBox;
    CheckBox mit3CheckBox;
    EditText mit1;
    EditText mit2;
    EditText mit3;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mit1CheckBox = findViewById(R.id.mit1CheckBox);
        mit2CheckBox = findViewById(R.id.mit2CheckBox);
        mit3CheckBox = findViewById(R.id.mit3CheckBox);
        mit1 = findViewById(R.id.mit1EditText);
        mit2 = findViewById(R.id.mit2EditText);
        mit3 = findViewById(R.id.mit3EditText);
        sharedPref = getSharedPreferences("mits", Context.MODE_PRIVATE);

        mit1CheckBox.setChecked(sharedPref.getBoolean("mit1CheckBox", false));
        mit2CheckBox.setChecked(sharedPref.getBoolean("mit2CheckBox", false));
        mit3CheckBox.setChecked(sharedPref.getBoolean("mit3CheckBox", false));
        mit1.setText(sharedPref.getString("mit1", ""), TextView.BufferType.EDITABLE);
        mit2.setText(sharedPref.getString("mit2", ""), TextView.BufferType.EDITABLE);
        mit3.setText(sharedPref.getString("mit3", ""), TextView.BufferType.EDITABLE);

        for (EditText mit : new EditText[]{mit1, mit2, mit3}) {
            mit.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    saveData();
                }

                @Override
                public void afterTextChanged(Editable editable) {
                }
            });
        }
        for (CheckBox checkBox : new CheckBox[]{mit1CheckBox, mit2CheckBox, mit3CheckBox}) {
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    saveData();
                }
            });
        }

    }

    public void openGuide(View view) {
        String url = "https://zenhabits.net/purpose-your-day-most-important-task/";
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }

    public void saveData() {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("mit1CheckBox", mit1CheckBox.isChecked());
        editor.putBoolean("mit2CheckBox", mit2CheckBox.isChecked());
        editor.putBoolean("mit3CheckBox", mit3CheckBox.isChecked());
        editor.putString("mit1", mit1.getText().toString());
        editor.putString("mit2", mit2.getText().toString());
        editor.putString("mit3", mit3.getText().toString());
        editor.apply();
    }

    public void resetActivities(View view) {
        for (EditText editText : new EditText[]{mit1, mit2, mit3}) {
            editText.setText("");
        }
        for (CheckBox checkBox : new CheckBox[]{mit1CheckBox, mit2CheckBox, mit3CheckBox}) {
            checkBox.setChecked(false);
        }
    }
}

package com.android.example.practical10;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;

public class NoteActivity extends AppCompatActivity {

    private static final String EXTRA_NOTE_TITLE = "extra_note_title", EXTRA_NOTE_BODY = "extra_note_body";
    private TextInputEditText mTitleEditText, mBodyEditText;

    public static Intent getIntent(Context packageContext, Note note) {
        Intent intent = new Intent(packageContext, NoteActivity.class);
        if (note != null) {
            intent.putExtra(EXTRA_NOTE_TITLE, note.getTitle());
            intent.putExtra(EXTRA_NOTE_BODY, note.getBody());
        }
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        wireUpWidgets();
        updateWidgets();
    }

    private void updateWidgets() {
        String title = getIntent().getStringExtra(EXTRA_NOTE_TITLE);
        String body = getIntent().getStringExtra(EXTRA_NOTE_BODY);
        if (title != null && body != null) {
            mTitleEditText.setText(title);
            mBodyEditText.setText(body);
        }
    }

    private void wireUpWidgets() {
        mTitleEditText = findViewById(R.id.title_edit_text);
        mBodyEditText = findViewById(R.id.body_edit_text);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_note, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.add_note_item:
                saveNote();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveNote() {
        String title = Objects.requireNonNull(mTitleEditText.getText()).toString().trim();
        String body = Objects.requireNonNull(mBodyEditText.getText()).toString().trim();
        if (title.length() == 0)
            mTitleEditText.setError("Empty field");
        if (body.length() == 0)
            mBodyEditText.setError("Empty field");
        if (title.length() > 0 && body.length() > 0) {
            if (NotesLab.getInstance(this).addNote(new Note(title, body)))
                onBackPressed();
        }
    }
}

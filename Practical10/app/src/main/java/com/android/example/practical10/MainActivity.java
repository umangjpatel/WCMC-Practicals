package com.android.example.practical10;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Note> mNotesList;

    private RecyclerView mNotesRecyclerView;
    private FloatingActionButton mNewNoteFab;

    private NotesAdapter mNotesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wireUpWidgets();
        updateUI();
        setListeners();
        NotesLab.getInstance(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
    }

    private void setListeners() {
        mNewNoteFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(NoteActivity.getIntent(MainActivity.this, null));
            }
        });
    }

    private void updateUI() {
        mNotesList = NotesLab.getInstance(this).getNotesList();
        mNotesList = NotesLab.getInstance(this).getNotesList();
        mNotesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mNotesAdapter = new NotesAdapter(mNotesList);
        mNotesRecyclerView.setAdapter(mNotesAdapter);
    }

    private void wireUpWidgets() {
        mNotesRecyclerView = findViewById(R.id.notes_recycler_view);
        mNewNoteFab = findViewById(R.id.new_note_fab);
    }

    private class NotesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Note mNote;

        private AppCompatTextView mTitleTextView, mBodyTextView;

        public NotesViewHolder(@NonNull LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.note_item, parent, false));
            mTitleTextView = itemView.findViewById(R.id.note_title_text_view);
            mBodyTextView = itemView.findViewById(R.id.note_body_text_view);
            itemView.setOnClickListener(this);
        }

        public void bind(Note note) {
            mNote = note;
            mTitleTextView.setText(mNote.getTitle());
            mBodyTextView.setText(mNote.getBody());
        }

        @Override
        public void onClick(View v) {
            startActivity(NoteActivity.getIntent(MainActivity.this, mNote));
        }
    }

    private class NotesAdapter extends RecyclerView.Adapter<NotesViewHolder> {

        private ArrayList<Note> mNotes;

        public NotesAdapter(ArrayList<Note> notesList) {
            mNotes = notesList;
        }

        @NonNull
        @Override
        public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new NotesViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
            Note note = mNotes.get(position);
            holder.bind(note);
        }

        @Override
        public int getItemCount() {
            return mNotes.size();
        }
    }

}




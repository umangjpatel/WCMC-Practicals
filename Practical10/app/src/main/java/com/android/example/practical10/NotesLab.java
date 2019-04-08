package com.android.example.practical10;

import android.content.Context;

import java.util.ArrayList;

class NotesLab {

    public static NotesLab sNotesLab;

    private DatabaseHelper mDBHelper;


    public static NotesLab getInstance(Context context) {
        if (sNotesLab == null)
            sNotesLab = new NotesLab(context);
        return sNotesLab;
    }

    private NotesLab(Context context) {
        mDBHelper = new DatabaseHelper(context);
    }

    public ArrayList<Note> getNotesList() {
        return mDBHelper.getAllData();
    }

    public boolean addNote(Note note) {
        return mDBHelper.insertData(note.getTitle(), note.getBody());
    }

    public boolean updateNote(Note note) {
        return mDBHelper.updateData(note.getId(), note.getTitle(), note.getBody());
    }


}

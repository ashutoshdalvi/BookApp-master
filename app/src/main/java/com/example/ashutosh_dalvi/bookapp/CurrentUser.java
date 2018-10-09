package com.example.ashutosh_dalvi.bookapp;

import com.google.firebase.auth.FirebaseUser;

public class CurrentUser {
    public static FirebaseUser getFirembaseUser() {
        return firembaseUser;
    }

    public static void setFirembaseUser(FirebaseUser firembaseUser) {
        CurrentUser.firembaseUser = firembaseUser;
    }

    public static FirebaseUser firembaseUser;
}

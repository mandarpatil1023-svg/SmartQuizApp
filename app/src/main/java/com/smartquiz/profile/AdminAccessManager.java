package com.smartquiz.profile;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.smartquiz.R;

public final class AdminAccessManager {

    private AdminAccessManager() {
    }

    public static boolean isOwner(@NonNull Context context) {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser == null || currentUser.getEmail() == null) {
            return false;
        }
        String ownerEmail = context.getString(R.string.owner_admin_email).trim();
        return !ownerEmail.isEmpty() && ownerEmail.equalsIgnoreCase(currentUser.getEmail().trim());
    }
}

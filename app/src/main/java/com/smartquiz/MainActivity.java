package com.smartquiz;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.smartquiz.databinding.ActivityMainBinding;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private final Set<Integer> bottomNavDestinations = new HashSet<>(Arrays.asList(
            R.id.homeFragment,
            R.id.profileFragment
    ));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_SECURE);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        if (navHostFragment == null) {
            return;
        }

        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(binding.bottomNavigation, navController);
        navController.addOnDestinationChangedListener((controller, destination, arguments) ->
                binding.bottomNavigation.setVisibility(
                        bottomNavDestinations.contains(destination.getId()) ? View.VISIBLE : View.GONE
                ));
    }
}

package com.example.muchikapp.ui.salir;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.muchikapp.Idioma;
import com.example.muchikapp.Iniciar;
import com.example.muchikapp.PrefUtil;
import com.example.muchikapp.databinding.FragmentSalirBinding;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.firebase.auth.FirebaseAuth;

public class SalirFragment extends Fragment {
    PrefUtil prefUtil;
    private FragmentSalirBinding binding;
    FirebaseAuth mAuth;
    private SignInClient oneTapClient;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SalirViewModel salirViewModel =
                new ViewModelProvider(this).get(SalirViewModel.class);

        binding = FragmentSalirBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        prefUtil = new PrefUtil(getActivity());
        mAuth = FirebaseAuth.getInstance();
        oneTapClient = Identity.getSignInClient(getContext());
        binding.btnSi.setOnClickListener(v -> {
            mAuth.signOut();
            prefUtil.clearAll();
            oneTapClient.signOut().addOnCompleteListener(task -> {
                irIniciar();
            });
        });
        binding.btnNo.setOnClickListener(v -> {
            irIniciar();
        });
        return root;
    }

    private void irIniciar() {
        Intent intent = new Intent(getActivity(), Idioma.class);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

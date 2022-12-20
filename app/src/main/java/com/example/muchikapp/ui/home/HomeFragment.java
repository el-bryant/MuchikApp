package com.example.muchikapp.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.muchikapp.Rutas;
import com.example.muchikapp.databinding.FragmentGalleryBinding;
import com.example.muchikapp.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.btnPuertoEten.setOnClickListener(v -> {
            getActivity().startActivity(new Intent(getActivity(), Rutas.class).putExtra("ruta", "Puerto Eten"));
        });
        binding.btnCiudadEten.setOnClickListener(v -> {
            getActivity().startActivity(new Intent(getActivity(), Rutas.class).putExtra("ruta", "Ciudad Eten"));
        });
        binding.btnLambayeque.setOnClickListener(v -> {
            getActivity().startActivity(new Intent(getActivity(), Rutas.class).putExtra("ruta", "Lambayeque"));
        });
        binding.btnSanJose.setOnClickListener(v -> {
            getActivity().startActivity(new Intent(getActivity(), Rutas.class).putExtra("ruta", "San José"));
        });
        binding.btnSantaRosa.setOnClickListener(v -> {
            getActivity().startActivity(new Intent(getActivity(), Rutas.class).putExtra("ruta", "Santa Rosa"));
        });
        binding.btnCayalti.setOnClickListener(v -> {
            getActivity().startActivity(new Intent(getActivity(), Rutas.class).putExtra("ruta", "Cayalti"));
        });
        binding.btnMonsefu.setOnClickListener(v -> {
            getActivity().startActivity(new Intent(getActivity(), Rutas.class).putExtra("ruta", "Monsefu"));
        });

//        final TextView textView = binding.textHome;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
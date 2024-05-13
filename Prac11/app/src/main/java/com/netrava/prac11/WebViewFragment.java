package com.netrava.prac11;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.netrava.prac11.databinding.FragmentWebviewBinding;

public class WebViewFragment extends Fragment {
    FragmentWebviewBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentWebviewBinding.inflate(inflater, container, false);

        binding.webView.setWebViewClient(new WebViewClient());
        binding.webView.getSettings().setJavaScriptEnabled(true);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (savedInstanceState == null) {
            binding.webView.loadUrl("https://online-edu.mirea.ru ");
        } else {
            binding.webView.restoreState(savedInstanceState);
        }

        if (savedInstanceState == null) {
            Log.d("SAVED_STRING", "EMPTY SAVEDINSTANCESTATE");
        } else {
            Log.d("SAVED_STRING", savedInstanceState.getString("test", "null"));
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("test", "hello world");

        binding.webView.saveState(outState);
    }
}

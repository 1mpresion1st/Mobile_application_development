package com.netrava.prac11;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.netrava.prac11.databinding.FragmentAnimationsBinding;

public class AnimationsFragment extends Fragment {
    FragmentAnimationsBinding binding;

    boolean isRotating = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAnimationsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Rotation
        ObjectAnimator rotateAnim = ObjectAnimator.ofFloat(binding.imageView, "rotation", 0f, 360f);
        rotateAnim.setDuration(2000);
        rotateAnim.setRepeatCount(ObjectAnimator.INFINITE);
        rotateAnim.setRepeatMode(ObjectAnimator.RESTART);

        // Move
        ObjectAnimator moveAnim = ObjectAnimator.ofFloat(binding.imageView, "translationX", 0f, 300f);
        moveAnim.setDuration(1000);

        // Scale
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(binding.imageView, "scaleX", 1f, 2f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(binding.imageView, "scaleY", 1f, 2f);
        scaleX.setDuration(1000);
        scaleY.setDuration(1000);

        binding.buttonRotate.setOnClickListener(v -> {
            if (!isRotating)
                rotateAnim.start();
            else
                rotateAnim.pause();
            isRotating = !isRotating;
        });

        binding.buttonMove.setOnClickListener(v -> {
            moveAnim.start();
        });

        binding.buttonScale.setOnClickListener(v -> {
            scaleX.start();
            scaleY.start();
        });
    }
}

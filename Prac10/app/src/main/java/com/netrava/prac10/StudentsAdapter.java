package com.netrava.prac10;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.netrava.prac10.databinding.DialogAddEditUserBinding;

import java.util.List;

public class StudentsAdapter extends RecyclerView.Adapter<StudentsAdapter.ViewHolder> {
    private List<Student> students;
    private Activity activity;
    private DatabaseHelper databaseHelper;

    public class ViewHolder extends RecyclerView.ViewHolder {
        com.netrava.prac10.databinding.ItemBinding binding;

        public ViewHolder(View view) {
            super(view);
            binding = com.netrava.prac10.databinding.ItemBinding.bind(view);
        }

        public void bind(Student student) {
            binding.name.setText(student.getName());
            binding.group.setText(student.getGroup());
            binding.email.setText(student.getEmail());

            binding.getRoot().setOnClickListener(v -> {
                DialogAddEditUserBinding dialogBinding = DialogAddEditUserBinding.inflate(activity.getLayoutInflater());
                dialogBinding.emailTextInputLayout.getEditText().setText(student.getEmail());
                dialogBinding.groupTextInputLayout.getEditText().setText(student.getGroup());
                dialogBinding.nameTextInputLayout.getEditText().setText(student.getName());

                new MaterialAlertDialogBuilder(activity).setView(dialogBinding.getRoot()).setTitle("Edit student").setPositiveButton("Add", (dialog, whichButton) -> {
                }).setPositiveButton("Save", (dialog, whichButton) -> {
                    String newName = dialogBinding.nameTextInputLayout.getEditText().getText().toString();
                    String newGroup = dialogBinding.groupTextInputLayout.getEditText().getText().toString();
                    String newEmail = dialogBinding.emailTextInputLayout.getEditText().getText().toString();

                    binding.name.setText(newName);
                    binding.group.setText(newGroup);
                    binding.email.setText(newEmail);

                    databaseHelper.updateStudent(student.getId(), newName, newEmail, newGroup);
                }).setNegativeButton("Cancel", (dialog, whichButton) -> {
                }).create().show();
            });
        }
    }

    public StudentsAdapter(Activity activity, DatabaseHelper databaseHelper, List<Student> students) {
        this.activity = activity;
        this.students = students;
        this.databaseHelper = databaseHelper;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(students.get(position));
    }

    @Override
    public int getItemCount() {
        return students.size();
    }
}

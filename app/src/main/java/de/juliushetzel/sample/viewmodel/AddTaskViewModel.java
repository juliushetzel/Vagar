package de.juliushetzel.sample.viewmodel;


import android.databinding.ObservableField;

import de.juliushetzel.sample.R;
import de.juliushetzel.sample.model.Task;
import de.juliushetzel.sample.model.TaskRepository;
import de.juliushetzel.sample.view.MainActivity;
import jhetzel.vagar.NavigationEvent;
import jhetzel.vagar.ViewModel;

public class AddTaskViewModel extends ViewModel{
    private final TaskRepository mTaskRepository;

    public final ObservableField<String> text = new ObservableField<>("");
    public final ObservableField<Integer> toastMessage = new ObservableField<>();

    public AddTaskViewModel(TaskRepository taskRepository) {
        mTaskRepository = taskRepository;
    }

    public void addTask(){
        if(text.get().isEmpty()){
            toastMessage.set(R.string.task_text_field_empty);
        }else {
            mTaskRepository.addTask(new Task(text.get()));
            navigateTo(new NavigationEvent().setComponentClass(MainActivity.class));
        }
    }
}

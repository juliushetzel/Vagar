package de.juliushetzel.sample.viewmodel;


import android.databinding.ObservableField;

import de.juliushetzel.sample.R;
import de.juliushetzel.sample.model.Task;
import de.juliushetzel.sample.model.AddTaskRepository;
import de.juliushetzel.sample.model.TaskRepositoryImpl;
import de.juliushetzel.sample.view.TaskListActivity;
import jhetzel.vagar.NavigationEvent;
import jhetzel.vagar.ViewModel;

public class AddTaskViewModel extends ViewModel{
    private final AddTaskRepository mAddTaskRepository = TaskRepositoryImpl.getInstance();

    public final ObservableField<String> text = new ObservableField<>("");
    public final ObservableField<Integer> toastMessage = new ObservableField<>();

    public void addTask(){
        if(text.get().isEmpty()){
            toastMessage.set(R.string.task_text_field_empty);
        }else {
            mAddTaskRepository.addTask(new Task(text.get()));
            navigateTo(new NavigationEvent().setComponentClass(TaskListActivity.class));
        }
    }
}

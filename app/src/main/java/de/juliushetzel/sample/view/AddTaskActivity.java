package de.juliushetzel.sample.view;


import android.app.Activity;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import de.juliushetzel.sample.R;
import de.juliushetzel.sample.databinding.ActivityAddTaskBinding;
import de.juliushetzel.sample.model.TaskRepositoryImpl;
import de.juliushetzel.sample.navigator.MainNavigator;
import de.juliushetzel.sample.viewmodel.AddTaskViewModel;
import jhetzel.vagar.Vagar;
import jhetzel.vagar.ViewModel;
import jhetzel.vagar.annotation.Assemble;

@Assemble(
        viewModel = AddTaskViewModel.class,
        layout = R.layout.activity_add_task,
        viewModelTag = "addTaskViewModel",
        navigator = MainNavigator.class

)
public class AddTaskActivity extends Activity implements ViewModel.Factory<ViewModel> {

    ActivityAddTaskBinding mViewBinding;

    Observable.OnPropertyChangedCallback mToastMessageCallback;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewBinding = Vagar.bind(this, this);
        mToastMessageCallback = new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                Toast.makeText(AddTaskActivity.this, ((ObservableField<Integer>) sender).get(), Toast.LENGTH_LONG)
                        .show();
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        mViewBinding.getAddTaskViewModel().toastMessage.addOnPropertyChangedCallback(mToastMessageCallback);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mViewBinding.getAddTaskViewModel().toastMessage.removeOnPropertyChangedCallback(mToastMessageCallback);
    }

    @Override
    public ViewModel createViewModel() {
        return new AddTaskViewModel(TaskRepositoryImpl.getInstance());
    }
}

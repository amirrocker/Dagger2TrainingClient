package de.amirrocker.testdagger2modules.base.presentation.utils;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;

public class ViewModelUtil {

    @Inject
    public ViewModelUtil() {}

    public <T extends ViewModel> ViewModelProvider.Factory createFor(@NonNull final T viewModel) {
        return new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                if(modelClass.isAssignableFrom(viewModel.getClass())) {
                    return (T) viewModel;
                }
                throw new IllegalArgumentException("unexpected viewModel Class ");
            }
        };
    }


}

package use_case.clear_users;

// TODO Complete me

import interface_adapter.clear_users.ClearPresenter;
import use_case.login.LoginOutputData;

public interface ClearOutputBoundary {
    void prepareSuccessView();


    void prepareFailView(String error);
}

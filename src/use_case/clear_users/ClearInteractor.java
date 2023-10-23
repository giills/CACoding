package use_case.clear_users;

import entity.User;
import use_case.login.LoginOutputData;


public class ClearInteractor implements ClearInputBoundary{
    final ClearUserDataAccessInterface userDataAccessObject;
    final ClearOutputBoundary clearPresenter;

    public ClearInteractor(ClearUserDataAccessInterface clearDataAccessInterface,
                           ClearOutputBoundary clearOutputBoundary) {
        this.userDataAccessObject = clearDataAccessInterface;
        this.clearPresenter = clearOutputBoundary;
    }

    @Override
    public void execute() {
        if (userDataAccessObject.isEmpty()) {
            clearPresenter.prepareFailView("No accounts to clear ):");
        } else {
            userDataAccessObject.clear();
            clearPresenter.prepareSuccessView();
        }

    }
}

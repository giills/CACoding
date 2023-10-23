package app;

import data_access.FileUserDataAccessObject;
import interface_adapter.clear_users.ClearController;
import interface_adapter.clear_users.ClearViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import use_case.clear_users.*;
import use_case.signup.SignupUserDataAccessInterface;
import interface_adapter.clear_users.ClearPresenter;
import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.*;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import view.SignupView;
import use_case.clear_users.ClearOutputBoundary;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class SignupUseCaseFactory {

    /** Prevent instantiation. */
    private SignupUseCaseFactory() {}

    public static SignupView create(
            ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, SignupViewModel signupViewModel, SignupUserDataAccessInterface userDataAccessObject, FileUserDataAccessObject userDataAccessObjectClear) {

        try {
            SignupController signupController = createUserSignupUseCase(viewManagerModel, signupViewModel, loginViewModel, userDataAccessObject);
            //ClearController clearController = createClearCase(userDataAccessObjectClear);
            return new SignupView(signupController, signupViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static SignupController createUserSignupUseCase(ViewManagerModel viewManagerModel, SignupViewModel signupViewModel, LoginViewModel loginViewModel, SignupUserDataAccessInterface userDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel, signupViewModel, loginViewModel);

        UserFactory userFactory = new CommonUserFactory();

        SignupInputBoundary userSignupInteractor = new SignupInteractor(
                userDataAccessObject, signupOutputBoundary, userFactory);

        return new SignupController(userSignupInteractor);
    }
    }

    //private static ClearController createClearCase(FileUserDataAccessObject userDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        //ClearOutputBoundary clearOutputBoundary = new ClearPresenter();


        //ClearInputBoundary clearSignupInteractor = new ClearInteractor(userDataAccessObject, clearOutputBoundary);

        //return new ClearController(clearSignupInteractor);
    //}
//}

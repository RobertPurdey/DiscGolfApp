package robert.purdey.caddytracker.ui.viewmodels;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import robert.purdey.caddytracker.networking.HttpClientConfig;
import robert.purdey.caddytracker.networking.RetrofitConfig;
import robert.purdey.caddytracker.networking.contracts.controllers.IAppUserController;
import robert.purdey.caddytracker.networking.controllers.AppUserController;
import robert.purdey.caddytracker.networking.services.ApiCallService;
import robert.purdey.caddytracker.ui.listeners.IApiResponseListener;
import robert.purdey.caddytracker.ui.models.AppUserCreationModel;
import robert.purdey.caddytracker.utilities.Strings;

public class CreateAccountViewModel extends ViewModel
{
    public MutableLiveData<String> loginName;
    public MutableLiveData<String> handle;
    public MutableLiveData<String> password;
    public MutableLiveData<String> confirmPassword;

    private IAppUserController appUserController;

    public CreateAccountViewModel()
    {
        loginName       = new MutableLiveData<>();
        handle          = new MutableLiveData<>();
        password        = new MutableLiveData<>();
        confirmPassword = new MutableLiveData<>();

        // todo: inject the following when possible
        ApiCallService apiCallService = new ApiCallService(
            new RetrofitConfig(),
            new HttpClientConfig()
        );

        appUserController = new AppUserController(apiCallService);
    }

    public void createAccount(IApiResponseListener loginListener)
    {
        AppUserCreationModel creationRequest = new AppUserCreationModel(
            loginName.getValue(),
            handle.getValue(),
            password.getValue(),
            confirmPassword.getValue());

        if ( validateCreationRequest(creationRequest) )
        {
            appUserController.createAccount(creationRequest, loginListener);
        }
        else
        {
            // todo: err msg toast?
        }
    }

    private boolean validateCreationRequest(AppUserCreationModel request)
    {
        return validatePassword(request.Password, request.ConfirmPassword)
            && validateHandle(request.Handle)
            && validateLoginName(request.LoginName);
    }

    private boolean validatePassword(String password, String confirmPassword)
    {
        return !Strings.isNullOrEmpty(password)
            && !Strings.isNullOrEmpty(confirmPassword)
            && password != confirmPassword;

    }

    private boolean validateLoginName(String loginName)
    {
        return !Strings.isNullOrEmpty(loginName);
    }

    private boolean validateHandle(String handle)
    {
        return !Strings.isNullOrEmpty(handle);
    }
}

package robert.purdey.caddytracker.ui.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import robert.purdey.caddytracker.networking.HttpClientConfig;
import robert.purdey.caddytracker.networking.RetrofitConfig;
import robert.purdey.caddytracker.networking.contracts.controllers.IAppUserController;
import robert.purdey.caddytracker.networking.controllers.AppUserController;
import robert.purdey.caddytracker.networking.services.ApiCallService;
import robert.purdey.caddytracker.ui.FrolfApp;
import robert.purdey.caddytracker.ui.listeners.IApiResponseListener;
import robert.purdey.caddytracker.ui.models.AppUserModel;
import robert.purdey.caddytracker.ui.models.AppUserUpdateModel;
import robert.purdey.caddytracker.utilities.Strings;

public class AccountViewModel extends ViewModel
{
    public MutableLiveData<String> loginName;
    public MutableLiveData<String> handle;
    public MutableLiveData<String> friendCode;
    public MutableLiveData<AppUserModel> appUser;

    private IAppUserController appUserController;

    public AccountViewModel()
    {
        loginName  = new MutableLiveData<>();
        handle     = new MutableLiveData<>();
        friendCode = new MutableLiveData<>();

        ApiCallService apiCallService = new ApiCallService(
            new RetrofitConfig(),
            new HttpClientConfig()
        );

        appUserController = new AppUserController(apiCallService);
    }

    public LiveData<AppUserModel> getAccountInfo(IApiResponseListener listener)
    {
        appUser = appUserController.getCurrentUserInfo(listener);

        return appUser;
    }

    public void setAccountInfo(AppUserModel model)
    {
        loginName.setValue( model.getLoginName() );
        handle.setValue( model.getHandle() );
        friendCode.setValue( model.getFriendCode() );
    }

    public void updateAccount(IApiResponseListener updateAccountListener)
    {
        AppUserUpdateModel updateRequest = new AppUserUpdateModel();

        updateRequest.setIdKey(FrolfApp.getUserSession().getCurrentUserId());
        updateRequest.setLoginName(loginName.getValue());
        updateRequest.setHandle(handle.getValue());

        if ( validateUpdateRequest(updateRequest) )
        {
            appUserController.updateAccount(updateRequest, updateAccountListener);
        }
        else
        {
            // todo: err msg toast?
        }
    }

    private boolean validateUpdateRequest(AppUserUpdateModel request)
    {
        return validateHandle(request.Handle)
            && validateLoginName(request.LoginName);
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

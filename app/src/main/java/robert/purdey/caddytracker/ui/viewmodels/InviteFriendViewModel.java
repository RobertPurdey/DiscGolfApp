package robert.purdey.caddytracker.ui.viewmodels;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.UUID;

import robert.purdey.caddytracker.networking.HttpClientConfig;
import robert.purdey.caddytracker.networking.RetrofitConfig;
import robert.purdey.caddytracker.networking.contracts.controllers.IFrolfGroupInviteController;
import robert.purdey.caddytracker.networking.controllers.FrolfGroupInviteController;
import robert.purdey.caddytracker.networking.services.ApiCallService;
import robert.purdey.caddytracker.ui.models.InviteCreationModel;

public class InviteFriendViewModel  extends ViewModel
{
   // public MutableLiveData<String> friendCode;
    //private IFrolfGroupInviteController frolfGroupInviteController;

    public InviteFriendViewModel()
    {
        // todo: inject the following when possible
      //  ApiCallService apiCallService = new ApiCallService(
      //      new RetrofitConfig(),
      //      new HttpClientConfig()
      //  );

       // frolfGroupInviteController = new FrolfGroupInviteController(apiCallService);
    }

    public void sendInvite(UUID groupId)
    {
       /// InviteCreationModel creationModel = new InviteCreationModel();
       // creationModel.setGroupId( groupId);
       // creationModel.setFriendCode( friendCode.getValue() );

        //frolfGroupInviteController.send(creationModel);
    }
}

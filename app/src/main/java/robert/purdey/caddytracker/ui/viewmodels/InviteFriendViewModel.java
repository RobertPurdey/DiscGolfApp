package robert.purdey.caddytracker.ui.viewmodels;

import androidx.lifecycle.ViewModel;

import java.util.UUID;


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

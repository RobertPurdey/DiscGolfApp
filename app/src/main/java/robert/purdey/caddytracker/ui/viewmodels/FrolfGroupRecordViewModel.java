package robert.purdey.caddytracker.ui.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.UUID;

import robert.purdey.caddytracker.networking.HttpClientConfig;
import robert.purdey.caddytracker.networking.RetrofitConfig;
import robert.purdey.caddytracker.networking.contracts.controllers.IFrolfGroupController;
import robert.purdey.caddytracker.networking.controllers.FrolfGroupController;
import robert.purdey.caddytracker.networking.services.ApiCallService;
import robert.purdey.caddytracker.ui.models.FrolfGroupModel;

public class FrolfGroupRecordViewModel extends ViewModel
{
    public MutableLiveData<String> groupId;
    public MutableLiveData<String> groupName;

    private MutableLiveData<FrolfGroupModel> frolfGroup;
    private IFrolfGroupController frolfGroupController;

    public FrolfGroupRecordViewModel()
    {
        groupId   = new MutableLiveData<>();
        groupName = new MutableLiveData<>();

        // todo: inject the following when possible
        ApiCallService apiCallService = new ApiCallService(
            new RetrofitConfig(),
            new HttpClientConfig()
        );

        frolfGroupController = new FrolfGroupController(apiCallService);
    }

    public LiveData<FrolfGroupModel> getFrolfGroup(UUID id)
    {
        // todo: maybe this check requires isDirty?? -- do i need this null check? do i care?
        //if (frolfGroup == null)
        //{
            frolfGroup = new MutableLiveData<>();
            loadFrolfGroup(id);
        //}

        return frolfGroup;
    }

    public LiveData<FrolfGroupModel> insert()
    {
        FrolfGroupModel model = new FrolfGroupModel();
        model.setName(groupName.getValue());

        frolfGroup = frolfGroupController.insert(model);

        return frolfGroup;
    }

    public LiveData<FrolfGroupModel> update()
    {
        FrolfGroupModel model = new FrolfGroupModel();
        model.setIdKey( UUID.fromString( groupId.getValue() ) );
        model.setName( groupName.getValue() );

        frolfGroup = frolfGroupController.insert(model);

        return frolfGroup;
    }

    private void loadFrolfGroup(UUID id)
    {
        frolfGroup = frolfGroupController.getFrolfGroup(id);

        groupId.setValue( frolfGroup.getValue().getIdKey().toString() );
        groupName.setValue( frolfGroup.getValue().getName() );
    }
}

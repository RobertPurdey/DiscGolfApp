package robert.purdey.caddytracker.ui.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import robert.purdey.caddytracker.ui.models.FriendModel;

/**
 * Represents an application user
 */
public class FriendsViewModel extends ViewModel
{
    private MutableLiveData<List<FriendModel>> friends;

    public LiveData<List<FriendModel>> getFriends()
    {
        // todo: maybe this check requires isDirty??
        if (friends == null)
        {
            friends = new MutableLiveData<>();

            loadFriends();
        }

        return friends;
    }

    private void loadFriends()
    {
        // todo: this needs to be an api call
        List<FriendModel> foundFriends = new ArrayList<FriendModel>()
        {{
            add(
                new FriendModel()
                {{
                    setIdKey(UUID.fromString("fab09466-a3e4-4d62-b77f-a974824cea9d"));
                    setNickName("Katie");
                }}
            );

            add(
                new FriendModel()
                {{
                    setIdKey(UUID.fromString("cdaa11e6-d3af-4865-8470-e10d9122e9b3"));
                    setNickName("Rob");
                }}
            );

        }};

        friends.setValue(foundFriends);
    }
}


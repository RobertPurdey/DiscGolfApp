package robert.purdey.caddytracker.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import robert.purdey.caddytracker.R;
import robert.purdey.caddytracker.ui.fragments.FriendListFragment;

public class ManageFriendsActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_friends);

        if (savedInstanceState == null)
        {
            // Setup friend List Fragment
            FriendListFragment friendListFrag = new FriendListFragment();

            getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.mng_friends_select_fragment, friendListFrag)
                .commit();
        }
    }
}

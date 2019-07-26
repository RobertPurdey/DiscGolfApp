package robert.purdey.caddytracker.UserActions;

import androidx.test.espresso.contrib.RecyclerViewActions;

import robert.purdey.caddytracker.R;
import robert.purdey.caddytracker.ViewAssertions.RecyclerViewItemCountAssertion;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static robert.purdey.caddytracker.EsspressoHelper.atPositionOnView;
import static robert.purdey.caddytracker.EsspressoHelper.clickNestedViewWithId;

public class InviteScreen
{
    public static void acceptInvite(final int pos)
    {
        onView( withId(R.id.rcvw_fragment_frolf_group_invites) )
            .perform(actionOnItemAtPosition(pos, clickNestedViewWithId(R.id.bttn_row_item_frolf_group_invite_accept_invite) ) );
    }

    public static void declineInvite(final int pos)
    {
        onView( withId(R.id.rcvw_fragment_frolf_group_invites) )
            .perform(actionOnItemAtPosition(pos, clickNestedViewWithId(R.id.bttn_row_item_frolf_group_invite_decline_invite) ) );
    }

    public static void countInvites(final int expectedCount)
    {
        onView( withId(R.id.rcvw_fragment_frolf_group_invites) )
            .check( new RecyclerViewItemCountAssertion(expectedCount) );
    }

    public static void matchInviteInviterName(final int pos, String inviterName)
    {
        onView( withId(R.id.rcvw_fragment_frolf_group_invites) )
            .check( matches( atPositionOnView(pos, withText(inviterName), R.id.txtv_row_item_frolf_group_inviter_handle) ) );
    }

    public static void matchInviteGroupName(final int pos, String groupName)
    {
        onView( withId(R.id.rcvw_fragment_frolf_group_invites) )
            .check( matches( atPositionOnView(pos, withText(groupName), R.id.txtv_row_item_frolf_group_invite_group_name) ) );
    }
}

package robert.purdey.caddytracker.UserActions;

import robert.purdey.caddytracker.R;
import robert.purdey.caddytracker.ViewAssertions.RecyclerViewItemCountAssertion;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class ManageGroupScreen
{
    public static void goToNewGroupCreation()
    {
        onView( withId(R.id.bttn_activity_manage_frolf_groups_new_frolf_group) )
            .perform( click() );
    }

    public static void countGroups(int expectedMemberCount)
    {
        onView( withId(R.id.rcvw_frolf_group_recycle_view) )
            .check( new RecyclerViewItemCountAssertion(expectedMemberCount) );
    }

    public static void goToFrolfGroup(int position)
    {
        onView( withId(R.id.rcvw_frolf_group_recycle_view) )
            .perform( actionOnItemAtPosition(position, click() ) );
    }
}

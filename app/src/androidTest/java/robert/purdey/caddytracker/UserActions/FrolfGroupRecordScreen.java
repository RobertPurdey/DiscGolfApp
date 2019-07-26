package robert.purdey.caddytracker.UserActions;

import robert.purdey.caddytracker.R;
import robert.purdey.caddytracker.ViewAssertions.RecyclerViewItemCountAssertion;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static robert.purdey.caddytracker.EsspressoHelper.atPositionOnView;

public class FrolfGroupRecordScreen
{

    public static void createFrolfGroup(String groupName)
    {
        setGroupName(groupName);
        pressBack();
        clickSaveGroup();
    }

    public static void validateFrolfGroupRecord(
        String expectedGroupName,
        String firstHandle,
        String secondHandle)
    {
        matchGroupName(expectedGroupName);
        countMembers(2);
        matchMemberHandle(0, firstHandle);
        matchMemberHandle(1, secondHandle);
    }

    public static void matchGroupName(String expectedGroupName)
    {
        onView( withId(R.id.etxt_activity_frolf_group_record_group_name) )
            .check( matches( withText(expectedGroupName) ) );
    }

    public static void setGroupName(String groupName)
    {
        onView( withId(R.id.etxt_activity_frolf_group_record_group_name) )
            .perform( clearText() )
            .perform( typeText(groupName) );
    }

    public static void countMembers(int expectedMemberCount)
    {
        onView( withId(R.id.rcvw_fragment_frolf_group_members) )
            .check( new RecyclerViewItemCountAssertion(expectedMemberCount) );
    }

    public static void clickSaveGroup()
    {
        onView( withId(R.id.bttn_activity_frolf_group_record_create_frolf_group) )
            .perform( click() );
    }

    public static void clickCourses()
    {
        onView( withId(R.id.bttn_frolf_group_record_manage_courses) )
            .perform( click() );
    }

    public static void setFriendCode(String friendCode)
    {
        onView( withId(R.id.etxt_activity_frolf_group_record_friend_code) )
            .perform( clearText() )
            .perform( typeText(friendCode) );
    }

    public static void clickSendInvite()
    {
        onView( withId(R.id.bttn_activity_frolf_group_record_add_friend) )
            .perform( click() );
    }

    public static void sendInvite(String friendCode)
    {
        setFriendCode(friendCode);
        clickSendInvite();
    }

    public static void matchMemberHandle(final int pos, String expectedHandle)
    {
        onView( withId(R.id.rcvw_fragment_frolf_group_members) )
            .check( matches( atPositionOnView(pos, withText(expectedHandle), R.id.txtv_row_item_frolf_group_member_handle) ) );
    }
}

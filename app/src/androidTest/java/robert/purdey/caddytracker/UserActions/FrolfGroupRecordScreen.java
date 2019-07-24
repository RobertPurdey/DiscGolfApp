package robert.purdey.caddytracker.UserActions;

import robert.purdey.caddytracker.R;
import robert.purdey.caddytracker.ViewAssertions.RecyclerViewItemCountAssertion;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class FrolfGroupRecordScreen
{

    /**
     * Validates the following about a group:
     *  - group name matches
     *  - count of users in the group
     *
     * @param expectedGroupName
     * @param expectedMemberCount
     */
    public static void validateFrolfGroupRecord(String expectedGroupName, int expectedMemberCount)
    {
        matchGroupName(expectedGroupName);
        countMembers(expectedMemberCount);
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
}

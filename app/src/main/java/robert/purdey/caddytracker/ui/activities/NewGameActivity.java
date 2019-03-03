package robert.purdey.caddytracker.ui.activities;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import robert.purdey.caddytracker.R;
import robert.purdey.caddytracker.ui.fragments.CourseListFragment;

public class NewGameActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

        if ( savedInstanceState == null )
        {
            CourseListFragment chooseCourseFrag = new CourseListFragment();

            FragmentManager fm = getSupportFragmentManager();

            fm.beginTransaction()
                .add(R.id.create_new_game_frame, chooseCourseFrag, "Tag1")
                .commit();

            fm.executePendingTransactions();

            fm.beginTransaction()
                //.hide(this.getPlayerListFragment() )
                .show(this.getCourseListFragment() )
                .commit();

            Button startNewGame = (Button) findViewById(R.id.bttn_create_new_game);
            startNewGame.setClickable(false);
        }
    }

    /**
     * Adds game to the db.
     *
     * If successful, the game is started. Otherwise, user errors are shown.
     *
     * @param view - view calling the method
     */
    public void addGame(View view)
    {

    }

/*    @Override
    public void onCourseIdPass(int courseId)
    {
        if (coursePkeyId == -1)
        {
            getSupportFragmentManager().beginTransaction()
                .hide(this.getCourseListFragment() )
                .show(this.getPlayerListFragment() )
                .commit();

            Button startNewGame = (Button) findViewById(R.id.bttn_start_new_game);
            startNewGame.setClickable(true);
        }

        coursePkeyId = courseId;
    }*/

    // hook into onclick of frags
    //@Override
    //public void onPlayerIdPass(int playerId)
    //{
    //    playerPKeyIds.add(playerId);
   // }

    /**
     * Gets the player fragment
     *
     * @return Player list fragment or null if it is not found.
     */
   // protected PlayerListFragment getPlayerListFragment()
   // {
    //    return (PlayerListFragment) getSupportFragmentManager()
    //        .findFragmentByTag("Tag2");
  //  }

    /**
     * Gets the course fragment
     *
     * @return Course list fragment or null if it is not found.
     */
    protected CourseListFragment getCourseListFragment()
    {
        return (CourseListFragment) getSupportFragmentManager()
            .findFragmentByTag("Tag1");
    }
}

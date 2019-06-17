package robert.purdey.caddytracker.ui.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.UUID;

import robert.purdey.caddytracker.R;
import robert.purdey.caddytracker.databinding.ActivityNewGameBinding;
import robert.purdey.caddytracker.domain.courses.CourseFilter;
import robert.purdey.caddytracker.ui.fragments.CourseListFragment;
import robert.purdey.caddytracker.ui.fragments.FrolfGroupListFragment;
import robert.purdey.caddytracker.ui.fragments.FrolfGroupMemberListFragment;
import robert.purdey.caddytracker.ui.helpers.ActivityStarter;
import robert.purdey.caddytracker.ui.listeners.IApiResponseListener;
import robert.purdey.caddytracker.ui.viewmodels.NewGameViewModel;

public class NewGameActivity extends AppCompatActivity
{
    private NewGameViewModel newGameViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        newGameViewModel               = ViewModelProviders.of(this).get(NewGameViewModel.class);
        ActivityNewGameBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_new_game);

        binding.setNewGameViewModel(newGameViewModel);
        binding.setLifecycleOwner(this);

        if ( savedInstanceState == null )
        {
            CourseListFragment chooseCourseFrag             = new CourseListFragment();
            FrolfGroupListFragment chooseGroupFrag          = new FrolfGroupListFragment();
            FrolfGroupMemberListFragment chooseMembersFrag  = new FrolfGroupMemberListFragment();

            chooseCourseFrag.SetCourseClickListener(  (view, id) -> onCourseSelected(id)     );
            chooseGroupFrag.SetGroupClickListener(    (view, id) -> onFrolfGroupSelected(id) );
            chooseMembersFrag.SetMemberClickListener( (view, id) -> onMemberSelected(id)     );

            FragmentManager fm = getSupportFragmentManager();

            fm.beginTransaction()
                .add(R.id.create_new_game_frame, chooseCourseFrag,  "Tag1")
                .add(R.id.create_new_game_frame, chooseGroupFrag,   "Tag2")
                .add(R.id.create_new_game_frame, chooseMembersFrag, "Tag3")
                .commit();

            fm.executePendingTransactions();

            fm.beginTransaction()
                .hide( this.getCourseListFragment()    )
                .hide( this.getMembersListFragment()  )
                .show( this.getGroupListFragment()   )
                .commit();

            SetSelectionDescription("Select group");

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
        newGameViewModel.CreateGame().observe(this, gameModel -> {
            ActivityStarter.startScoreGameActivity(this, gameModel.getIdKey());
        });
    }

    private void onCourseSelected(UUID id)
    {
        if (id != null)
        {
            newGameViewModel.setCourse(id);
            SetSelectionDescription("Select players");

            this.getMembersListFragment().Load( newGameViewModel.getGroupId() );

            getSupportFragmentManager().beginTransaction()
                .hide( this.getCourseListFragment() )
                .hide( this.getGroupListFragment() )
                .show( this.getMembersListFragment() )
                .commit();

            Button startNewGame = (Button) findViewById(R.id.bttn_create_new_game);
            startNewGame.setClickable(true);
        }
    }

    private void SetSelectionDescription(String description)
    {
        TextView selectionDesc = findViewById(R.id.txtv_activity_new_game_select_description);
        selectionDesc.setText(description);
    }

    private void onFrolfGroupSelected(UUID id)
    {
        if (id != null)
        {
            newGameViewModel.setGroup(id);
            newGameViewModel.clearPlayers();

            SetSelectionDescription("Select course");

            CourseFilter groupCoursesFilter = new CourseFilter();
            groupCoursesFilter.setFrolfGroupId(id);

            this.getCourseListFragment().LoadCourses(groupCoursesFilter);

            getSupportFragmentManager().beginTransaction()
                .hide( this.getMembersListFragment()  )
                .hide( this.getGroupListFragment()   )
                .show( this.getCourseListFragment() )
                .commit();
        }
    }

    private void onMemberSelected(UUID id)
    {
        if (id != null)
        {
            newGameViewModel.managePlayer(id);
        }
    }

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

    /**
     * Gets the group fragment
     *
     * @return Course list fragment or null if it is not found.
     */
    protected FrolfGroupListFragment getGroupListFragment()
    {
        return (FrolfGroupListFragment) getSupportFragmentManager()
            .findFragmentByTag("Tag2");
    }

    /**
     * Gets the members fragment
     *
     * @return Course list fragment or null if it is not found.
     */
    protected FrolfGroupMemberListFragment getMembersListFragment()
    {
        return (FrolfGroupMemberListFragment) getSupportFragmentManager()
            .findFragmentByTag("Tag3");
    }
}

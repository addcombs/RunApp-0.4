package infinity.runapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import infinity.runapp.fragments.AssignWorkoutFragment;
import infinity.runapp.fragments.CreateGroupFragment;
import infinity.runapp.fragments.CreateWorkoutFragment;
import infinity.runapp.fragments.GroupsFragment;
import infinity.runapp.fragments.HistoryFragment;
import infinity.runapp.fragments.HomeFragment;
import infinity.runapp.fragments.LogoutFragment;
import infinity.runapp.fragments.MessagesFragment;
import infinity.runapp.fragments.NavigationDrawerFragment;
import infinity.runapp.fragments.ProfileFragment;
import infinity.runapp.fragments.WorkoutSummaryFragment;
import infinity.runapp.fragments.WorkoutsFragment;
import infinity.runapp.getsets.ActiveUser;
import infinity.runapp.library.InfinityDBHandler;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    private SpannableString s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(getIntent().hasExtra("data")){
            goRunSummary();
        }

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {

        Fragment objFragment = null;

        switch (position)
        {
            case 0:
                objFragment = new HomeFragment();
                break;
            case 1:
                objFragment = new ProfileFragment();
                break;
            case 2:
                objFragment = new GroupsFragment();
                break;
            case 3:
                objFragment = new WorkoutsFragment();
                break;
            case 4:
                objFragment = new HistoryFragment();
                break;
            case 5:
                objFragment = new MessagesFragment();
                break;
            case 6:
                objFragment = new LogoutFragment();
                break;
        }

        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, objFragment)
                .commit();
    }

    public void goHome(View view) {
        Fragment home = new HomeFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, home)
                .commit();
    }

    public void goWorkouts(View view) {
        Fragment workouts = new WorkoutsFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, workouts)
                .commit();
    }

    public void goGroups(View view) {
        Fragment groups = new GroupsFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, groups)
                .commit();
    }

    public void goHistory(View view) {
        Fragment history = new HistoryFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, history)
                .commit();
    }

    public void goRun(View view) {
        Intent intent = new Intent(this, RunActivity.class);
        startActivity(intent);
    }

    public void goLoadApp(View view){
        Intent intent = new Intent(this, LoadApp.class);
        startActivity(intent);
    }
    public void goMessages(View view){
        Fragment goMessages = new MessagesFragment();
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.container, goMessages)
                .commit();
    }

    public void goRunSummary(){
        Fragment runSummary = new WorkoutSummaryFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, runSummary)
                .commit();
    }

    public void goCreateGroup(View view){
        Fragment createGroup = new CreateGroupFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, createGroup)
                .commit();
    }

    public void goCreateWorkout(View view){
        Fragment createWorkout = new CreateWorkoutFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, createWorkout)
                .commit();
    }

    public void goProfile(View view){
        Fragment profile = new CreateWorkoutFragment();
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.container, profile)
                .commit();
    }


    public void goAssignWorkout(View view){
        Fragment fragment = new AssignWorkoutFragment();
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    // Database Methods
    public Integer getUserID(){
        InfinityDBHandler dbHandler = new InfinityDBHandler(this, null, null, 1);

        ActiveUser myActiveUser = dbHandler.setUser();

        return myActiveUser.getUserID();
    }

    public void changeProfilePhoto(){

    }

    public void onSectionAttached(int number){
        String[] stringArray = getResources().getStringArray(R.array.section_titles);
        if (number >= 1){
            mTitle = stringArray[number-1];
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
//        s = new SpannableString("Infinity Run");
//        s.setSpan(new TypefaceSpan(this, "LatoRegular.ttf"), 0, s.length(),
//                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle("Infinity Run");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        // **Disabled Settings** //

//        int id = item.getItemId();
//
//        noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

    @Override
    public void onBackPressed(){

    }
}

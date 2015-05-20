package infinity.runapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import infinity.runapp.getsets.AssignedWorkouts;
import infinity.runapp.getsets.Group;
import infinity.runapp.getsets.GroupDetails;
import infinity.runapp.getsets.History;
import infinity.runapp.getsets.RecentMessages;
import infinity.runapp.getsets.Users;
import infinity.runapp.getsets.Workouts;
import infinity.runapp.library.InfinityDBHandler;
import infinity.runapp.library.JSONParser;


public class LoadApp extends ActionBarActivity {

    private ProgressDialog pDialog;
    private Spannable s;

    JSONObject users_json;
    JSONObject groups_json;
    JSONObject group_details_json;
    JSONObject history_json;
    JSONObject message_json;
    JSONObject workouts_json;
    JSONObject assigned_workouts_json;

    JSONParser jsonParser = new JSONParser();

    // URLs
    private static final String USERS_URL = "http://cgi.soic.indiana.edu/~team36/infinity/build_sqlite/get_users_LITE.php";
    private static final String GROUPS_URL = "http://cgi.soic.indiana.edu/~team36/infinity/build_sqlite/get_groups_LITE.php";
    private static final String GROUP_DETAILS_URL = "http://cgi.soic.indiana.edu/~team36/infinity/build_sqlite/get_group_details_LITE.php";
    private static final String WORKOUTS_URL = "http://cgi.soic.indiana.edu/~team36/infinity/build_sqlite/get_workouts_LITE.php";
    private static final String HISTORY_URL = "http://cgi.soic.indiana.edu/~team36/infinity/build_sqlite/get_history_LITE.php";
    private static final String MESSAGES_URL = "http://cgi.soic.indiana.edu/~team36/infinity/build_sqlite/get_messages_LITE.php";
    private static final String ASSIGNED_URL = "http://cgi.soic.indiana.edu/~team36/infinity/build_sqlite/get_assigned_workouts_LITE.php";

    // users
    public Integer userID;
    public String fName;
    public String lName;
    public String userEmail;
    public String userPic;

    // groups
    public Integer groupID;
    public String groupName;
    public String tagline;
    public String bulletin;
    public Integer isClosed;

    // group_details
    public Integer groupDetailsID;
    public Integer groupDetailsUserID;
    public Integer hasAlerts;
    public Integer isAdmin;

    // workouts
    public Integer workoutID;
    public Double workoutsDistance;
    public Integer createdBy;
    public String workoutName;
    public String exprDate;

    // history
    public Integer historyID;
    public String dateRan;
    public Integer timeRan;
    public Double historyDistance;
    public Integer ranBy;
    public Integer historyWorkoutID;

    // messages
    public Integer messageID;
    public Integer sender;
    public Integer receiver;
    public String subject;
    public String messageText;
    public String sendingDate;
    public Integer isRead;
    public Integer isDeleted;
    public Integer groupRequested;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        restoreActionBar();

        setContentView(R.layout.activity_load_app);

        resetTables();

        new Load().execute();
    }

    class Load extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pDialog = new ProgressDialog(LoadApp.this);
            pDialog.setMessage("Loading ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... args){

            try{
                // Users
                users_json = jsonParser.getJSONFromUrl(USERS_URL);
                for(int i = 0; i < users_json.getInt("count"); i++)
                {
                    JSONObject users = users_json.getJSONObject("" + i);
                    userID = Integer.parseInt(users.getString("userID"));
                    userEmail = users.getString("userEmail");
                    fName = users.getString("fName");
                    lName = users.getString("lName");
                    userPic = users.getString("userPic");
                    addUsersSQLite(userID, userEmail, fName, lName, userPic);
                }
                //Groups
                groups_json = jsonParser.getJSONFromUrl(GROUPS_URL);
                for(int i = 0; i < groups_json.getInt("count"); i++) {
                    JSONObject groups = groups_json.getJSONObject("" + i);
                    groupID = Integer.parseInt(groups.getString("groupID"));
                    groupName = groups.getString("groupName");
                    tagline = groups.getString("tagline");
                    bulletin = groups.getString("bulletin");
                    isClosed = groups.getInt("isClosed");
                    createdBy = groups.getInt("createdBy");
                    addGroupSQLite(groupID, groupName, tagline, bulletin, isClosed, createdBy);
                }
                //Group Details
                group_details_json = jsonParser.getJSONFromUrl(GROUP_DETAILS_URL);
                for(int i = 0; i < group_details_json.getInt("count"); i++) {
                    JSONObject group_details = group_details_json.getJSONObject("" + i);
                    groupDetailsID = Integer.parseInt(group_details.getString("groupID"));
                    groupDetailsUserID = Integer.parseInt(group_details.getString("userID"));
                    hasAlerts = Integer.parseInt(group_details.getString("hasAlerts"));
                    isAdmin = Integer.parseInt(group_details.getString("isAdmin"));
                    addGroupDetailsSQLite(groupDetailsID, groupDetailsUserID, hasAlerts, isAdmin);
                }
                //Workouts
                workouts_json = jsonParser.getJSONFromUrl(WORKOUTS_URL);
                for(int i = 0; i < workouts_json.getInt("count"); i++) {
                    JSONObject workouts = workouts_json.getJSONObject("" + i);
                    workoutID = Integer.parseInt(workouts.getString("workoutID"));
                    workoutsDistance = Double.parseDouble(workouts.getString("distance"));
                    createdBy = Integer.parseInt(workouts.getString("createdBy"));
                    workoutName = workouts.getString("workoutName");
                    addWorkoutSQLite(workoutID, workoutsDistance, createdBy, workoutName);
                }
                //Assigned Workouts
                assigned_workouts_json = jsonParser.getJSONFromUrl(ASSIGNED_URL);
                for(int i = 0; i < assigned_workouts_json.getInt("count"); i++){
                    JSONObject assigned = assigned_workouts_json.getJSONObject("" + i);
                    workoutID = Integer.parseInt(assigned.getString("workoutID"));
                    userID = Integer.parseInt(assigned.getString("userID"));
                    exprDate = assigned.getString("exprDate");
                    addAssignedWorkoutSQLite(workoutID, userID, exprDate);
                }
                //History
                history_json = jsonParser.getJSONFromUrl(HISTORY_URL);
                for(int i = 0; i < history_json.getInt("count"); i++) {
                    JSONObject history = history_json.getJSONObject("" + i);
                    historyID = Integer.parseInt(history.getString("historyID"));
                    dateRan = history.getString("dateRan");
                    historyDistance = Double.parseDouble(history.getString("distanceRan"));
                    timeRan = Integer.parseInt(history.getString("timeRan"));
                    ranBy = Integer.parseInt(history.getString("ranBy"));
                    historyWorkoutID = Integer.parseInt(history.getString("workoutID"));
                    addHistorySQLite(historyID, dateRan, timeRan, historyDistance, ranBy, historyWorkoutID);
                }
                //Messages
                message_json = jsonParser.getJSONFromUrl(MESSAGES_URL);
                for(int i = 0; i < message_json.getInt("count"); i++) {
                    JSONObject message = message_json.getJSONObject("" + i);
                    messageID = Integer.parseInt(message.getString("messageID"));
                    sender = Integer.parseInt(message.getString("sender"));
                    receiver = Integer.parseInt(message.getString("receiver"));
                    subject = message.getString("subject");
                    messageText = message.getString("message");
                    sendingDate = message.getString("sendingDate");
                    isRead = Integer.parseInt(message.getString("isRead"));
                    isDeleted = Integer.parseInt(message.getString("isDeleted"));
                    groupRequested = Integer.parseInt(message.getString("groupRequested"));
                    addMessagesSQLite(messageID, sender, receiver, subject, messageText, sendingDate, isRead, isDeleted, groupRequested);
                }
            }catch(JSONException e){
                pDialog.dismiss();
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String file_url){
            pDialog.dismiss();

            Intent intent = new Intent(LoadApp.this, MainActivity.class);
            startActivity(intent);

            if(file_url != null){
                Toast.makeText(LoadApp.this, file_url, Toast.LENGTH_LONG).show();
            }
        }
    }

    public void resetTables(){
        InfinityDBHandler db = new InfinityDBHandler(this, null, null, 1);

        db.resetTables();
    }

    private void addUsersSQLite(Integer id, String email, String fname, String lname, String pic){
        InfinityDBHandler infinityDbHandler = new InfinityDBHandler(this, null, null, 1);

        Users newUser = new Users(id, email, fname, lname, pic);
        infinityDbHandler.addUsers(newUser);

        infinityDbHandler.close();
    }

    private void addGroupSQLite(Integer id, String groupName, String tagline, String bulletin, Integer isClosed, Integer createdBy){
        InfinityDBHandler infinityDbHandler = new InfinityDBHandler(this, null, null, 1);

        Group newGroup = new Group(id, groupName, tagline, bulletin, isClosed, createdBy);
        infinityDbHandler.addGroup(newGroup);

        infinityDbHandler.close();
    }

    private void addGroupDetailsSQLite(Integer id, Integer userID, Integer hasAlerts, Integer isAdmin){
        InfinityDBHandler infinityDbHandler = new InfinityDBHandler(this, null, null, 1);

        GroupDetails newGroupDetails = new GroupDetails(id, userID, hasAlerts, isAdmin);
        infinityDbHandler.addGroupDetails(newGroupDetails);

        infinityDbHandler.close();
    }

    private void addWorkoutSQLite(Integer id, Double distance, Integer createdBy, String workoutName){
        InfinityDBHandler infinityDbHandler = new InfinityDBHandler(this, null, null, 1);

        Workouts newWorkouts = new Workouts(id, distance, createdBy, workoutName);
        infinityDbHandler.addWorkouts(newWorkouts);

        infinityDbHandler.close();
    }

    private void addAssignedWorkoutSQLite(Integer workoutID, Integer userID, String exprDate){
        InfinityDBHandler infinityDbHandler = new InfinityDBHandler(this, null, null, 1);

        AssignedWorkouts newAssignedWorkouts = new AssignedWorkouts(workoutID, userID, exprDate);
        infinityDbHandler.addAssignedWorkouts(newAssignedWorkouts);

        infinityDbHandler.close();
    }


    private void addHistorySQLite(Integer id, String dateRan, Integer timeRan, Double distance, Integer ranBy, Integer workoutID){
        InfinityDBHandler infinityDbHandler = new InfinityDBHandler(this, null, null, 1);

        History newHistory = new History(id, dateRan, timeRan, distance, ranBy, workoutID);
        infinityDbHandler.addHistory(newHistory);

        infinityDbHandler.close();
    }

    private void addMessagesSQLite(Integer id, Integer sender, Integer receiver, String subject, String message, String sendDate, Integer isRead, Integer isDeleted, Integer groupRequested){
        InfinityDBHandler infinityDbHandler = new InfinityDBHandler(this, null, null, 1);

        RecentMessages newMessage = new RecentMessages(id, sender, receiver, subject, message, sendDate, isRead, isDeleted, groupRequested);
        infinityDbHandler.addMessages(newMessage);

        infinityDbHandler.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_load_app, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed(){

    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        s = new SpannableString("Loading..");
//        s.setSpan(new TypefaceSpan(this, "LatoRegular.ttf"), 0, s.length(),
//                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(s);
    }
}

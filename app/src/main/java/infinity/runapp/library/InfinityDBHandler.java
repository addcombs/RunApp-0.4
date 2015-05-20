package infinity.runapp.library;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import infinity.runapp.getsets.ActiveUser;
import infinity.runapp.getsets.AssignedWorkouts;
import infinity.runapp.getsets.Group;
import infinity.runapp.getsets.GroupDetails;
import infinity.runapp.getsets.History;
import infinity.runapp.getsets.RecentMessages;
import infinity.runapp.getsets.Users;
import infinity.runapp.getsets.Workouts;


/**
 * Created by ADC on 3/6/2015.
 */
public class InfinityDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "infinityDB.db";

      // constructor
    public InfinityDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    //User table
    private static final String TABLE_USER = "ActiveUser";
    public static final String COLUMN_USERID = "userID";
    public static final String COLUMN_EMAIL = "userEmail";
    public static final String COLUMN_FNAME = "fName";
    public static final String COLUMN_LNAME = "lName";
    private static final String COLUMN_USERPIC = "userPic";

    //Create Users table
    private static final String TABLE_USERS = "Users";
    //userID^
    //fName^
    //lName^
    //userEmail^
    //userPic^

    //Workouts table
    private static final String TABLE_WORKOUTS = "Workouts";
    public static final String COLUMN_WORKOUTID = "workoutID";
    public static final String COLUMN_DISTANCE = "distance";
    public static final String COLUMN_CREATEDBY = "createdBy";
    public static final String COLUMN_WORKOUTNAME = "workoutName";

    //Assigned table
    private static final String TABLE_ASSIGNED = "AssignedWorkouts";
    //workoutID
    //userID
    public static final String COLUMN_EXPR_DATE = "exprDate";

    //History table
    private static final String TABLE_HISTORY = "History";
    public static final String COLUMN_HISTORYID = "historyID";
    public static final String COLUMN_DATERAN = "dateRan";
    public static final String COLUMN_TIMERAN = "timeRan";
    //distance^
    public static final String COLUMN_RANBY = "ranBy";
    //workoutID^

    //Groups table
    private static final String TABLE_GROUPS = "Groups";
    public static final String COLUMN_GROUPID = "groupID";
    public static final String COLUMN_GROUPNAME = "groupName";
    public static final String COLUMN_TAGLINE = "tagline";
    public static final String COLUMN_BULLETIN = "bulletin";
    public static final String COLUMN_ISCLOSED = "isClosed";
    //createdBy^

    //Group Details table
    private static final String TABLE_GROUP_DETAILS = "GroupDetails";
    //groupID^
    //userID^
    public static final String COLUMN_HASALERTS = "hasAlerts";
    public static final String COLUMN_ISADMIN = "isAdmin";

    //Messages table
    private static final String TABLE_MESSAGES = "MessageDetails";
    public static final String COLUMN_MESSAGEID = "messageID";
    public static final String COLUMN_SENDER = "sender";
    public static final String COLUMN_RECEIVER = "receiver";
    public static final String COLUMN_MESSAGE = "message";
    public static final String COLUMN_SENDDATE = "sendingDate";
    public static final String COLUMN_ISREAD = "isRead";
    public static final String COLUMN_ISDELETED = "isDeleted";
    public static final String COLUMN_GROUPREQUEST = "groupRequest";
    public static final String COLUMN_SUBJECT = "subject";

    // Call create table functions
    public void onCreate(SQLiteDatabase db) {
        createUserTable(db);
        createUsersTable(db);
        createWorkoutsTable(db);
        createAssignedWorkoutTable(db);
        createHistoryTable(db);
        createGroupsTable(db);
        createGroupDetailsTable(db);
        createMessagesTable(db);
    }

    //Drop tables and recreate on upgrade
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORKOUTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ASSIGNED);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HISTORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GROUPS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GROUP_DETAILS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MESSAGES);
        onCreate(db);
    }

    public void createUserTable(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " +
                TABLE_USER + " (" +
                COLUMN_USERID + " INTEGER PRIMARY KEY, " +
                COLUMN_EMAIL + " TEXT, " +
                COLUMN_FNAME + " TEXT, " +
                COLUMN_LNAME + " TEXT, " +
                COLUMN_USERPIC + " TEXT);";

        db.execSQL(CREATE_TABLE);
    }

    public void createUsersTable(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " +
                TABLE_USERS + " (" +
                COLUMN_USERID + " INTEGER PRIMARY KEY, " +
                COLUMN_FNAME + " TEXT, " +
                COLUMN_LNAME + " TEXT, " +
                COLUMN_EMAIL + " TEXT, " +
                COLUMN_USERPIC + " TEXT);";

        db.execSQL(CREATE_TABLE);
    }

    public void createWorkoutsTable(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " +
                TABLE_WORKOUTS + " (" +
                COLUMN_WORKOUTID + " INTEGER PRIMARY KEY, " +
                COLUMN_DISTANCE + " REAL, " +
                COLUMN_CREATEDBY + " TEXT, " +
                COLUMN_WORKOUTNAME + " TEXT);";

        db.execSQL(CREATE_TABLE);
    }

    public void createAssignedWorkoutTable(SQLiteDatabase db){
        String CREATE_TABLE = "CREATE TABLE " +
                TABLE_ASSIGNED + " (" +
                COLUMN_WORKOUTID + " INTEGER, " +
                COLUMN_USERID + " INTEGER, " +
                COLUMN_EXPR_DATE + " String);";

        db.execSQL(CREATE_TABLE);
    }
    public void createHistoryTable(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " +
                TABLE_HISTORY + " (" +
                COLUMN_HISTORYID + " INTEGER PRIMARY KEY, " +
                COLUMN_DATERAN + " TEXT, " +
                COLUMN_DISTANCE + " REAL, " +
                COLUMN_TIMERAN + " INTEGER, " +
                COLUMN_RANBY + " INTEGER, " +
                COLUMN_WORKOUTID + " INTEGER);";

        db.execSQL(CREATE_TABLE);
    }

    public void createGroupsTable(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " +
                TABLE_GROUPS + " (" +
                COLUMN_GROUPID + " INTEGER PRIMARY KEY, " +
                COLUMN_GROUPNAME + " TEXT, " +
                COLUMN_TAGLINE + " TEXT, " +
                COLUMN_BULLETIN + " TEXT, " +
                COLUMN_ISCLOSED + " INTEGER, " +
                COLUMN_CREATEDBY + " INTEGER);";

        db.execSQL(CREATE_TABLE);
    }

    public void createGroupDetailsTable(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " +
                TABLE_GROUP_DETAILS + " (" +
                COLUMN_GROUPID + " INTEGER, " +
                COLUMN_USERID + " INTEGER, " +
                COLUMN_HASALERTS + " INTEGER, " +
                COLUMN_ISADMIN + " INTEGER);";

        db.execSQL(CREATE_TABLE);
    }

    public void createMessagesTable(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " +
                TABLE_MESSAGES + " (" +
                COLUMN_MESSAGEID + " INTEGER PRIMARY KEY, " +
                COLUMN_SENDER + " INTEGER, " +
                COLUMN_RECEIVER + " INTEGER, " +
                COLUMN_SUBJECT + " TEXT, " +
                COLUMN_MESSAGE + " INTEGER, " +
                COLUMN_SENDDATE + " TEXT, " +
                COLUMN_ISREAD + " INTEGER, " +
                COLUMN_ISDELETED + " INTEGER, " +
                COLUMN_GROUPREQUEST + " INTEGER);";

        db.execSQL(CREATE_TABLE);

    }

    public void resetTables(){

        SQLiteDatabase db = this.getReadableDatabase();

        Users user = new Users();
        Group group = new Group();
        GroupDetails groupDetails = new GroupDetails();
        History history = new History();
        RecentMessages recentMessages = new RecentMessages();
        Workouts workouts = new Workouts();
        AssignedWorkouts assignedWorkouts = new AssignedWorkouts();

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORKOUTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HISTORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GROUPS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GROUP_DETAILS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MESSAGES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ASSIGNED);

        createUsersTable(db);
        createWorkoutsTable(db);
        createHistoryTable(db);
        createGroupsTable(db);
        createGroupDetailsTable(db);
        createMessagesTable(db);
        createAssignedWorkoutTable(db);

        db.close();
    }

    public void resetUserTable() {
        ActiveUser activeUser = new ActiveUser();

        String DROP_USER_TABLE = "DROP TABLE " + TABLE_USER;

        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL(DROP_USER_TABLE);
        createUserTable(db);

        db.close();
    }

    public void addActiveUser(ActiveUser activeUser) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERID, activeUser.getUserID());
        values.put(COLUMN_EMAIL, activeUser.getEmail());
        values.put(COLUMN_FNAME, activeUser.getFname());
        values.put(COLUMN_LNAME, activeUser.getLname());
        values.put(COLUMN_USERPIC, activeUser.getmPic());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_USER, null, values);

        db.close();
    }

    public void addUsers(Users users) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERID, users.getUserID());
        values.put(COLUMN_EMAIL, users.getEmail());
        values.put(COLUMN_FNAME, users.getFname());
        values.put(COLUMN_LNAME, users.getLname());
        values.put(COLUMN_USERPIC, users.getmPic());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_USERS, null, values);

        db.close();
    }

    public void addGroup(Group group) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_GROUPID, group.getmGroupID());
        values.put(COLUMN_GROUPNAME, group.getmGroupName());
        values.put(COLUMN_TAGLINE, group.getmTagline());
        values.put(COLUMN_BULLETIN, group.getmBulletin());
        values.put(COLUMN_ISCLOSED, group.getmIsClosed());
        values.put(COLUMN_CREATEDBY, group.getmCreatedBy());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_GROUPS, null, values);

        db.close();
    }

    public void addGroupDetails(GroupDetails groupDetails) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_GROUPID, groupDetails.getGroupID());
        values.put(COLUMN_USERID, groupDetails.getUserID());
        values.put(COLUMN_HASALERTS, groupDetails.getHasAlerts());
        values.put(COLUMN_ISADMIN, groupDetails.getIsAdmin());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_GROUP_DETAILS, null, values);

        db.close();
    }

    // insert to workouts
    public void addWorkouts(Workouts workouts) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_WORKOUTID, workouts.getmWorkoutID());
        values.put(COLUMN_DISTANCE, workouts.getmDistance());
        values.put(COLUMN_CREATEDBY, workouts.getmCreatedBy());
        values.put(COLUMN_WORKOUTNAME, workouts.getmWorkoutName());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_WORKOUTS, null, values);

        db.close();
    }

    // insert to workouts
    public void addAssignedWorkouts(AssignedWorkouts workouts) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_WORKOUTID, workouts.getmWorkoutID());
        values.put(COLUMN_USERID, workouts.getmUserID());
        values.put(COLUMN_EXPR_DATE, workouts.getmExprDate());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_ASSIGNED, null, values);

        db.close();
    }
    // insert to history
    public void addHistory(History history) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_HISTORYID, history.getmHistoryID());
        values.put(COLUMN_DATERAN, history.getmDateRan());
        values.put(COLUMN_TIMERAN, history.getmTimeRan());
        values.put(COLUMN_DISTANCE, history.getDistance());
        values.put(COLUMN_RANBY, history.getmRanBy());
        values.put(COLUMN_WORKOUTID, history.getWorkoutID());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_HISTORY, null, values);

        db.close();
    }

    // insert to messages
    public void addMessages(RecentMessages recentMessages) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_MESSAGEID, recentMessages.getmMessageID());
        values.put(COLUMN_SENDER, recentMessages.getmSender());
        values.put(COLUMN_RECEIVER, recentMessages.getmReceiver());
        values.put(COLUMN_SUBJECT, recentMessages.getSubject());
        values.put(COLUMN_MESSAGE, recentMessages.getmMessage());
        values.put(COLUMN_SENDDATE, recentMessages.getmSendingDate());
        values.put(COLUMN_ISREAD, recentMessages.getmIsRead());
        values.put(COLUMN_ISDELETED, recentMessages.getmIsDeleted());
        values.put(COLUMN_GROUPREQUEST, recentMessages.getmGroupRequest());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_MESSAGES, null, values);

        db.close();
    }

    //Active Table Methods
    public boolean checkUser() {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cur = db.rawQuery("SELECT count(*) FROM " + TABLE_USER + ";", null);

        if (cur != null) {
            cur.moveToFirst();
            if (cur.getInt(0) == 0) {
                db.close();
                return false;
            }
        }
        db.close();
        return true;
    }
//
//    public String getUsersName(int userID){
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        Cursor cur = db.rawQuery("SELECT (fName || ' ' || lName)" +
//                "FROM " + TABLE_USERS + "WHERE userID = " + userID, null);
//
//        cur.moveToFirst();
//
//        return cur.getString(0);
//    }

    public ActiveUser setUser() {
        String sql_query = "SELECT * FROM " + TABLE_USER + " LIMIT 1";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor myCursor = db.rawQuery(sql_query, null);

        ActiveUser myActiveUser = new ActiveUser();

        if (myCursor.moveToFirst()) {
            myActiveUser.setUserID(myCursor.getInt(myCursor.getColumnIndex("userID")));
            myActiveUser.setEmail(myCursor.getString(myCursor.getColumnIndex("userEmail")));
            myActiveUser.setFname(myCursor.getString(myCursor.getColumnIndex("fName")));
            myActiveUser.setLname(myCursor.getString(myCursor.getColumnIndex("lName")));
            myActiveUser.setmPic(myCursor.getString(myCursor.getColumnIndex("userPic")));
            myCursor.close();
        } else
            myActiveUser = null;

        db.close();

        return myActiveUser;
    }


    // Get int number of rows for list of past workouts
    public History [] history(Integer userID, int number){
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_HISTORY + " WHERE ranBy = " + userID + " ORDER BY date(dateRan)";

        Cursor cur = db.rawQuery(query, null);

        History[] newHistory;

        if(cur != null)
        {
            int count = cur.getCount();
            int length = count;
            if (length > number)
                length = number;

            newHistory = new History[length];

            int index = count - length;

            cur.moveToFirst();

            for (int i = 0; i < length; i++){
                newHistory[i] = new History();
                newHistory[i].setmDateRan(cur.getString(1));
                newHistory[i].setDistance(cur.getDouble(2));
                cur.moveToNext();
            }

            cur.close();
        }else{
            newHistory = new History[1];
            newHistory[0] = new History();
            newHistory[0].setmDateRan("Empty History..");
            newHistory[0].setDistance(0.0);
        }
        db.close();
        return newHistory;
    }

    // Get int number of rows for list of messages
    public RecentMessages[] messages(Integer userID, int number){
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT m.messageID, m.sender, m.sendingDate, m.subject, m.message, m.isRead, m.isDeleted" +
                " FROM " + TABLE_USERS + " AS u, " + TABLE_MESSAGES + " AS m" +
                " WHERE u.userID = m.receiver AND m.isDeleted = -1 AND m.receiver = " + userID +
                " ORDER BY m.isRead ASC, m.messageID DESC;";

        Cursor cur = db.rawQuery(query, null);

        RecentMessages[] message;

        if(cur != null)
        {
            int count = cur.getCount();
            int length = count;
            if (length > number)
                length = number;

            message = new RecentMessages[length];

            int index = count - length;
            cur.moveToFirst();
            cur.move(index);

            for (int i = 0; i < length; i++){
                message[i] = new RecentMessages();
                message[i].setmMessageID(cur.getInt(0));
                message[i].setmSender(cur.getInt(1));
                message[i].setmSendingDate(cur.getString(2));
                message[i].setSubject(cur.getString(3));
                message[i].setmMessage(cur.getString(4));
                message[i].setmIsRead(cur.getInt(5));
                message[i].setmIsDeleted(cur.getInt(6));
                cur.moveToNext();
            }
            cur.close();
        }else{
            message = new RecentMessages[1];
            message[0] = new RecentMessages();
            message[0].setmMessageID(0);
            message[0].setmSender(0);
            message[0].setmSendingDate("");
            message[0].setmMessage("No Messages.");
            message[0].setmIsRead(0);
            message[0].setmIsDeleted(0);
        }
        db.close();
        return message;
    }

    // Get int number of rows for list of messages
    public AssignedWorkouts [] workouts(Integer userID, int number){
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT w.workoutID, w.workoutName, w.distance, aw.exprDate " +
                "FROM " + TABLE_WORKOUTS + " as w, " + TABLE_ASSIGNED + " as aw " +
                "WHERE w.workoutID = aw.workoutID AND aw.userID = " + userID;

        Cursor cur = db.rawQuery(query, null);

        AssignedWorkouts [] workouts;

        if(cur != null)
        {
            int count = cur.getCount();
            int length = count;
            if (length > number)
                length = number;

            workouts = new AssignedWorkouts[length];

            int index = count - length;
            cur.moveToFirst();
            cur.move(index);

            for (int i = 0; i < length; i++){
                workouts[i] = new AssignedWorkouts();
                workouts[i].setmWorkoutID(cur.getInt(0));
                workouts[i].setmWorkoutName(cur.getString(1));
                workouts[i].setmDistance(cur.getDouble(2));
                workouts[i].setmExprDate(cur.getString(3));
                cur.moveToNext();
            }
            cur.close();
        }else{
            workouts = new AssignedWorkouts[1];
            workouts[0].setmWorkoutID(0);
            workouts[0].setmWorkoutName("");
            workouts[0].setmDistance(0.0);
            workouts[0].setmExprDate("");
        }
        db.close();
        return workouts;
    }

    public ArrayList<String> workoutList(Integer userID) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT w.workoutID, w.workoutName, w.distance, aw.exprDate " +
                "FROM " + TABLE_WORKOUTS + " as w, " + TABLE_ASSIGNED + " as aw " +
                "WHERE w.workoutID = aw.workoutID AND aw.userID = " + userID;

        Cursor cur = db.rawQuery(query, null);

        ArrayList<String> workout = new ArrayList<>();

        if (cur != null && cur.moveToFirst()) {
            int count = cur.getCount();

            cur.moveToFirst();

            for (int i = 0; i < count; i++) {
                workout.add(cur.getString(1));
                cur.moveToNext();
            }
            cur.close();
            db.close();
        }
        return workout;
    }


    public ArrayList<String> groupAdmin(Integer userID) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT groupName " +
                "FROM " + TABLE_GROUPS +
                " WHERE createdBy = " + userID;

        Cursor cur = db.rawQuery(query, null);

        ArrayList<String> group = new ArrayList<>();

        if (cur != null && cur.moveToFirst()) {
            int count = cur.getCount();

            cur.moveToFirst();

            for (int i = 0; i < count; i++) {
                group.add(cur.getString(0));
                cur.moveToNext();
            }
            cur.close();
            db.close();
        }
        return group;
    }

    public ArrayList<String> groupList(Integer userID) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT g.groupID, g.groupName, gd.hasAlerts, gd.isAdmin, counter.members " +
                "FROM " + TABLE_GROUPS + " AS g, " + TABLE_GROUP_DETAILS + " AS gd " +
                "LEFT JOIN (SELECT groupID, COUNT(*) AS members " +
                "FROM " + TABLE_GROUP_DETAILS + " GROUP BY groupID) AS counter ON (gd.groupID = counter.groupID) " +
                "WHERE g.groupID = gd.groupID AND gd.userID = " + userID;

        Cursor cur = db.rawQuery(query, null);

        ArrayList<String> group = new ArrayList<>();

        if (cur != null && cur.moveToFirst()) {
            int count = cur.getCount();

            cur.moveToFirst();

            for (int i = 0; i < count; i++) {
                group.add(cur.getString(1));
                cur.moveToNext();
            }
            cur.close();
            db.close();
        }
        return group;
    }

    public ArrayList<String> createdWorkouts(Integer userID) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT workoutName, distance " +
                "FROM " + TABLE_WORKOUTS +
                " WHERE createdBy = " + userID;

        Cursor cur = db.rawQuery(query, null);

        ArrayList<String> workouts = new ArrayList<>();
        workouts.add("");

        if (cur != null && cur.moveToFirst()) {
            int count = cur.getCount();

            cur.moveToFirst();

            for (int i = 0; i < count; i++) {
                workouts.add(cur.getString(0));
                cur.moveToNext();
            }
            cur.close();
            db.close();
        }
        return workouts;
    }

    public Double getDistance(String wname, Integer userID)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT distance " +
                "FROM " + TABLE_WORKOUTS +
                " WHERE workoutName = '" + wname +
                "' AND createdBy = " + userID;

        Cursor cur = db.rawQuery(query, null);
        cur.moveToFirst();

        Double distance = cur.getDouble(0);

        cur.close();
        db.close();

        return distance;
    }

    public String getUserName(Integer userID){
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT fName, lName FROM " + TABLE_USERS +
                " WHERE userID = " + userID;

        Cursor cur = db.rawQuery(query, null);

        cur.moveToFirst();

        String fullName =  cur.getString(0) + " " + cur.getString(1);

        cur.close();
        db.close();

        return fullName;
    }

    public ArrayList<String> workoutDetails(Integer userID, String workoutName)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT w.distance, w.createdBy, aw.exprDate" +
                " FROM " + TABLE_WORKOUTS + " AS w, " + TABLE_ASSIGNED + " AS aw" +
                " WHERE w.workoutName = '" + workoutName +
                "' AND w.workoutID = aw.workoutID AND aw.userID = " + userID;

        Cursor cur = db.rawQuery(query, null);

        ArrayList<String> woDetails = new ArrayList<>();

        cur.moveToFirst();
        woDetails.add(cur.getString(0));
        woDetails.add(cur.getString(1));
        woDetails.add(cur.getString(2));

        cur.close();
        db.close();

        return woDetails;
    }

    public ArrayList<String> historyDetails(Integer historyID)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT dateRan, distance, timeRan FROM history" +
                " WHERE historyID = " + historyID;

        Cursor cur = db.rawQuery(query, null);

        ArrayList<String> histDetails = new ArrayList<>();

        cur.moveToFirst();
        // date
        histDetails.add(cur.getString(0));
        // time
        histDetails.add(cur.getString(1));
        // distance
        histDetails.add(cur.getString(2));

        cur.close();
        db.close();

        return histDetails;
    }

    public Integer getHistoryID(Integer position, Integer userID){
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT h.historyID " +
                " FROM " + TABLE_HISTORY + " as h, " + TABLE_USERS + " AS u" +
                " WHERE u.userID = h.ranBy AND h.ranBy = " + userID;

        Cursor cur = db.rawQuery(query, null);

        cur.moveToFirst();
        cur.move(position);

        Integer historyID = cur.getInt(0);

        cur.close();
        db.close();

        return historyID;
    }
}
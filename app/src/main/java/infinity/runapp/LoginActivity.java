package infinity.runapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.SpannableString;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import infinity.runapp.getsets.ActiveUser;
import infinity.runapp.library.InfinityDBHandler;
import infinity.runapp.library.JSONParser;


public class LoginActivity extends ActionBarActivity {


    private EditText mEmail, mPassword;

    private ProgressDialog pDialog;

    JSONParser jsonParser = new JSONParser();

    private SpannableString s;

    //URL to get JSON Array
    private static final String URL = "http://cgi.soic.indiana.edu/~team36/infinity/login.php";
    //JSON Node Names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    private static final String TAG_USERID = "userID";
    private static final String TAG_EMAIL = "userEmail";
    private static final String TAG_FNAME = "fName";
    private static final String TAG_LNAME = "lName";
    private static final String TAG_USERPIC = "userPic";

    public Integer userID = 0;
    public String userEmail = "";
    public String firstName= "";
    public String lastName = "";
    public String userPic = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmail = (EditText) findViewById(R.id.email);
        mPassword = (EditText) findViewById(R.id.password);

        Button login = (Button) findViewById(R.id.btnLogin);
        login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                new UserLogin().execute();
            }
        });
    }

    class UserLogin extends AsyncTask <String, String, String>{

        @Override
        protected void onPreExecute(){
            super.onPreExecute();

            pDialog = new ProgressDialog(LoginActivity.this);
            pDialog.setMessage("Logging In...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... args){

            int success;
            String email = mEmail.getText().toString().toUpperCase();
            String pass = mPassword.getText().toString();

            try{
                List<NameValuePair> params = new ArrayList<>();
                params.add(new BasicNameValuePair("userEmail", email));
                params.add(new BasicNameValuePair("pswd", pass));

                JSONObject json = jsonParser.makeHttpRequest(URL, "POST", params);

                success = json.getInt(TAG_SUCCESS);

                if(success == 1)
                {
                    userID = json.getInt(TAG_USERID);
                    userEmail = json.getString(TAG_EMAIL);
                    firstName = json.getString(TAG_FNAME);
                    lastName = json.getString(TAG_LNAME);
                    if(json.getString(TAG_USERPIC) != null)
                        userPic = json.getString(TAG_USERPIC);
                    addUserSQLite(userID, userEmail, firstName, lastName, userPic);

                    Intent intent = new Intent(LoginActivity.this, LoadApp.class);
                    startActivity(intent);

                    return json.getString(TAG_MESSAGE);
                }
                else
                {
                    return json.getString(TAG_MESSAGE);
                }
            }catch(JSONException e){
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String file_url){
            pDialog.dismiss();

            if(file_url != null){
                Toast.makeText(LoginActivity.this, file_url, Toast.LENGTH_LONG).show();
            }
        }
    }

    public void goReg(View view) {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    public void addUserSQLite(Integer newID, String newEmail, String newFname, String newLname, String newPic){
        InfinityDBHandler infinityDbHandler = new InfinityDBHandler(this, null, null, 1);

        ActiveUser newActiveUser = new ActiveUser(newID, newEmail, newFname, newLname, newPic);
        infinityDbHandler.addActiveUser(newActiveUser);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        restoreActionBar();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
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
        s = new SpannableString("Login");
//        s.setSpan(new TypefaceSpan(this, "LatoRegular.ttf"), 0, s.length(),
//                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(s);
    }
}

package infinity.runapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.SpannableString;
import android.view.Menu;
import android.view.MenuItem;

import infinity.runapp.library.InfinityDBHandler;


public class SplashActivity extends ActionBarActivity {

    private SpannableString s;

    private final int TIME = 2000;
    ConnectivityManager cm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread mSplash = new Thread() {

            @Override
            public void run() {
                synchronized (this) {
                    try
                    {
                        wait(TIME);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    finally
                    {
                        if(!checkConnection())
                        {
                            Intent intent = new Intent(getBaseContext(), NoNetwork.class);
                            startActivity(intent);
                            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        }
                        else{
                            checkForUserSQLite();
                        }
                        finish();
                    }
                }
            }
        };
        mSplash.start();

    }

    public boolean checkConnection(){
        cm = (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    public void checkForUserSQLite()
    {
        InfinityDBHandler infinityDbHandler = new InfinityDBHandler(this, null, null, 1);

        if(infinityDbHandler.checkUser())
        {
            Intent intent = new Intent(getBaseContext(), LoadApp.class);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
        else
        {
            Intent intent = new Intent(getBaseContext(), LoginActivity.class);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash, menu);
        restoreActionBar();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

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
        s = new SpannableString("Infinity Run");
//        s.setSpan(new TypefaceSpan(this, "LatoRegular.ttf"), 0, s.length(),
//                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(s);
    }
}

package infinity.runapp.fragments;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import infinity.runapp.MainActivity;
import infinity.runapp.R;
import infinity.runapp.library.JSONParser;

/**
 * Created by ADC on 2/9/2015.
 */
public class WorkoutSummaryFragment extends Fragment
{
    int success;
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    private static final String URL = "http://cgi.soic.indiana.edu/~team36/infinity/save_workout.php";

    JSONParser mJSONParser = new JSONParser();
    Button save;
    Button discard;
    TextView workoutTime;
    TextView workoutDistance;
    View v;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.workout_summary_layout,container,false);

        Intent in = getActivity().getIntent();
        Bundle b = in.getExtras();

        workoutTime = (TextView) v.findViewById(R.id.workoutTime);
        workoutDistance = (TextView) v.findViewById(R.id.workoutDistance);
        TextView workoutSpeed = (TextView) v.findViewById(R.id.workoutSpeed);

        save = (Button)v.findViewById(R.id.saveBtn);
        discard = (Button)v.findViewById(R.id.discardBtn);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new saveWorkout().execute();
            }
        });

        discard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).goHistory(v);
            }
        });

        long time = b.getLong("time");
        long seconds = time/1000;
        String timeString = Long.toString(seconds);

        float distance = b.getFloat("distance");
        String distString = Float.toString(distance);

        float metersPerSecond = distance/seconds;
        String mpsString = Float.toString(metersPerSecond);

        workoutTime.setText(timeString);
        workoutDistance.setText(distString);
        workoutSpeed.setText(mpsString);

        return v;
    }

    class saveWorkout extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() { super.onPreExecute(); }

        @Override
        protected String doInBackground(String... args){

            try{
                String userID = ((MainActivity)getActivity()).getUserID().toString();
                String time = workoutTime.getText().toString();
                String distance = workoutDistance.getText().toString();

                List<NameValuePair> params = new ArrayList<>();
                params.add(new BasicNameValuePair("distance", distance));
                params.add(new BasicNameValuePair("userID", userID));
                params.add(new BasicNameValuePair("time", time));

                JSONObject json = mJSONParser.makeHttpRequest(URL, "POST", params);

                success = json.getInt(TAG_SUCCESS);

                if (success == 1)
                {
                    return json.getString(TAG_MESSAGE);
                } else
                {
                    return json.getString(TAG_MESSAGE);
                }
            }catch (JSONException e){
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String file_url){
            if(file_url != null)
            {
                Toast.makeText(getActivity(), file_url, Toast.LENGTH_LONG).show();
            }

            ((MainActivity)getActivity()).goLoadApp(v);
        }
    }

}

package infinity.runapp.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import infinity.runapp.MainActivity;
import infinity.runapp.R;
import infinity.runapp.library.InfinityDBHandler;
import infinity.runapp.library.JSONParser;

/**
 * Created by adc on 3/19/15.
 */
public class CreateWorkoutFragment extends Fragment
{
    JSONParser mJSONParser = new JSONParser();

    Integer userID;
    Spinner workoutSpinner;
    int success;
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    private static final String URL = "http://cgi.soic.indiana.edu/~team36/infinity/create_workout.php";

    private EditText mWorkoutName, mWorkoutDistance;

    View v;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.create_workout_layout,container,false);

        userID = ((MainActivity)getActivity()).getUserID();

        mWorkoutName = (EditText)v.findViewById(R.id.workoutName);
        mWorkoutDistance = (EditText)v.findViewById(R.id.workoutDistance);

        workoutSpinner = (Spinner)v.findViewById(R.id.workoutsSpinner);

        InfinityDBHandler db = new InfinityDBHandler(getActivity(), null, null, 1);

        ArrayList<String> createdWorkouts = db.createdWorkouts(userID);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                R.layout.list_items, R.id.list_item, createdWorkouts);

        workoutSpinner.setAdapter(adapter);
        workoutSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String workoutName = workoutSpinner.getSelectedItem().toString();
                if (workoutName != "")
                    setValues(workoutName);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button mCreateWorkoutBtn = (Button)v.findViewById(R.id.createWorkoutBtn);
        mCreateWorkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CreateWorkout().execute();
            }
        });

        return v;
    }

    public void setValues(String wname){
        InfinityDBHandler db = new InfinityDBHandler(getActivity(), null, null, 1);

        Double distance = db.getDistance(wname, userID);

        mWorkoutDistance.setText(distance.toString());
        mWorkoutName.setText(wname);
    }

    class CreateWorkout extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() { super.onPreExecute(); }

        @Override
        protected String doInBackground(String... args){

            try{
                String userID = ((MainActivity)getActivity()).getUserID().toString();
                String name = mWorkoutName.getText().toString();
                String distance = mWorkoutDistance.getText().toString();

                List<NameValuePair> params = new ArrayList<>();
                params.add(new BasicNameValuePair("distance", distance));
                params.add(new BasicNameValuePair("userID", userID));
                params.add(new BasicNameValuePair("workoutName", name));

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

            if(success == 1)
            {
                goCreateWorkout();
            }
            else
            {
                mWorkoutName.setText("");
            }
        }
    }

    public void goCreateWorkout(){
        String workoutName = mWorkoutName.getText().toString();

        Fragment fragment = new AssignWorkoutFragment();
        Bundle bundle = new Bundle();
        bundle.putString("workoutName", workoutName);
        fragment.setArguments(bundle);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

}

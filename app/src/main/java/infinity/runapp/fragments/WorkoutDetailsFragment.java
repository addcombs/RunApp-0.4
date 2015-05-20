package infinity.runapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import infinity.runapp.MainActivity;
import infinity.runapp.R;
import infinity.runapp.library.InfinityDBHandler;
import infinity.runapp.library.JSONParser;

/**
 * Created by adc on 3/23/15.
 */
public class WorkoutDetailsFragment extends Fragment {

    private String workoutName;
    private JSONParser mJSONParser = new JSONParser();
    private static final String URL = "http://cgi.soic.indiana.edu/~team36/infinity/get_workout_details.php";
    private static final String TAG_COUNT = "count";
    private ArrayList<String> workoutDetails = new ArrayList<>();
    private ListView mListView;
    private TextView distance;
    private TextView createdBy;
    private TextView expDate;

    View v;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.workout_details_layout, container, false);
        Bundle bundle = this.getArguments();

        workoutName = bundle.getString("workoutName");
        distance = (TextView) v.findViewById(R.id.distance);
        createdBy = (TextView) v.findViewById(R.id.createdBy);
        expDate = (TextView) v.findViewById(R.id.expDate);

        TextView gName = (TextView) v.findViewById(R.id.heading);

        gName.setText(workoutName);

        showWorkoutDetails();

        return v;
    }

    public void showWorkoutDetails() {
        Integer userID = ((MainActivity)getActivity()).getUserID();

        InfinityDBHandler db = new InfinityDBHandler(getActivity(), null, null, 1);

        ArrayList<String> workoutDetails = db.workoutDetails(userID, workoutName);

        distance.setText(workoutDetails.get(0));
        createdBy.setText(db.getUserName(Integer.parseInt(workoutDetails.get(1))));
        expDate.setText(workoutDetails.get(2));

    }

    public void setList(){
        mListView = (ListView) v.findViewById(R.id.workoutList);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                R.layout.list_items, R.id.list_item, workoutDetails);



        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                String itemValue = (String) mListView.getItemAtPosition(position);

//                Fragment groupDetails = new GroupDetailsFragment();
//                Bundle bundle = new Bundle();
//                bundle.putString("group", itemValue);
//                groupDetails.setArguments(bundle);
//
//                FragmentManager fm = getActivity().getSupportFragmentManager();
//                fm.beginTransaction()
//                        .replace(R.id.container, groupDetails)
//                        .commit();
                Toast.makeText(getActivity().getApplicationContext(),
                        "Position: " + position + ", Value: " + itemValue, Toast.LENGTH_LONG).show();
            }
        });
    }

}
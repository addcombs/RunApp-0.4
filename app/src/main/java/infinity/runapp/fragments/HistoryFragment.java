package infinity.runapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import infinity.runapp.R;
import infinity.runapp.getsets.ActiveUser;
import infinity.runapp.getsets.History;
import infinity.runapp.library.InfinityDBHandler;

/**
 * Created by ADC on 2/9/2015.
 */
public class HistoryFragment extends Fragment
{
    ListView mListView;
    View v;
    Integer userID;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.history_layout,container,false);

        mListView = (ListView) v.findViewById(R.id.history);

        getHistory();

        return v;
    }

    public void getHistory(){
        InfinityDBHandler dbHandler = new InfinityDBHandler(getActivity(), null, null, 1);

        ActiveUser myActiveUser = dbHandler.setUser();
        userID = myActiveUser.getUserID();

        History[] newHistory = dbHandler.history(userID, 100);

        ArrayAdapter<History> adapter = new ArrayAdapter<>(getActivity(),
                R.layout.list_items, R.id.list_item, newHistory);

        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                InfinityDBHandler dbHandler2 = new InfinityDBHandler(getActivity(), null, null, 1);

                Integer historyID = dbHandler2.getHistoryID(position, userID);

                Fragment historyDetails = new HistoryDetailsFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("historyID", historyID);
                historyDetails.setArguments(bundle);

                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.beginTransaction()
                        .replace(R.id.container, historyDetails)
                        .commit();
            }
        });
    }
}

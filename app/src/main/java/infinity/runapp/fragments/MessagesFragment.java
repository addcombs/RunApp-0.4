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

import infinity.runapp.MainActivity;
import infinity.runapp.R;
import infinity.runapp.getsets.RecentMessages;
import infinity.runapp.library.InfinityDBHandler;

/**
 * Created by ADC on 2/9/2015.
 */
public class MessagesFragment extends Fragment
{
    ListView mMessagesView;

    View v;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.messages_layout,container,false);


        mMessagesView = (ListView)v.findViewById(R.id.messagesList);

        getMessages();

        return v;
    }

    public void getMessages(){
        InfinityDBHandler dbHandler = new InfinityDBHandler(getActivity(), null, null, 1);

        Integer userID = ((MainActivity)getActivity()).getUserID();

        RecentMessages[] newMessages = dbHandler.messages(userID, 100);

        ArrayAdapter<RecentMessages> adapter = new ArrayAdapter<>(getActivity(),
                R.layout.list_items, R.id.list_item, newMessages);



        mMessagesView.setAdapter(adapter);
        mMessagesView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((MainActivity)getActivity()).goMessages(v);
            }
        });

    }
}

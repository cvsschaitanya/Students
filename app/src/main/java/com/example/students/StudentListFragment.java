package com.example.students;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.example.students.dummy.DummyContent;
import com.example.students.dummy.DummyContent.DummyItem;

import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class StudentListFragment extends ListFragment {

    // TODO: Customize parameters
    private int mColumnCount = 1;
    Context CurrentContext;
    LayoutInflater Inflater;
    OnListFragmentInteractionListener listener;
    Cursor cursor;

    private OnListFragmentInteractionListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = super.onCreateView(inflater,container,savedInstanceState);

        Inflater = inflater;
        // Set the adapter
       /* if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new MyStudentRecyclerViewAdapter(DummyContent.ITEMS, mListener));
        }
*/


        return view;
    }


    public void onStart()
    {
        super.onStart();
        CurrentContext = Inflater.getContext();
        listener = (NominalRoll)CurrentContext;

        new GetListTask().execute();


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }


    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(String user);
    }


    public void onListItemClick(ListView l,View v,int position ,long id)
    {
        String username;
        int pos = 0;
        if(listener!=null)
        {
            if(cursor.moveToFirst())
            {
                while(pos!=position)
                {
                    cursor.moveToNext();
                    pos++;
                }
                listener.onListFragmentInteraction(cursor.getString(1));
            }
        }
    }


    class GetListTask extends AsyncTask<Void,Void,Object>
    {


        @Override
        protected Object doInBackground(Void[] voids) {
            try
            {

                SQLhelper helper = new SQLhelper(CurrentContext);
                SQLiteDatabase database =  helper.getReadableDatabase();

                cursor = database.query("students", new String[]{"_id","USERNAME", "PASSWORD"}, "NOT roll = ?" ,new String[]{"-1"}, null, null, null);
                //cursor = database.query("students", new String[]{"_id","USERNAME", "PASSWORD"}, null, null, null, null, null);

                return null;


            } catch (Exception e) {
                return e;
            }
        }

        protected void onPostExecute(Object object) {
            if (object == null) {
                CursorAdapter listAdapter = new SimpleCursorAdapter(CurrentContext, android.R.layout.simple_list_item_1, cursor, new String[]{"USERNAME"}, new int[]{android.R.id.text1}, 0) {

                };

                setListAdapter(listAdapter);
            }
            else {
                new MyToast(CurrentContext, ((Exception)object).getMessage());
            }
        }
    }


}

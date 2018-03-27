package com.example.kylephan.learningapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;


public class AbstractFragment extends Fragment {

    private String fragmentTitle;
    private int navId;

    private OnFragmentInteractionListener mListener;

    public AbstractFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    protected String getFragmentTitle() {
        return fragmentTitle;
    }

    protected int getNavId() {
        return navId;
    }

    protected void setFragmentTitle(String title) {
        fragmentTitle = title;
    }

    protected void setNavId(int item) {
        navId = item;
    }

    public OnFragmentInteractionListener getmListener() {
        return mListener;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isResumed()) {
            mListener.onFragmentInteraction(fragmentTitle, navId);
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(String fragTitle, int navId);
    }
}

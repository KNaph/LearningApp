package com.example.kylephan.learningapp;

import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FlickrViewerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FlickrViewerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FlickrViewerFragment extends AbstractFragment {

    private OnFragmentInteractionListener mListener;

    public FlickrViewerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment FlickrViewerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FlickrViewerFragment newInstance() {
        FlickrViewerFragment fragment = new FlickrViewerFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.setFragmentTitle("Flickr Viewer");
        super.setNavId(R.id.nav_second);
        return inflater.inflate(R.layout.fragment_flickr_viewer, container, false);
    }
}

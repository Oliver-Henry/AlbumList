package com.example.oliver.albumlist.albums;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.oliver.albumlist.R;
import com.example.oliver.albumlist.data.local.RealmAlbumListModel;
import com.example.oliver.albumlist.data.local.controller.RealmHelper;
import com.example.oliver.albumlist.data.network.AppDataManager;
import com.example.oliver.albumlist.data.network.model.AlbumsModel;
import com.example.oliver.albumlist.ui.base.BaseFragment;
import com.example.oliver.albumlist.ui.utils.rx.AppSchedulerProvider;
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AlbumsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AlbumsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AlbumsFragment extends BaseFragment implements IAlbumsMvpView {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private RecyclerView rVAlbums;
    private AlbumsMvpPresenterImpl<AlbumsFragment> albumsFragmentAlbumsMvpPresenter;
    private Realm realm;
    private RealmHelper realmHelper;
    private ArrayList<RealmAlbumListModel> albumArrayList;
    private RealmAlbumListModel realmAlbumListModel;

    private OnFragmentInteractionListener mListener;

    public AlbumsFragment() {
        // Required empty public constructor
    }

    public void initRealm(){
        realm = Realm.getDefaultInstance();
        realmHelper = new RealmHelper(realm);
        albumArrayList = new ArrayList<>();
        albumArrayList = realmHelper.getAlbumsR();
    }

    public static AlbumsFragment newInstance(String param1, String param2) {
        AlbumsFragment fragment = new AlbumsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        albumsFragmentAlbumsMvpPresenter = new AlbumsMvpPresenterImpl<>(new AppDataManager(), new AppSchedulerProvider(), new CompositeDisposable());
        albumsFragmentAlbumsMvpPresenter.onAttach(this);
        rVAlbums = view.findViewById(R.id.rVAlbumList);
        rVAlbums.setLayoutManager(new LinearLayoutManager(getActivity()));
        initRealm();
        callService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_albums, container, false);
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void callService(){
        ReactiveNetwork.observeInternetConnectivity()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean Connected) throws Exception {
                        if(Connected){albumsFragmentAlbumsMvpPresenter.loadAlbumsList();}
                        else {
                            albumArrayList = realmHelper.getAlbumsR();
                            rVAlbums.setAdapter(new RealmAlbumsListAdapter(getActivity().getApplicationContext(), albumArrayList, R.layout.row_layout));
                        }
                    }
                });
    }

    @Override
    public void onFetchDataProgress() {

    }

    @Override
    public void onFetchDataSuccess(List<AlbumsModel>  albumsModel) {
        rVAlbums.setAdapter(new AlbumsListAdapter(getActivity(), albumsModel, R.layout.row_layout));
    }

    @Override
    public void onFetchDataError(String error) {
        showMessage(error);
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

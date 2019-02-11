package com.example.oliver.albumlist.albums;

import com.example.oliver.albumlist.data.network.model.AlbumsModel;
import com.example.oliver.albumlist.ui.base.MvpView;

import java.util.List;

public interface IAlbumsMvpView extends MvpView {
    void onFetchDataProgress();
    void onFetchDataSuccess(List<AlbumsModel> albumsModel);
    void onFetchDataError(String error);
}

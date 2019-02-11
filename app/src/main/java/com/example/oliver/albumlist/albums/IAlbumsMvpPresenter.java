package com.example.oliver.albumlist.albums;

import com.example.oliver.albumlist.ui.base.MvpPresenter;

public interface IAlbumsMvpPresenter<V extends IAlbumsMvpView> extends MvpPresenter<V> {
    void loadAlbumsList();
}

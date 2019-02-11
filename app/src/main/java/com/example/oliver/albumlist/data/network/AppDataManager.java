package com.example.oliver.albumlist.data.network;

import com.example.oliver.albumlist.data.network.model.AlbumsModel;

import java.util.List;

import io.reactivex.Observable;

public class AppDataManager implements IDataManager {
    private IApiHelper iApiHelper;
    public AppDataManager(){iApiHelper = new AppApiHelper();}


    @Override
    public Observable<List<AlbumsModel>> getAlbumsList() {
        return iApiHelper.getAlbumsList();
    }
}

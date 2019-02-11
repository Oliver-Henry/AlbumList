package com.example.oliver.albumlist.data.network;

import com.example.oliver.albumlist.data.network.model.AlbumsModel;
import com.example.oliver.albumlist.data.network.service.IRequestInterface;
import com.example.oliver.albumlist.data.network.service.ServiceConnection;

import java.util.List;

import io.reactivex.Observable;

public class AppApiHelper implements IApiHelper {

    private IRequestInterface iRequestInterface;

    @Override
    public Observable<List<AlbumsModel>> getAlbumsList() {
        return iRequestInterface.getAlbums();
    }

    public AppApiHelper() {iRequestInterface = ServiceConnection.getConnection();}
}

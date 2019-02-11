package com.example.oliver.albumlist.data.network;

import com.example.oliver.albumlist.data.network.model.AlbumsModel;

import java.util.List;

import io.reactivex.Observable;

public interface IApiHelper {
    Observable<List<AlbumsModel>> getAlbumsList();
}

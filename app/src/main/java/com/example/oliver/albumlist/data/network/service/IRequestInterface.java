package com.example.oliver.albumlist.data.network.service;


import com.example.oliver.albumlist.data.network.model.AlbumsModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface IRequestInterface {

    @GET(ApiList.ALBUMS)
    Observable<List<AlbumsModel>> getAlbums();
}

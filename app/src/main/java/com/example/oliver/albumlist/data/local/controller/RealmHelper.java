package com.example.oliver.albumlist.data.local.controller;

import com.example.oliver.albumlist.data.local.RealmAlbumListModel;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by olive on 11/02/2018.
 */

public class RealmHelper {

    Realm realm;

    public RealmHelper(Realm realm) {
        this.realm = realm;
    }

    public void saveAlbumsR(final RealmAlbumListModel realmAlbumListModel){

        //Async
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {realm.copyToRealm(realmAlbumListModel);}
        });
    }

    public void clearAlbumsR(){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.deleteAll();
            }
        });

    }

    public ArrayList<RealmAlbumListModel> getAlbumsR(){

        ArrayList<RealmAlbumListModel> albumArrayList = new ArrayList<>();

        RealmResults<RealmAlbumListModel> realmResults= realm.where(RealmAlbumListModel.class).findAll();

        for(RealmAlbumListModel realmAlbumListModel : realmResults){
            albumArrayList.add(realmAlbumListModel );
        }
        return albumArrayList;
    }
}

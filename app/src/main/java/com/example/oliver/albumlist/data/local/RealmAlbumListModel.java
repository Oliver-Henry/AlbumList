package com.example.oliver.albumlist.data.local;

import io.realm.RealmObject;

public class RealmAlbumListModel extends RealmObject {

    public RealmAlbumListModel(){}

    public RealmAlbumListModel(String AlbumName, String AlbumID){
        this.AlbumName = AlbumName;
        this.AlbumID = AlbumID;
    }

    private String AlbumName;
    private String AlbumID;

    public String getAlbumName() {return AlbumName;}

    public String getAlbumID() {return AlbumID;}

    public void setAlbumName(String AlbumName) {this.AlbumName = AlbumName;}

    public void setAlbumID(String AlbumID) {this.AlbumID = AlbumID;}
}

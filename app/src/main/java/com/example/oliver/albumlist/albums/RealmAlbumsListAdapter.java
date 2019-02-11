package com.example.oliver.albumlist.albums;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.oliver.albumlist.R;
import com.example.oliver.albumlist.data.local.RealmAlbumListModel;
import com.example.oliver.albumlist.data.local.controller.RealmBackupRestore;
import com.example.oliver.albumlist.data.local.controller.RealmHelper;

import java.util.ArrayList;

import io.realm.Realm;

class RealmAlbumsListAdapter extends RecyclerView.Adapter<RealmAlbumsListAdapter.MyViewHolder> {
    Context applicationContext;
    ArrayList<RealmAlbumListModel> albumArrayList;
    int row_layout;
    private Realm realm;
    private RealmHelper realmHelper;
    private RealmBackupRestore realmBackupRestore;
    private Activity activity;

    public RealmAlbumsListAdapter(Context applicationContext, ArrayList<RealmAlbumListModel> albumArrayList, int row_layout) {
        this.applicationContext = applicationContext;
        this.albumArrayList = albumArrayList;
        this.row_layout = row_layout;
        this.activity = activity;
    }

    private void initRealm() {
        realm = Realm.getDefaultInstance();
        realmHelper = new RealmHelper(realm);
    }

    @Override
    public RealmAlbumsListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        initRealm();
        realmBackupRestore = new RealmBackupRestore(activity);
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(row_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(RealmAlbumsListAdapter.MyViewHolder holder, int position) {
        holder.rAlbumName.setText(albumArrayList.get(position).getAlbumName());
        holder.rAlbumID.setText(albumArrayList.get(position).getAlbumID());
    }

    @Override
    public int getItemCount() {
        return albumArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView rAlbumName;
        private TextView rAlbumID;

        public MyViewHolder(View itemView) {
            super(itemView);
            rAlbumName = itemView.findViewById(R.id.tVAlbumName);
            rAlbumID = itemView.findViewById(R.id.tVAlbumID);
        }
    }
}

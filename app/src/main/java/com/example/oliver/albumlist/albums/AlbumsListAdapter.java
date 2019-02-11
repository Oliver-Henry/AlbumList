package com.example.oliver.albumlist.albums;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.oliver.albumlist.R;
import com.example.oliver.albumlist.data.local.RealmAlbumListModel;
import com.example.oliver.albumlist.data.local.controller.RealmBackupRestore;
import com.example.oliver.albumlist.data.local.controller.RealmHelper;
import com.example.oliver.albumlist.data.network.model.AlbumsModel;

import java.util.List;

import io.realm.Realm;

class AlbumsListAdapter extends RecyclerView.Adapter<AlbumsListAdapter.MyViewHolder> {
    FragmentActivity activity;
    List<AlbumsModel> albumsModel;
    int row_layout;
    private Realm realm;
    private RealmHelper realmHelper;
    private RealmBackupRestore realmBackupRestore;
    private RealmAlbumListModel realmAlbumListModel;


    public AlbumsListAdapter(FragmentActivity activity, List<AlbumsModel> albumsModel, int row_layout) {
        this.activity = activity;
        this.albumsModel = albumsModel;
        this.row_layout = row_layout;
    }

    private void initRealm() {
        realm = Realm.getDefaultInstance();
        realmHelper = new RealmHelper(realm);
    }

    @Override
    public AlbumsListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        initRealm();
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(row_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(AlbumsListAdapter.MyViewHolder holder, int position) {
        realmAlbumListModel = new RealmAlbumListModel(
                albumsModel.get(position).getTitle(),
                String.valueOf(albumsModel.get(position).getId())
        );
        realmHelper.saveAlbumsR(realmAlbumListModel);

        holder.txtAlbumName.setText("Album Name: " + albumsModel.get(position).getTitle());
        holder.txtAlbumID.setText("Album ID: " + String.valueOf(albumsModel.get(position).getId()));
    }

    @Override
    public int getItemCount() {
        return albumsModel.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView txtAlbumName;
        private TextView txtAlbumID;

        public MyViewHolder(View itemView) {
            super(itemView);
            txtAlbumName = itemView.findViewById(R.id.tVAlbumName);
            txtAlbumID = itemView.findViewById(R.id.tVAlbumID);
        }
    }
}

package com.example.oliver.albumlist.albums;

import com.example.oliver.albumlist.data.network.IDataManager;
import com.example.oliver.albumlist.data.network.model.AlbumsModel;
import com.example.oliver.albumlist.ui.base.BasePresenter;
import com.example.oliver.albumlist.ui.utils.rx.SchedulerProvider;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class AlbumsMvpPresenterImpl<V extends IAlbumsMvpView> extends BasePresenter<V> implements IAlbumsMvpPresenter<V> {


    public AlbumsMvpPresenterImpl(IDataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void loadAlbumsList() {
        getMvpView().onFetchDataProgress();
        getCompositeDisposable().add(getDataManager().getAlbumsList()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<List<AlbumsModel>>() {
                               @Override
                               public void accept(List<AlbumsModel> albumsModels) throws Exception {
                                   getMvpView().onFetchDataSuccess(albumsModels);
                               }
                           },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                getMvpView().onFetchDataError(throwable.getMessage());
                            }
                        }));

    }
}

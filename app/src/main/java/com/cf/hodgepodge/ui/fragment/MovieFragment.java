package com.cf.hodgepodge.ui.fragment;



import com.cf.hodgepodge.R;
import com.cf.hodgepodge.presenter.home.MoviePresenter;
import com.cf.hodgepodge.ui.base.BaseFragment;
import com.cf.hodgepodge.ui.iview.IMovieView;


public class MovieFragment extends BaseFragment<MoviePresenter> implements IMovieView {



    @Override
    protected void loadData() {

    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public int getContentViewResId() {
        return R.layout.fragment_movie;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initInject() {

    }
}

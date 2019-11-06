package com.mezmeraiz.blank.di.paging

import android.app.Application
import androidx.paging.DataSource
import dagger.Module
import dagger.Provides
import com.mezmeraiz.blank.R
import com.mezmeraiz.blank.common.MarginDecoration
import com.mezmeraiz.blank.db.UserDao
import com.mezmeraiz.blank.model.User
import com.mezmeraiz.blank.network.UserService
import com.mezmeraiz.blank.repository.paging.PagedDataSource
import com.mezmeraiz.blank.repository.paging.PagingSourceFactory
import javax.inject.Provider

@Module
class PagingModule() {

    @Provides
    fun provideRemoteFactory(service: Provider<PagedDataSource>): PagingSourceFactory {
        return PagingSourceFactory(service)
    }

    @Provides
    fun provideLocalFactory(userDao: UserDao): DataSource.Factory<Int, User> {
        return userDao.getAllCachedUsers()
    }

    @Provides
    fun providePagedDataSource(userService: UserService, userDao: UserDao): PagedDataSource {
        return PagedDataSource(userService, userDao)
    }

    @Provides
    fun provideMarginDecoration(app: Application): MarginDecoration {
        return MarginDecoration(app.resources.getDimension(R.dimen.card_margin).toInt())
    }

}
package ie.queallygroup_android.ui.policies.viewmodel

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import ie.queallygroup_android.data.models.db.Policy
import ie.queallygroup_android.manager.interfaces.PoliciesManager
import ie.queallygroup_android.ui.base.BaseViewModel
import ie.queallygroup_android.ui.policies.adapter.PoliciesBoundaryCallback
import ie.queallygroup_android.utils.AppConstants
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class PoliciesViewModel @Inject constructor(
    private val boundaryCallback: PoliciesBoundaryCallback,
    private val mPoliciesManager: PoliciesManager

) : BaseViewModel(mPoliciesManager) {

    private val liveData: LiveData<PagedList<Policy>>

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(AppConstants.GetPostsCount)
            .setEnablePlaceholders(false)
            .build()

        liveData = LivePagedListBuilder(mPoliciesManager.getPoliciesDataSource(), config)
            .setBoundaryCallback(boundaryCallback)
            .build()
    }

    fun getPosts(): LiveData<PagedList<Policy>> {
        return liveData
    }

    fun callRefresh(needToShowProgress: Boolean, callback: () -> Unit) {
        makeRx(
            mPoliciesManager.refreshPolicies()
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(callback)
                .doOnComplete {
                    boundaryCallback.release()
                }
        )
        isLoading.set(needToShowProgress)
    }

    override fun onCleared() {
        super.onCleared()
        boundaryCallback.compositeDisposable.clear()
    }

}
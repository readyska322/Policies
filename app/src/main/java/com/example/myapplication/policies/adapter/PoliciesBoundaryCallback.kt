package ie.queallygroup_android.ui.policies.adapter

import android.util.Log
import androidx.paging.PagedList
import ie.queallygroup_android.data.models.db.Policy
import ie.queallygroup_android.manager.interfaces.PoliciesManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PoliciesBoundaryCallback @Inject constructor(private val mPoliciesManager: PoliciesManager) :
    PagedList.BoundaryCallback<Policy>() {
    private var pageNumber: Int = 1

    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onZeroItemsLoaded() {
        loadNewPolicy()
    }

    override fun onItemAtEndLoaded(policy: Policy) {
        //++pageNumber
         //if(pageNumber < 2) loadNewPolicy()
        //loadNewPolicy()
    }


    private fun loadNewPolicy() {
        compositeDisposable.add(
            mPoliciesManager.callNewPolicies(
                pageNumber
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({}, {
                    onError(it)
                })
        )
    }

    private fun onError(throwable: Throwable) {
        Log.d("NewsBoundaryCallback", throwable.localizedMessage ?: "getPolicies")
    }

    fun release() {
        pageNumber = 1
    }
}
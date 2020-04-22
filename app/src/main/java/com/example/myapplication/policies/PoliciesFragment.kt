package ie.queallygroup_android.ui.policies

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import ie.queallygroup_android.R
import ie.queallygroup_android.databinding.FragmentPoliciesBinding
import ie.queallygroup_android.ui.base.BaseFragment
import ie.queallygroup_android.ui.policies.adapter.PoliciesAdapter
import ie.queallygroup_android.ui.policies.viewmodel.PoliciesViewModel
import ie.queallygroup_android.ui.policyopened.PolicyOpenedActivity
import kotlinx.android.synthetic.main.toolbar_policies.view.*
import javax.inject.Inject

class PoliciesFragment : BaseFragment<FragmentPoliciesBinding, PoliciesViewModel>() {
    override val viewModelClass = PoliciesViewModel::class.java
    override val layoutId = R.layout.fragment_policies

    @Inject
    lateinit var mAdapter: PoliciesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAdapter.onClickedPdf = {
            showPdf(it[0],it[1])
        }

        viewDataBinding.recyclerView.adapter = mAdapter

        viewDataBinding.refresh.setOnRefreshListener {
            //viewDataBinding.refresh.isRefreshing = true///////////////
            callRefresh(false)
        }
        subscribeToLiveData()

        initToolbar()
    }

    private fun showPdf(url: String, title: String) {
        startActivity(PolicyOpenedActivity.newIntent(activity!!).putExtra("pdf_url",url).putExtra("title",title))
    }

    private fun subscribeToLiveData() {
        callRefresh(true)
        viewModel.getPosts().observe(this, Observer {

            mAdapter.submitList(it) {
                viewDataBinding.refresh?.isRefreshing = false
                viewModel.isLoading.set(false)
            }
        })
    }

    private fun callRefresh(needToShowProgress: Boolean) {
        viewModel.callRefresh(needToShowProgress) {
           viewDataBinding.refresh.isRefreshing = false
        }
    }

    private fun initToolbar() {
        activity!!.window.statusBarColor = Color.argb(255,217, 83, 44)
        val toolbar = viewDataBinding.root.policies_toolbar
        toolbar.initDefaultNavigation()
    }

    companion object {
        val TAG: String = PoliciesFragment::class.java.simpleName

        fun newInstance():  PoliciesFragment {
            val args = Bundle()
            val fragment = PoliciesFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
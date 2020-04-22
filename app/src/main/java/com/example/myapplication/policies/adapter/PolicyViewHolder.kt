package ie.queallygroup_android.ui.policies.adapter

import ie.queallygroup_android.data.models.db.Policy
import ie.queallygroup_android.databinding.ItemPoliciesBinding
import ie.queallygroup_android.ui.base.BaseViewHolder
import ie.queallygroup_android.ui.policies.item.ItemPolicyViewModel

class PolicyViewHolder constructor(
    private val mBinding: ItemPoliciesBinding,
    private val mViewModel: ItemPolicyViewModel

) : BaseViewHolder<Policy>(mBinding.root) {

    init {
        mBinding.viewModel = mViewModel
    }

    override fun bind(data: Policy) {
        super.bind(data)
        data?.let { mViewModel.initFields(it) }

        if (!data.documentUrl.isNullOrEmpty()) {


        } else {

        }

        subscribeToObservableFields()
    }


    private fun subscribeToObservableFields() {

    }
}
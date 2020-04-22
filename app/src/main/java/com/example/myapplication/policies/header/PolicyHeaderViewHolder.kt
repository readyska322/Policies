package ie.queallygroup_android.ui.policies.header

import ie.queallygroup_android.data.models.db.Policy
import ie.queallygroup_android.databinding.ItemPoliciesBinding
import ie.queallygroup_android.ui.base.BaseViewHolder
import ie.queallygroup_android.ui.policies.item.ItemPolicyViewModel

class PolicyHeaderViewHolder constructor(
    private val mBinding: ItemPoliciesBinding,
    private val mViewModel: ItemPolicyViewModel
) : BaseViewHolder<Policy>(mBinding.root) {

    override fun bind(data: Policy) {
        super.bind(data)
        mViewModel
    }

    init {
        mBinding.viewModel = mViewModel
    }

}
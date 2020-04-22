package ie.queallygroup_android.ui.policies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ObservableInt
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import ie.queallygroup_android.data.models.db.Policy
import ie.queallygroup_android.databinding.ItemPoliciesBinding
import ie.queallygroup_android.ui.base.BaseViewHolder
import ie.queallygroup_android.ui.policies.item.ItemPolicyViewModel
import javax.inject.Inject
import javax.inject.Provider

class PoliciesAdapter @Inject constructor(
    private val mItemPolicyVMProvider: Provider<ItemPolicyViewModel>

    ) : PagedListAdapter<Policy, BaseViewHolder<Policy>>(DIFF_CALLBACK) {

    val postIdForActionWithPost = ObservableInt(0)
    var onClickedPdf:(( readOnlyView: List<String>) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<Policy> {
        return when (viewType) {

                PoliciesAdapter.VIEW_TYPE_POST -> PolicyViewHolder(
                    ItemPoliciesBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                    mItemPolicyVMProvider.get()

                ).apply {
                    onItemClickCallback = {
                        onClickedPdf?.invoke(arrayListOf(it.documentUrl!!,it.title!!))
                    }
                }
                else -> {
                    PolicyViewHolder(
                        ItemPoliciesBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                        mItemPolicyVMProvider.get()

                    ).apply {
                        onItemClickCallback = {
                            onClickedPdf?.invoke(arrayListOf(it.documentUrl!!,it.title!!))
                        }
                    }
                }

        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Policy>, position: Int) {
        holder.run {
            bind(getItem(position)!!)
        }
    }


    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            VIEW_TYPE_HEADER
        } else {
            VIEW_TYPE_POST
        }
    }


    companion object {

        const val VIEW_TYPE_POST = 0
        const val VIEW_TYPE_HEADER = 1

        private val DIFF_CALLBACK: DiffUtil.ItemCallback<Policy> =
            object : DiffUtil.ItemCallback<Policy>() {
                override fun areItemsTheSame(
                    oldValue: Policy,
                    newValue: Policy
                ): Boolean {
                    // User properties may have changed if reloaded from the DB, but ID is fixed
                    return oldValue.uniqueId.equals(newValue.uniqueId)
                }

                override fun areContentsTheSame(
                    oldValue: Policy,
                    newValue: Policy
                ): Boolean {
                    // NOTE: if you use equals, your object must properly override Object#equals()
                    // Incorrectly returning false here will result in too many animations.
                    return oldValue.title.equals(newValue.title)
                }
            }
    }
}

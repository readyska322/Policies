package ie.queallygroup_android.ui.policies.item

import androidx.databinding.ObservableField
import ie.queallygroup_android.data.models.db.Policy
import ie.queallygroup_android.manager.interfaces.PoliciesManager
import ie.queallygroup_android.ui.base.BaseViewModel
import java.util.*

import javax.inject.Inject

class ItemPolicyViewModel @Inject constructor(policiesManager: PoliciesManager)
    : BaseViewModel(policiesManager) {
    val docunentUrl = ObservableField<String>()
    val title = ObservableField<String>()
    val uniqueId = ObservableField<String>()
    val modifiedBy = ObservableField<String>()
    val dateModified = ObservableField<Date>()
    val type = ObservableField<String>()
    val content = ObservableField<Int>()
    val ImageUrl = ObservableField<String>()

    fun initFields(data: Policy){
        uniqueId.set(data.uniqueId)
        docunentUrl.set(data.documentUrl)
        title.set(data.title!!.replace(".pdf",""))
        modifiedBy.set(data.modifiedBy)
        dateModified.set(data.dateModified)
        type.set(data.type)
        ImageUrl.set(data.imageUrl)

    }

}
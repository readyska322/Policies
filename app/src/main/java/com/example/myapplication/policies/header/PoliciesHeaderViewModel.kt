package ie.queallygroup_android.ui.policies.header

import ie.queallygroup_android.manager.interfaces.ProfileManager
import ie.queallygroup_android.ui.base.BaseProfileDataViewModel
import javax.inject.Inject

class PoliciesHeaderViewModel @Inject constructor(profileManager: ProfileManager) :
    BaseProfileDataViewModel(profileManager)
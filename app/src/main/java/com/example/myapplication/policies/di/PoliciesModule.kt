package ie.queallygroup_android.ui.policies.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ie.queallygroup_android.di.qualifiers.ViewModelKey
import ie.queallygroup_android.ui.policies.viewmodel.PoliciesViewModel

@Module
interface PoliciesModule {
    @Binds
    @IntoMap
    @ViewModelKey(PoliciesViewModel::class)
    fun provideVM(policiesVM: PoliciesViewModel): ViewModel
}
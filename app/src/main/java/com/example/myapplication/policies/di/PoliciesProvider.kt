package ie.queallygroup_android.ui.policies.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ie.queallygroup_android.ui.policies.PoliciesFragment

@Module
abstract class PoliciesProvider {
    @ContributesAndroidInjector(modules = [PoliciesModule::class])
    abstract fun providePoliciesFragment(): PoliciesFragment
}
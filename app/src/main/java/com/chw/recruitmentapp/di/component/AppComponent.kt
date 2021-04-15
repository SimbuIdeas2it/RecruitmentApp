package com.chw.recruitmentapp.di.component

import com.chw.recruitmentapp.application.RecruitmentApp
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
//    modules = [
//        NetworkModule::class
//    ]
)

interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: RecruitmentApp): Builder

        fun build(): AppComponent
    }

    fun inject(app: RecruitmentApp)
}
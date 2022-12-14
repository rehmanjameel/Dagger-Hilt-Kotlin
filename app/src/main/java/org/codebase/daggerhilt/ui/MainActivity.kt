package org.codebase.daggerhilt.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.components.SingletonComponent
import org.codebase.daggerhilt.R
import javax.inject.Inject
import javax.inject.Qualifier
import javax.inject.Singleton

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    // can't inject the dependency in an activity so inject in field

    // field injection
//    @Impl3
//    @Inject
//    lateinit var someRandomString: String

    @Inject
    lateinit var someClass: SomeClass

    @Inject
    lateinit var fragmentFactory: MainFragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.fragmentFactory = fragmentFactory
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_fragment_container, MainFragment::class.java, null)
            .commit()

//        Log.e("Random sting printing, ", "onCreate $someRandomString")
//        Log.e("some other class, ", "onCreate ${someClass.doSomeOtherThing()}")
        Log.e("some class thing 1, ", "onCreate ${someClass.doAthing1()}")
        Log.e("some class thing 2, ", "onCreate ${someClass.doAthing2()}")
    }
}

//@AndroidEntryPoint
//class MyFragment: Fragment() {
//
//    @Inject
//    lateinit var someClass: SomeClass
//}

//@FragmentScoped
class SomeClass
@Inject
constructor(
// generates error while using interface or some third party library like GSON directly
    @Impl1 private val someInterfaceImpl1: SomeInterface,
    @Impl2 private val someInterfaceImpl2: SomeInterface
//    private val gson: Gson
    ) {

    fun doAthing1(): String{
        return "Look I got: ${someInterfaceImpl1.getAThing()}"
    }

    fun doAthing2(): String{
        return "Look I got: ${someInterfaceImpl2.getAThing()}"
    }

//    fun doSomeOtherThing() : String {
//        return someOtherClass.doSomeOtherThing()
//    }
}

class SomeDependency @Inject constructor() {
    fun getAThing() : String {
        return "A Thing"
    }
}

class SomeInterfaceImpl1
@Inject
constructor(
) : SomeInterface {
    override fun getAThing(): String {
        return "Get a thing1"
    }

}

class SomeInterfaceImpl2
@Inject
constructor(
) : SomeInterface {
    override fun getAThing(): String {
        return "Get a thing2"
    }

}

interface SomeInterface {
    fun getAThing() : String
}


@InstallIn(SingletonComponent::class)
@Module
class MyModule {

//    @Singleton
//    @Binds
//    abstract fun bindSomeDependency(
//        someImpl: SomeInterfaceImpl
//    ) : SomeInterface
//
//    @Singleton
//    @Binds    // generates error with binds fetching third party library
//    abstract fun bindGSON(gson: Gson) : Gson

//    @Singleton
//    @Provides
//    fun provideSomeString() :String {
//        return "Some String"
//    }

    @Impl1
    @Singleton
    @Provides
    fun provideSomeInterface1(
    ) : SomeInterface {
        return SomeInterfaceImpl1()
    }

    @Impl2
    @Singleton
    @Provides
    fun provideSomeInterface2(
    ) : SomeInterface {
        return SomeInterfaceImpl2()
    }

//    @Singleton
//    @Provides //provides annotation is a way to use third party libraries
//    fun provideSomeGSon(): Gson {
//        return Gson()
//    }
}

// It will create the annotations to specify same types of multiple bounds
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Impl1

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Impl2

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Impl3


//class SomeOtherClass
//@Inject
//constructor() {
//     fun doSomeOtherThing(): String {
//         return "Look I did some other thing!"
//     }
//}
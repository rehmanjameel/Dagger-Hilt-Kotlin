package org.codebase.daggerhilt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject
import javax.inject.Singleton

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    // can't inject the dependency in an activity so inject in field

    // field injection
    @Inject
    lateinit var someRandomString: String

    @Inject
    lateinit var someClass: SomeClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.e("Random sting printing, ", "onCreate $someRandomString")
//        Log.e("some other class, ", "onCreate ${someClass.doSomeOtherThing()}")
        Log.e("some class, ", "onCreate ${someClass.doAthing()}")
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
//    private val someInterface: SomeInterface,
    private val gson: Gson) {

    fun doAthing(): String{
//        return "Look I got: ${someInterface.getAThing()}"
        return ""
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

class SomeInterfaceImpl
@Inject
constructor() : SomeInterface {
    override fun getAThing(): String {
        TODO("Not yet implemented")
    }

}

interface SomeInterface {
    fun getAThing() : String
}


//class SomeOtherClass
//@Inject
//constructor() {
//     fun doSomeOtherThing(): String {
//         return "Look I did some other thing!"
//     }
//}
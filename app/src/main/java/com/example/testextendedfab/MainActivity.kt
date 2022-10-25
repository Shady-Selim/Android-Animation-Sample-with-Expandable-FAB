package com.example.testextendedfab

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.view.isVisible
import androidx.transition.Visibility
import com.airbnb.lottie.LottieCompositionFactory
import com.airbnb.lottie.LottieDrawable
import com.example.testextendedfab.databinding.ActivityMainBinding
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    /*private var addButtonClicked = false

    private val rotateOpenAnimation: Animation by lazy {AnimationUtils.loadAnimation(this, R.anim.rotate_open_animation)}
    private val rotateCloseAnimation: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.rotate_close_animation)}
*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        val fab = binding.fab
        fab.setOnClickListener { view ->
            binding.animationContainer.callOnClick()
            //setAnimation(addButtonClicked)
            fab.isActivated = !fab.isActivated
        }

        val lottieDrawable = LottieDrawable()
        LottieCompositionFactory.fromRawRes(baseContext, R.raw.fab).addListener { lottieComposition ->
            lottieDrawable.composition = lottieComposition
        }
        with(binding){
            fab1.icon = lottieDrawable
            fab1.iconSize = 120
            fab1.iconPadding = 2
            fab1.iconGravity = MaterialButton.ICON_GRAVITY_TOP
            fab1.setOnClickListener {
                if (fab1.isActivated) lottieDrawable.setMinAndMaxFrame(10,18)
                else lottieDrawable.setMinAndMaxFrame(1,9)
                lottieDrawable.playAnimation()
                fab1.isActivated = !fab1.isActivated
            }
        }


    }

    /*private fun setAnimation(buttonClicked: Boolean) {
        if (!buttonClicked){
            binding.fab.startAnimation(rotateOpenAnimation)
        }else{
            binding.fab.startAnimation(rotateCloseAnimation)
        }
    }*/

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}
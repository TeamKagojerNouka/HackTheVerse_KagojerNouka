package com.kagojernouka.utils

import androidx.navigation.NavController
import androidx.navigation.NavDirections
import com.ashiqur.androidbaselib.util.helper.Toaster


/*
 * Created by : 
 * <a href="https://www.github.com/ashiqursuperfly">Ashiqur Rahman</a> on 11/14/20.
*/

object NavigationUtil {

    fun navigate(
        navController: NavController,
        currentDestinationResId: Int,
        action: NavDirections
    ) {

        if (navController.currentDestination?.id == currentDestinationResId) {
            navController.navigate(
                action
            )
        } else {
            Toaster.showToast("Could not navigate to next page!!")
        }

    }

}
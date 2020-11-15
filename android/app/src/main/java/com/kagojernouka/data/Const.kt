package com.kagojernouka.data

import com.kagojernouka.BuildConfig


/*
 * Created by : 
 * <a href="https://www.github.com/ashiqursuperfly">Ashiqur Rahman</a> on 11/14/20.
*/

class Const {

    enum class UserType(val genericName: String) {
        CUSTOMER("Customer"), DELIVERY_MAN("Delivery Man")
    }

    object RequestResult {
        const val SCAN_RESULT = "1031"
    }

    object RequestCode {
        const val QR_SCAN = 1031
    }

    object Api {
        val BASE_URL =  if (BuildConfig.DEBUG) "http://765c33c33098.ngrok.io/" else "https://kagojer-nouka-backend.herokuapp.com/"

        object ENDPOINTS {
            const val TEST = "test/"
            const val CUSTOMER_INFO = "api/customer/$1/"
            const val DELIVERY_MAN_INFO = "api/deliveryman/$1/"
            const val COMPLETE_DELIVERY = "api/delivery/$1/complete/"
            const val CONFIRM_PICKUP = "api/delivery/$1/pickup/"
        }

        object Params {

        }


    }

}
package com.kagojernouka.views.dialog

import android.view.View
import com.kagojernouka.R
import kotlinx.android.synthetic.main.dialog_rate_delivery_man.*


/*
 * Created by : 
 * <a href="https://www.github.com/ashiqursuperfly">Ashiqur Rahman</a> on 11/15/20.
*/

class RateNowDialog(
    private var mListener: OnAppliedRating
): BaseBottomSheetDialog() {

    interface OnAppliedRating {
        fun onAppliedRating(rating: Float)
    }

    override fun getLayoutId(): Int {
        return R.layout.dialog_rate_delivery_man
    }

    override fun afterOnCreateView() {
        setClickListener(mRootView.findViewById(R.id.btn_rate))
    }

    override fun onClick(v: View?) {
        super.onClick(v)


        mListener.onAppliedRating(rating = rating.rating)

        dismiss()

    }
}
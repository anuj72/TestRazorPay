package com.example.testrazorpay

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity(), PaymentResultListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
button.setOnClickListener { startpayment() }
    }

    private fun startpayment() {
        val activity: Activity = this
        val co = Checkout()
        co.setKeyID("rzp_test_aL22jBTCO43ENn")

        try {
            val options = JSONObject()
            options.put("name","anuj")
            options.put("description","payment")
            //You can omit the image option to fetch the image from dashboard
            options.put("image","https://s3.amazonaws.com/rzp-mobile/images/rzp.png")
            options.put("theme.color", "#FF8600");
            options.put("currency","INR");
          /*  options.put("order_id", "order_FYvfX2RDr6LcUu");*/
            options.put("amount","500000")//pass amount in currency subunits

            val prefill = JSONObject()
            prefill.put("email","gaurav.kumar@example.com")
            prefill.put("contact","9876543210")

            options.put("prefill",prefill)
            co.open(activity,options)
        }catch (e: Exception){
            Toast.makeText(activity,"Error in payment: "+ e.message, Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }
    }

    override fun onPaymentError(p0: Int, p1: String?) {

        Log.v("tttttttttttttttt",p1.toString())
    }

    override fun onPaymentSuccess(p0: String?) {
        Log.v("tttttttttttttttt",p0.toString())
    }


}
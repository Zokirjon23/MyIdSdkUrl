package uz.uzbekcard.myidsdkurl

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import uz.myid.android.sdk.capture.MyIdClient
import uz.myid.android.sdk.capture.MyIdConfig
import uz.myid.android.sdk.capture.MyIdException
import uz.myid.android.sdk.capture.MyIdResult
import uz.myid.android.sdk.capture.MyIdResultListener
import uz.myid.android.sdk.capture.model.MyIdBuildMode
import uz.myid.android.sdk.capture.model.MyIdCameraShape
import uz.myid.android.sdk.capture.model.MyIdEntryType
import uz.myid.android.sdk.capture.model.MyIdResidentType
import uz.myid.android.sdk.capture.takeUserResult
import java.util.Locale

class MainActivity : AppCompatActivity(), MyIdResultListener {
    private val myIdClient = MyIdClient()
    private lateinit var withSdk: TextView

    private val clientHashId = "504ec426-faa6-4c39-8d9d-fcb77853748b"
    private val clientHash = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA3EQkkjhyn/cuLYmMeqxz" +
            "ze8IS38s8jTkxN0jq7TXOxF1kRsVv1/AW0tQn4zCVEc+FapoKzKL2+TuB2g2HuK9" +
            "iqug7qr98EpvrmNszEfSZRXfrgAqXIm2eSzWqXZphsnaGj/NNL3iARG4KFbs3mjF" +
            "pVwLi79OV+4ILuvRXhZaek+DxqH0WoyxbBFQE+/SN2HF5GfDYMBakIfDhbSK9LBA" +
            "oUj5MXaYyXOf30TqJzNI7J7uOkWNCKHU4v5cn1wYXOvJBYbyXH35eNHlA0rGV2Bl" +
            "8sNr1JsFGmS2ed650Cj+SAZuDEes8o5xAyrmKnFczKg7zH3VpJ3W+fptl1CvU8AQ" +
            "PQIDAQAB"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        withSdk = findViewById(R.id.with_sdk)
        withSdk.setOnClickListener {
            startMyId()
        }
    }

    private fun startMyId() {
        val clientId = "lendo_sdk-vF5Yvcr8bBCpH2cCvyJyo4nOd6caBaGLbMUxIHl3"

        val config = MyIdConfig.Builder(clientId)
            .withClientHash(clientHash, clientHashId)
            .withThreshold(0.50f)
            .withBuildMode(MyIdBuildMode.PRODUCTION)
            .withEntryType(MyIdEntryType.AUTH)
            .withResidency(MyIdResidentType.RESIDENT)
            .withLocale(Locale(Locale.getDefault().language))
            .withCameraShape(MyIdCameraShape.CIRCLE)
            .withPhoto(false)
            .build()

        val intent = myIdClient.createIntent(activity = this, myIdConfig = config)
        result.launch(intent)
    }

    private val result = takeUserResult(listener = this)
    override fun onError(e: MyIdException) {
        Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
    }

    override fun onSuccess(result: MyIdResult) {
        val intent = Intent(this,OpenUrlActivity::class.java)
        intent.putExtra("code",result.code)
        startActivity(intent)
    }

    override fun onUserExited() {}
}
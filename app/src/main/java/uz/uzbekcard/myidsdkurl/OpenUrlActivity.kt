package uz.uzbekcard.myidsdkurl

import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class OpenUrlActivity: AppCompatActivity() {
    private lateinit var webView: WebView
    private lateinit var editText: EditText
    private lateinit var goButton: View
    private lateinit var myShp: MyShp
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.open_url_activity_layout)
        myShp = MyShp(this)
        webView = findViewById(R.id.web_view)
        editText = findViewById(R.id.edit_text)
        goButton = findViewById(R.id.go_button)
        val code = intent.getStringExtra("code").toString()
        editText.setText(myShp.url)
        goButton.setOnClickListener {
            if (editText.text == null || editText.text.toString().isEmpty()){
                Toast.makeText(this, "Not filled host field", Toast.LENGTH_SHORT).show()
            }else{
                val host = editText.text.toString()
                myShp.url = host
                webView.loadUrl("$host$code")
            }
        }
    }
}


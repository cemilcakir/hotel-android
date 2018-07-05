package cemilcakir.com.hotel

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {

    companion object {
        const val SPLASH_TIME:Long = 2000
    }

    private var disappearRunnable: Runnable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        disappearRunnable = Runnable { disappear() }

        val handler: Handler = Handler()
        handler.postDelayed(disappearRunnable, SPLASH_TIME)
    }

    private fun disappear(){
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
        finish()
    }
}

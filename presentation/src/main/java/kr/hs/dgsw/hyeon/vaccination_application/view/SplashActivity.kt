package kr.hs.dgsw.hyeon.vaccination_application.view

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.hyeon.vaccination_application.base.BaseActivity
import kr.hs.dgsw.hyeon.vaccination_application.databinding.ActivitySplashBinding
import kr.hs.dgsw.hyeon.vaccination_application.viewmodel.SplashViewModel

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>() {

    override val viewModel: SplashViewModel by viewModels()

    override fun createBinding(): ActivitySplashBinding = ActivitySplashBinding.inflate(layoutInflater)

    override fun initObserver() {
        with(viewModel) {
            collectDoneEvent.observe(this@SplashActivity) {
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            centerList.observe(this@SplashActivity) {
                Log.d("Hyeon_Test5", "$it")

            }
        }
    }
}
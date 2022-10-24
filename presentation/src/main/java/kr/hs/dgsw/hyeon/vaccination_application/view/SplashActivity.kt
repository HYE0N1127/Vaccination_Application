package kr.hs.dgsw.hyeon.vaccination_application.view

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import androidx.activity.viewModels
import androidx.core.animation.doOnEnd
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.hyeon.vaccination_application.base.BaseActivity
import kr.hs.dgsw.hyeon.vaccination_application.databinding.ActivitySplashBinding
import kr.hs.dgsw.hyeon.vaccination_application.viewmodel.SplashViewModel

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>() {
    private var currentProgress = 8

    override val viewModel: SplashViewModel by viewModels()

    override fun createBinding(): ActivitySplashBinding =
        ActivitySplashBinding.inflate(layoutInflater)

    override fun initObserver() {
        binding.progressBar.max = 10
        with(viewModel) {
            collectSuccessEvent.observe(this@SplashActivity) {
                currentProgress = 10
                val animator =
                    ObjectAnimator.ofInt(binding.progressBar, "progress", currentProgress)
                        .setDuration(2000)

                animator.start()
                animator.doOnEnd {
                    val intent = Intent(this@SplashActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
            collectFailureEvent.observe(this@SplashActivity) {
                ObjectAnimator.ofInt(binding.progressBar, "progress", currentProgress)
                    .setDuration(2000).start()
            }
        }
    }
    override fun onError(e: Throwable) {
        super.onError(e)
        val dialog = AlertDialog.Builder(this).apply {
            setTitle("에러 발생")
            setMessage("에러 : $e")
            setNegativeButton("확인") { dialog, _ ->
                dialog.dismiss()
            }
        }
        dialog.show()
    }
}
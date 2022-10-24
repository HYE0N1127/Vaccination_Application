package kr.hs.dgsw.hyeon.vaccination_application.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import kr.hs.dgsw.hyeon.vaccination_application.BR
import kr.hs.dgsw.hyeon.vaccination_application.R
import java.lang.reflect.ParameterizedType
import java.util.*

abstract class BaseActivity<VB : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity() {
    protected lateinit var binding: VB
    protected lateinit var mViewModel: VM

    protected abstract val viewModel: VM

    abstract fun createBinding(): VB
    protected abstract fun initObserver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()
        setContentView(binding.root)
        initObserver()
    }

    private fun performDataBinding() {
        binding = createBinding()
        mViewModel = if (::mViewModel.isInitialized) mViewModel else viewModel
        binding.setVariable(BR.vm, mViewModel)
        binding.lifecycleOwner = this
        viewModel.onErrorEvent.observe(this) {
            onError(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::binding.isInitialized) binding.unbind()
    }

    open fun onError(e: Throwable) {}
}
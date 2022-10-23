package kr.hs.dgsw.hyeon.vaccination_application.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentActivity
import com.naver.maps.map.OnMapReadyCallback
import kr.hs.dgsw.hyeon.vaccination_application.BR

abstract class BaseMapActivity<VB : ViewDataBinding, VM : BaseViewModel> : FragmentActivity(), OnMapReadyCallback {
    protected lateinit var binding: VB
    protected lateinit var mViewModel: VM

    protected abstract val viewModel: VM

    abstract fun createBinding(): VB
    protected abstract fun initObserver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()
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
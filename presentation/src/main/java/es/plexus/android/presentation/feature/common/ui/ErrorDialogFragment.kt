package es.plexus.android.presentation.feature.common.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import dagger.hilt.android.AndroidEntryPoint
import es.plexus.android.presentation.databinding.FragmentErrorDialogBinding

@AndroidEntryPoint
class ErrorDialogFragment(private val callback: () -> Unit, private val message: String) :
    DialogFragment() {

    private lateinit var viewBinding: FragmentErrorDialogBinding


    override fun onStart() {
        super.onStart()
        dialog?.window?.apply {
            setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        dialog?.setCanceledOnTouchOutside(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentErrorDialogBinding.inflate(inflater)
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog?.setOnKeyListener { dialog, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_UP
            ) {
                return@setOnKeyListener true
            }
            false
        }
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewBinding) {
            ivErrorClose.setOnClickListener {
                dismiss()
                callback.invoke()
            }
            tvErrorText.text = message
        }
    }
}
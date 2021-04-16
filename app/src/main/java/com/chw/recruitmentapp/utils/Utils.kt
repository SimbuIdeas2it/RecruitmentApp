package com.chw.recruitmentapp.utils

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Utils @Inject constructor(private val context: Context) {
    companion object {
        fun showAlertDialog(context: Context, dialogBuilder: AlertDialog.Builder.() -> Unit) {
            val builder = AlertDialog.Builder(context)
            builder.dialogBuilder()
            val dialog = builder.create()
            dialog.show()
        }
        fun AlertDialog.Builder.positiveButton(text: String = "Okay", handleClick: (which: Int) -> Unit = {}) {
            this.setPositiveButton(text, { dialogInterface, which-> handleClick(which) })
        }

        fun AlertDialog.Builder.negativeButton(text: String = "Cancel", handleClick: (which: Int) -> Unit = {}) {
            this.setNegativeButton(text, { dialogInterface, which-> handleClick(which) })
        }

        fun setProgressDialog(context: Context, message: String): AlertDialog {
            val padding = 50
            val linearLayout = LinearLayout(context)
            linearLayout.orientation = LinearLayout.HORIZONTAL
            linearLayout.setPadding(padding, padding, padding, padding)
            linearLayout.gravity = Gravity.START
            var params = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.gravity = Gravity.CENTER
            linearLayout.layoutParams = params

            val progressBar = ProgressBar(context)
            progressBar.isIndeterminate = true
            progressBar.setPadding(0, 0, padding, 0)
            progressBar.layoutParams = params

            params = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            )
            params.gravity = Gravity.CENTER
            val tvText = TextView(context)
            tvText.text = message
            tvText.setTextColor(Color.parseColor("#000000"))
            tvText.textSize = 20.toFloat()
            tvText.layoutParams = params

            linearLayout.addView(progressBar)
            linearLayout.addView(tvText)

            val builder = AlertDialog.Builder(context)
            builder.setCancelable(false)
            builder.setView(linearLayout)

            val dialog = builder.create()
            val window = dialog.window
            if (window != null) {
                val layoutParams = WindowManager.LayoutParams()
                layoutParams.copyFrom(dialog.window?.attributes)
                layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT
                layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT
                dialog.window?.attributes = layoutParams
            }
            return dialog
        }
    }


}
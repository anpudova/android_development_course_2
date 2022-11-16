package com.example.task_6

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import com.example.task_6.databinding.ActivityMainBinding
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private var headText: String? = null
    private var shortMsgText: String? = null
    private var extendedMsgText: String? = null
    private var timeBeforeMsg: Int = 0
    private var notificationTime: Long = 0
    private var isCheckedCheckbox: Boolean = false
    private var alarmManager: AlarmManager? = null
    private var emptyHead: Boolean = false
    private var emptyShortMsg: Boolean = false
    private var emptyTime: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            btnShowMsg.setOnClickListener {
                headText = etHeadMsg.text.toString()
                shortMsgText = etShortMsg.text.toString()
                extendedMsgText = if (isCheckedCheckbox) {
                    etExtendMsg.text.toString()
                } else {
                    ""
                }
                timeBeforeMsg = Integer.parseInt(etTime.text.toString())
                notificationTime = SystemClock.elapsedRealtime() + timeBeforeMsg * 1000
                alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
                val pendIntent = getPendIntent(headText, shortMsgText, extendedMsgText)
                alarmManager!!.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, notificationTime, pendIntent)
            }

            btnCancelMsg.setOnClickListener {
                if (notificationTime - SystemClock.elapsedRealtime() > 0) {
                    alarmManager!!.cancel(getPendIntent(headText, shortMsgText, extendedMsgText))
                }
            }

            btnTime.setOnClickListener {
                val timeInMillisecond = SystemClock.uptimeMillis()
                val hours = TimeUnit.MILLISECONDS.toHours(timeInMillisecond)
                val minutes = TimeUnit.MILLISECONDS.toMinutes(timeInMillisecond) % 60
                val seconds = TimeUnit.MILLISECONDS.toSeconds(timeInMillisecond) % 60
                val timePassed = String.format("%02d:%02d:%02d", hours, minutes, seconds)
                Toast.makeText(baseContext, timePassed, Toast.LENGTH_SHORT).show()
            }

            checkbox.setOnCheckedChangeListener { _, isChecked ->
                etExtendMsg.isEnabled = isChecked
                isCheckedCheckbox = isChecked
            }

            etHeadMsg.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    emptyHead = etHeadMsg.text.toString() != ""
                }

                override fun afterTextChanged(s: Editable?) {
                    checkNotNullText()
                }

            })

            etShortMsg.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    emptyShortMsg = etShortMsg.text.toString() != ""
                }

                override fun afterTextChanged(s: Editable?) {
                    checkNotNullText()
                }

            })

            etTime.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    emptyTime = etTime.text.toString() != ""
                }

                override fun afterTextChanged(s: Editable?) {
                    checkNotNullText()
                }

            })

        }
    }

    private fun checkNotNullText() {
        with(binding) {
            if (emptyHead && emptyShortMsg && emptyTime) {
                btnShowMsg.isEnabled = true
                btnShowMsg.setBackgroundResource(R.color.purple_500)
            } else {
                btnShowMsg.isEnabled = false
                btnShowMsg.setBackgroundResource(R.color.grey)
            }
        }
    }

    @SuppressLint("UnspecifiedImmutableFlag")
    private fun getPendIntent(headText: String?, shortMsgText: String?, extendedMsgText: String?): PendingIntent =
        Intent(this, AppBroadcastReceiver::class.java).apply {
            putExtra(AppBroadcastReceiver.HEAD_TEXT, headText)
            putExtra(AppBroadcastReceiver.SHORT_TEXT, shortMsgText)
            putExtra(AppBroadcastReceiver.EXTENDED_TEXT, extendedMsgText)
        }.let { intent ->
            PendingIntent.getBroadcast(baseContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        }
}
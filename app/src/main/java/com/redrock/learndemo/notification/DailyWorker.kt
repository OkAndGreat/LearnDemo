package com.redrock.learndemo.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.redrock.learndemo.R
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Author by OkAndGreat
 * Date on 2022/5/4 16:12.
 *
 */
class DailyWorker(private val ctx: Context, params: WorkerParameters) : Worker(ctx, params) {

    override fun doWork(): Result {
        val currentDate = Calendar.getInstance()
        val dueDate = Calendar.getInstance()

        // 设置在大约 20:00:00 AM 执行
        dueDate.set(Calendar.HOUR_OF_DAY, 16)
        dueDate.set(Calendar.MINUTE, 24)
        dueDate.set(Calendar.SECOND, 0)

        if (dueDate.before(currentDate)) {
            dueDate.add(Calendar.HOUR_OF_DAY, 24)
        }

        val timeDiff = dueDate.timeInMillis - currentDate.timeInMillis
        val dailyWorkRequest = OneTimeWorkRequestBuilder<DailyWorker>()
            .setInitialDelay(timeDiff, TimeUnit.MILLISECONDS)
            .build()

        WorkManager.getInstance(applicationContext)
            .enqueue(dailyWorkRequest)

        val manager =
            ctx.getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel("signNoti", "signNoti", NotificationManager.IMPORTANCE_HIGH)
            manager.createNotificationChannel(channel)
        }


        //为了版本兼容  选择V7包下的NotificationCompat进行构造
        val builder = NotificationCompat.Builder(ctx, "signNoti")
        //Ticker是状态栏显示的提示
        builder.setTicker("打卡咯")
        //第一行内容  通常作为通知栏标题
        builder.setContentTitle("掌上重邮")
        //第二行内容 通常是通知正文
        builder.setContentText("还没有打卡噢")
        //可以点击通知栏的删除按钮删除
        builder.setAutoCancel(true)
        //系统状态栏显示的小图标
        builder.setSmallIcon(R.mipmap.ic_launcher)
        //下拉显示的大图标
        val intent = Intent(ctx, this::class.java)
        val pIntent = PendingIntent.getActivity(ctx, 1, intent, 0)
        builder.setContentIntent(pIntent)
        builder.setDefaults(NotificationCompat.DEFAULT_ALL)
        val notification = builder.build()
        manager.notify(1, notification)

        return Result.success()
    }

}

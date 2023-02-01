package com.bignerdranch.android.jetpack3deeplinktest

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        view.findViewById<Button>(R.id.btnToSettingsFragment).setOnClickListener {
            val bundle = Bundle()
            bundle.putString("params", "from DeepLinkMainFragment")
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_settingFragment, bundle)
        }
        view.findViewById<Button>(R.id.btnSendNotification).setOnClickListener {
            sendNotification(view)
        }
        return view
    }

    // 通过PendingIntent设置，当通知被点击后需要跳转到哪个destination，以及传递的参数
    private fun getPendingIntent(): PendingIntent? {
        if (activity != null) {
            val bundle = Bundle()
            bundle.putString("params", "ParamsFromPendingIntent_HelloMichael")
            return Navigation
                .findNavController(requireActivity(), R.id.btnSendNotification)
                .createDeepLink()
                .setGraph(R.navigation.nav_graph)
                .setDestination(R.id.settingFragment)
                .setArguments(bundle)
                .createPendingIntent()
        }
        return null
    }

    // 向通知栏发送一个通知
    private val CHANNEL_ID = "1"
    private val notificationId = 8
    private fun sendNotification(view: View) {
        if (activity == null) {
            return
        }
        val channel = NotificationChannel("1", "ChannelName", NotificationManager.IMPORTANCE_DEFAULT)
        channel.description = "description"
        requireActivity().getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
        val builder: NotificationCompat.Builder = NotificationCompat.Builder(requireActivity(), CHANNEL_ID)
            .setSmallIcon(android.R.drawable.sym_def_app_icon)
            .setContentTitle("来自 jetpack3deeplinktest App的一条通知")
            .setContentText("Hello World! 支付宝到账一百万元")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(getPendingIntent())
            .setAutoCancel(true)
        val notificationManager = NotificationManagerCompat.from(requireActivity())
        notificationManager.notify(notificationId, builder.build())
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
package com.example.test

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MainActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private val LOCATION_PERMISSION_REQUEST_CODE = 100

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        if (checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                    100
            )
        }
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
    }

    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<String?>,
            grantResults: IntArray
    ) {
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE){
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            return
        }
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){

        } else {
            return
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onMapReady(googleMap: GoogleMap?) {
        if (checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (googleMap != null) {
                mMap = googleMap
            }
            mMap.isMyLocationEnabled = true
            val tokyo_station = LatLng(35.6812405, 139.7649361)
            mMap.addMarker(MarkerOptions().position(tokyo_station).title("東京駅"))
            mMap.moveCamera(CameraUpdateFactory.newLatLng(tokyo_station))
            mMap.moveCamera(CameraUpdateFactory.zoomTo(17F))
        }else{
            return
        }
    }
}
/*
        val mainActivity = findViewById<LinearLayout>(R.id.MainActivity)    //トップレベルのLinearLayoutの呼び出し
        val button = findViewById<Button>(R.id.button)                      //配置したボタンの取得
        val hoge = arrayOf("Apple","Banana","Orange","Strawberry")          //表示させたい文字列
        val WC = LinearLayout.LayoutParams.WRAP_CONTENT
        val MP = LinearLayout.LayoutParams.MATCH_PARENT

        val LP = ViewGroup.LayoutParams(MP,WC)          //LinearLayout用のLayoutParams
        val textLP = LinearLayout.LayoutParams(MP,MP)   //textView用LayoutParams

        //LinearLayoutの縦バージョン
        val LinearV = LinearLayout(this).also{
            it.orientation = LinearLayout.VERTICAL
        }
        LinearV.layoutParams = LP

        var flg = true      //ボタン用のフラグ

        //ボタンを押したときの処理
        button.setOnClickListener {
            if (flg) {
                var i = 0
                //hoge配列の要素数分繰り返し
                for (text in hoge) {
                    //LinearLayoutの横バージョン
                    val LinearH = LinearLayout(this)
                    LinearH.layoutParams = LP

                    //hoge配列内の文字列を挿入
                    val textHoge: TextView = TextView(this).also {
                        it.text = text
                        it.textSize = 20F
                        it.setTypeface(Typeface.DEFAULT_BOLD)
                    }

                    //要素のindex+1分を挿入
                    val textCount: TextView = TextView(this).also {
                        it.text = (i + 1).toString()        //数値型はtoString()で文字列にしてから代入
                        it.textSize = 20F
                        it.layoutParams = textLP
                        it.gravity = Gravity.RIGHT
                    }

                    //1つずつLinearHに追加し、最後にLinearVに追加する
                    LinearH.addView(textHoge)
                    LinearH.addView(textCount)
                    LinearV.addView(LinearH)
                    i += 1
                }
                LinearV.setPadding(30, 10, 30, 10)
                mainActivity.addView(LinearV, 0)    //index指定で追加場所の変更が可能0が一番先頭
                flg = false
            }else{
                LinearV.removeAllViews()                   //LinearVの要素をすべて削除
                mainActivity.removeViewAt(0)        //mainActivityの0番目の要素を削除する
                flg = true
            }
        }
    }

 */
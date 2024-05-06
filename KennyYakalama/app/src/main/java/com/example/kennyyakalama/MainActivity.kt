package com.example.kennyyakalama

import android.content.DialogInterface
import android.media.Image
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kennyyakalama.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var skor = 0
    var randomIndeks = Random
    var gorselDizi = ArrayList<ImageView>()
    var runnable : Runnable = Runnable{}
    var handler : Handler = Handler(Looper.getMainLooper())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)



        gorselDizi.add(binding.imageView1)
        gorselDizi.add(binding.imageView2)
        gorselDizi.add(binding.imageView3)
        gorselDizi.add(binding.imageView4)
        gorselDizi.add(binding.imageView5)
        gorselDizi.add(binding.imageView6)
        gorselDizi.add(binding.imageView7)
        gorselDizi.add(binding.imageView8)
        gorselDizi.add(binding.imageView9)
        gorselDizi.add(binding.imageView10)
        gorselDizi.add(binding.imageView11)
        gorselDizi.add(binding.imageView12)
        gorselDizi.add(binding.imageView13)
        gorselDizi.add(binding.imageView14)
        gorselDizi.add(binding.imageView15)
        gorselDizi.add(binding.imageView16)
        gizle()

        object : CountDownTimer (10000,1000){
            override fun onTick(millisUntilFinished: Long) {
                binding.txtSure.text="Süre : ${millisUntilFinished/1000}"


            }

            override fun onFinish() {
           binding.txtSure.text = " Süre : 0"
                handler.removeCallbacks(runnable)
                for (son in gorselDizi){
                    son.visibility=View.INVISIBLE
                }
                var alert = AlertDialog.Builder(this@MainActivity)
                alert.setTitle("Sıkıysa Yakala")
                alert.setMessage("oyun tekrar başlatılsın mı ? ")
                alert.setPositiveButton("evet",object :DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        var intent = intent
                        finish()
                            startActivity(intent)
                    }


                })
                alert.setNegativeButton("hayır" , object :DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        Toast.makeText(this@MainActivity,"oyun bitti",Toast.LENGTH_LONG).show()
                    }

                })
                alert.show()

            }
        }
            .start()

    }
    fun gorsel(view : View){
        skor = skor + 1
        binding.txtSkor.text = "Skor : ${skor}"
    }

    fun gizle(){
       runnable = object : Runnable{
           override fun run() {
               for (image in gorselDizi){
                   image.visibility=View.INVISIBLE
               }
               var random = randomIndeks.nextInt(16)
               gorselDizi[random].visibility = View.VISIBLE

               handler.postDelayed(runnable,200)


           }


       }
        handler.post (runnable)
    }
}
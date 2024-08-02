package com.example.contador

import android.os.AsyncTask
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.contador.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener{
            binding.button.text = "Processing"
            binding.button.isEnabled = false
            binding.counter.text = "0"

            CounterTask().execute()
        }
    }

    inner class CounterTask : AsyncTask<Void, Int, Void>() {
        override fun doInBackground(vararg params: Void?): Void? {
            var progress = 0
            while (progress <= 10) {
                publishProgress(progress)
                Thread.sleep(1000)
                progress++
            }
            return null
        }

        override fun onProgressUpdate(vararg values: Int?) {
            super.onProgressUpdate(*values)
            binding.counter.text = values[0].toString()
        }

        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            binding.button.text = "Process"
            binding.counter.text = ""
            binding.button.isEnabled = true
        }
    }
}

package com.example.contador

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

            this.executeThread()
        }
    }

    private fun executeThread() {
        Thread(
            Runnable {this.increaseProgressBar()}
        ).start()
    }

    private fun increaseProgressBar() {
        var progress = 0
        while (progress <= 10) {
            runOnUiThread() {
                binding.counter.text = progress.toString()
            }
            Thread.sleep(1000)
            progress++
        }

        runOnUiThread() {
            binding.button.text = "Process"
            binding.counter.text = ""
            binding.button.isEnabled = true
        }
    }
}
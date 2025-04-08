package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.database.database

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText = findViewById<EditText>(R.id.editTextMessage)
        val buttonSend = findViewById<Button>(R.id.buttonSend)

        val database = Firebase.database
        val messagesRef = database.getReference("messages")

        buttonSend.setOnClickListener {
            val message = editText.text.toString().trim()

            if (message.isNotEmpty()) {
                // Cria uma chave única automaticamente
                val messageKey = messagesRef.push()
                messageKey.setValue(message)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Mensagem enviada!", Toast.LENGTH_SHORT).show()
                        editText.text.clear()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Erro ao enviar!", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(this, "Digite uma mensagem!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

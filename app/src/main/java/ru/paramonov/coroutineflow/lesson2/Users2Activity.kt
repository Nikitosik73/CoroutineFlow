package ru.paramonov.coroutineflow.lesson2

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import ru.paramonov.coroutineflow.R
import ru.paramonov.coroutineflow.databinding.ActivityUsers2Binding

class Users2Activity : AppCompatActivity() {

    private val binding by lazy {
        ActivityUsers2Binding.inflate(layoutInflater)
    }

    private val viewModel by viewModels<UsersViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.buttonAddUser.setOnClickListener {
            binding.editTextUsername.text.toString()
                .trim()
                .takeIf { it.isNotBlank() }
                ?.let { user ->
                    viewModel.addUser(user)
                }
        }
        viewModel.users.observe(this) {
            binding.textViewUsers.text = it.joinToString()
        }
    }

    companion object {

        fun newIntent(context: Context) = Intent(context, Users2Activity::class.java)
    }
}
package ru.paramonov.coroutineflow.lesson2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.paramonov.coroutineflow.databinding.ActivityUsersBinding

class UsersActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityUsersBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModels<UsersViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupOnClickListener()
        observeViewModel()
    }

    private fun setupOnClickListener() {
        binding.buttonAddUser.setOnClickListener {
            binding.editTextUsername.text.toString()
                .trim()
                .takeIf { it.isNotBlank() }
                ?.let { user ->
                    viewModel.addUser(user)
                }
        }
        binding.buttonNextScreen.setOnClickListener {
            startActivity(Users2Activity.newIntent(this))
        }
    }

    private fun observeViewModel() {
        viewModel.users.observe(this) { users ->
            binding.textViewUsers.text = users.joinToString()
        }
    }

    companion object {

        fun newIntent(context: Context) = Intent(context, UsersActivity::class.java)
    }
}
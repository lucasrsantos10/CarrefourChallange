package com.example.carrefourchallenge.users.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.carrefourchallenge.databinding.AdapterUsersListBinding
import com.example.carrefourchallenge.domain.users.Users

class UsersListAdapter(
    var usersList: List<Users>,
    private val context: Context
    ): RecyclerView.Adapter<UsersListAdapter.ViewHolder>() {

    var onUserClick: ((userName: String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            AdapterUsersListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = usersList[position]
        holder.binding.apply {
            Glide.with(context)
                .load(user.avatarUrl)
                .into(sivAvatar)

            tvUserName.text = user.name
        }
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    inner class ViewHolder(
        val binding: AdapterUsersListBinding
        ): RecyclerView.ViewHolder(binding.root) {
            init {
                binding.root.setOnClickListener {
                    onUserClick?.invoke(usersList[adapterPosition].name.toString())
                }
            }
    }
}
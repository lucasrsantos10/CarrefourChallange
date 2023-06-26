package com.example.carrefourchallenge.users.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.carrefourchallenge.databinding.AdapterReposListBinding
import com.example.carrefourchallenge.domain.users.Repository


class ReposListAdapter(
    private val repositories: List<Repository>
):  RecyclerView.Adapter<ReposListAdapter.ViewHolder>() {
    inner class ViewHolder(
        val binding: AdapterReposListBinding
    ): RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int {
        return repositories.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AdapterReposListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repository = repositories[position]
        holder.binding.tvRepoName.text = repository.name
    }
}


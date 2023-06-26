package com.example.carrefourchallenge.users.findUsers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView.OnQueryTextListener
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.carrefourchallenge.databinding.FragmentFindUsersBinding
import com.example.carrefourchallenge.domain.users.Users
import com.example.carrefourchallenge.users.adapters.UsersListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FindUsersFragment: Fragment() {

    lateinit var binding: FragmentFindUsersBinding
    private val viewModel: FindUsersViewModel by viewModels()

    private var mUsersListAdapter: UsersListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFindUsersBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
    }

    private fun initClicks() {
        binding.svSearchUser.setOnQueryTextListener(object: OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(text: String?): Boolean {
                viewModel.onSearchingUser(text)
                return true
            }
        })

        mUsersListAdapter?.onUserClick = {userName ->
            findNavController().navigate(
                FindUsersFragmentDirections.actionFindUsersFragmentToUserInformationsFragment(userName)
            )
        }
    }

    private fun initObservers() {
        viewModel.usersList.observeForever { usersList ->
            setupUserListadapter(usersList)
        }

        viewModel.filteredUsersList.observeForever {
            mUsersListAdapter?.usersList = it
            mUsersListAdapter?.notifyDataSetChanged()
        }

        viewModel.loading.observeForever {
            binding.lvLoader.isVisible = it
        }

        viewModel.showErrorSnackbar.observeForever {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        }
    }

    private fun setupUserListadapter(usersList: List<Users>) {
        mUsersListAdapter = UsersListAdapter(usersList, requireContext())
        binding.rvUsers.adapter = mUsersListAdapter
        initClicks()
    }
}
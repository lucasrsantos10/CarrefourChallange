package com.example.carrefourchallenge.users.userInformation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.carrefourchallenge.R
import com.example.carrefourchallenge.databinding.FragmentUserInformationsBinding
import com.example.carrefourchallenge.domain.users.UserInformation
import com.example.carrefourchallenge.users.adapters.ReposListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserInformationsFragment : Fragment() {

    private lateinit var binding: FragmentUserInformationsBinding
    private val viewModel: UserInformationsViewModel by viewModels()
    private val args: UserInformationsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserInformationsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onInit(args.userName)
        initObservers()
    }

    private fun initObservers() {
        viewModel.userInfo.observeForever {
            initViews(it)
        }
        viewModel.repoNames.observeForever {
            binding.rvRepo.adapter = ReposListAdapter(it)
        }
        viewModel.loadingScreen.observeForever {
            binding.lavLoader.isVisible = it
        }
        viewModel.loadingRepository.observeForever {
            binding.lavRepositoryLoader.isVisible = it
        }
        viewModel.showErrorToast.observeForever {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        }
    }

    private fun initViews(userInformation: UserInformation) {
        binding.tvUserName.text = userInformation.login
        binding.tvName.text = userInformation.name
        if (userInformation.twitterUsername.isNullOrEmpty()){
            binding.tvTwitterAccount.visibility = View.GONE
        } else {
            binding.tvTwitterAccount.visibility = View.VISIBLE
            binding.tvTwitterAccount.text = userInformation.twitterUsername
        }
        binding.tvEmail.text = userInformation.email
        binding.tvLocalization.text = userInformation.location
        binding.tvCompany.text = userInformation.company
        binding.tvBlog.text = userInformation.blog
        binding.tvBio.text = userInformation.bio
        binding.tvFollowers.text = getString(R.string.followers,userInformation.followers.toString())
        binding.tvFollowing.text = getString(R.string.following, userInformation.following.toString())

        Glide
            .with(this)
            .load(userInformation.avatarUrl)
            .into(binding.sivAvatar)

    }
}
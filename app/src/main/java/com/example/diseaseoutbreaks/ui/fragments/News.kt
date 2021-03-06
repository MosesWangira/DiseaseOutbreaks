package com.example.diseaseoutbreaks.ui.fragments

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.diseaseoutbreaks.R
import com.example.diseaseoutbreaks.data.Model.news.NewsDataClass
import com.example.diseaseoutbreaks.databinding.FragmentNewsBinding
import com.example.diseaseoutbreaks.util.animate
import com.example.diseaseoutbreaks.util.hideLoadingProgress
import com.example.diseaseoutbreaks.util.rotateAndFadeIn
import com.example.diseaseoutbreaks.util.toast
import com.example.diseaseoutbreaks.viewmodels.NewsViewModel

class News : Fragment(R.layout.fragment_news) {

    private lateinit var binding: FragmentNewsBinding
    private lateinit var viewModel: NewsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind(view)!!
        viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        binding.apply {
            fragmentNewsViewModel = viewModel
            lifecycleOwner = this@News
            emptyView.visibility = GONE
            loading.apply {
                visibility = VISIBLE
                startAnimation(rotateAndFadeIn(requireContext(), R.anim.rotate_animation))
            }
        }

        makeApiCallCoroutine()
    }

    private fun makeApiCallCoroutine(): NewsViewModel  {
        viewModel.getAllNewsData().observe(viewLifecycleOwner, Observer<NewsDataClass> {
            if (it != null) {
                binding.emptyView.visibility = GONE
                hideLoadingProgress(binding.loading)
                /**
                 * update the adapter
                 * */
                binding.newsRecyclerView.apply {
                    hasFixedSize()
                    layoutAnimation = animate(requireContext(), R.anim.layout_animation_slide_right)
                }

                viewModel.setAdapterData(it.items)

            } else {
                binding.emptyView.visibility = VISIBLE
                hideLoadingProgress(binding.loading)
                requireContext().toast("Click refresh icon to load data")
            }
        })

        return viewModel
    }

}
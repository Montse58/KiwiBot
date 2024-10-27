package com.example.kiwibotapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.kiwibotapp.databinding.ActivityResultsBinding  // Import the correct binding class

class Results : Fragment() {

    private var _binding: ActivityResultsBinding? = null  // Use the correct binding class
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout using the binding
        _binding = ActivityResultsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up navigation for the "Retry" button
        binding.retryButton.setOnClickListener {
            // Trigger the navigation action defined in nav_graph.xml
            findNavController().navigate(R.id.action_Results_to_MainPage)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null  // Clear the binding when the view is destroyed
    }
}

package com.example.kiwibotapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.kiwibotapp.databinding.ActivityWelcomePageBinding  // Import the correct binding class

class WelcomePage : Fragment() {

    private var _binding: ActivityWelcomePageBinding? = null  // Use the correct binding class here
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout using the binding
        _binding = ActivityWelcomePageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up navigation for the "Get Started" button
        binding.getStartedButton.setOnClickListener {
            // Trigger the navigation action defined in nav_graph.xml
            findNavController().navigate(R.id.action_WelcomePage_to_MainPage)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null  // Clear the binding when the view is destroyed
    }
}

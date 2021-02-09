package com.udacity.shoestore.screens.login

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.LoginFragmentBinding

class LoginFragment: Fragment(), TextToSpeech.OnInitListener {

    private lateinit var tts: TextToSpeech

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val binding: LoginFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.login_fragment, container, false)

        binding.loginNewAccountButton.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
        }

        binding.loginExistingAccountButton.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
        }
        tts = TextToSpeech(requireContext(), this)

        binding
        binding.ttsButtonAlexa.setOnClickListener {
            tts.speak(
                "Testing out text to speech service from Alexa.",
                TextToSpeech.QUEUE_FLUSH,
                null,
                "LOGIN_UTTERANCE_STATUS"
            )
        }

        binding.ttsButtonBenson.setOnClickListener {
            tts.speak(
                "Testing out text to speech service from Benson.",
                TextToSpeech.QUEUE_FLUSH,
                null,
                "LOGIN_UTTERANCE_STATUS"
            )
        }

        return binding.root
    }

    override fun onInit(status: Int) {

    }
}
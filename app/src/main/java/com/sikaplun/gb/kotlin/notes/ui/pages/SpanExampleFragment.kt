package com.sikaplun.gb.kotlin.notes.ui.pages

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.method.LinkMovementMethod
import android.text.style.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.sikaplun.gb.kotlin.notes.databinding.FragmentSpanExampleBinding


class SpanExampleFragment : Fragment() {

    private var _binding: FragmentSpanExampleBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSpanExampleBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        colorSelectionSpan()
        fontSizeAndChoiceSpan()
        clickableTextSpan()
    }


    private fun colorSelectionSpan() {
        val spannableString = SpannableString("Hi there how are you")

        val foreGroundColor = ForegroundColorSpan(Color.RED)
        val backGroundColor = BackgroundColorSpan(Color.GREEN)

        spannableString.setSpan(foreGroundColor, 0, 2, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(backGroundColor, 3, 20, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)

        binding.oneTextView.text = spannableString
    }

    private fun fontSizeAndChoiceSpan() {
        val spannableString = SpannableStringBuilder("How are you")

        val sizeSpan = RelativeSizeSpan(2f)
        spannableString.setSpan(sizeSpan, 0, 3, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)

        val styleSpan = StyleSpan(Typeface.BOLD)
        spannableString.setSpan(styleSpan, 0, 3, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)

        val underLineSpan = UnderlineSpan()
        spannableString.setSpan(underLineSpan, 8, 11, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)

        spannableString.append("?")

        binding.twoTextView.text = spannableString
    }

    private fun clickableTextSpan() {

        val str = binding.threeTextView
        val spannableString = SpannableStringBuilder("Click to know")

        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                Snackbar.make(requireView(), "Home work", Snackbar.LENGTH_LONG).show()
            }

        }

        spannableString.setSpan(clickableSpan, 0, 13, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)

        str.text = spannableString
        str.movementMethod = LinkMovementMethod.getInstance()


    }

}



package com.ibnsina.notes.features.notes

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ibnsina.entites.Resource
import com.ibnsina.entites.note.Note
import com.ibnsina.notes.R
import com.ibnsina.notes.databinding.FragmentAddNoteBinding
import com.ibnsina.utils.base.BaseFragment
import com.ibnsina.utils.hide
import com.ibnsina.utils.isEmpty
import com.ibnsina.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddNoteFragment : BaseFragment() {
    private lateinit var binding: FragmentAddNoteBinding
    private var note: Note? = null
    private val viewModel: NotesViewModel by viewModels()
    private val safeArgs by navArgs<AddNoteFragmentArgs>()
    override fun doBinding(container: ViewGroup?, inflater: LayoutInflater): View {
        return FragmentAddNoteBinding.inflate(inflater, container, false).run {
            binding = this
            root
        }
    }

    override fun initViews() {
        note = safeArgs.note
        binding.note = note
        binding.save.setOnClickListener {
            if(isEmpty(binding.noteTitle)){
                messagesHandler.showToast(R.string.we_need_title)
                return@setOnClickListener
            }
            if (note == null) {
                viewModel.saveNote(0,
                    binding.noteTitle.text.toString(),
                    binding.noteContent.text.toString())
            } else {
                viewModel.saveNote(note!!.id,
                    binding.noteTitle.text.toString(),
                    binding.noteContent.text.toString(),
                    note!!.created_at)
            }
        }
    }

    override fun saveInstanceState(savedInstanceState: Bundle) {
    }

    override fun initData(data: Bundle) {
    }

    override fun initVariables(context: Context) {
    }

    override fun initViewModel() {
        viewModel.saveStatusLiveData.observe(this) {
            when (it.status) {
                Resource.Status.LOADING -> messagesHandler.showProgressBar()
                Resource.Status.ERROR -> it.message?.let { message ->
                    messagesHandler.showMessageDialog(message)
                }
                Resource.Status.SUCCESS -> findNavController().navigate(R.id.action_back)
            }

        }
    }
}
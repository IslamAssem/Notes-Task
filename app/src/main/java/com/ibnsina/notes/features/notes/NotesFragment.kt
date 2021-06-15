package com.ibnsina.notes.features.notes

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ibnsina.entites.Resource
import com.ibnsina.entites.note.Note
import com.ibnsina.notes.R
import com.ibnsina.notes.databinding.FragmentNotesBinding
import com.ibnsina.utils.base.BaseFragment
import com.ibnsina.utils.hide
import com.ibnsina.utils.recyclerview.BaseAdapter
import com.ibnsina.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotesFragment : BaseFragment() {
    lateinit var binding: FragmentNotesBinding

    private val viewModel: NotesViewModel by viewModels()
    override fun doBinding(container: ViewGroup?, inflater: LayoutInflater): View {
        return FragmentNotesBinding.inflate(inflater, container, false).run {
            binding = this
            root
        }
    }

    val adapter = BaseAdapter.initialize(ArrayList<Note>(), R.layout.list_item_note) { view, note ->
        view.findViewById<TextView>(R.id.note_title).text = note.title
        view.findViewById<TextView>(R.id.note_content).text = note.body
        view.findViewById<TextView>(R.id.note_updated).text = note.updated_at
        view.setOnClickListener {
            val action = NotesFragmentDirections.actionAddNote(note)
            findNavController().navigate(action)
        }

    }

    override fun initViews() {
        binding.recyclerView.adapter = adapter
        binding.addNewNoteFab.setOnClickListener{
            val action = NotesFragmentDirections.actionAddNote(null)
            findNavController().navigate(action)
        }
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.getNotes()
        }
        viewModel.getNotes()
    }

    override fun saveInstanceState(savedInstanceState: Bundle) {
    }

    override fun initData(data: Bundle) {
    }

    override fun initVariables(context: Context) {
    }

    override fun initViewModel() {
        viewModel.notesLiveData.observe(this) {
            it.data?.let { data ->
                adapter.setItems(data)
            }
            when (it.status) {
                Resource.Status.LOADING -> messagesHandler.showProgressBar()
                Resource.Status.ERROR -> it.message?.let { message ->
                    messagesHandler.showMessageDialog(message)
                }
                else -> messagesHandler.hideDialog()
            }
            if(adapter.itemCount == 0)
                binding.empty.show()
            else binding.empty.hide()

            binding.swipeRefresh.isRefreshing = false
        }
    }
}
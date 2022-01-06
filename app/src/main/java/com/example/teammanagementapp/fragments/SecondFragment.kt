package com.example.teammanagementapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.teammanagementapp.R
import com.example.teammanagementapp.Utils.TeamDataViewModel
import com.example.teammanagementapp.adapters.SelectedMember
import com.example.teammanagementapp.adapters.rv_MembersListAdapter
import com.example.teammanagementapp.databinding.FragmentSecondBinding
import com.google.android.material.snackbar.Snackbar

class SecondFragment : Fragment(), SelectedMember {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!
    val viewmodel: TeamDataViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvSetUp()

        viewmodel.TeamData.observe(viewLifecycleOwner) { it ->
        }
    }

    private fun rvSetUp() {


        binding.rvMemberslist.layoutManager = GridLayoutManager(context, 2)

        val list = ArrayList<String>()
        list.add("Member 1")
        list.add("Member 2")
        list.add("Member 3")

        val adapter = rv_MembersListAdapter(list, this)
        binding.rvMemberslist.adapter = adapter

        binding.fab.setOnClickListener { _ ->

            val view =
                LayoutInflater.from(requireContext()).inflate(R.layout.add_member_layout, null)
            val et_Name = view.findViewById<EditText>(R.id.et_memberName)


            AlertDialog.Builder(requireContext())
                .setView(view)
                .setTitle("Add Member")
                .setPositiveButton("Add") { _, _ ->
                    val name = et_Name.text.toString()

                    list.add(name)
                    adapter.notifyItemInserted(list.size - 1)

                }
                .setNegativeButton("Cancel") { _, _ -> }
                .show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun ClickedMember(member: String) {


        AlertDialog.Builder(requireContext())
            .setTitle("Statistics")
            .setMessage("Showing the Statistics of \n\n $member")
            .setPositiveButton("Show detailed Statistics") { _, _ ->
                Snackbar.make(view?.rootView!!, "action pending", Snackbar.LENGTH_SHORT).show()
            }
            .setNegativeButton("Close") { _, _ -> }
            .show()

    }

}
package com.example.teammanagementapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.teammanagementapp.Model.TeamData
import com.example.teammanagementapp.R
import com.example.teammanagementapp.Utils.TeamDataViewModel
import com.example.teammanagementapp.adapters.SelectedTeam
import com.example.teammanagementapp.adapters.rv_TeamsListAdapter
import com.example.teammanagementapp.databinding.FragmentFirstBinding
import com.example.teammanagementapp.db.Teamdatabase
import com.example.teammanagementapp.db.entities.Members
import com.example.teammanagementapp.db.entities.Teams
import kotlinx.coroutines.launch

class FirstFragment : Fragment(), SelectedTeam {
    private var _binding: FragmentFirstBinding? = null
    val viewmodel: TeamDataViewModel by activityViewModels()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvSetUp()
    }

    private fun addTeamToDB(data: TeamData) {
        val Tname = data.name
        var Tid = 0


        lifecycleScope.launch {
            val i = Tid++
            val dao = Teamdatabase.getInstance(requireContext()).dao
            val __team = Teams(i, Tname) 
            val __members = Members(33, "M33", 33)
            dao.insertTeam(__team)
            dao.insertMember(__members)
        }
    }

    private fun rvSetUp() {


        binding.rvTeamslist.layoutManager = GridLayoutManager(context, 2)

        val list = ArrayList<TeamData>()
        list.add(TeamData("team 1", 2))
        list.add(TeamData("team 2", 3))
        list.add(TeamData("team 3", 4))

        val adapter = rv_TeamsListAdapter(list, this)
        binding.rvTeamslist.adapter = adapter

        binding.fab.setOnClickListener { _ ->

            val view = LayoutInflater.from(requireContext()).inflate(R.layout.add_team_layout, null)
            val et_Name = view.findViewById<EditText>(R.id.et_team_name)
            val et_Size = view.findViewById<EditText>(R.id.et_team_size)


            AlertDialog.Builder(requireContext())
                .setView(view)
                .setTitle("Add Team")
                .setPositiveButton("Add") { _, _ ->
                    val name = et_Name.text.toString()
                    val size = et_Size.text.toString().toInt()

                    val data = TeamData(name, size)


                    addTeamToDB(data)
                    list.add(data)
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

    override fun ClickedTeam(teamList: ArrayList<TeamData>) {
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        viewmodel.setData(teamList)
    }
}

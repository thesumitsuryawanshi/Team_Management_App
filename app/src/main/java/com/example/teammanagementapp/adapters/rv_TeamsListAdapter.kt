package com.example.teammanagementapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.teammanagementapp.Model.TeamData
import com.example.teammanagementapp.databinding.RvTeamsListItemBinding

class rv_TeamsListAdapter(
    var TeamList: ArrayList<TeamData>,
    val listener: SelectedTeam

) : RecyclerView.Adapter<rv_TeamsListAdapter.ViewHolder>() {

    class ViewHolder(binding: RvTeamsListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val teamName = binding.tvTeamName
        val teamSize = binding.tvTeamSize
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view =
            RvTeamsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val viewholder = ViewHolder(view)

        view.root.setOnClickListener {
            listener.ClickedTeam(TeamList)
        }

        return viewholder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.teamName.text = TeamList[position].name
        holder.teamSize.text = "Members : " + TeamList[position].size.toString()
    }

    override fun getItemCount(): Int {
        return TeamList.size
    }
}

interface SelectedTeam {
    fun ClickedTeam(teamList: ArrayList<TeamData>)
}

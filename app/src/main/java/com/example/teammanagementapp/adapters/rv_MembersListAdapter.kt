package com.example.teammanagementapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.teammanagementapp.databinding.RvMembersListItemBinding

class rv_MembersListAdapter(
    var MemberList: ArrayList<String>,
    val listener: SelectedMember

) : RecyclerView.Adapter<rv_MembersListAdapter.ViewHolder>() {

    class ViewHolder(binding: RvMembersListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val memberName = binding.tvMemberName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view =
            RvMembersListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val viewholder = ViewHolder(view)

        view.root.setOnClickListener {
            listener.ClickedMember(MemberList[viewholder.adapterPosition])
        }

        return viewholder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.memberName.text = MemberList[position]
    }

    override fun getItemCount(): Int {
        return MemberList.size
    }

}

interface SelectedMember {
    fun ClickedMember(member: String)
}

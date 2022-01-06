package com.example.teammanagementapp.Utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.teammanagementapp.Model.TeamData

class TeamDataViewModel : ViewModel() {

    private val mutableLiveData = MutableLiveData<ArrayList<TeamData>>()
    val TeamData: LiveData<ArrayList<TeamData>> get() = mutableLiveData

    fun setData(__data: ArrayList<TeamData>) {
        mutableLiveData.value = __data
    }

}
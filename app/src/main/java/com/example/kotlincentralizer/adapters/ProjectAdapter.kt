package com.example.kotlincentralizer.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlincentralizer.databinding.ProjectBinding
import com.example.kotlincentralizer.models.Project

public class ProjectAdapter (
    private val context: Context,
    private val projectList: MutableList<Project>
): RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder>() {

    inner class ProjectViewHolder(private val binding: ProjectBinding): RecyclerView.ViewHolder(binding.root)  {
        fun bind(project: Project) {
            binding.projectName.text = project.name

            binding.projectName.setOnClickListener {
                context.startActivity(project.intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        val binding = ProjectBinding.inflate(
            LayoutInflater.from(context), parent, false
        )
        return ProjectViewHolder(binding)
    }

    override fun getItemCount(): Int = projectList.size
    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        holder.bind(projectList[position])
    }

}
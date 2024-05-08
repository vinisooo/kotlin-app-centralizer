package com.example.kotlincentralizer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlincentralizer.adapters.ProjectAdapter
import com.example.kotlincentralizer.databinding.ActivityProjectsBinding
import com.example.kotlincentralizer.models.Project

class Projects : AppCompatActivity() {
    private lateinit var binding: ActivityProjectsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProjectsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val logOutBtn = binding.logOut.setOnClickListener {
            val logOutIntent = Intent(this, MainActivity::class.java)

            startActivity(logOutIntent)
        }
    }

    fun renderProjects() {
        val projects = getProjects()

        val recyclerView = binding.projectRecycler
        val projectsAdapter = ProjectAdapter(this, projects)
        recyclerView.adapter = projectsAdapter
    }

    private fun getProjects(): MutableList<Project> {
        val projectList = mutableListOf<Project>()

        val intent = Intent(this, SplashScreen::class.java)
        val pokePage = Project("Poke HTTP", intent)\

        projectList.add(pokePage)

        return projectList
    }
}
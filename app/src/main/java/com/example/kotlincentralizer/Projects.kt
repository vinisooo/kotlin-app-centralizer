package com.example.kotlincentralizer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlincentralizer.activities.Finances
import com.example.kotlincentralizer.adapters.ProjectAdapter
import com.example.kotlincentralizer.databinding.ActivityProjectsBinding
import com.example.kotlincentralizer.models.Project

class Projects : AppCompatActivity() {
    private lateinit var binding: ActivityProjectsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProjectsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        renderProjects()

        binding.logOut.setOnClickListener {
            val logOutIntent = Intent(this, MainActivity::class.java)

            startActivity(logOutIntent)
        }
    }

    private fun renderProjects() {
        val projects = getProjects()

        val recyclerView = binding.projectRecycler
        recyclerView.layoutManager = LinearLayoutManager(this)
        val projectsAdapter = ProjectAdapter(this, projects)
        recyclerView.adapter = projectsAdapter
    }

    private fun getProjects(): MutableList<Project> {
        val projectList = mutableListOf<Project>()

        val pokeIntent = Intent(this, SplashScreen::class.java)
        val pokePage = Project("Poke HTTP", pokeIntent)

        val financesIntent = Intent(this, Finances::class.java)
        val financesPage = Project("Finances", financesIntent)

        projectList.add(pokePage)
        projectList.add(financesPage)
        return projectList
    }
}
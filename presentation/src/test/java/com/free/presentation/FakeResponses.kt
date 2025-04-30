package com.free.presentation


object FakeResponses {
    private fun loadJSONFromResource(fileName: String): String {
        return this::class.java.classLoader?.getResource(fileName)?.readText()
            ?: throw IllegalArgumentException("File not found: $fileName")
    }

    val githubUsers = loadJSONFromResource("github_users.json")
}

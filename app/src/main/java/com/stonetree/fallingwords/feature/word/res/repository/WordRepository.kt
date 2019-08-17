package com.stonetree.fallingwords.feature.word.res.repository

class WordRepository {

    companion object {
        @Volatile
        private var instance: WordRepository? = null

        fun getInstance() = instance ?: synchronized(this) {
            WordRepository().also { repository ->
                instance = repository
            }
        }
    }
}
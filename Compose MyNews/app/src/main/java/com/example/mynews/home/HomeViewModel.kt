package com.example.mynews.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mynews.common.Constants
import com.kwabenaberko.newsapilib.NewsApiClient
import com.kwabenaberko.newsapilib.models.Article
import com.kwabenaberko.newsapilib.models.request.EverythingRequest
import com.kwabenaberko.newsapilib.models.request.TopHeadlinesRequest
import com.kwabenaberko.newsapilib.models.response.ArticleResponse

class HomeViewModel: ViewModel() {

    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>> = _articles

    init {
        getTopHeadlines()
    }

    fun getTopHeadlines(category: String = "GENERAL") {
        val newsApiClient = NewsApiClient(Constants.API_KEY)
        val request = TopHeadlinesRequest
            .Builder()
            .language("en")
            .category(category)
            .build()
        newsApiClient.getTopHeadlines(request, object : NewsApiClient.ArticlesResponseCallback {
            override fun onSuccess(response: ArticleResponse?) {
                response?.articles?.let {
                    _articles.postValue(it)
                }
            }

            override fun onFailure(throwable: Throwable?) {
                if (throwable != null) {
                    throwable.localizedMessage?.let { Log.e("Mynews response:", it) }
                }
            }
        })
    }

    fun getEverythingByQuery(query: String) {
        if (query.isEmpty()) {
            return
        }
        val newsApiClient = NewsApiClient(Constants.API_KEY)
        val request = EverythingRequest
            .Builder()
            .language("en")
            .q(query)
            .build()
        newsApiClient.getEverything(request, object : NewsApiClient.ArticlesResponseCallback {
            override fun onSuccess(response: ArticleResponse?) {
                response?.articles?.let {
                    _articles.postValue(it)
                }
            }

            override fun onFailure(throwable: Throwable?) {
                if (throwable != null) {
                    throwable.localizedMessage?.let { Log.e("Mynews response:", it) }
                }
            }
        })
    }
}
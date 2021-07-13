package kr.hs.dgsw.mmr.repository

import io.reactivex.Single
import kr.hs.dgsw.mmr.network.Server
import kr.hs.dgsw.mmr.network.api.Api
import kr.hs.dgsw.mmr.network.model.request.CreatePostRequest
import kr.hs.dgsw.mmr.network.model.request.MaterialRequest
import kr.hs.dgsw.mmr.network.model.response.BaseResponse
import kr.hs.dgsw.mmr.network.model.response.PostResponse
import org.json.JSONObject
import retrofit2.Response

class PostRepository {
    fun writePost(
        userId: String,
        title: String,
        summary: String,
        content: String,
        imgUrl: String,
        materials: List<MaterialRequest>
    ): Single<Boolean> {
        val post = CreatePostRequest(userId, title, summary, content, imgUrl, materials)
        return Server.retrofit.createPost(post).map {
            if(!it.isSuccessful) {
                val errorBody = JSONObject(it.errorBody().toString())
                throw Throwable(errorBody.getString("message"))
            }
            it.body()!!.data
        }
    }

    fun getAllPost(): Single<List<PostResponse>> {
        return Server.retrofit.getAllPost().map{
            if(!it.isSuccessful){
                val errorBody = JSONObject(it.errorBody().toString())
                throw Throwable(errorBody.getString("message"))
            }
            it.body()!!.data
        }
    }
}
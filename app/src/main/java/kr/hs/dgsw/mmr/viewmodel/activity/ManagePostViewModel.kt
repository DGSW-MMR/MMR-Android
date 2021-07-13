package kr.hs.dgsw.mmr.viewmodel.activity

import androidx.lifecycle.MutableLiveData
import io.reactivex.observers.DisposableSingleObserver
import kr.hs.dgsw.mmr.base.BaseViewModel
import kr.hs.dgsw.mmr.network.model.response.PostResponse
import kr.hs.dgsw.mmr.repository.PostRepository
import kr.hs.dgsw.mmr.view.adapter.MyPostListAdapter

class ManagePostViewModel: BaseViewModel() {
    private val repository = PostRepository()

    val myPost = MutableLiveData<List<PostResponse>>()

    val adapter:MyPostListAdapter = MyPostListAdapter()

    fun getMyPost(userId: String){
        addDisposable(repository.getMyPost(userId),
        object: DisposableSingleObserver<List<PostResponse>>(){
            override fun onSuccess(t: List<PostResponse>) {
                myPost.value = t
            }

            override fun onError(e: Throwable) {
                onErrorEvent.value = e
            }

        })
    }
}
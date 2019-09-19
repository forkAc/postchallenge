package com.example.postchallenge

import com.example.postchallenge.data.PostRepository
import com.example.postchallenge.data.model.Post
import com.example.postchallenge.service.PostService
import com.example.postchallenge.ui.postlist.PostListContract.View
import com.example.postchallenge.ui.postlist.PostListPresenter
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import org.junit.Test

class PostListPresenterTest {

  private val publisherTapToRetry = PublishSubject.create<Any>()
  private val publisherPostItemSelected = PublishSubject.create<Post>()

  private val view: View = mockk(relaxUnitFun = true) {
    every { tapToRetry() } returns publisherTapToRetry
    every { postItemSelected() } returns publisherPostItemSelected
  }

  private val postService: PostService = mockk(relaxed = true)

  private val repository: PostRepository = mockk(relaxed = true)

  private val presenter: PostListPresenter = spyk(
    PostListPresenter(
      view = view,
      navigator = mockk(),
      postService = postService,
      repository = repository
    ).apply {
      viewScheduler = Schedulers.trampoline()
    }
  )

  @Test
  fun `presenter resume`() {
    // it should call all handler
    presenter.resume()
    verify(exactly = 1) { presenter.handlePostSelected() }
    verify(exactly = 1) { presenter.handleTapToRetry() }
    verify(exactly = 1) { presenter.handleGetPostList() }
  }

  @Test
  fun `internet OFF and go get from room`() {
    // internet OFF
    every { view.isInternetOn() } returns false

    presenter.handleGetPostList()

    loading()

    // it should get info from room
    verify(exactly = 1) { repository.getAllPosts() }
  }

  @Test
  fun `internet ON and go get through http request`() {
    // internet ON
    every { view.isInternetOn() } returns true

    presenter.handleGetPostList()

    loading()

    // it should get info from room
    verify(exactly = 1) { postService.getPostList() }
  }

  private fun loading() {
    // it should hide view
    verify(exactly = 1) { view.hideParent() }
    // it should show progress bar
    verify(exactly = 1) { view.showProgressBar() }
  }

}
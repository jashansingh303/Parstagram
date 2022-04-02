package edu.qc.seclass.parstagram.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseQuery
import edu.qc.seclass.parstagram.MainActivity
import edu.qc.seclass.parstagram.Post
import edu.qc.seclass.parstagram.PostAdapter
import edu.qc.seclass.parstagram.R


open class FeedFragment : Fragment() {

    lateinit var postsRecyclerView: RecyclerView

    lateinit var adapter: PostAdapter

    var allPosts: MutableList<Post> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //this is where we set up our views and click listners

        postsRecyclerView = view.findViewById(R.id.postRecyclerView)
        //steps
        //1. create layout for each row in list (item_post.xml)
        //2. create data source for each row (post class)
        //3. create adapter that will bridge data and row layout
        //4. set adapter on RecyclerView
        adapter = PostAdapter(requireContext(), allPosts)
        postsRecyclerView.adapter = adapter
        //5. set layout manager on RecyclerView
        postsRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        queryPosts()
    }

    open fun queryPosts() {
        val query: ParseQuery<Post> = ParseQuery.getQuery(Post::class.java)
        query.include(Post.KEY_USER)
        query.addDescendingOrder("createdAt")
        query.findInBackground(object : FindCallback<Post> {
            override fun done(posts: MutableList<Post>?, e: ParseException?){
                if (e != null) {
                    Log.e(TAG, "Error fetching posts")
                }else {
                    if (posts != null){
                        for (post in posts) {
                            Log.i(TAG, "Post: " + post.getDescription() + " , username: " + post.getUser()?.username)
                        }
                        allPosts.addAll(posts)
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        })
    }
    companion object {
        const val TAG = "FeedFragment"
    }

}
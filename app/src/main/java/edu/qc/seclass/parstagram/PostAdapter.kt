package edu.qc.seclass.parstagram

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.w3c.dom.Text

class PostAdapter(val context: Context, val posts: List<Post>)  : RecyclerView.Adapter<PostAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostAdapter.ViewHolder {
        //specify the layout file to use for this item
        val view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostAdapter.ViewHolder, position: Int) {
        val post = posts.get(position)
        holder.bind(post)
    }

    override fun getItemCount(): Int {
        return posts.size
    }



    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvUserName: TextView
        val ivImage: ImageView
        val tvDescription: TextView
        val likeBtn: Button
        val tvLikes: TextView



        init{
            tvUserName = itemView.findViewById(R.id.tvUserName)
            ivImage = itemView.findViewById(R.id.ivImage)
            tvDescription = itemView.findViewById(R.id.tvDescription)
            likeBtn = itemView.findViewById(R.id.likeBtn)
            tvLikes = itemView.findViewById(R.id.tvLikes)
        }

        fun bind(post: Post) {
            tvDescription.text = post.getDescription()
            tvUserName.text = post.getUser()?.username
            tvLikes.text = post.getLike().toString()
            //populate image
            Glide.with(itemView.context).load(post.getImage()?.url).into(ivImage)
        }


    }

}
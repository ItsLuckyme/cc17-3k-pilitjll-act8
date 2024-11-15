package com.example.bookshelfapplication

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class BookAdaptersActivity(
    private var bookList: ArrayList<BookRVModal>,
    private var ctx: Context
) : RecyclerView.Adapter<BookAdaptersActivity.BookViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BookAdaptersActivity.BookViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.activity_book_adapters,
            parent, false
        )
        return BookAdaptersActivity.BookViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BookAdaptersActivity.BookViewHolder, position: Int) {
        val bookInfo = bookList[position]

        var modifiedThumbnailUrl = bookInfo.thumbnail
        if (modifiedThumbnailUrl.startsWith("http://")) {
            modifiedThumbnailUrl = modifiedThumbnailUrl.replace("http://", "https://")
            bookInfo.thumbnail = modifiedThumbnailUrl
        }

        Log.d("BookThumbnail", "Thumbnail URL: ${modifiedThumbnailUrl}")
        Picasso.get()
            .load(bookInfo.thumbnail)
            .error(R.drawable.ic_launcher_background)
            .into(holder.bookIV);
        holder.bookTitleTV.text = bookInfo.title
        holder.bookPagesTV.text = "Pages : " + bookInfo.pageCount

        holder.itemView.setOnClickListener {
            val i = Intent(ctx, BookAdaptersActivity::class.java)
            i.putExtra("title", bookInfo.title)
            i.putExtra("subtitle", bookInfo.subtitle)
            i.putExtra("authors", bookInfo.authors)
            i.putExtra("publisher", bookInfo.publisher)
            i.putExtra("publishedDate", bookInfo.publishedDate)
            i.putExtra("description", bookInfo.description)
            i.putExtra("pageCount", bookInfo.pageCount)
            i.putExtra("thumbnail", bookInfo.thumbnail)
            i.putExtra("previewLink", bookInfo.previewLink)
            i.putExtra("infoLink", bookInfo.infoLink)
            i.putExtra("buyLink", bookInfo.buyLink)

            ctx.startActivity(i)
        }

    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bookTitleTV: TextView = itemView.findViewById(R.id.idTVBookName)
        val bookPagesTV: TextView = itemView.findViewById(R.id.idTVBookPages)
        val bookIV: ImageView = itemView.findViewById(R.id.idIVBook)
    }
}
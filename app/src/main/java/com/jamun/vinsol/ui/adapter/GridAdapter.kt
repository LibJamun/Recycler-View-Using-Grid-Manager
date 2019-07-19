package com.jamun.vinsol.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jamun.vinsol.jetpack.entites.ImageModel
import com.jamun.vinsol.utils.DownloadImage
import com.jamun.vinsol.variables.Constants
import com.jamun.vinsol.variables.OnImageClickListener
import kotlinx.android.synthetic.main.adapter_image.view.*
import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.content.Context
import android.os.Handler
import com.jamun.vinsol.R


class GridAdapter(
    private val context: Context,
    private val modelList: MutableList<ImageModel>,
    private val listener: OnImageClickListener
) :
    RecyclerView.Adapter<GridAdapter.ViewHolder>() {

    private var size: Int = 0
    private var placeHolderType: Int = 0
    private var handler = Handler()

    init {
        if (!modelList.isNullOrEmpty())
            size = modelList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapter_image, parent, false))


    override fun getItemCount(): Int = size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(modelList[position])
        if (placeHolderType < Constants.PLACE_HOLDERS.size - 1) {
            placeHolderType += 1
        } else {
            placeHolderType = 0
        }
    }

    fun notifyAdapterItemRemoved(position: Int) {
        modelList.removeAt(position)
        size -= 1
        notifyItemRemoved(position)
    }

    fun notifyAdapterItemInserted(model: ImageModel) {
        modelList.add(model.adapterPosition, model)
        size += 1
        notifyItemInserted(model.adapterPosition)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private var mSetRightOut: AnimatorSet? = null
        private var mSetLeftIn: AnimatorSet? = null

        init {
            itemView.id_image.setOnClickListener(this)
            mSetRightOut = AnimatorInflater.loadAnimator(context, R.animator.out_animator) as AnimatorSet
            mSetLeftIn = AnimatorInflater.loadAnimator(context, R.animator.in_animator) as AnimatorSet
        }

        internal fun bindView(model: ImageModel) {
            DownloadImage.downloadImages(
                itemView.context,
                model.image,
                itemView.id_image,
                Constants.PLACE_HOLDERS[placeHolderType]
            )
            DownloadImage.downloadImages(
                itemView.context,
                model.image,
                itemView.id_image_back,
                Constants.PLACE_HOLDERS[placeHolderType]
            )
        }


        override fun onClick(v: View?) {
            mSetRightOut?.setTarget(itemView.id_parent)
            mSetLeftIn?.setTarget(itemView.id_parent_back)
            mSetRightOut?.start()
            mSetLeftIn?.start()
            handler.postDelayed({
                listener.onClick(modelList[adapterPosition], adapterPosition)
            }, 1000)
        }
    }
}
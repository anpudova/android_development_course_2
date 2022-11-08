import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.task_5.ItemModel
import com.example.task_5.databinding.ItemRecyclerBinding

class BottomSheetAdapter: RecyclerView.Adapter<BottomSheetAdapter.ItemViewHolder>() {

    var onItemClickListener: ((ItemModel) -> Unit)? = null

    var items: List<ItemModel> = listOf()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            _binding = ItemRecyclerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindItem(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ItemViewHolder(
        private var _binding: ItemRecyclerBinding
    ) : RecyclerView.ViewHolder(_binding.root) {

        init {
            with (_binding) {
                root.setOnClickListener {
                    onItemClickListener?.invoke(items[adapterPosition])

                }
                llRec.setOnClickListener {
                    onItemClickListener?.invoke(items[adapterPosition])
                }
            }
        }

        fun bindItem(item: ItemModel) {
            with(_binding) {
                tvRec.text = item.textView
                ivRec.setImageResource(item.imageView)
            }
        }
    }
}
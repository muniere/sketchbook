package net.muniere.sketchbook

import com.airbnb.epoxy.EpoxyController

public final class MainController : EpoxyController() {

  public fun interface OnItemClickListener {
    public fun onItemClick(view: SketchTile)
  }

  public fun setOnItemClickListener(listener: OnItemClickListener?) {
    this.onItemClickListener = listener
  }

  private var onItemClickListener: OnItemClickListener? = null

  override fun buildModels() {
    val ctrl = this

    SketchKind.values().map { it.seed }.forEach { seed ->
      ctrl.sketchTile {
        id(seed.id)
        seed(seed)
        onClickListener { view ->
          ctrl.onItemClickListener?.onItemClick(view as SketchTile)
        }
      }
    }
  }
}

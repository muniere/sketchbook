package net.muniere.sketchbook

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import net.muniere.sketchbook.databinding.SketchTileBinding
import kotlin.properties.Delegates

@SuppressLint("NonConstantResourceId")
@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
public final class SketchTile : ConstraintLayout {

  private val binding = SketchTileBinding.inflate(LayoutInflater.from(this.context), this)

  constructor(context: Context) : super(context)
  constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
  constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

  @set:ModelProp
  public var seed: SketchSeed? by Delegates.observable(null) { _, _, newValue ->
    this.binding.titleLabel.text = newValue?.let { "#${it.id}: ${it.title}" }
    this.binding.captionLabel.text = newValue?.caption
  }

  @CallbackProp
  override fun setOnClickListener(listener: View.OnClickListener?) {
    super.setOnClickListener(listener)

    val resolved = TypedValue().also {
      this.context.theme.resolveAttribute(android.R.attr.selectableItemBackground, it, true)
    }

    this.setBackgroundResource(resolved.resourceId)
  }
}

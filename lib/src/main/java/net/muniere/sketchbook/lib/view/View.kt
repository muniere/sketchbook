package net.muniere.sketchbook.lib.view

import android.util.Size
import android.view.View

public val View.size: Size
  get() = Size(this.width, this.height)

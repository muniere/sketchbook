package net.muniere.sketchbook.lib.processing

import android.os.Handler
import android.os.Looper
import processing.android.PFragment
import processing.core.PApplet

public open class SFragment : PFragment {

  constructor() : super()
  constructor(sketch: PApplet) : super(sketch)

  public val mainHandler: Handler
    get() = Looper.getMainLooper().let(::Handler)

  public val myHandler: Handler?
    get() = Looper.myLooper()?.let(::Handler)

  public fun post(r: Runnable): Boolean {
    return this.mainHandler.post(r)
  }

  public fun post(r: () -> Unit): Boolean {
    return this.mainHandler.post(r)
  }

  public fun postDelayed(delayInMillis: Long, r: Runnable): Boolean {
    return this.mainHandler.postDelayed(r, delayInMillis)
  }

  public fun postDelayed(delayInMillis: Long, r: () -> Unit): Boolean {
    return this.mainHandler.postDelayed(r, delayInMillis)
  }

  public fun requireSketch(): SApplet {
    return this.sketch as SApplet
  }

  public inline fun <reified Sketch : PApplet> requireSketch(): Sketch {
    return this.sketch as Sketch
  }
}

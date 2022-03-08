package net.muniere.sketchbook

import android.app.Activity
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

public inline fun <reified T : ViewBinding> Activity.viewBinding() = ActivityViewBindingDelegate(T::class.java)

public final class ActivityViewBindingDelegate<T : ViewBinding>(
  private val bindingClass: Class<T>
) : ReadOnlyProperty<Activity, T> {

  private var binding: T? = null

  @Suppress("UNCHECKED_CAST")
  override fun getValue(thisRef: Activity, property: KProperty<*>): T {
    val binding = this.binding
    if (binding != null) {
      return binding
    }

    val method = this.bindingClass.getMethod("inflate", LayoutInflater::class.java)
    val layout = method.invoke(null, thisRef.layoutInflater) as T

    thisRef.setContentView(layout.root)

    this.binding = layout

    return layout
  }
}

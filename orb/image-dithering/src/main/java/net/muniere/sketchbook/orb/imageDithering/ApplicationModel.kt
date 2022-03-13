package net.muniere.sketchbook.orb.imageDithering

internal class ApplicationModel(
  public val machine: ImageMachineModel,
) {
  public val hasNext: Boolean
    get() = this.machine.hasNext

  public fun update() {
    this.machine.dither()
  }
}

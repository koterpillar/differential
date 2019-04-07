package com.koterpillar.differential

trait Update[Original] {
  type Patch

  def apply(original: Original, patch: Patch): Original
}

object Update {
  type Aux[Original, Patch0] = Update[Original] {type Patch = Patch0}

  def apply[Original, Patch](original: Original, patch: Patch)(implicit update: Update[Original]): Original = update(original, patch)
}

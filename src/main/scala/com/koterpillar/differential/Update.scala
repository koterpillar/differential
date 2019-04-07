package com.koterpillar.differential

trait Update[Original, -Patch] {
  def apply(original: Original, patch: Patch): Original
}

object Update {
  def apply[Original, Patch](original: Original, patch: Patch)(implicit update: Update[Original, Patch]): Original = update(original, patch)
}

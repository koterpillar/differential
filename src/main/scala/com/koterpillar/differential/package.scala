package com.koterpillar

package object differential {
  implicit def optionUpdate[A]: Update[A, Option[A]] = (original, patch) => patch.getOrElse(original)
}

package com.koterpillar

package object differential {
  implicit def optionUpdate[A]: Update.Aux[A, Option[A]] = new Update[A] {
    override type Patch = Option[A]

    override def apply(original: A, patch: Option[A]): A = patch.getOrElse(original)
  }
}

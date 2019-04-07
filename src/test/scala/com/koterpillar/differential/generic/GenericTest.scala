package com.koterpillar.differential.generic

import shapeless._
import org.scalatest._

class GenericTest extends FreeSpec with Matchers {
  "Generic update" - {
    "can be summoned" in {
      case class Foo(i: Int, s: String, b: Boolean)

      val genericFoo = Generic(Foo)

      val genericUpdater = ???
    }
  }
}

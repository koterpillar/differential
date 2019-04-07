package com.koterpillar.differential

import org.scalatest._

class BasicTest extends FreeSpec with Matchers {
  "Update" - {
    "updates with an optional value" in {
      Update(1: Int, Some(2)) should be(2)
      Update(1: Int, None) should be(1)
    }
  }
}

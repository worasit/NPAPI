package com.nongped.example

import org.scalatest.FunSuite

class BootTest extends FunSuite {
  test("Hello should start with H") {
    assert("Hello".startsWith("H"))
  }
}

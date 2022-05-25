package inductivederivation

object InductiveDerivation {
  class MyThing[A]
  object MyThing {
    implicit val mtInt: MyThing[Int] = new MyThing[Int]
    implicit val mtString: MyThing[String] = new MyThing[String]

    implicit def mtList[A](implicit ev: MyThing[A]): MyThing[List[A]] = new MyThing[List[A]]
  }
}

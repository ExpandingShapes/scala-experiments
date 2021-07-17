package higherkindedtypes

object Basics {
  def main(args: Array[String]): Unit = {
    //instead of
    def getSeq[A](a: Seq[A]): Seq[A] = a
    def getList[A](a: List[A]): List[A] = a

    //use
    def getCollection[T[_], B](a: T[B]): T[B] = a

    println(getCollection(List(11, 22, 33)))
    println(getCollection(Seq(13, 24, 35)))
  }
}

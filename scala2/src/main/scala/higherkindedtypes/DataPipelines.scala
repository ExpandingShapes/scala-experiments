package higherkindedtypes

object DataPipelines extends App {
  trait BatchRun[M[_]] {
    def write[A](item: A, db: M[A]): M[A] = transform(item, db)
    def transform[A](item: A, db: M[A]): M[A]
  }

  val newData = Map()
  
  val listDb: List[String] = List("data 1", "data 2")
  val listBatchRun = new BatchRun[List] {
    def transform[A](item: A, db: List[A]): List[A] = db :+ item
  }
  val savedList = listBatchRun.write("data 3", listDb)
  println(savedList == List("data 1", "data 2", "data 3"))

  val seqDb: Seq[Int] = Seq(1, 2)
  val seqBatchRun = new BatchRun[Seq] {
    def transform[A](item: A, db: Seq[A]): Seq[A] = item +: db
  }
  val savedSeq = seqBatchRun.write(3, seqDb)
  println(savedSeq == Seq(1, 2, 3))
}

package racecondition

import scala.concurrent.{ExecutionContext, Future}//.Implicits.global
//import scala.concurrent.Future
import scala.util.Random

object Future {
//  val random = new Random()
//  val aString = List("a", "ab", "abc", "abcd")
//
//  val action = () => Future {
//    val idx = random.nextInt(4)
//    aString(idx)
//}
//
//  def retry[A](action: () => Future[A], condition: A => Boolean): Future[A] = {
//    action.filter(action => condition(action)).recoverWith {
//      case e: Throwable => action
//    }
//  }
//
//  retry(action, (s: String) =>  s.length > 3).foreach(result => println(result))
def anotherFoo() = {
  val exception = throw new RuntimeException("Oops")
  if (1 > 2) exception  else println(2) // заменили new RuntimeException на переменную exception
}
}

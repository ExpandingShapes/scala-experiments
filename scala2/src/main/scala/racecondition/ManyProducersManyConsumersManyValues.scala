package racecondition

import scala.collection.mutable
import scala.util.Random

object ManyProducersManyConsumersManyValues {
  def main(args: Array[String]): Unit = {
    work(3, 8)
  }

  class Consumer(id: Int, buffer: mutable.Queue[Int]) extends Thread {
    override def run(): Unit = {
      val random = new Random()

      while(true) {
        buffer.synchronized {
          while (buffer.isEmpty) {
            println(s"потребитель $id: ожидается значение...")
            buffer.wait()
          }

          val x = buffer.dequeue()
          println(s"потребитель $id: значение получено " + x)

          buffer.notify()
        }

        Thread.sleep(random.nextInt(250))
      }
    }
  }

  class Producer(id: Int, buffer: mutable.Queue[Int], capacity: Int) extends Thread {
    override def run(): Unit = {
      val random = new Random()
      var i = 0

      while(true) {
        buffer.synchronized {
          while (buffer.size == capacity) {
            println(s"производитель $id: буфет заполнен - ожидаю...")
            buffer.wait()
          }

          println(s"производитель $id: предосталяю значение " + i)
          buffer.enqueue(i)

          buffer.notify()

          i += 1
        }

        Thread.sleep(random.nextInt(1000))
      }
    }
  }

  def work(nConsumers: Int, nProducers: Int): Unit = {
    val buffer: mutable.Queue[Int] = new mutable.Queue[Int]
    val capacity = 200

    (1 to nConsumers).foreach(i => new Consumer(i, buffer).start())
    (1 to nProducers).foreach(i => new Producer(i, buffer, capacity).start())
  }
}

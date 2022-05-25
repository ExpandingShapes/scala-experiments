package racecondition

import scala.collection.mutable
import scala.util.Random

object OneProducerOneConsumerManyValues {
  def main(args: Array[String]): Unit = {
    work()
  }

  def work(): Unit = {
    val buffer: mutable.Queue[Int] = new mutable.Queue[Int]
    val capacity = 3

    val consumer = new Thread(() => {
      val random = new Random()

      while (true) {
        buffer.synchronized {
          if (buffer.isEmpty) {
            println("потребитель: буфер пуст - ожидаю значений...")
            buffer.wait()
          }

          val x = buffer.dequeue()
          println("потребитель: значение получено " + x)

          buffer.notify()
        }

        Thread.sleep(random.nextInt(250))
      }
    })

    val producer = new Thread(() => {
      val random = new Random()
      var i = 0

      while (true) {
        buffer.synchronized {
          if (buffer.size == capacity) {
            println("производитель: буфер заполнен - ожидаю...")
            buffer.wait()
          }

          println("производитель: предоставляю значение " + i)
          buffer.enqueue(i)

          buffer.notify()

          i += 1
        }

        Thread.sleep(random.nextInt(500))
      }
    })

    consumer.start()
    producer.start()
  }
}

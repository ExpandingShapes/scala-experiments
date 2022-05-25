package racecondition

object OneProducerOneConsumerOneValue {
  def main(args: Array[String]): Unit = {
    work()
  }

  class Container {
    private var num: Int = 0

    def isEmpty: Boolean = num == 0

    // производитель
    def set(newVal: Int) = num = newVal

    // потребитель
    def get = {
      val result = num
      num = 0
      result
    }
  }

  def work(): Unit = {
    val container = new Container

    val consumer = new Thread(() => {
      println("потребитель: приступает к ожиданию...")

      container.synchronized {
        container.wait() // ждет сигнал от notify
      }

      println("потребитель: значение получено " + container.get)
    })

    val producer = new Thread(() => {
      println("производитель: производятся вычисления...")
      Thread.sleep(1000)
      val value = 12

      container.synchronized {
        println("производитель: значение рассчитано " + value)
        container.set(value)
        container.notify()
      }
    })

    consumer.start()
    producer.start()
  }
}

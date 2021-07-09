package racecondition

object RaceCondition2 {
  def main(args: Array[String]): Unit = {
    val account = new Account(10)

    for (_ <- 1 to 5) {
      new Thread(() => account.deposit(1)).start()
    }

    for (_ <- 1 to 5) {
      new Thread(() => account.withdraw(1)).start()
    }

    println(s"balance now is ${account.balance}")
  }

  /**
   * How to resolve race condition?
   * Either
   * 1. @volatile s class field or
   * 2. account.synchronized {
          account.amount -= price
        }
   * only one thread can access .synchronized
   */
  class Account(private var amount: Int) {
    def deposit(money: Int) = this.amount += money
    def withdraw(money: Int) = this.amount -= money

    def balance = amount
  }
}

package racecondition

object RaceCondition {
  class BankAccount(var amount: Int) {
    override def toString: String = s"На счету $amount"
  }

  def payWithCard(account: BankAccount, price: Int, details: String) = {

    account.amount -= price

    println(details)
    println(s"Остаток на счете ${account.amount}")
  }

  def main(args: Array[String]): Unit = {

    for (_ <- 1 to 50) {
      val account = new BankAccount(100)

      val thread1 = new Thread(() => payWithCard(account, 20, "футболка"))
      val thread2 = new Thread(() => payWithCard(account, 50, "ботинки"))

      thread1.start()
      thread2.start()

      Thread.sleep(10)

      if (account.amount != 30) println(s"Остаток на счёте: ${account.amount}")
    }
  }
}

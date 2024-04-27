object Inheritance {

  class Person(val name: String, val age: Int) extends Creature {
    def play(): Unit = {
      println(s"$name is playing!")
    }

    override val isLive: Boolean = true
  }

  abstract class Creature {
    val isLive: Boolean
  }

  class Superman(val name1: String, val age1: Int) extends Person(name1, age1) {
    override def play(): Unit = {
      println(s"Superman $name is playing and $isLive!")
    }
  }

  class Batman(val name1: String, val age1: Int) extends Person(name1, age1) {
    override def play(): Unit = {
      println(s"Batman $name is playing and $isLive!")
    }
  }
  def main(args: Array[String]): Unit = {
    val superman: Person = new Superman("superman", 5)
    val batman: Person = new Batman("batman", 5)
    val unknownCreature: Creature = new Creature {
      override val isLive: Boolean = false
    }
    superman.play()
    batman.play()
    println(s"the living status of unknown creature is ${unknownCreature.isLive}!")
  }
}

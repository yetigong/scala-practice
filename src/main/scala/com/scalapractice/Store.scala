package com.scalapractice

class Store {
  final case class Order(id: Int, description: String)

  final case class DeliveryDetails(id: Int, address: String)

  final case class Fulfillment(deliveryDate: String)

  def getOrder(userId: Int): Option[Order] =
    userId match {
      case 0 => Some(Order(0, "Assorted seashells"))
      case 1 => Some(Order(1, "Fromage"))
      case 2 => Some(Order(2, "Hard tack"))
      case _ => None
    }

  def getDeliveryDetails(orderId: Int): Option[DeliveryDetails] =
    orderId match {
      case 0 => Some(DeliveryDetails(6, "Buckingham Palace"))
      case 1 => Some(DeliveryDetails(9, "21 Jump Street"))
      case 2 => Some(DeliveryDetails(10, "10 Jump Street"))
      case _ => None
    }

  def getFulfillment(
                      order: Order,
                      delivery: DeliveryDetails
                    ): Option[Fulfillment] =
    (order.id, delivery.id) match {
      case (_, 6) => None
      case (1, _) => Some(Fulfillment("Today"))
      case (2, _) => Some(Fulfillment("Tomorrow, if you're lucky"))
      case _ => None
    }

  def processOrderForUser(userId: Int): Option[Fulfillment] = {
    getOrder(userId).flatMap(order => {
      getDeliveryDetails(order.id).flatMap(deliveryDetails => {
        getFulfillment(order, deliveryDetails)
      })
    })
  }

}

object Store {
  def main(args: Array[String]): Unit = {
    val result1 = new Store().processOrderForUser(1)
    println(result1) // expect Some(Fulfillment("Today"))

    println(new Store().processOrderForUser(0)) // None
    println(new Store().processOrderForUser(2)) // Some values for tomorrow
    println(new Store().processOrderForUser(3)) // None
    println(new Store().processOrderForUser(4)) // None
  }
}

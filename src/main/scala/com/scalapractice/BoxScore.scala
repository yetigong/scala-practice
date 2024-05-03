package com.scalapractice


final case class StatLine(
                           player: String,
                           twoPointMakes: Int,
                           twoPointAttempts: Int,
                           threePointMakes: Int,
                           threePointAttempts: Int,
                           offensiveRebounds: Int,
                           defensiveRebounds: Int
                         ) {
  def addTwoPointMake: StatLine =
    this.copy(
      twoPointMakes = twoPointMakes + 1,
      twoPointAttempts = twoPointAttempts + 1
    )

  def addTwoPointAttempt: StatLine =
    this.copy(
      twoPointAttempts = twoPointAttempts + 1
    )

  def addThreePointMake: StatLine =
    this.copy(
      threePointMakes = threePointMakes + 1,
      threePointAttempts = threePointAttempts + 1
    )

  def addThreePointAttempt: StatLine =
    this.copy(
      threePointAttempts = threePointAttempts + 1
    )

  def addOffensiveRebound: StatLine =
    this.copy(offensiveRebounds = offensiveRebounds + 1)

  def addDefensiveRebound: StatLine =
    this.copy(defensiveRebounds = defensiveRebounds + 1)
}

final case class BoxScore(home: List[StatLine], away: List[StatLine])

object Basketball {
  def boxScore(
                home: String,
                away: String,
                events: List[Event]
              ): BoxScore =
    ???

  // Run this to see an example
  @main def exampleGame: BoxScore = {
    val events =
      BasketballGenerator
        .gameEvents(
          120,
          BasketballGenerator.birmingham,
          BasketballGenerator.highbury
        )
        .run

    Basketball.boxScore(
      BasketballGenerator.birmingham.name,
      BasketballGenerator.highbury.name,
      events
    )
  }
}

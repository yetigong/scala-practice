package com.scalapractice

import com.scalapractice.Event.{DefensiveRebound, OffensiveRebound, ThreePtMake, ThreePtMiss, TwoPtMake, TwoPtMiss}


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
              ): BoxScore = {
    val teamPlayerEvents = events.groupBy(event => event.team).map((team, teamEvents) => (team, teamEvents.groupBy(event => event.player)))
    BoxScore(statLinesHelper(teamPlayerEvents, home), statLinesHelper(teamPlayerEvents, away))
  }

  def statLinesHelper(teamPlayerEvents: Map[String, Map[String, List[Event]]], team: String): List[StatLine] = {
    teamPlayerEvents.getOrElse(team, Map.empty).map((player, playerEvents) => {
      helper(playerEvents, StatLine(player, 0, 0, 0, 0, 0, 0))
    }).toList
  }

  def helper(events: List[Event], statLine: StatLine): StatLine = {
    if (events.isEmpty) {
      statLine
    } else {
      val curr = events.head
      val newStatLine = curr match {
        case TwoPtMake(_, _) => statLine.addTwoPointMake
        case TwoPtMiss(_, _) => statLine.addTwoPointAttempt
        case ThreePtMake(_, _) => statLine.addThreePointMake
        case ThreePtMiss(_, _) => statLine.addThreePointAttempt
        case OffensiveRebound(_, _) => statLine.addOffensiveRebound
        case DefensiveRebound(_, _) => statLine.addDefensiveRebound
      }
      helper(events.tail, newStatLine)
    }
  }

  // Run this to see an example
  @main def exampleGame: BoxScore = {
    val events =
      BasketballGenerator
        .gameEvents(
          10,
          BasketballGenerator.birmingham,
          BasketballGenerator.highbury
        )
        .run

    val bs = Basketball.boxScore(
      BasketballGenerator.birmingham.name,
      BasketballGenerator.highbury.name,
      events
    )
    println(s"for events ${events.mkString("\n")}, the generated boxscore is $bs")
    bs
  }
}

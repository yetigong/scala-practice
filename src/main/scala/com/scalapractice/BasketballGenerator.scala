package com.scalapractice

import doodle.random.*

enum Event {

  def player: String

  def team: String

  // True if this event is a made shot
  def make: Boolean =
    this match {
      case _: TwoPtMake => true
      case _: ThreePtMake => true
      case _ => false
    }

  case TwoPtMake(player: String, team: String)
  case TwoPtMiss(player: String, team: String)
  case ThreePtMake(player: String, team: String)
  case ThreePtMiss(player: String, team: String)
  case OffensiveRebound(player: String, team: String)
  case DefensiveRebound(player: String, team: String)
}

final case class Team(name: String, players: List[Player])

final case class Player(
                         name: String,
                         twoPointPercentage: Double,
                         threePointPercentage: Double,
                         twoPointPropensity: Double
                       )

object BasketballGenerator {
  val defensiveReboundPropensity: Double = 0.7
  val gameLength: Int = 120

  val birmingham =
    Team(
      "Birmingham Basketballers",
      List(
        Player("Jimothy Dunkin", 0.65, 0.3, 0.9),
        Player("Timmy Butler", 0.58, 0.33, 0.8),
        Player("Modula Siakam", 0.52, 0.35, 0.7),
        Player("Danny Red", 0.48, 0.38, 0.6),
        Player("Stephen Gumbo", 0.55, 0.42, 0.4)
      )
    )

  val highbury =
    Team(
      "Highbury Hoopers",
      List(
        Player("Nicolas Joker", 0.62, 0.35, 0.9),
        Player("Mini Kleber", 0.58, 0.3, 0.85),
        Player("Osaka Bryant", 0.5, 0.37, 0.65),
        Player("Oskar Rupertson", 0.52, 0.4, 0.6),
        Player("Paul Chris", 0.5, 0.37, 0.6)
      )
    )

  def shotEvent(team: String, player: Player): Random[Event] =
    for {
      twoPt <- Random.double.map(_ <= player.twoPointPropensity)
      make <- Random.double.map(r =>
        if twoPt then r <= player.twoPointPercentage
        else r <= player.threePointPercentage
      )
    } yield {
      if twoPt then
        if make then Event.TwoPtMake(player.name, team)
        else Event.TwoPtMiss(player.name, team)
      else if make then Event.ThreePtMake(player.name, team)
      else Event.ThreePtMiss(player.name, team)
    }

  def reboundEvent(offense: Team, defence: Team): Random[Event] =
    for {
      defensive <- Random.double.map(_ <= defensiveReboundPropensity)
      player <-
        if defensive then Random.oneOf(defence.players: _*)
        else Random.oneOf(offense.players: _*)
    } yield {
      if defensive then Event.DefensiveRebound(player.name, defence.name)
      else Event.OffensiveRebound(player.name, offense.name)
    }

  def possessionEvents(offense: Team, defence: Team): Random[List[Event]] =
    for {
      shooter <- Random.oneOf(offense.players: _*)
      shot <- shotEvent(offense.name, shooter)
      events <-
        if shot.make then Random.always(List(shot))
        else {
          reboundEvent(offense, defence).flatMap(evt =>
            evt match {
              case rebound: Event.OffensiveRebound =>
                possessionEvents(offense, defence).map(evts =>
                  shot :: rebound :: evts
                )
              case other => Random.always(List(shot, other))
            }
          )
        }
    } yield events

  def gameEvents(
                  count: Int,
                  offense: Team,
                  defence: Team
                ): Random[List[Event]] =
    if count == 0 then Random.always(List.empty)
    else
      for {
        evts <- possessionEvents(offense, defence)
        rest <- gameEvents(count - 1, defence, offense)
      } yield evts ++ rest
}


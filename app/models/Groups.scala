package models

import java.util.Date

import models.db.Groups
import play.api.libs.json.Json
import scalikejdbc._

/**
  * Created by Fumiyasu on 2016/06/17.
  */
case class User(name: String)

case class Event(
  id: Long,
  eventName: String,
  date: Date,
  location: Location,
  groups: List[Groups]
)

case class Location(id: Long, prefecture: String, city: String, name: String)

//case class Groups(
//                  group_id: Long,
//                  group_name: String
//                ) {
//}
//
//object Groups extends SQLSyntaxSupport[Groups] {
//
//  override val tableName = "groups"
//  override val columns = Seq("group_id", "group_name")
//
//  def apply(x: SyntaxProvider[Groups])(rs: WrappedResultSet): Groups= apply(x.resultName)(rs)
//  def apply(x: ResultName[Groups])(rs: WrappedResultSet): Groups = new Groups(
//    group_id = rs.get(x.group_id),
//    group_name = rs.get(x.group_name)
//  )
//
//  def create(name: String)(implicit session: DBSession = autoSession) = {
//    val id = withSQL {
//      insert.into(Groups).namedValues(
//        column.group_id -> 0L, // TODO
//        column.group_name -> name
//      )
//    }.update().apply()
//
//    Groups(id, name)
//  }
//
//}

case class CreateGroup(
  name: String
)
object CreateGroup{
  implicit def jsonFormat = Json.format[CreateGroup]
}

object Groups {
  implicit def jsonFormat = Json.format[Groups]
}

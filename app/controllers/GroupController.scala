package controllers

import javax.inject.Inject

import models.CreateGroup
import models.db.Groups
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import utils.Id64

import scala.concurrent.Future

/**
  * Created by Fumiyasu on 2016/06/17.
  */
class GroupController @Inject()() extends Controller {
  def create = Action.async(parse.json) { request =>
    val groupJson = request.body.as[CreateGroup]
    val id = Id64.nextAscId()
    val group = Groups.create(id, groupJson.name)
    val all = Groups.findAll()
    Future.successful(Ok)
  }

  def showAll = Action.async {
    import models.Groups._
    val groups = Groups.findAll()
    Future.successful(Ok(Json.toJson(groups)))
  }
}

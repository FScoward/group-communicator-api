package models.db

import scalikejdbc._

case class Groups(
  groupId: Long,
  groupName: String) {

  def save()(implicit session: DBSession = Groups.autoSession): Groups = Groups.save(this)(session)

  def destroy()(implicit session: DBSession = Groups.autoSession): Unit = Groups.destroy(this)(session)

}


object Groups extends SQLSyntaxSupport[Groups] {

  override val schemaName = Some("group_communicator")

  override val tableName = "groups"

  override val columns = Seq("GROUP_ID", "GROUP_NAME")

  def apply(g: SyntaxProvider[Groups])(rs: WrappedResultSet): Groups = apply(g.resultName)(rs)
  def apply(g: ResultName[Groups])(rs: WrappedResultSet): Groups = new Groups(
    groupId = rs.get(g.groupId),
    groupName = rs.get(g.groupName)
  )

  val g = Groups.syntax("g")

  override val autoSession = AutoSession

  def find(groupId: Long)(implicit session: DBSession = autoSession): Option[Groups] = {
    withSQL {
      select.from(Groups as g).where.eq(g.groupId, groupId)
    }.map(Groups(g.resultName)).single.apply()
  }

  def findAll()(implicit session: DBSession = autoSession): List[Groups] = {
    withSQL(select.from(Groups as g)).map(Groups(g.resultName)).list.apply()
  }

  def countAll()(implicit session: DBSession = autoSession): Long = {
    withSQL(select(sqls.count).from(Groups as g)).map(rs => rs.long(1)).single.apply().get
  }

  def findBy(where: SQLSyntax)(implicit session: DBSession = autoSession): Option[Groups] = {
    withSQL {
      select.from(Groups as g).where.append(where)
    }.map(Groups(g.resultName)).single.apply()
  }

  def findAllBy(where: SQLSyntax)(implicit session: DBSession = autoSession): List[Groups] = {
    withSQL {
      select.from(Groups as g).where.append(where)
    }.map(Groups(g.resultName)).list.apply()
  }

  def countBy(where: SQLSyntax)(implicit session: DBSession = autoSession): Long = {
    withSQL {
      select(sqls.count).from(Groups as g).where.append(where)
    }.map(_.long(1)).single.apply().get
  }

  def create(
    groupId: Long,
    groupName: String)(implicit session: DBSession = autoSession): Groups = {
    withSQL {
      insert.into(Groups).columns(
        column.groupId,
        column.groupName
      ).values(
        groupId,
        groupName
      )
    }.update.apply()

    Groups(
      groupId = groupId,
      groupName = groupName)
  }

  def batchInsert(entities: Seq[Groups])(implicit session: DBSession = autoSession): Seq[Int] = {
    val params: Seq[Seq[(Symbol, Any)]] = entities.map(entity => 
      Seq(
        'groupId -> entity.groupId,
        'groupName -> entity.groupName))
        SQL("""insert into groups(
        GROUP_ID,
        GROUP_NAME
      ) values (
        {groupId},
        {groupName}
      )""").batchByName(params: _*).apply()
    }

  def save(entity: Groups)(implicit session: DBSession = autoSession): Groups = {
    withSQL {
      update(Groups).set(
        column.groupId -> entity.groupId,
        column.groupName -> entity.groupName
      ).where.eq(column.groupId, entity.groupId)
    }.update.apply()
    entity
  }

  def destroy(entity: Groups)(implicit session: DBSession = autoSession): Unit = {
    withSQL { delete.from(Groups).where.eq(column.groupId, entity.groupId) }.update.apply()
  }

}

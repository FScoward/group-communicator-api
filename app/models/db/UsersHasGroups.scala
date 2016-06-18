package models.db

import scalikejdbc._

case class UsersHasGroups(
  usersUserId: Long,
  groupsGroupId: Long) {

  def save()(implicit session: DBSession = UsersHasGroups.autoSession): UsersHasGroups = UsersHasGroups.save(this)(session)

  def destroy()(implicit session: DBSession = UsersHasGroups.autoSession): Unit = UsersHasGroups.destroy(this)(session)

}


object UsersHasGroups extends SQLSyntaxSupport[UsersHasGroups] {

  override val schemaName = Some("group_communicator")

  override val tableName = "users_has_groups"

  override val columns = Seq("USERS_USER_ID", "GROUPS_GROUP_ID")

  def apply(uhg: SyntaxProvider[UsersHasGroups])(rs: WrappedResultSet): UsersHasGroups = apply(uhg.resultName)(rs)
  def apply(uhg: ResultName[UsersHasGroups])(rs: WrappedResultSet): UsersHasGroups = new UsersHasGroups(
    usersUserId = rs.get(uhg.usersUserId),
    groupsGroupId = rs.get(uhg.groupsGroupId)
  )

  val uhg = UsersHasGroups.syntax("uhg")

  override val autoSession = AutoSession

  def find(groupsGroupId: Long, usersUserId: Long)(implicit session: DBSession = autoSession): Option[UsersHasGroups] = {
    withSQL {
      select.from(UsersHasGroups as uhg).where.eq(uhg.groupsGroupId, groupsGroupId).and.eq(uhg.usersUserId, usersUserId)
    }.map(UsersHasGroups(uhg.resultName)).single.apply()
  }

  def findAll()(implicit session: DBSession = autoSession): List[UsersHasGroups] = {
    withSQL(select.from(UsersHasGroups as uhg)).map(UsersHasGroups(uhg.resultName)).list.apply()
  }

  def countAll()(implicit session: DBSession = autoSession): Long = {
    withSQL(select(sqls.count).from(UsersHasGroups as uhg)).map(rs => rs.long(1)).single.apply().get
  }

  def findBy(where: SQLSyntax)(implicit session: DBSession = autoSession): Option[UsersHasGroups] = {
    withSQL {
      select.from(UsersHasGroups as uhg).where.append(where)
    }.map(UsersHasGroups(uhg.resultName)).single.apply()
  }

  def findAllBy(where: SQLSyntax)(implicit session: DBSession = autoSession): List[UsersHasGroups] = {
    withSQL {
      select.from(UsersHasGroups as uhg).where.append(where)
    }.map(UsersHasGroups(uhg.resultName)).list.apply()
  }

  def countBy(where: SQLSyntax)(implicit session: DBSession = autoSession): Long = {
    withSQL {
      select(sqls.count).from(UsersHasGroups as uhg).where.append(where)
    }.map(_.long(1)).single.apply().get
  }

  def create(
    usersUserId: Long,
    groupsGroupId: Long)(implicit session: DBSession = autoSession): UsersHasGroups = {
    withSQL {
      insert.into(UsersHasGroups).columns(
        column.usersUserId,
        column.groupsGroupId
      ).values(
        usersUserId,
        groupsGroupId
      )
    }.update.apply()

    UsersHasGroups(
      usersUserId = usersUserId,
      groupsGroupId = groupsGroupId)
  }

  def batchInsert(entities: Seq[UsersHasGroups])(implicit session: DBSession = autoSession): Seq[Int] = {
    val params: Seq[Seq[(Symbol, Any)]] = entities.map(entity => 
      Seq(
        'usersUserId -> entity.usersUserId,
        'groupsGroupId -> entity.groupsGroupId))
        SQL("""insert into users_has_groups(
        USERS_USER_ID,
        GROUPS_GROUP_ID
      ) values (
        {usersUserId},
        {groupsGroupId}
      )""").batchByName(params: _*).apply()
    }

  def save(entity: UsersHasGroups)(implicit session: DBSession = autoSession): UsersHasGroups = {
    withSQL {
      update(UsersHasGroups).set(
        column.usersUserId -> entity.usersUserId,
        column.groupsGroupId -> entity.groupsGroupId
      ).where.eq(column.groupsGroupId, entity.groupsGroupId).and.eq(column.usersUserId, entity.usersUserId)
    }.update.apply()
    entity
  }

  def destroy(entity: UsersHasGroups)(implicit session: DBSession = autoSession): Unit = {
    withSQL { delete.from(UsersHasGroups).where.eq(column.groupsGroupId, entity.groupsGroupId).and.eq(column.usersUserId, entity.usersUserId) }.update.apply()
  }

}

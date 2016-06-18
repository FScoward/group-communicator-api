package models.db

import scalikejdbc.specs2.mutable.AutoRollback
import org.specs2.mutable._
import scalikejdbc._


class UsersHasGroupsSpec extends Specification {

  "UsersHasGroups" should {

    val uhg = UsersHasGroups.syntax("uhg")

    "find by primary keys" in new AutoRollback {
      val maybeFound = UsersHasGroups.find(1L, 1L)
      maybeFound.isDefined should beTrue
    }
    "find by where clauses" in new AutoRollback {
      val maybeFound = UsersHasGroups.findBy(sqls.eq(uhg.groupsGroupId, 1L))
      maybeFound.isDefined should beTrue
    }
    "find all records" in new AutoRollback {
      val allResults = UsersHasGroups.findAll()
      allResults.size should be_>(0)
    }
    "count all records" in new AutoRollback {
      val count = UsersHasGroups.countAll()
      count should be_>(0L)
    }
    "find all by where clauses" in new AutoRollback {
      val results = UsersHasGroups.findAllBy(sqls.eq(uhg.groupsGroupId, 1L))
      results.size should be_>(0)
    }
    "count by where clauses" in new AutoRollback {
      val count = UsersHasGroups.countBy(sqls.eq(uhg.groupsGroupId, 1L))
      count should be_>(0L)
    }
    "create new record" in new AutoRollback {
      val created = UsersHasGroups.create(usersUserId = 1L, groupsGroupId = 1L)
      created should not beNull
    }
    "save a record" in new AutoRollback {
      val entity = UsersHasGroups.findAll().head
      // TODO modify something
      val modified = entity
      val updated = UsersHasGroups.save(modified)
      updated should not equalTo(entity)
    }
    "destroy a record" in new AutoRollback {
      val entity = UsersHasGroups.findAll().head
      UsersHasGroups.destroy(entity)
      val shouldBeNone = UsersHasGroups.find(1L, 1L)
      shouldBeNone.isDefined should beFalse
    }
    "perform batch insert" in new AutoRollback {
      val entities = UsersHasGroups.findAll()
      entities.foreach(e => UsersHasGroups.destroy(e))
      val batchInserted = UsersHasGroups.batchInsert(entities)
      batchInserted.size should be_>(0)
    }
  }

}

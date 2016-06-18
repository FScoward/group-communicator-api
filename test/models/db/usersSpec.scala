package models.db

import scalikejdbc.specs2.mutable.AutoRollback
import org.specs2.mutable._
import scalikejdbc._


class usersSpec extends Specification {

  "users" should {

    val  = users.syntax("")

    "find by primary keys" in new AutoRollback {
      val maybeFound = users.find(1L, 1L)
      maybeFound.isDefined should beTrue
    }
    "find by where clauses" in new AutoRollback {
      val maybeFound = users.findBy(sqls.eq(.groupsGroupId, 1L))
      maybeFound.isDefined should beTrue
    }
    "find all records" in new AutoRollback {
      val allResults = users.findAll()
      allResults.size should be_>(0)
    }
    "count all records" in new AutoRollback {
      val count = users.countAll()
      count should be_>(0L)
    }
    "find all by where clauses" in new AutoRollback {
      val results = users.findAllBy(sqls.eq(.groupsGroupId, 1L))
      results.size should be_>(0)
    }
    "count by where clauses" in new AutoRollback {
      val count = users.countBy(sqls.eq(.groupsGroupId, 1L))
      count should be_>(0L)
    }
    "create new record" in new AutoRollback {
      val created = users.create(usersUserId = 1L, groupsGroupId = 1L)
      created should not beNull
    }
    "save a record" in new AutoRollback {
      val entity = users.findAll().head
      // TODO modify something
      val modified = entity
      val updated = users.save(modified)
      updated should not equalTo(entity)
    }
    "destroy a record" in new AutoRollback {
      val entity = users.findAll().head
      users.destroy(entity)
      val shouldBeNone = users.find(1L, 1L)
      shouldBeNone.isDefined should beFalse
    }
    "perform batch insert" in new AutoRollback {
      val entities = users.findAll()
      entities.foreach(e => users.destroy(e))
      val batchInserted = users.batchInsert(entities)
      batchInserted.size should be_>(0)
    }
  }

}

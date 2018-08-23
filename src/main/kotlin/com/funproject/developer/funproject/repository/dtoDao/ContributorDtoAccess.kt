package com.funproject.developer.funproject.repository.dtoDao

import com.funproject.developer.funproject.dto.replyDto.ContributorReplyDto
import com.funproject.developer.funproject.dto.transformer.ContributorReplyTransformer
import com.funproject.developer.funproject.dto.userDto.ContributorDto
import com.funproject.developer.funproject.model.Location
import com.funproject.developer.funproject.model.Mood
import com.funproject.developer.funproject.model.StatusReply
import com.funproject.developer.funproject.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.persistence.EntityManager
import org.hibernate.transform.Transformers
import java.time.LocalDateTime
import javax.persistence.criteria.*
import java.util.ArrayList




@Component
class ContributorDtoAccess @Autowired constructor(
        private val contributorReplyTransformer: ContributorReplyTransformer,
        private val entityManager: EntityManager
) {
    fun findAllNotPersonal(userId: Long): ArrayList<ContributorReplyDto> {
        return entityManager.createQuery(
                "select " +
                        "sr.id as id, " +
                        "sr.comment as comment, " +
                        "sr.publish_date as publish_date, " +
                        "sr.location.name as location_name, " +
//                        "sr.user.id, " +
                        "sr.user.firstname, " +
                        "sr.user.lastname, " +
                        "sr.mood.text, " +
                        "sr.mood.icon " +
                        "from StatusReply sr " +
                        "where user_id != :id")
                .setParameter("id", userId)
                .unwrap( org.hibernate.query.Query::class.java )
                .setResultTransformer(contributorReplyTransformer)
                .resultList as ArrayList<ContributorReplyDto>
    }

    fun findLastContributorReply(): ArrayList<ContributorReplyDto> {
        return entityManager.createQuery(
                "select " +
                        "sr.id as id, " +
                        "sr.comment as comment, " +
                        "sr.publish_date as publish_date, " +
                        "sr.location.name as location_name, " +
//                        "sr.user.id, " +
                        "sr.user.firstname, " +
                        "sr.user.lastname, " +
                        "sr.mood.text as mood_text, " +
                        "sr.mood.icon as mood_icon " +
                        "from StatusReply sr " +
                        "where sr.publish_date = (" +
                                "select max(publish_date) " +
                                "from StatusReply sb " +
                                "where sr.user.id = sb.user.id)")
                .unwrap( org.hibernate.query.Query::class.java )
                .setResultTransformer(contributorReplyTransformer)
                .resultList as ArrayList<ContributorReplyDto>
    }

//    fun findContributorWithoutReply(): ArrayList<ContributorDto> {
//        return entityManager.createQuery(
//                "select " +
//                        "id, " +
//                        "firstname, " +
//                        "lastname " +
//                        "from User " +
//                        "where id not in (SELECT sr.user.id FROM StatusReply sr)")
//                .unwrap( org.hibernate.query.Query::class.java )
//                .setResultTransformer(Transformers.aliasToBean(ContributorDto::class.java))
//                .resultList as ArrayList<ContributorDto>
//    }

    fun findAllNotPersonalCriteria(userId: Long): ArrayList<ContributorReplyDto> {
        val cb = entityManager.criteriaBuilder
        val criteriaQuery = cb.createQuery(ContributorReplyDto::class.java)
        val from = criteriaQuery.from(StatusReply::class.java)

        val mood: Join<StatusReply, Mood> = from.join("mood", JoinType.LEFT)
        val user: Join<StatusReply, User> = from.join("user", JoinType.LEFT)
        val location: Join<StatusReply, Location> = from.join("location", JoinType.LEFT)

        val predicate: Predicate = cb.notEqual(user.get<Long>("id"), userId)

        criteriaQuery.multiselect(
                from.get<Long>("id"),
                from.get<String>("comment"),
                from.get<LocalDateTime>("publish_date"),
                location.get<String>("name"),
                mood.get<String>("text"),
                mood.get<String>("icon"),
                user.get<String>("firstname"),
                user.get<String>("lastname")
        )

//                cb.construct(
//                        ReplyDto::class.java,
//                        from.get<Long>("id"),
//                        from.get<String>("comment"),
//                        location.get<String>("name"),
//                        cb.construct(
//                                MoodDto::class.java,
//                                mood.get<String>("text"),
//                                mood.get<String>("icon")
//                        )
//                ),
//                cb.construct(
//                        ContributorDto::class.java,
//                        user.get<Long>("id"),
//                        user.get<String>("firstname"),
//                        user.get<String>("lastname")
//                )
        criteriaQuery.where(cb.and(predicate))

        return entityManager.createQuery(criteriaQuery).resultList as ArrayList<ContributorReplyDto>
    }

    fun findContributorWithoutReply(): ArrayList<ContributorDto> {
        val cb = entityManager.criteriaBuilder
        val criteriaQuery = cb.createQuery(ContributorDto::class.java)
        val fromUser = criteriaQuery.from(User::class.java)

        val criteriaUserRepliesQuery = cb.createQuery()
        val reply = criteriaUserRepliesQuery.from(StatusReply::class.java)
        val user: Join<StatusReply, User> = reply.join("user", JoinType.LEFT)
        criteriaUserRepliesQuery.multiselect(
            user.get<Long>("id")
        )
        val predicate: Predicate = cb.not(fromUser.get<Long>("id").`in`(entityManager.createQuery(criteriaUserRepliesQuery).resultList))
        val predicateNotDeleted: Predicate = cb.notEqual(fromUser.get<Boolean>("is_deleted"), true)

        criteriaQuery.multiselect(
                fromUser.get<Long>("id"),
                fromUser.get<String>("firstname"),
                fromUser.get<String>("lastname")
        )
        criteriaQuery.where(cb.and(predicate, predicateNotDeleted))
        return entityManager.createQuery(criteriaQuery).resultList as ArrayList<ContributorDto>
    }
}
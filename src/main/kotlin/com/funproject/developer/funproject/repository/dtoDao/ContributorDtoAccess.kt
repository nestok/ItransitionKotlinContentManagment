package com.funproject.developer.funproject.repository.dtoDao

import com.funproject.developer.funproject.dto.replyDto.ContributorReplyDto
import com.funproject.developer.funproject.dto.transformer.ContributorReplyTransformer
import com.funproject.developer.funproject.dto.userDto.ContributorDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.persistence.EntityManager
import org.hibernate.transform.Transformers



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
                        "sr.user.id, " +
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
                        "sr.user.id, " +
                        "sr.user.firstname, " +
                        "sr.user.lastname, " +
                        "sr.mood.text, " +
                        "sr.mood.icon " +
                        "from StatusReply sr " +
                        "where sr.publish_date = (" +
                                "select max(publish_date) " +
                                "from StatusReply sb " +
                                "where sr.user.id = sb.user.id)")
                .unwrap( org.hibernate.query.Query::class.java )
                .setResultTransformer(contributorReplyTransformer)
                .resultList as ArrayList<ContributorReplyDto>
    }

    fun findContributorWithoutReply(): ArrayList<ContributorDto> {
        return entityManager.createQuery(
                "select " +
                        "id, " +
                        "firstname, " +
                        "lastname " +
                        "from User " +
                        "where id not in (SELECT sr.user.id FROM StatusReply sr)")
                .unwrap( org.hibernate.query.Query::class.java )
                .setResultTransformer(Transformers.aliasToBean(ContributorDto::class.java))
                .resultList as ArrayList<ContributorDto>
    }
}
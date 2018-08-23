package com.funproject.developer.funproject.dto.transformer

import com.funproject.developer.funproject.dto.moodDto.MoodDto
import com.funproject.developer.funproject.dto.replyDto.ContributorReplyDto
import com.funproject.developer.funproject.dto.replyDto.ReplyDto
import com.funproject.developer.funproject.dto.userDto.ContributorDto
import com.funproject.developer.funproject.model.StatusReply
import com.funproject.developer.funproject.repository.LocationRepository
import com.funproject.developer.funproject.repository.MoodRepository
import com.funproject.developer.funproject.repository.UserRepository
import org.hibernate.transform.AliasedTupleSubsetResultTransformer
import org.hibernate.transform.ResultTransformer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class ContributorReplyTransformer : AliasedTupleSubsetResultTransformer() {
    override fun isTransformedValueATupleElement(p0: Array<out String>?, p1: Int): Boolean {
        return true;
    }

    fun makeDto(replies: ArrayList<StatusReply>): ArrayList<ContributorReplyDto> {
        val contributorReplyDtoList = ArrayList<ContributorReplyDto>()
        for (reply in replies){
            contributorReplyDtoList.add(ContributorReplyDto(
                    id = reply.id,
                    comment = reply.comment,
                    publish_date = reply.publish_date,
                    location_name = reply.location.name,
                    mood_text = reply.mood.text,
                    mood_icon = reply.mood.icon,
                    contributor_firstname = reply.user.firstname,
                    contributor_lastname = reply.user.lastname)
            )
        }
        return contributorReplyDtoList
    }

    override fun transformTuple(tuple: Array<Any>, aliases: Array<String>?): Any {
        return ContributorReplyDto(
                id = tuple[0] as Long,
                comment = tuple[1] as String,
                publish_date = tuple[2] as LocalDateTime,
                location_name = tuple[3] as String,
                contributor_firstname = tuple[4] as String,
                contributor_lastname = tuple[5] as String,
                mood_text = tuple[6] as String,
                mood_icon = tuple[7] as String
        )
    }
}

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
                    reply = ReplyDto(
                            id = reply.id,
                            comment = reply.comment,
                            publish_date = reply.publish_date,
                            location_name = reply.location.name,
                            mood = MoodDto(
                            text = reply.mood.text,
                            icon = reply.mood.icon)
                    ),
                    contributor = ContributorDto(
                            id = reply.user.id,
                            firstname = reply.user.firstname,
                            lastname = reply.user.lastname)
                    ))
        }
        return contributorReplyDtoList
    }

    override fun transformTuple(tuple: Array<Any>, aliases: Array<String>?): Any {
        return ContributorReplyDto(
                reply = ReplyDto(
                        id = tuple[0] as Long,
                        comment = tuple[1] as String,
                        publish_date = tuple[2] as LocalDateTime,
                        location_name = tuple[3] as String,
                        mood = MoodDto(
                                text = tuple[7] as String,
                                icon = tuple[8] as String)
                ),
                contributor = ContributorDto(
                        id = tuple[4] as Long,
                        firstname = tuple[5] as String,
                        lastname = tuple[6] as String)
        )
    }
}

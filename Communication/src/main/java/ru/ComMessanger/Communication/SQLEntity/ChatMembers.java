package ru.ComMessanger.Communication.SQLEntity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "chat_members")
public class ChatMembers {

    @Id
    @ManyToOne
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "chat_id")
    private Chats chats;

    @Id
    @ManyToOne
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "joined_at")
    private LocalDateTime joinedAt;

}

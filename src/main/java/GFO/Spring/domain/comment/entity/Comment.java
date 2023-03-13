package GFO.Spring.domain.comment.entity;

import GFO.Spring.domain.post.entity.Post;
import GFO.Spring.domain.user.entity.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "comment_id")
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    public Comment update(Long id, String content) {
        Comment comment = Comment.builder()
                .content(content)
                .post(this.post)
                .user(this.user)
                .build();
        comment.id = id;
        return comment;
    }
}

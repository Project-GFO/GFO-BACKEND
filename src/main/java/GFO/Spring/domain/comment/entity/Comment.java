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
    private String comment;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}

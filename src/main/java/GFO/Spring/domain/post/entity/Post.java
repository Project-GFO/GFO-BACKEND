package GFO.Spring.domain.post.entity;

import GFO.Spring.domain.user.entity.User;
import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Builder
@Table(name = "post")
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private String postId;

    @Column(nullable = false, name = "title")
    private String title;

    @Column(nullable = false, name = "content")
    private String content;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "post")
    private List<Attachment> attachment;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "member")
    private User user;
}

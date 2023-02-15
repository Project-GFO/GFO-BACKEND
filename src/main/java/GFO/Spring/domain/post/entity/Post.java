package GFO.Spring.domain.post.entity;

import GFO.Spring.domain.image.entity.Attachment;
import GFO.Spring.domain.post.presentation.dto.request.ModifyPostRequest;
import GFO.Spring.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "email")
    private User user;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "post")
    private List<Attachment> attachments;

    public void modifyPost(ModifyPostRequest modifyPostRequest) {
        this.title = modifyPostRequest.getTitle();
        this.content = modifyPostRequest.getContent();
    }
}

package GFO.Spring.domain.post.entity;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "attachment")
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content_file_url")
    private String contentFileURL;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "post")
    private Post post;
}

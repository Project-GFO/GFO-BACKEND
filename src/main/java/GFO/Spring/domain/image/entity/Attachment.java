package GFO.Spring.domain.image.entity;

import GFO.Spring.domain.post.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "origin_file_name")
    private String originFileName;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "file_size")
    private Long fileSize;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public Attachment(String originFileName, String filePath, Long fileSize) {
        this.originFileName = originFileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }
}

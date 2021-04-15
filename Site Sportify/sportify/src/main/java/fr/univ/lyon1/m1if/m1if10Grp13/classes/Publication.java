package fr.univ.lyon1.m1if.m1if10Grp13.classes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "publication")
public class Publication {
    @Id
    @Column(name = "publicationid")
    private Long publicationId;

//    public Publication(String contenu, String publisher) {
//        this.contenu = contenu;
//        this.publisher = publisher;
//    }

//    public String getContenu() {
//        return contenu;
//    }
//    public void setContenu(String contenu) {
//        this.contenu = contenu;
//    }
//    public String getPublisher() {
//        return publisher;
//    }
//    public void setPublisher(String publisher) {
//        this.publisher = publisher;
//    }
//

    public void setPublicationId(Long publicationId) {
        this.publicationId = publicationId;
    }

    public Long getPublicationId() {
        return publicationId;
    }
}

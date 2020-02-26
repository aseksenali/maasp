package org.kbtu.maaspservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "point_info")
public class PointInfo {
    @Id
    @GeneratedValue
    private UUID id;
    @CreatedDate
    @Column(name = "created_at")
    private Date createdAt;
    @OneToOne
    private Point point;
    @OneToOne
    private Well well;
    private double maasp;
    public PointInfo(Point point, Well well, double maasp) {
        this.point = point;
        this.well = well;
        this.maasp = maasp;
    }
}

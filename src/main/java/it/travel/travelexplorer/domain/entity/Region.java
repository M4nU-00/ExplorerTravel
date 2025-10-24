package it.travel.travelexplorer.domain.entity;

import it.travel.travelexplorer.domain.entity.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "region", schema = "explorer")
public class Region extends BaseEntity {

    @Column(name = "name")
    private String name;
}

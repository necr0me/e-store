package by.necr0me.estore.entity;

import by.necr0me.estore.entity.enums.item_detail.ComparativeRule;
import by.necr0me.estore.entity.enums.item_detail.UnitType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "item_details")
public class ItemDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    private String unit;

    @Column(name = "unit_type")
    @Enumerated(EnumType.STRING)
    private UnitType unitType;

    @Column(name = "comparative_rule")
    @Enumerated(EnumType.STRING)
    private ComparativeRule comparativeRule;

    @OneToMany(mappedBy = "itemDetail", cascade = CascadeType.REMOVE)
    @JsonIgnore // TODO: remove after service fix
    private List<SpecificItemDetail>  specificItemDetails;
}

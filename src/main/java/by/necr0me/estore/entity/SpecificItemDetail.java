package by.necr0me.estore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "specific_item_details")
public class SpecificItemDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String value;

    @ManyToOne
    @JoinColumn(name = "item_detail_id", nullable = false)
    private ItemDetail itemDetail;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    @JsonIgnore // TODO: remove after service fix
    private Item item;
}

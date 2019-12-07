package kr.co.papercraft.eatgo.domain.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Restaurant> restaurant;

    public void setRestaurants(List<Restaurant> resources){
        this.restaurant = new ArrayList<>(resources);
    }
}

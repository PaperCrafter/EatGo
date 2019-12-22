package kr.co.papercraft.eatgo.domain.Model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String email;

    @NotEmpty
    private String name;

    @NotNull
    private Long level;

    private Long restaurantId;

    private String password;
    //1 일반유저 2 owner 3 master
    public boolean isAdmin(){
        return level==3;
    }

    public void deactivate(){
        this.level = 0L;
    }

    public void setRestaurantId(Long restaurantId){
        this.level = 2L;
        this.restaurantId = restaurantId;
    }

    public boolean isRestaurantOwner() {
        return level==2;
    }
}

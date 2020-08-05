package doremi;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="View_table")
public class View {

        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
        private Long id;
        private Long menuId;
        private Integer score;
        private String menuName;
        private Float menuPrice;
        private String description;


        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
        public Long getMenuId() {
            return menuId;
        }

        public void setMenuId(Long menuId) {
            this.menuId = menuId;
        }
        public Integer getScore() {
            return score;
        }

        public void setScore(Integer score) {
            this.score = score;
        }
        public String getMenuName() {
            return menuName;
        }

        public void setMenuName(String menuName) {
            this.menuName = menuName;
        }
        public Float getMenuPrice() {
            return menuPrice;
        }

        public void setMenuPrice(Float menuPrice) {
            this.menuPrice = menuPrice;
        }
        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

}

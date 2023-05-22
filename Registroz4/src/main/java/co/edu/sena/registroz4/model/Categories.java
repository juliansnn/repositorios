package co.edu.sena.registroz4.model;

public class Categories {
    public Integer category_id;
    public String category_name;

    public Categories (){
    }
    public Categories(Integer category_id, String category_name){
        this.category_id = category_id;
        this.category_name = category_name;
    }
    public Integer getCategory_id() {
        return category_id;
    }
    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }
    public String getCategory_name() {
        return category_name;
    }
    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    @Override
    public String toString() {
        return "Categories{" +
                "category_id=" + category_id +
                ", category_name='" + category_name + '\'' +
                '}';
    }
}

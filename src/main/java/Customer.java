import java.io.Serializable;

public class Customer implements Serializable{

    private String name;
    private int id;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void set(int id, String name)
    {
        this.id = id;
        this.name = name;
    }
}

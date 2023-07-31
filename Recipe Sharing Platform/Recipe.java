import java.util.*;
public class Recipe {
    private String name;
    private String description;
    private User owner;
    private int likes;
    private List<String> comments;

  public Recipe(User owner, String name, String description){
    this.name=name;
    this.description=description;
    this.owner=owner;
    this.likes=0;
    this.comments=new ArrayList<>();
  }
public String getRecipeName(){
     return name;
}
public User getOwner(){
    return owner;
}
public String getDescription(){
    return description;
} 
public void addLike(){
    likes++;
}
public void addComment(String comment){
   comments.add(comment);
}
}

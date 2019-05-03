import java.util.ArrayList;


public class UserProfile {
    private ArrayList<UserProfile> friends = new ArrayList<>();
    private String userName;
    private ChatObserverClient toUser;


    public UserProfile(String name, ChatObserverClient user){
        userName = name;
        toUser = user;
    }

    public String getUserName(){
        return userName;
    }

    public ArrayList getFriends(){
        return friends;
    }

    public ChatObserverClient getConnection(){
        return toUser;
    }

    public void addFriend(UserProfile user){
        friends.add(user);
    }
}
